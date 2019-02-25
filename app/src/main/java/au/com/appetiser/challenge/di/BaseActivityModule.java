package au.com.appetiser.challenge.di;

import android.app.Activity;
import android.content.Context;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.LifecycleOwner;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class BaseActivityModule<T extends AppCompatActivity> {

  @Provides
  @ActivityContext
  public Context provideContext(T activity) {
    return activity;
  }

  @Provides
  public Activity provideActivity(T activity) {
    return activity;
  }

  @Provides
  @ActivityFragmentManager
  public FragmentManager provideFragmentManager(T activity) {
    return activity.getSupportFragmentManager();
  }

  @Provides
  public LifecycleOwner provideLifeCycleOwner(T activity) {
    return activity;
  }
}
