package au.com.appetiser.challenge.ui.main;

import android.content.Context;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import au.com.appetiser.challenge.R;
import au.com.appetiser.challenge.data.local.model.Track;
import au.com.appetiser.challenge.databinding.ItemTrackBinding;
import au.com.appetiser.challenge.di.ActivityContext;
import au.com.appetiser.challenge.ui.base.adapter.BaseAdapter;
import au.com.appetiser.challenge.ui.base.adapter.BaseViewHolder;
import javax.inject.Inject;

public class TrackItemAdapter extends BaseAdapter<Track> {

  class ItemViewHolder extends BaseViewHolder<Track> {

    private final ItemTrackBinding binding;

    public ItemViewHolder(ItemTrackBinding binding) {
      super(binding.getRoot());
      this.binding = binding;
    }

    @Override
    public void bind(@NonNull Track track) {
      this.binding.setItem(track);
      this.binding.executePendingBindings();
    }
  }

  @Inject
  public TrackItemAdapter(@ActivityContext Context context) {
    super(context);
  }

  @Override
  protected void bindItemViewHolder(RecyclerView.ViewHolder viewHolder, @NonNull Track track) {
    ((ItemViewHolder) viewHolder).bind(track);
  }

  @Override
  protected RecyclerView.ViewHolder createItemHolder(ViewGroup viewGroup, int itemType) {
    ItemTrackBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.item_track, viewGroup, false);
    return new ItemViewHolder(binding);
  }
}
