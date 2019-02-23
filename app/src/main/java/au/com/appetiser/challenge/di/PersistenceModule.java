package au.com.appetiser.challenge.di;

import javax.inject.Singleton;

import au.com.appetiser.challenge.data.local.LocalDb;
import dagger.Module;
import dagger.Provides;
import io.realm.RealmConfiguration;

@Module
public class PersistenceModule {

  @Singleton
  @Provides
  public LocalDb providesLocalRealmDB(RealmConfiguration realmConfiguration) {
    return new LocalDb(realmConfiguration);
  }

  @Singleton
  @Provides
  public RealmConfiguration providesRealmConfiguration() {
    RealmConfiguration.Builder builder = new RealmConfiguration.Builder();

    // TODO: handle proper database migration
    builder.deleteRealmIfMigrationNeeded();

    return builder.build();
  }

}
