package au.com.appetiser.challenge.di;

import javax.inject.Named;
import javax.inject.Singleton;

import au.com.appetiser.challenge.Constants;
import au.com.appetiser.challenge.data.remote.service.ItunesApiService;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class RestApiModule {

  @Singleton
  @Provides
  ItunesApiService provideItunesApiService(@Named("ItunesApi") Retrofit retrofit) {
    return retrofit.create(ItunesApiService.class);
  }

  @Singleton
  @Provides
  @Named("ItunesApi")
  Retrofit provideItunesRetrofit(Retrofit.Builder retrofitBuilder) {
    return retrofitBuilder
      .baseUrl(Constants.API_ITUNES)
      .build();
  }
}
