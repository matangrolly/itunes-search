package au.com.appetiser.challenge.di;

import android.app.Application;
import au.com.appetiser.challenge.App;
import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.support.AndroidSupportInjectionModule;
import javax.inject.Singleton;

@Singleton
@Component(modules = {
  AndroidSupportInjectionModule.class,
  AndroidInjectionModule.class,
  AppModule.class,
  NetworkModule.class,
  PersistenceModule.class,
  ActivityUiModule.class
}
)
public interface AppComponent {

  void inject(App application);

  @Component.Builder
  interface Builder {
    @BindsInstance
    Builder application(Application application);

    AppComponent build();
  }

}
