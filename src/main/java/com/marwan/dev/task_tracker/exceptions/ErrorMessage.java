package com.marwan.dev.task_tracker.exceptions;

import lombok.Getter;

@Getter
public enum ErrorMessage {
  TASK_NOT_FOUND("task not found.");

  private final String message;

  ErrorMessage(String message) {
    this.message = message;
  }
}