package com.marwan.dev.task_tracker.commands;

import com.marwan.dev.task_tracker.model.Task;
import com.marwan.dev.task_tracker.services.TaskService;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
public class AddCommand {

  private final TaskService taskService;

  public AddCommand(TaskService taskService) {
    this.taskService = taskService;
  }

  /**
   * This method adds a new task.
   *
   * @param task The task to be added.
   * @return A string indicating the task has been added.
   */
  @ShellMethod(key = "add", value = "Add a new task")
  public Task add(@ShellOption String task) {
    return taskService.createTask(task);
  }
}