package au.com.appetiser.challenge.ui.base.interfaces;

import android.view.View;

public interface OnItemClickListener<T> {
  void onItemClick(View v, T item);
}
