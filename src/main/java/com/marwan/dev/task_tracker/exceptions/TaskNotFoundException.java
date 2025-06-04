package com.marwan.dev.task_tracker.exceptions;

public class TaskNotFoundException extends RuntimeException {

  public TaskNotFoundException() {
    super(ErrorMessage.TASK_NOT_FOUND.getMessage());
  }
}