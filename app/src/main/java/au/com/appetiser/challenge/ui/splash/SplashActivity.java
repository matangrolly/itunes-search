package au.com.appetiser.challenge.ui.splash;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import au.com.appetiser.challenge.di.Injectable;
import au.com.appetiser.challenge.ui.main.MainActivity;

public class SplashActivity extends AppCompatActivity implements Injectable {

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    startActivity(new Intent(this, MainActivity.class));

    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    finish();
  }
}