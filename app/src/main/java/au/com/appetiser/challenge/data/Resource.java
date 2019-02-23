package au.com.appetiser.challenge.data;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import static au.com.appetiser.challenge.data.Status.ERROR;
import static au.com.appetiser.challenge.data.Status.LOADING;
import static au.com.appetiser.challenge.data.Status.SUCCESS;

/**
 * A generic class that holds a value with its loading status.
 *
 * @param <T>
 */
public class Resource<T> {

  @NonNull
  private final State state;

  @Nullable
  private final T data;

  public Resource(@NonNull Status status, @Nullable T data, @Nullable String message) {
    this.state = new State(status, message);
    this.data = data;
  }

  public Resource(@NonNull Status status, @Nullable T data, @Nullable String message, @Nullable Double progress) {
    this.state = new State(status, message, progress);
    this.data = data;
  }

  public static <T> Resource<T> success(@Nullable T data) {
    return new Resource<>(SUCCESS, data, null);
  }

  public static <T> Resource<T> error(String msg, @Nullable T data) {
    return new Resource<>(ERROR, data, msg);
  }

  public static <T> Resource<T> loading(@Nullable T data) {
    return new Resource<>(LOADING, data, null);
  }

  public static <T> Resource<T> loading(@Nullable T data, @Nullable Double progress) {
    return new Resource<>(LOADING, data, null, progress);
  }

  @NonNull
  public State getState() {
    return state;
  }

  @Nullable
  public T getData() {
    return data;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Resource<?> resource = (Resource<?>) o;

    return resource.state.equals(this.state) && data != null ? data.equals(resource.data) : resource.data == null;
  }

  @Override
  public int hashCode() {
    int result = state.hashCode();
    result = 31 * result + (data != null ? data.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "Resource{" +
      "status=" + state.getStatus() +
      ", progress=" + state.getProgress() +
      ", message='" + state.getMessage() + '\'' +
      ", data=" + data +
      '}';
  }
}