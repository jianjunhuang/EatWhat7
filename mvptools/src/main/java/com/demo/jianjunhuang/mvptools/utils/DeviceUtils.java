package com.demo.jianjunhuang.mvptools.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Environment;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * @author jianjunhuang.me@foxmail.com
 * @since 2017/5/24.
 */

public class DeviceUtils {
  private static final String TAG = "DeviceUtils";

  /**
   * 隐藏软键盘
   *
   * @param context context
   * @param view view
   */
  public static void hideSoftKeyboard(Context context, View view) {
    if (view == null) {
      Log.e(TAG, "hideSoftKeyboard: view is null !!");
      return;
    }
    InputMethodManager inputMethodManager =
        (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
    if (inputMethodManager.isActive()) {
      inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
  }

  /**
   * SD 卡判断
   *
   * @return 是否有 SD 卡
   */
  public static boolean isSDCardAvailable() {
    return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
  }

  /**
   * 获取版本名称
   *
   * @param context context
   * @return version name
   */
  public static String getVersionName(Context context) {
    String versionName = "";
    try {
      PackageManager packageManager = context.getPackageManager();
      PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
      versionName = packageInfo.versionName;
    } catch (NameNotFoundException e) {
      e.printStackTrace();
    }
    return versionName;
  }

  /**
   * 获取版本号
   *
   * @param context context
   * @return version code
   */
  public static String getVersionCode(Context context) {
    String versionCode = "";
    try {
      PackageManager packageManager = context.getPackageManager();
      PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
      versionCode = String.valueOf(packageInfo.versionCode);
    } catch (NameNotFoundException e) {
      e.printStackTrace();
    }
    return versionCode;
  }

  /**
   * 获取设备号
   *
   * @param context context
   * @return device id
   */
  public static String getDeviceId(Context context) {
    TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
    @SuppressLint("HardwareIds") String deviceId = tm.getDeviceId();
    if (deviceId == null) {
      deviceId = "";
    }
    return deviceId;
  }

  /**
   * 品牌
   *
   * @return 手机品牌
   */
  public static String getBrand() {
    return Build.BRAND;
  }

  /**
   * 手机型号
   */
  public static String getPhoneModel() {
    return Build.MODEL;
  }

  /**
   * Android api
   *
   * @return api
   */
  public static int getBuildLevel() {
    return VERSION.SDK_INT;
  }

  /**
   * Android version
   *
   * @return android version
   */
  public static String getBuildVersion() {
    return VERSION.RELEASE;
  }
}
