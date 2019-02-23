package au.com.appetiser.challenge.data.local.dao;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import au.com.appetiser.challenge.data.local.LocalDb;
import au.com.appetiser.challenge.data.local.model.Track;
import io.realm.Sort;

public class TrackDao extends BaseRealmDao<Track> {

  @Inject
  public TrackDao(LocalDb db) {
    super(db, Track.class);
  }

  @NonNull
  @Override
  protected String getPrimaryField() {
    return "trackId";
  }

  @Nullable
  @Override
  protected String getDefaultSortField() {
    return "trackName";
  }

  @NonNull
  @Override
  protected Sort getDefaultSort() {
    return Sort.ASCENDING;
  }
}
