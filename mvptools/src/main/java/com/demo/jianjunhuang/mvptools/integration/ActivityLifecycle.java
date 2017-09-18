package com.demo.jianjunhuang.mvptools.integration;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

/**
 * @author jianjunhuang.me@foxmail.com
 * @since 2017/5/24.
 *
 * 做一些全局的注入操作
 * destroyed 的操作也在这里调用？
 *
 */

public class ActivityLifecycle implements Application.ActivityLifecycleCallbacks {

  private IActivityLifecycle iActivityLifecycle = null;

  public void setIActivityLifecycle(IActivityLifecycle iActivityLifecycle) {
    this.iActivityLifecycle = iActivityLifecycle;
  }

  @Override public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
    iActivityLifecycle.onActivityCreated(activity, savedInstanceState);
  }

  @Override public void onActivityStarted(Activity activity) {
    iActivityLifecycle.onActivityStarted(activity);
  }

  @Override public void onActivityResumed(Activity activity) {
    iActivityLifecycle.onActivityResumed(activity);
  }

  @Override public void onActivityPaused(Activity activity) {
    iActivityLifecycle.onActivityPaused(activity);
  }

  @Override public void onActivityStopped(Activity activity) {
    iActivityLifecycle.onActivityStopped(activity);
  }

  @Override public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
    iActivityLifecycle.onActivitySaveInstanceState(activity, outState);
  }

  @Override public void onActivityDestroyed(Activity activity) {
    iActivityLifecycle.onActivityDestroyed(activity);
  }
}
