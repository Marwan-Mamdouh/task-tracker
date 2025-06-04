package com.marwan.dev.task_tracker.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.marwan.dev.task_tracker.model.Task;
import com.marwan.dev.task_tracker.model.TaskStatus;
import jakarta.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

@Repository
public class TaskRepository implements ITaskRepository {

  public static int maxId = 0;
  private final ObjectMapper objectMapper;
  private final String filePath;
  private final ReadWriteLock lock = new ReentrantReadWriteLock();

  public TaskRepository(ObjectMapper objectMapper,
      @Value("${app.data.file.path:tasks.json}") String filePath) {
    this.objectMapper = objectMapper;
    this.filePath = filePath;
  }

  @PostConstruct
  public void initializeMaxId() {
    final LinkedList<Task> tasks = readTasksFromFile();
    maxId = tasks.stream().mapToInt(Task::getTaskId).max().orElse(0);
  }

  @Override
  public Task save(Task task) {
    lock.writeLock().lock();
    try {
      final LinkedList<Task> tasks = readTasksFromFile();

      // Check if task exists (update scenario)
      final Optional<Task> existingTask = tasks.stream()
          .filter(t -> t.getTaskId() == task.getTaskId()).findFirst();

      if (existingTask.isPresent()) {
        // Update existing task
        tasks.removeIf(t -> t.getTaskId() == task.getTaskId());
        task.setUpdatedAt(LocalDate.now());
        tasks.add(task);
      } else {
        // New task
        if (task.getTaskId() == 0) {
          task.setTaskId(++maxId);
        }
        tasks.add(task);
      }

      writeTasksToFile(tasks);
      return task;
    } finally {
      lock.writeLock().unlock();
    }
  }

  @Override
  public Optional<Task> findById(int id) {
    lock.readLock().lock();
    try {
      final LinkedList<Task> tasks = readTasksFromFile();
      return tasks.stream().filter(task -> task.getTaskId() == id).findFirst();
    } finally {
      lock.readLock().unlock();
    }
  }

  @Override
  public LinkedList<Task> findAll() {
    lock.readLock().lock();
    try {
      return new LinkedList<>(readTasksFromFile());
    } finally {
      lock.readLock().unlock();
    }
  }

  @Override
  public List<Task> findByStatus(TaskStatus status) {
    lock.readLock().lock();
    try {
      final LinkedList<Task> tasks = readTasksFromFile();
      return tasks.stream().filter(task -> task.getStatus() == status).collect(Collectors.toList());
    } finally {
      lock.readLock().unlock();
    }
  }

  @Override
  public void deleteById(int id) {
    lock.writeLock().lock();
    try {
      final LinkedList<Task> tasks = readTasksFromFile();
      tasks.removeIf(task -> task.getTaskId() == id);
      writeTasksToFile(tasks);
    } finally {
      lock.writeLock().unlock();
    }
  }

  @Override
  public boolean existsById(int id) {
    lock.readLock().lock();
    try {
      final LinkedList<Task> tasks = readTasksFromFile();
      return tasks.stream().anyMatch(task -> task.getTaskId() == id);
    } finally {
      lock.readLock().unlock();
    }
  }

  @Override
  public long count() {
    lock.readLock().lock();
    try {
      return readTasksFromFile().size();
    } finally {
      lock.readLock().unlock();
    }
  }

  @Override
  public void deleteAll() {
    lock.writeLock().lock();
    try {
      writeTasksToFile(new LinkedList<>());
      maxId = 0;
    } finally {
      lock.writeLock().unlock();
    }
  }

  private LinkedList<Task> readTasksFromFile() {
    try {
      final File file = new File(filePath);
      if (!file.exists()) {
        return new LinkedList<>() {
        };
      }
      return objectMapper.readValue(file, new TypeReference<LinkedList<Task>>() {
      });
    } catch (IOException e) {
      throw new RuntimeException("Error reading tasks from file: " + filePath, e);
    }
  }

  private void writeTasksToFile(List<Task> tasks) {
    try {
      final File file = new File(filePath);
      file.getParentFile().mkdirs(); // Create directories if they don't exist
      objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, tasks);
    } catch (IOException e) {
      throw new RuntimeException("Error writing tasks to file: " + filePath, e);
    }
  }
}