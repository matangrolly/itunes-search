package au.com.appetiser.challenge.di;


import android.content.Context;

import com.google.gson.Gson;

import java.io.File;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Singleton;

import au.com.appetiser.challenge.BuildConfig;
import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


@Module(includes = {RestApiModule.class})
public class NetworkModule {

  private static final int HTTP_CACHE_SIZE = 5 * 1024 * 1024;
  private static final int READ_TIMEOUT = 60 * 1000;
  private static final int CONNECTION_TIMEOUT = 180 * 1000;

  public NetworkModule() {
  }

  @Singleton
  @Provides
  Retrofit.Builder provideRetrofitBuilder(OkHttpClient okHttpClient, RxJava2CallAdapterFactory rxJava2CallAdapterFactory, GsonConverterFactory gsonConverterFactory) {
    return new Retrofit.Builder()
      .client(okHttpClient)
      .addCallAdapterFactory(rxJava2CallAdapterFactory)
      .addConverterFactory(gsonConverterFactory);
  }

  @Singleton
  @Provides
  @Inject
  OkHttpClient getOkHttpClient(@AppContext Context context) {
    OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();

    httpClientBuilder
      .cache(new Cache(new File(context.getCacheDir(), "http"), HTTP_CACHE_SIZE))
      .retryOnConnectionFailure(false)
      .readTimeout(READ_TIMEOUT, TimeUnit.MILLISECONDS)
      .connectTimeout(CONNECTION_TIMEOUT, TimeUnit.MILLISECONDS);

    if (BuildConfig.DEBUG) {
      // HTTP logs in Logcat
      HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
      logging.setLevel(HttpLoggingInterceptor.Level.BODY);
      httpClientBuilder.addNetworkInterceptor(logging);
    }

    return httpClientBuilder.build();
  }

  @Singleton
  @Provides
  RxJava2CallAdapterFactory provideRxJava2CallAdapterFactory() {
    return RxJava2CallAdapterFactory.create();
  }

  @Singleton
  @Provides
  GsonConverterFactory provideGsonConverterFactory(Gson gson) {
    return GsonConverterFactory.create(gson);
  }
}
