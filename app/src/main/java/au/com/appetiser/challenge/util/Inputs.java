package au.com.appetiser.challenge.util;

import android.text.TextUtils;
import androidx.annotation.Nullable;

/**
 * Input Helper to validate stuff related to input fields.
 */
public final class Inputs {

  public static boolean isEmpty(@Nullable String text) {
    return text == null || TextUtils.isEmpty(text) || isWhiteSpaces(text) || text.equalsIgnoreCase("null");
  }

  private static boolean isWhiteSpaces(@Nullable String s) {
    return s != null && s.matches("\\s+");
  }

}
