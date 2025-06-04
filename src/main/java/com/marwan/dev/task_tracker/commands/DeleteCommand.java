package com.marwan.dev.task_tracker.commands;

import com.marwan.dev.task_tracker.services.TaskService;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
public class DeleteCommand {

  private final TaskService taskService;

  public DeleteCommand(TaskService taskService) {
    this.taskService = taskService;
  }

  /**
   * This method deletes an existing task.
   *
   * @param taskId The ID of the task to be deleted.
   * @return A string indicating the task has been deleted.
   */
  @ShellMethod(key = "delete", value = "Delete an existing task")
  public String delete(@ShellOption int taskId) {
    taskService.deleteTask(taskId);
    return "Task with ID " + taskId + " has been deleted.";
  }
}