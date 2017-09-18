package com.demo.jianjunhuang.mvptools.integration;

import android.app.Activity;
import android.os.Bundle;

/**
 * @author jianjunhuang.me@foxmail.com
 * @since 2017/5/24.
 *
 * 用于扩展 ActivityLifecycle
 *
 */

public interface IActivityLifecycle {
  void onActivityCreated(Activity activity, Bundle savedInstanceState);

  void onActivityStarted(Activity activity);

  void onActivityResumed(Activity activity);

  void onActivityPaused(Activity activity);

  void onActivityStopped(Activity activity);

  void onActivitySaveInstanceState(Activity activity, Bundle outState);

  void onActivityDestroyed(Activity activity);
}
