package com.marwan.dev.task_tracker.commands;

import com.marwan.dev.task_tracker.model.Task;
import com.marwan.dev.task_tracker.model.TaskStatus;
import com.marwan.dev.task_tracker.services.TaskService;
import java.util.List;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
public class listCommand {

  private final TaskService taskService;

  public listCommand(TaskService taskService) {
    this.taskService = taskService;
  }

  /**
   * This method lists all tasks.
   *
   * @return A string indicating the list of tasks.
   */
  @ShellMethod(key = "list", value = "List tasks based on the filter type no filter mean list all")
  public List<Task> list(@ShellOption(defaultValue = "all") String filter) {
    if (filter.equalsIgnoreCase("all")) {
      return taskService.getAllTasks();
    } else {
      return taskService.getTasksByStatus(stringToStatus(filter));
    }
  }

  /**
   * Helper method to convert string to Status enum
   *
   * @param statusString The string representation of status
   * @return The corresponding Status enum
   */
  private TaskStatus stringToStatus(String statusString) {
    return switch (statusString.toLowerCase()) {
      case "todo" -> TaskStatus.TODO;
      case "in-progress", "inprogress", "progress", "in_progress" -> TaskStatus.IN_PROGRESS;
      case "done", "completed" -> TaskStatus.DONE;
      default ->
        // Try direct enum parsing as fallback
          TaskStatus.valueOf(statusString.toUpperCase().replace("-", "_"));
    };
  }
}