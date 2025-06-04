package com.marwan.dev.task_tracker.commands;

import com.marwan.dev.task_tracker.model.Task;
import com.marwan.dev.task_tracker.model.TaskStatus;
import com.marwan.dev.task_tracker.services.TaskService;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
public class MarkDone {

  private final TaskService taskService;

  public MarkDone(TaskService taskService) {
    this.taskService = taskService;
  }

  /**
   * This method marks a task as done.
   *
   * @param taskId The ID of the task to be marked as done.
   * @return A string indicating the task has been marked as done.
   */
  @ShellMethod(key = {"mark-done", "done"}, value = "Mark a task as done")
  public Task markDone(@ShellOption int taskId) {
    // Logic to mark the task as done would go here
    return taskService.MarkTask(taskId, TaskStatus.DONE);
  }
}
