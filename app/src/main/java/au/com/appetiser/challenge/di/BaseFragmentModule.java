package au.com.appetiser.challenge.di;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.LifecycleOwner;
import dagger.Module;
import dagger.Provides;

/**
 * Module for fragment component
 * <p>
 * NOTE: all method must be public (since children module might not in same package,
 * thus dagger can't generate inherit method
 */
@Module
public abstract class BaseFragmentModule<T extends Fragment> {

  @Provides
  @ActivityContext
  public Context provideContext(T fragment) {
    return fragment.getContext();
  }

  @Provides
  @ChildFragmentManager
  public FragmentManager provideChildFragmentManager(T fragment) {
    return fragment.getChildFragmentManager();
  }

  @Provides
  @ActivityFragmentManager
  public FragmentManager provideActivityFragmentManager(FragmentActivity activity) {
    return activity.getSupportFragmentManager();
  }

  @Provides
  public FragmentActivity provideActivity(T fragment) {
    return fragment.getActivity();
  }

  @Provides
  public LifecycleOwner provideLifeCycleOwner(T fragment) {
    return fragment;
  }

}
