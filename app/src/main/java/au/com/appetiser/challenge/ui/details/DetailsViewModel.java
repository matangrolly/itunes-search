package au.com.appetiser.challenge.ui.details;

import android.os.Bundle;
import androidx.annotation.Nullable;
import au.com.appetiser.challenge.Constants;
import au.com.appetiser.challenge.data.local.model.Track;
import au.com.appetiser.challenge.data.repo.TrackRepository;
import au.com.appetiser.challenge.ui.base.viewmodel.BaseViewModel;
import au.com.appetiser.challenge.util.SafeMutableLiveData;
import javax.inject.Inject;

public class DetailsViewModel extends BaseViewModel {

  private final SafeMutableLiveData<Track> trackData;

  private final TrackRepository trackRepository;

  @Inject
  public DetailsViewModel(TrackRepository trackRepository) {
    this.trackRepository = trackRepository;
    this.trackData = new SafeMutableLiveData<>();
  }

  public SafeMutableLiveData<Track> getTrackData() {
    return trackData;
  }

  @Override
  protected void onFirsTimeUiCreate(@Nullable Bundle bundle) {
    if (bundle != null && bundle.containsKey(Constants.EXTRA_ID)) {
      loadTrack(bundle.getString(Constants.EXTRA_ID));
    }
  }

  private void loadTrack(String id) {
    Track track = trackRepository.geTrackFromDb(id);
    trackData.setValue(track);
  }
}
