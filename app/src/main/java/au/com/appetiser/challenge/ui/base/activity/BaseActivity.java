package au.com.appetiser.challenge.ui.base.activity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.appbar.AppBarLayout;

import javax.inject.Inject;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import au.com.appetiser.challenge.R;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;
import timber.log.Timber;

/**
 * Base activity that will be injected automatically by implementing {@link HasSupportFragmentInjector}
 * All fragment inside this activity is injected as well
 */
public abstract class BaseActivity extends AppCompatActivity
  implements HasSupportFragmentInjector {

  @Nullable
  protected AppBarLayout appBar;
  @Nullable
  protected Toolbar toolbar;
  // dispatch android injector to all fragments
  @Inject
  DispatchingAndroidInjector<Fragment> dispatchingAndroidInjector;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setupLayoutStableFullscreen();

    if (!shouldUseDataBinding()) {
      // set contentView if child activity not use dataBinding
      setContentView(getLayout());
      initViews();
    }

    if (shouldPostponeTransition()) {
      ActivityCompat.postponeEnterTransition(this);
    }
  }

  protected void initViews() {
    appBar = findViewById(R.id.appbar);
    toolbar = findViewById(R.id.toolbar);
    setupToolbarAndStatusBar();
  }

  public abstract int getLayout();

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    if (canBack()) {
      if (item.getItemId() == android.R.id.home) {
        onBackPressed();
        return true;
      }
    }
    return super.onOptionsItemSelected(item);
  }

  @Override
  public AndroidInjector<Fragment> supportFragmentInjector() {
    return dispatchingAndroidInjector;
  }

  /**
   * @return true if should use back button on toolbar
   */
  protected abstract boolean canBack();

  /**
   * @return true if this activity should use layout stable fullscreen (status bar overlap activity's content)
   */
  protected boolean shouldUseLayoutStableFullscreen() {
    return false;
  }

  /**
   * @return true if this activity should postpone transition (in case of destination view is in viewpager)
   */
  protected boolean shouldPostponeTransition() {
    return false;
  }

  /**
   * @return true if child activity should use data binding instead of {@link #setContentView(View)}
   */
  protected boolean shouldUseDataBinding() {
    return false;
  }

  private void setupToolbarAndStatusBar() {
    if (toolbar != null) {
      setSupportActionBar(toolbar);
      if (canBack()) {
        if (getSupportActionBar() != null) {
          getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
      }
    }
  }

  public void setToolbarTitle(@StringRes int res) {
    if (getSupportActionBar() != null) {
      getSupportActionBar().setTitle(res);
    }
  }

  private void setupLayoutStableFullscreen() {
    if (shouldUseLayoutStableFullscreen()) {
      View decorView = getWindow().getDecorView();
      // Hide the status bar.
      int uiOptions = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
        View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
      decorView.setSystemUiVisibility(uiOptions);
    }
  }


  protected void setLoading(boolean loading) {
    Timber.d("loading: %s", loading);
  }
}
