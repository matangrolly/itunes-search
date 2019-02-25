package au.com.appetiser.challenge.ui.details;

import android.net.Uri;
import android.os.Bundle;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.core.content.ContextCompat;
import au.com.appetiser.challenge.Constants;
import au.com.appetiser.challenge.R;
import au.com.appetiser.challenge.databinding.ActivityDetailsBinding;
import au.com.appetiser.challenge.ui.base.activity.BaseViewModelActivity;
import timber.log.Timber;

public class DetailsActivity extends BaseViewModelActivity<ActivityDetailsBinding, DetailsViewModel> {

  @Override
  public int getLayout() {
    return R.layout.activity_details;
  }

  @Override
  protected boolean canBack() {
    return true;
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    binding.setVm(viewModel);
    binding.toolbarLayout.setTitle(getIntent().getStringExtra(Constants.EXTRA_TITLE));

    binding.fab.setOnClickListener(v -> {
      if (viewModel.getTrackData().getValue() != null) {
        openChromeTab(viewModel.getTrackData().getValue().getTrackViewUrl());
      }
    });
  }

  public void openChromeTab(String url) {
    try {
      CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();

      builder.setToolbarColor(ContextCompat.getColor(this, R.color.colorPrimary));
      builder.setShowTitle(true);
      builder.build().launchUrl(this, Uri.parse(url));
    } catch (Exception e) {
      Timber.e(e);
    }
  }
}