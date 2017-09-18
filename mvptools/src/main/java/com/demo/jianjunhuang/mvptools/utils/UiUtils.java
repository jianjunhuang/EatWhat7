package com.demo.jianjunhuang.mvptools.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * @author jianjunhuang.me@foxmail.com
 * @since 2017/5/24.
 */

public class UiUtils {
  public static float displayDensity = 0.0F;

  /**
   * 获取分辨率
   *
   * @param context context
   * @return float density
   */
  public static float getDensity(Context context) {
    if (displayDensity == 0.0F) {
      displayDensity = getDisplayMetrics(context).density;
    }
    return displayDensity;
  }

  public static DisplayMetrics getDisplayMetrics(Context context) {
    DisplayMetrics displayMetrics = new DisplayMetrics();
    ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay()
        .getMetrics(displayMetrics);
    return displayMetrics;
  }

  /**
   * 获取屏幕高度(pixels)
   *
   * @param context context
   * @return float pixels 屏幕高度
   */
  public static float getScreenHeight(Context context) {
    return getDisplayMetrics(context).heightPixels;
  }

  /**
   * 获取屏幕宽度(pixels)
   *
   * @param context context
   * @return float pixels 屏幕宽度
   */
  public static float getScreenWitdth(Context context) {
    return getDisplayMetrics(context).widthPixels;
  }

  /**
   * dp to pixel
   *
   * @param context context
   * @param dp dp
   * @return pixel
   * @see <a href="https://developer.android.com/guide/practices/screens_support.html?hl=zh-cn">android
   * doc</a>
   */
  public static float dpToPx(Context context, float dp) {
    return dp * (getDisplayMetrics(context).densityDpi / 160F);
  }

  /**
   * px to dp
   *
   * @param context context
   * @param px px
   * @return dp
   * @see <a href="https://developer.android.com/guide/practices/screens_support.html?hl=zh-cn">android
   * doc</a>
   */
  public static float pxToDp(Context context, float px) {
    return px / (getDisplayMetrics(context).densityDpi / 160F);
  }

  /**
   * 通过反射获取 id 来获取状态栏高度（固定值）
   *
   * @param context context
   * @return 状态栏高度
   */
  public static int getStatusBarHeight(Context context) {
    Class<?> c = null;
    Object obj = null;
    java.lang.reflect.Field field = null;
    int x = 0;
    try {
      c = Class.forName("com.android.internal.R$dimen");
      obj = c.newInstance();
      field = c.getField("status_bar_height");
      x = Integer.parseInt(field.get(obj).toString());
      return context.getResources().getDimensionPixelSize(x);
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    } catch (InstantiationException e) {
      e.printStackTrace();
    } catch (NoSuchFieldException e) {
      e.printStackTrace();
    }
    return 0;
  }
}
