package com.marwan.dev.task_tracker.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.marwan.dev.task_tracker.exceptions.TaskNotFoundException;
import com.marwan.dev.task_tracker.model.Task;
import com.marwan.dev.task_tracker.model.TaskStatus;
import com.marwan.dev.task_tracker.services.TaskService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UpdateCommandTest {

  @InjectMocks
  private UpdateCommand updateCommand;

  @Mock
  private TaskService taskService;

  @Test
  void gavin_task_exits_when_call_to_update_task_return_the_updated_task() {
    final int taskId = 1;
    final String newTaskDetails = "Updated task details";
    final String updatedDetails = "Updated task details hola";

    final Task mockTask = new Task(updatedDetails, TaskStatus.TODO);
    when(taskService.updateTask(taskId, newTaskDetails)).thenReturn(mockTask);

    final Task result = updateCommand.update(taskId, newTaskDetails);

    assertEquals(mockTask, result);
    verify(taskService, times(1)).updateTask(taskId, newTaskDetails);
  }

  @Test
  void gavin_task_not_exits_when_call_to_update_task_throw_task_not_found_exception() {
    final int taskId = 10;
    final String newTaskDetails = "Invalid details";

    when(taskService.updateTask(taskId, newTaskDetails)).thenThrow(new TaskNotFoundException());

    assertThrows(TaskNotFoundException.class, () -> updateCommand.update(taskId, newTaskDetails));
    verify(taskService, times(1)).updateTask(taskId, newTaskDetails);
  }
}