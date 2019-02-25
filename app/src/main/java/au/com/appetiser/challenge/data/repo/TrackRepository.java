package au.com.appetiser.challenge.data.repo;

import au.com.appetiser.challenge.Constants;
import au.com.appetiser.challenge.data.Resource;
import au.com.appetiser.challenge.data.helper.RestHelper;
import au.com.appetiser.challenge.data.local.dao.TrackDao;
import au.com.appetiser.challenge.data.local.model.Track;
import au.com.appetiser.challenge.data.remote.model.SearchResultsResponse;
import au.com.appetiser.challenge.data.remote.service.ItunesApiService;
import io.reactivex.Flowable;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class TrackRepository {

  private final ItunesApiService itunesApiService;

  private final TrackDao trackDao;

  @Inject
  public TrackRepository(TrackDao trackDao, ItunesApiService itunesApiService) {
    this.trackDao = trackDao;
    this.itunesApiService = itunesApiService;
  }

  public Flowable<Resource<SearchResultsResponse>> defaultSearch() {
    return search(Constants.DEFAULT_SEARCH_TERM, Constants.DEFAULT_SEARCH_COUNTRY, Constants.DEFAULT_SEARCH_MEDIA);
  }

  public Track geTrackFromDb(String id) {
    return trackDao.asCopy(trackDao.getById(id));
  }

  public List<Track> getAlTracksFromDb(String searchTerm) {
    return trackDao.asListCopy(trackDao.getAllBySearchTerm(searchTerm));
  }

  public Flowable<Resource<SearchResultsResponse>> search(String term, String country, String media) {
    return RestHelper.createRemoteSourceMapper(itunesApiService.search(term, country, media),
      response -> {
        List<Track> tracks = response.getResults();
        for (Track track : tracks) {
          track.setSearchTerm(term);
        }
        trackDao.addAll(tracks);
      });
  }

}
