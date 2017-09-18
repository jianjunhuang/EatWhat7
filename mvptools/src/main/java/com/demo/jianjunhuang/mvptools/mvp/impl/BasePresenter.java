package com.demo.jianjunhuang.mvptools.mvp.impl;

import com.demo.jianjunhuang.mvptools.mvp.IModel;
import com.demo.jianjunhuang.mvptools.mvp.IPresenter;
import com.demo.jianjunhuang.mvptools.mvp.IView;

/**
 * @author jianjunhuang.me@foxmail.com
 * @since 2017/5/24.
 */

public class BasePresenter<M extends IModel, V extends IView> implements IPresenter {

  protected M mModel;
  protected V mView;

  public BasePresenter(M mModel, V mView) {
    this.mModel = mModel;
    this.mView = mView;
    onStart();
  }

  public BasePresenter(V mView) {
    this.mView = mView;
    onStart();
  }

  public BasePresenter() {
    onStart();
  }

  @Override public void onStart() {

  }

  @Override public void onDestroy() {
    if (mModel != null) {
      mModel.onDestroy();
    }
    this.mModel = null;
    this.mView = null;
  }
}
