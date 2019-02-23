package au.com.appetiser.challenge.data.remote.model;

import com.google.gson.annotations.SerializedName;

public class ItunesErrorResponse {

  @SerializedName("errorMessage")
  private String errorMessage;

  public String getErrorMessage() {
    return errorMessage;
  }
}
