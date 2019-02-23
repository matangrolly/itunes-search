package au.com.appetiser.challenge.data.local.dao;

import com.duyp.androidutils.realm.LiveRealmObject;
import com.duyp.androidutils.realm.LiveRealmResults;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import au.com.appetiser.challenge.data.local.LocalDb;
import io.realm.RealmObject;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import io.realm.Sort;

public class BaseRealmDao<M extends RealmObject> {
  private final LocalDb db;
  private final Class<M> clazz;
  @Nullable
  private final String defaultSortField;
  @NonNull
  private final String primaryField;
  private Sort defaultSort;

  public BaseRealmDao(LocalDb db, Class<M> mClass) {
    this.db = db;
    this.clazz = mClass;
    this.primaryField = this.getPrimaryField();
    this.defaultSortField = this.getDefaultSortField();
    this.defaultSort = this.getDefaultSort();
  }

  @NonNull
  protected String getPrimaryField() {
    return "id";
  }

  @Nullable
  protected String getDefaultSortField() {
    return null;
  }

  @NonNull
  protected Sort getDefaultSort() {
    return Sort.DESCENDING;
  }

  public LocalDb getDb() {
    return db;
  }

  protected RealmQuery<M> query() {
    return this.getDb().getLocalInstance(true).where(this.clazz);
  }

  protected RealmQuery<M> queryById(String id) {
    return this.query().equalTo(this.primaryField, id);
  }

  protected RealmResults<M> findAllSorted(RealmQuery<M> query) {
    return this.defaultSortField == null ?
      query.findAll() : query.sort(this.defaultSortField, this.defaultSort).findAll();
  }

  public RealmResults<M> getAll() {
    return this.findAllSorted(this.query());
  }

  public M getById(@NonNull String id) {
    return this.queryById(id).findFirst();
  }

  public void addAll(@NonNull List<M> data) {
    this.db.executeInTransaction(realm -> realm.copyToRealmOrUpdate(data));
  }

  public void addOrUpdate(@NonNull M item) {
    this.db.executeInTransaction(realm -> realm.insertOrUpdate(item));
  }

  public void delete(@NonNull String itemId) {
    this.db.executeInTransaction(realm -> this.queryById(itemId).findAll().deleteAllFromRealm());
  }

  public void deleteAll(RealmQuery<M> query) {
    this.db.executeInTransaction(realm -> query.findAll().deleteAllFromRealm());
  }

  public void deleteAll() {
    this.db.executeInTransaction(realm -> realm.delete(this.clazz));
  }

  public void closeRealm() {
    this.db.closeLocalInstance();
  }

  public LiveRealmResults<M> asLiveData(RealmResults<M> realmResults) {
    return new LiveRealmResults(realmResults);
  }

  public LiveRealmObject<M> asLiveData(M data) {
    return new LiveRealmObject(data);
  }

  public List<M> asListCopy(RealmResults<M> realmResults) {
    if (realmResults == null) {
      return null;
    }

    return this.db.getLocalInstance(true).copyFromRealm(realmResults);
  }

  public M asCopy(M data) {
    if (data == null) {
      return null;
    }

    return this.db.getLocalInstance(true).copyFromRealm(data);
  }
}
