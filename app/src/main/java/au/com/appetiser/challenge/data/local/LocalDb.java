package au.com.appetiser.challenge.data.local;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import timber.log.Timber;

public class LocalDb {

  private final ThreadLocal<Realm> localThreadRealm = new ThreadLocal<>();
  private final RealmConfiguration realmConfiguration;

  public LocalDb(RealmConfiguration realmConfiguration) {
    this.realmConfiguration = realmConfiguration;
    Realm.setDefaultConfiguration(realmConfiguration);
  }

  public Realm openLocalInstance() {
    Realm realm = Realm.getInstance(realmConfiguration);
    if (localThreadRealm.get() == null) {
      localThreadRealm.set(realm);
    }
    return realm;
  }

  public Realm getLocalInstance(boolean createIfNotExists) {
    Realm realm = localThreadRealm.get();
    if (realm == null && !createIfNotExists) {
      Timber.w("No open Realms were found on this thread.");
    } else {
      realm = openLocalInstance();
    }
    return realm;
  }

  public void closeLocalInstance() {
    Realm realm = localThreadRealm.get();
    if (realm == null) {
      Timber.w("Cannot close a Realm that is not open.");
      return;
    }
    realm.close();
    if (Realm.getLocalInstanceCount(realmConfiguration) <= 0) {
      localThreadRealm.set(null);
    }
  }

  public void executeInTransaction(Realm.Transaction transaction) {
    try {
      Realm realm = getLocalInstance(true);
      if (!realm.isAutoRefresh()) {
        try {
          boolean wasInTransaction = realm.isInTransaction();
          if (!wasInTransaction) {
            realm.beginTransaction();
          }
          transaction.execute(realm);
          if (!wasInTransaction) {
            realm.commitTransaction();
          }
        } catch (Throwable e) {
          if (realm.isInTransaction()) {
            realm.cancelTransaction();
          }
          Timber.e(e);
        }
      } else {
        realm.executeTransaction(transaction);
      }
    } catch (Exception e) {
      Timber.e(e, "Error executing Realm Transaction");
    } finally {
      closeLocalInstance();
    }
  }
}
