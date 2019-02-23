package au.com.appetiser.challenge.data.helper;

import com.google.gson.Gson;

import java.net.UnknownHostException;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import au.com.appetiser.challenge.data.ErrorEntity;
import au.com.appetiser.challenge.data.remote.model.ItunesErrorResponse;
import okhttp3.ResponseBody;
import retrofit2.HttpException;
import timber.log.Timber;

public class ResponseHelper {

  /**
   * Get http error code from {@link Throwable} if it is instance of {@link HttpException}
   *
   * @param throwable input throwable
   * @return http code or -1 if throwable isn't a instance of {@link HttpException}
   */
  public static int getErrorCode(Throwable throwable) {
    if (throwable instanceof HttpException) {
      return ((HttpException) throwable).code();

    }
    return -1;
  }

  /**
   * Get error response from all api's response
   *
   * @param throwable throwable instance from retrofit service's response
   * @return an instance of {@link ItunesErrorResponse} contains error message and some other fields
   */
  @Nullable
  public static ItunesErrorResponse getErrorResponse(@NonNull Throwable throwable) {
    ResponseBody body = null;
    if (throwable instanceof HttpException) {
      body = ((HttpException) throwable).response().errorBody();
    }
    if (body != null) {
      try {
        return new Gson().fromJson(body.string(), ItunesErrorResponse.class);
      } catch (Exception e) {
        Timber.e(e, "Error parsing Error Response.");
      }
    }
    return null;
  }

  /**
   * Get a error message from retrofit response throwable
   *
   * @param throwable retrofit rx throwable
   * @return error message
   */
  public static String getPrettifiedErrorMessage(@Nullable Throwable throwable) {
    if (throwable instanceof UnknownHostException) {
      return ErrorEntity.NETWORK_UNAVAILABLE;
    }

    return ErrorEntity.OOPS;
  }
}
