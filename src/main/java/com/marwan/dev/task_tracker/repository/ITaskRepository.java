package com.marwan.dev.task_tracker.repository;

import com.marwan.dev.task_tracker.model.Task;
import com.marwan.dev.task_tracker.model.TaskStatus;
import java.util.List;
import java.util.Optional;

public interface ITaskRepository {

  Task save(Task task);

  Optional<Task> findById(int id);

  List<Task> findAll();

  List<Task> findByStatus(TaskStatus status);

  void deleteById(int id);

  boolean existsById(int id);

  long count();

  void deleteAll();
}