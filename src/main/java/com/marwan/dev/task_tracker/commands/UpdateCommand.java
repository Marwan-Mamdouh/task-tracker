package com.marwan.dev.task_tracker.commands;

import com.marwan.dev.task_tracker.model.Task;
import com.marwan.dev.task_tracker.services.TaskService;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
public class UpdateCommand {

  private final TaskService taskService;

  public UpdateCommand(TaskService taskService) {
    this.taskService = taskService;
  }

  /**
   * This method updates an existing task description.
   *
   * @param taskId         The ID of the task to be updated.
   * @param newDescription The new details for the task.
   * @return A Task indicating the task has been updated.
   */
  @ShellMethod(key = "update", value = "Update an existing task")
  public Task update(@ShellOption int taskId, @ShellOption String newDescription) {

    return taskService.updateTask(taskId, newDescription);
  }
}