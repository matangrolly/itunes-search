package au.com.appetiser.challenge.ui.base.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public abstract class BaseViewHolder<T> extends RecyclerView.ViewHolder {

  public static View getView(@NonNull ViewGroup parent, @LayoutRes int layoutRes) {
    return LayoutInflater.from(parent.getContext()).inflate(layoutRes, parent, false);
  }

  protected BaseViewHolder(@NonNull View itemView) {
    super(itemView);
  }

  public abstract void bind(@NonNull T t);
}
