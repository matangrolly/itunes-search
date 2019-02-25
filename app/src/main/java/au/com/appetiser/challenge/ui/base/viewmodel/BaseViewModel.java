package au.com.appetiser.challenge.ui.base.viewmodel;

import android.os.Bundle;
import android.os.Handler;
import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModel;
import au.com.appetiser.challenge.data.ErrorEntity;
import au.com.appetiser.challenge.data.Resource;
import au.com.appetiser.challenge.data.SimpleRemoteSourceMapper;
import au.com.appetiser.challenge.data.State;
import au.com.appetiser.challenge.data.Status;
import au.com.appetiser.challenge.data.helper.RestHelper;
import au.com.appetiser.challenge.util.Inputs;
import au.com.appetiser.challenge.util.SafeMutableLiveData;
import com.duyp.androidutils.rx.functions.PlainConsumer;
import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Base class that provides base implementation for handling loading status, and hole all api request disposables.
 * All api requests called will be added to a composite disposable which is disposed when view model is cleared
 * <p>
 * Reference: https://developer.android.com/topic/libraries/architecture/viewmodel.html
 */

public abstract class BaseViewModel extends ViewModel {

  @NonNull
  protected final SafeMutableLiveData<State> stateLiveData = new SafeMutableLiveData<>();
  private boolean isFirstTimeUiCreate = true;
  @NonNull
  private CompositeDisposable compositeDisposable = new CompositeDisposable();

  /**
   * called after fragment / activity is created with input bundle arguments
   *
   * @param bundle argument data
   */
  @CallSuper
  public void onCreate(@Nullable Bundle bundle) {
    Timber.d("onCreate: UI creating...");
    if (isFirstTimeUiCreate) {
      onFirsTimeUiCreate(bundle);
      isFirstTimeUiCreate = false;
    }
  }

  /**
   * Called when UI create for first time only, since activity / fragment might be rotated,
   * we don't need to re-init data, because view model will survive, data aren't destroyed
   *
   * @param bundle
   */
  protected abstract void onFirsTimeUiCreate(@Nullable Bundle bundle);

  public void disposeAllExecutions() {
    compositeDisposable.dispose();
    compositeDisposable = new CompositeDisposable();
    publishState(State.success());
  }

  @CallSuper
  @Override
  protected void onCleared() {
    super.onCleared();
    compositeDisposable.dispose();
  }

  public void addDisposable(@NonNull Disposable disposable) {
    compositeDisposable.add(disposable);
  }

  /**
   * Add and execute an resource flowable created by
   * {@link RestHelper#createRemoteSourceMapper(Single, PlainConsumer)}
   * Loading, error, success status will be updated automatically via {@link #stateLiveData} which should be observed
   * by fragments / activities to update UI appropriately
   *
   * @param showProgress     true if should show progress when executing, false if not
   * @param resourceFlowable flowable resource, see {@link SimpleRemoteSourceMapper}
   * @param responseConsumer consume response data
   * @param <T>              type of response
   */
  protected <T> void execute(boolean showProgress, Flowable<Resource<T>> resourceFlowable,
                             @Nullable PlainConsumer<T> responseConsumer) {

    Disposable disposable = resourceFlowable.observeOn(AndroidSchedulers.mainThread())
      .subscribeOn(Schedulers.newThread())
      .subscribe(resource -> {
        if (resource != null) {
          Timber.d("addRequest: resource changed: %s", resource.toString());
          if (resource.getData() != null && responseConsumer != null) {
            responseConsumer.accept(resource.getData());
          }
          if (resource.getState().getStatus() == Status.LOADING && !showProgress) {
            // do nothing if progress showing is not allowed
          } else {

            if (resource.getState().getStatus() == Status.ERROR &&
              ErrorEntity.NETWORK_UNAVAILABLE.equals(resource.getState().getMessage())) {
            }

            publishState(resource.getState());

          }
        }
      });
    compositeDisposable.add(disposable);
  }

  public void publishState(State state) {
    stateLiveData.setValue(state);
    if (!Inputs.isEmpty(state.getMessage())) {
      // if state has a message, after show it, we should reset to prevent
      // message will still be shown if fragment / activity is rotated (re-observe state live data)
      new Handler().postDelayed(() -> stateLiveData.setValue(State.success()), 100);
    }
  }

  @NonNull
  public SafeMutableLiveData<State> getStateLiveData() {
    return stateLiveData;
  }

}