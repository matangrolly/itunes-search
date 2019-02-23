package au.com.appetiser.challenge;


import android.app.Activity;

import javax.inject.Inject;

import androidx.multidex.MultiDexApplication;
import au.com.appetiser.challenge.di.AppComponent;
import au.com.appetiser.challenge.di.AppInjector;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import io.realm.Realm;
import timber.log.Timber;

public class App extends MultiDexApplication implements HasActivityInjector {

  @Inject
  DispatchingAndroidInjector<Activity> dispatchingAndroidInjector;

  private AppComponent appComponent;

  @Override
  public void onCreate() {
    super.onCreate();

    Realm.init(this);

    appComponent = AppInjector.init(this);
    appComponent.inject(this);

    if (BuildConfig.DEBUG) {
      Timber.plant(new Timber.DebugTree());
    }
  }

  @Override
  public AndroidInjector<Activity> activityInjector() {
    return dispatchingAndroidInjector;
  }

}
