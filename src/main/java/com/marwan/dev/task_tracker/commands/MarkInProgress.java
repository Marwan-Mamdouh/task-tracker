package com.marwan.dev.task_tracker.commands;

import com.marwan.dev.task_tracker.model.Task;
import com.marwan.dev.task_tracker.model.TaskStatus;
import com.marwan.dev.task_tracker.services.TaskService;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
public class MarkInProgress {

  private final TaskService taskService;

  public MarkInProgress(TaskService taskService) {
    this.taskService = taskService;
  }

  /**
   * This method marks a task as in progress.
   *
   * @param taskId The ID of the task to be marked as in progress.
   * @return A string indicating the task has been marked as in progress.
   */
  @ShellMethod(key = {"mark-in-progress", "mark-progress",
      "progress"}, value = "Mark a task as in progress")
  public Task markInProgress(@ShellOption int taskId) {
    // Logic to mark the task as in progress would go here
    return taskService.MarkTask(taskId, TaskStatus.IN_PROGRESS);
  }
}
