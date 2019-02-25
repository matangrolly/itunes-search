package au.com.appetiser.challenge.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;
import androidx.databinding.BindingAdapter;
import au.com.appetiser.challenge.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.FutureTarget;
import com.bumptech.glide.request.RequestOptions;
import com.duyp.androidutils.rx.functions.PlainConsumer;
import io.reactivex.Single;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

public class Bindings {
  @BindingAdapter({"imageUrl"})
  public static void setImageUrl(ImageView imageView, String imageUrl) {
    if (Inputs.isEmpty(imageUrl)) {
      imageView.setImageResource(R.drawable.placeholder);
      return;
    }

    Glide
      .with(imageView.getContext())
      .load(imageUrl)
      .apply(
        new RequestOptions()
          .placeholder(R.drawable.placeholder)
          .error(R.drawable.placeholder)
      )
      .into(imageView);
  }

  @SuppressLint("CheckResult")
  @BindingAdapter({"thumbUrl", "largeUrl"})
  public static void setImageUrl(ImageView imageView, String thumbUrl, String largeUrl) {
    final Context context = imageView.getContext();

    setImageUrl(imageView, thumbUrl);
    Single
      .create((SingleOnSubscribe<Bitmap>) emitter -> {
        FutureTarget<Bitmap> futureTarget = Glide
          .with(context)
          .asBitmap()
          .load(largeUrl)
          .submit();
        try {
          Bitmap bitmap = futureTarget.get();
          emitter.onSuccess(bitmap);
        } catch (Exception e) {
          emitter.onError(e);
        }
      })
      .subscribeOn(Schedulers.io())
      .observeOn(AndroidSchedulers.mainThread())
      .subscribe((PlainConsumer<Bitmap>) bitmap -> {
        if (bitmap != null) {
          imageView.setImageBitmap(bitmap);
        }
      }, (PlainConsumer<Throwable>) Timber::e);
  }
}