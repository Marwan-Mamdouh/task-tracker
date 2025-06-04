package com.marwan.dev.task_tracker.model;

import static com.marwan.dev.task_tracker.repository.TaskRepository.maxId;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;
import java.util.StringJoiner;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class Task {

  private static int id = maxId;
  private final LocalDate createdAt;
  private int taskId;
  private String description;
  private TaskStatus status;
  private LocalDate updatedAt;

  @JsonCreator
  public Task(@JsonProperty("taskId") int taskId,
      @JsonProperty("description") String description,
      @JsonProperty("status") TaskStatus status,
      @JsonProperty("createdAt") LocalDate createdAt,
      @JsonProperty("updatedAt") LocalDate updatedAt) {
    id = taskId;
    this.taskId = taskId;
    this.description = description;
    this.status = status;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
  }

  public Task(String description, TaskStatus status) {
    this.taskId = 0;
    this.description = description;
    this.status = status;
    this.createdAt = LocalDate.now();
    this.updatedAt = null;
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", Task.class.getSimpleName() + "[", "]")
        .add("taskId=" + taskId).add("description='" + description + "'")
        .add("status=" + status).add("createdAt=" + createdAt)
        .add("updatedAt=" + updatedAt).toString();
  }
}