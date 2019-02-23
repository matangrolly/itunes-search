package au.com.appetiser.challenge.data;

/**
 * Entity for handling error
 */
public class ErrorEntity {

  public static final int HTTP_ERROR_CODE_UNAUTHORIZED = 401;

  public static final String OOPS = "Unexpected error while requesting the server.";
  public static final String NETWORK_UNAVAILABLE = "Network unavailable!";

  private String message;
  private int httpCode;

  public ErrorEntity(String message, int httpCode) {
    this.message = message;
    this.httpCode = httpCode;
  }

  public static ErrorEntity getError(int code, String reason) {
    if (reason != null) {
      return new ErrorEntity(reason, code);
    } else {
      return new ErrorEntity(OOPS, code);
    }
  }

  public static ErrorEntity getErrorOops() {
    return new ErrorEntity(OOPS, 0);
  }

  public String getMessage() {
    return message;
  }

  public int getHttpCode() {
    return httpCode;
  }

  @Override
  public boolean equals(Object obj) {
    if (this.getClass() != obj.getClass()) {
      return false;
    }
    if (this == obj) {
      return true;
    }
    ErrorEntity entity = (ErrorEntity) obj;
    return this.httpCode == entity.httpCode && this.message.equals(entity.getMessage());
  }
}
