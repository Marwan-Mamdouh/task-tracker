package com.marwan.dev.task_tracker.services;

import com.marwan.dev.task_tracker.exceptions.TaskNotFoundException;
import com.marwan.dev.task_tracker.model.Task;
import com.marwan.dev.task_tracker.model.TaskStatus;
import com.marwan.dev.task_tracker.repository.ITaskRepository;
import java.time.LocalDate;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

  private final ITaskRepository taskRepository;

  public TaskService(ITaskRepository taskRepository) {
    this.taskRepository = taskRepository;
  }

  public Task createTask(String description) {
    final Task task = new Task(description, TaskStatus.TODO);
    return taskRepository.save(task);
  }

  public Task updateTask(int taskId, String description) {
    final Task existingTask = taskRepository.findById(taskId)
        .orElseThrow(TaskNotFoundException::new);
    final Task updatedTask = setNewDescription(existingTask, description);
    return taskRepository.save(updatedTask);
  }

  public Task getTaskById(int id) {
    return taskRepository.findById(id).orElseThrow(TaskNotFoundException::new);
  }

  public List<Task> getAllTasks() {
    return taskRepository.findAll();
  }

  public List<Task> getTasksByStatus(TaskStatus status) {
    return taskRepository.findByStatus(status);
  }

  public void deleteTask(int id) {
    if (taskExists(id)) {
      taskRepository.deleteById(id);
    } else {
      throw new TaskNotFoundException();
    }
  }

  public boolean taskExists(int id) {
    return taskRepository.existsById(id);
  }

  public long getTaskCount() {
    return taskRepository.count();
  }

  public Task MarkTask(int id, TaskStatus status) {
    final Task task = getTaskById(id);
    task.setStatus(status);
    return task;
  }

  private Task setNewDescription(Task updaedTask, String description) {
    updaedTask.setDescription(description);
    updaedTask.setUpdatedAt(LocalDate.now());
    return updaedTask;
  }
}