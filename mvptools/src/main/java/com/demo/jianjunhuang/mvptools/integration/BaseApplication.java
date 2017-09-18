package com.demo.jianjunhuang.mvptools.integration;

import android.app.Application;

import com.demo.jianjunhuang.mvptools.utils.NetworkUtils;
import com.demo.jianjunhuang.mvptools.utils.SPUtils;
import com.demo.jianjunhuang.mvptools.utils.ToastUtils;

/**
 * @author jianjunhuang.me@foxmail.com
 * @since 2017/5/24.
 */

public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }

    protected void init() {
        SPUtils.init(this);
        ToastUtils.init(this);
        NetworkUtils.init(this);
    }
}
