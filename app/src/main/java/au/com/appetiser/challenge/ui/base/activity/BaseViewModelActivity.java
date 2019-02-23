package au.com.appetiser.challenge.ui.base.activity;

import android.os.Bundle;
import android.widget.Toast;

import java.lang.reflect.ParameterizedType;

import javax.inject.Inject;

import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import au.com.appetiser.challenge.data.State;
import au.com.appetiser.challenge.data.Status;
import au.com.appetiser.challenge.ui.base.viewmodel.BaseViewModel;
import timber.log.Timber;

/**
 * Base activity class that has a ViewModel extending {@link BaseViewModel}. The viewModel will be provided automatically
 * <p>
 * Progress showing and message showing will be handled automatically when viewModel's state changed
 * through {@link BaseViewModel#stateLiveData}
 */

public abstract class BaseViewModelActivity<B extends ViewDataBinding, VM extends BaseViewModel> extends BaseActivity {

  protected VM viewModel;
  @VisibleForTesting
  protected B binding;
  @Inject
  ViewModelProvider.Factory viewModelFactory;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    //init data binding
    binding = DataBindingUtil.setContentView(this, getLayout());
    binding.setLifecycleOwner(this);
    initViews();

    // int view model
    // noinspection unchecked
    Class<VM> viewModelClass = (Class<VM>) ((ParameterizedType) getClass()
      .getGenericSuperclass()).getActualTypeArguments()[1]; // 1 is BaseViewModel

    viewModel = ViewModelProviders.of(this, viewModelFactory).get(viewModelClass);

    viewModel.onCreate(getIntent().getExtras());
    viewModel.getStateLiveData().observe(this, this::handleState);
  }

  @Override
  protected boolean shouldUseDataBinding() {
    return true;
  }

  /**
   * Default state handling, can be override
   *
   * @param state viewModel's state
   */
  protected void handleState(@Nullable State state) {
    setLoading(state != null && state.getStatus() == Status.LOADING);
    handleMessageState(state);
  }

  protected void handleMessageState(@Nullable State state) {
    if (state != null && state.getMessage() != null) {
      Timber.d("handleMessageState: %s", state.getMessage());
      Toast.makeText(this, state.getMessage(), Toast.LENGTH_LONG).show();
    }
  }
}