package au.com.appetiser.challenge.di;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(includes = {ViewModelModule.class})
public class AppModule {

  private static final String SHARED_PREF = "itunes-search.xml";

  @Provides
  @Singleton
  Resources getResources(Application app) {
    return app.getResources();
  }

  @Provides
  @Singleton
  @AppContext
  Context providesContext(Application app) {
    return app.getApplicationContext();
  }

  @Provides
  @Singleton
  SharedPreferences provideSharedPreferences(Application app) {
    return app.getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE);
  }

  @Provides
  @Singleton
  Gson provideGson() {
    return new GsonBuilder()
      .setLenient()
      .create();
  }

}
