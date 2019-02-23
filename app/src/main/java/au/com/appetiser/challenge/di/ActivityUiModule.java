package au.com.appetiser.challenge.di;

import au.com.appetiser.challenge.ui.details.DetailsActivity;
import au.com.appetiser.challenge.ui.details.DetailsActivityModule;
import au.com.appetiser.challenge.ui.main.MainActivity;
import au.com.appetiser.challenge.ui.main.MainActivityModule;
import au.com.appetiser.challenge.ui.splash.SplashActivity;
import au.com.appetiser.challenge.ui.splash.SplashActivityModule;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityUiModule {

  @ContributesAndroidInjector(modules = SplashActivityModule.class)
  abstract SplashActivity splashActivity();

  @ContributesAndroidInjector(modules = MainActivityModule.class)
  abstract MainActivity maindActivity();

  @ContributesAndroidInjector(modules = DetailsActivityModule.class)
  abstract DetailsActivity detailsActivity();
}