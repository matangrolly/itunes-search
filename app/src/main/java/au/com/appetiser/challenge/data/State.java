package au.com.appetiser.challenge.data;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class State {
  @NonNull
  private Status status;

  @Nullable
  private String message;

  @Nullable
  private Double progress;

  private boolean isHardAlert = false;

  public State(@NonNull Status status, @Nullable String message) {
    this.status = status;
    this.message = message;
    this.progress = status == Status.SUCCESS ? 100d : 0d;
  }

  public State(@NonNull Status status, @Nullable String message, @Nullable Double progress) {
    this.status = status;
    this.message = message;
    this.progress = progress;
  }

  public static State loading() {
    return new State(Status.LOADING, null);
  }

  public static State loading(String message) {
    return new State(Status.LOADING, message);
  }

  public static State loading(String message, Double progress) {
    return new State(Status.LOADING, message, progress);
  }

  public static State error(String message) {
    return new State(Status.ERROR, message);
  }

  public static State success() {
    return new State(Status.SUCCESS, null);
  }

  public static State success(String message) {
    return new State(Status.SUCCESS, message);
  }

  @NonNull
  public Status getStatus() {
    return status;
  }

  @Nullable
  public String getMessage() {
    return message;
  }

  @Nullable
  public Double getProgress() {
    return progress;
  }

  public boolean isHardAlert() {
    return isHardAlert;
  }

  public void setHardAlert(boolean hardAlert) {
    isHardAlert = hardAlert;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    State state = (State) o;

    if (status != state.status) {
      return false;
    }

    return message != null ? message.equals(state.message) : state.message == null;
  }

  @Override
  public int hashCode() {
    int result = status.hashCode();
    return 31 * result + (message != null ? message.hashCode() : 0);
  }

  @Override
  public String toString() {
    return "status: " + status + ", message: " + message;
  }
}
