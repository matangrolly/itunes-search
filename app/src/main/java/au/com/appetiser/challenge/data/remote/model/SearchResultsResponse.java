package au.com.appetiser.challenge.data.remote.model;

import au.com.appetiser.challenge.data.local.model.Track;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class SearchResultsResponse {
  @SerializedName("resultCount")
  private int resultCount;

  @SerializedName("results")
  private List<Track> results;

  public int getResultCount() {
    return resultCount;
  }

  public List<Track> getResults() {
    return results;
  }
}
