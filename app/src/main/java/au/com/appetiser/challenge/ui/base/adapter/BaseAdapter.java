package au.com.appetiser.challenge.ui.base.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import au.com.appetiser.challenge.di.ActivityContext;
import au.com.appetiser.challenge.ui.base.interfaces.OnItemClickListener;
import java.util.List;

public abstract class BaseAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

  @Nullable
  protected List<T> data;

  @Nullable
  protected OnItemClickListener<T> itemClickListener;

  @NonNull
  protected LayoutInflater layoutInflater;

  @NonNull
  protected final Context context;

  public BaseAdapter(@ActivityContext Context context) {
    setHasStableIds(true);
    this.context = context;
    this.layoutInflater = LayoutInflater.from(context);
  }

  public void setData(@Nullable List<T> newData) {
    if (this.data != newData) {
      this.data = newData;
    }
    notifyDataSetChanged();
  }


  @Override
  public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
    T item = getItem(position);
    if (item != null) {
      bindItemViewHolder(holder, item);
    }
  }

  @NonNull
  @Override
  public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    RecyclerView.ViewHolder holder = createItemHolder(parent, viewType);
    holder.itemView.setOnClickListener(v -> {
      if (itemClickListener != null) {
        T item = getItem(holder.getAdapterPosition());
        if (item != null) {
          itemClickListener.onItemClick(holder.itemView, item);
        }
      }
    });
    return holder;
  }

  protected abstract RecyclerView.ViewHolder createItemHolder(ViewGroup viewGroup, int itemType);

  protected abstract void bindItemViewHolder(RecyclerView.ViewHolder viewHolder, @NonNull T t);

  @Override
  public long getItemId(int position) {
    return position;
  }

  @Nullable
  protected T getItem(int position) {
    if (data != null) {
      if (position >= 0 && position < data.size()) {
        return data.get(position);
      }
    }
    return null;
  }

  @Override
  public int getItemCount() {
    return data != null ? data.size() : 0;
  }

  /**
   * clear adapter data
   */
  public void clear() {
    data = null;
    notifyDataSetChanged();
  }

  public void setItemClickListener(@Nullable OnItemClickListener<T> itemClickListener) {
    this.itemClickListener = itemClickListener;
  }
}
