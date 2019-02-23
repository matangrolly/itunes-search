package au.com.appetiser.challenge.data.remote.service;

import au.com.appetiser.challenge.data.remote.model.SearchResultsResponse;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ItunesApiService {

  @GET("search")
  Single<SearchResultsResponse> search(
    @Query("term") String term,
    @Query("country") String country,
    @Query("media") String media
  );

}
