package au.com.appetiser.challenge.data.local.dao;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import au.com.appetiser.challenge.data.local.LocalDb;
import au.com.appetiser.challenge.data.local.model.Track;
import io.realm.RealmResults;
import io.realm.Sort;
import javax.inject.Inject;

public class TrackDao extends BaseRealmDao<Track> {

  @Inject
  public TrackDao(LocalDb db) {
    super(db, Track.class);
  }

  public RealmResults<Track> getAllBySearchTerm(String searchTerm) {
    return findAllSorted(query().equalTo("searchTerm", searchTerm));
  }

  @NonNull
  @Override
  protected Sort getDefaultSort() {
    return Sort.ASCENDING;
  }

  @Nullable
  @Override
  protected String getDefaultSortField() {
    return "trackName";
  }

  @NonNull
  @Override
  protected String getPrimaryField() {
    return "trackId";
  }

}
