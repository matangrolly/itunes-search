package au.com.appetiser.challenge.ui.main;

import android.os.Bundle;
import android.os.Handler;
import androidx.annotation.Nullable;
import au.com.appetiser.challenge.Constants;
import au.com.appetiser.challenge.data.local.model.Track;
import au.com.appetiser.challenge.data.repo.TrackRepository;
import au.com.appetiser.challenge.ui.base.viewmodel.BaseViewModel;
import au.com.appetiser.challenge.util.SafeMutableLiveData;
import java.util.List;
import javax.inject.Inject;

public class MainViewModel extends BaseViewModel {

  private final Handler handler = new Handler();

  private String query;

  private final SafeMutableLiveData<List<Track>> trackListLiveData;

  private final TrackRepository trackRepository;

  @Inject
  public MainViewModel(TrackRepository trackRepository) {
    this.trackRepository = trackRepository;
    this.trackListLiveData = new SafeMutableLiveData<>();
  }

  public SafeMutableLiveData<List<Track>> getTrackListLiveData() {
    return trackListLiveData;
  }

  public void getTracksFromApi() {
    execute(true, trackRepository.defaultSearch(),
      response -> trackListLiveData.setValue(response.getResults()));
  }

  public void search() {
    execute(true, trackRepository.search(query, Constants.DEFAULT_SEARCH_COUNTRY, Constants.DEFAULT_SEARCH_MEDIA),
      response -> trackListLiveData.setValue(response.getResults()));
  }

  public void setQuery(String query) {
    this.query = query;
  }

  @Override
  protected void onFirsTimeUiCreate(@Nullable Bundle bundle) {
    List<Track> tracks = trackRepository.getAlTracksFromDb(Constants.DEFAULT_SEARCH_TERM);
    if (tracks != null && !tracks.isEmpty()) {
      trackListLiveData.setValue(tracks);
    }

    handler.postDelayed(this::getTracksFromApi, 300);
  }
}
