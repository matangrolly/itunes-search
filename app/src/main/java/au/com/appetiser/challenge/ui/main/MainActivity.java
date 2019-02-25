package au.com.appetiser.challenge.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import androidx.appcompat.widget.SearchView;
import androidx.core.app.ActivityOptionsCompat;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import au.com.appetiser.challenge.Constants;
import au.com.appetiser.challenge.R;
import au.com.appetiser.challenge.data.local.model.Track;
import au.com.appetiser.challenge.databinding.ActivityMainBinding;
import au.com.appetiser.challenge.ui.base.activity.BaseViewModelActivity;
import au.com.appetiser.challenge.ui.base.interfaces.OnItemClickListener;
import au.com.appetiser.challenge.ui.details.DetailsActivity;
import javax.inject.Inject;

public class MainActivity extends BaseViewModelActivity<ActivityMainBinding, MainViewModel>
  implements SwipeRefreshLayout.OnRefreshListener, OnItemClickListener<Track> {

  @Inject
  TrackItemAdapter trackItemAdapter;

  private boolean isSearchExpanded = false;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setToolbarTitle(R.string.app_name);

    binding.setVm(viewModel);
    binding.recyclerView.setAdapter(trackItemAdapter);
    binding.refreshLayout.setOnRefreshListener(this);
    trackItemAdapter.setItemClickListener(this);

    viewModel.getTrackListLiveData().observe(this, tracks -> {
      if (tracks == null || tracks.isEmpty()) {
        binding.textEmpty.setVisibility(View.VISIBLE);
      } else {
        binding.textEmpty.setVisibility(View.GONE);
        trackItemAdapter.setData(tracks);
      }
    });
  }

  @Override
  public int getLayout() {
    return R.layout.activity_main;
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu_main, menu);

    MenuItem searchItem = menu.findItem(R.id.search);
    SearchView searchView = (SearchView) searchItem.getActionView();
    searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
      @Override
      public boolean onQueryTextChange(String newText) {
        return false;
      }

      @Override
      public boolean onQueryTextSubmit(String query) {
        trackItemAdapter.clear();
        viewModel.setQuery(query);
        viewModel.search();
        return true;
      }
    });
    searchItem.setOnActionExpandListener(new MenuItem.OnActionExpandListener() {
      @Override
      public boolean onMenuItemActionCollapse(MenuItem item) {
        isSearchExpanded = false;
        trackItemAdapter.clear();
        viewModel.setQuery(null);
        viewModel.getTracksFromApi();
        return true;
      }

      @Override
      public boolean onMenuItemActionExpand(MenuItem item) {
        isSearchExpanded = true;
        return true;
      }
    });

    return super.onCreateOptionsMenu(menu);
  }

  @Override
  public void onItemClick(View v, Track item) {
    Intent intent = new Intent(this, DetailsActivity.class);
    intent.putExtra(Constants.EXTRA_ID, item.getTrackId());
    intent.putExtra(Constants.EXTRA_TITLE, item.getTrackName());
    ActivityOptionsCompat options = ActivityOptionsCompat.
        makeSceneTransitionAnimation(this, v.findViewById(R.id.imageArtwork),
            getString(R.string.shared_element_name));
    startActivity(intent, options.toBundle());
  }

  @Override
  public void onRefresh() {
    if (isSearchExpanded) {
      viewModel.search();
    } else {
      viewModel.getTracksFromApi();
    }
  }

  @Override
  public void setLoading(boolean loading) {
    binding.progressBar.setVisibility(loading ? View.VISIBLE : View.GONE);

    if (!loading && binding.refreshLayout.isRefreshing()) {
      binding.refreshLayout.setRefreshing(false);
    }
  }

  @Override
  protected boolean canBack() {
    return false;
  }
}