package com.jinjunhuang.eatwhat.common.application;

import com.demo.jianjunhuang.mvptools.integration.BaseApplication;
import com.jinjunhuang.eatwhat.model.local.FoodDbOpenHelper;

/**
 * @author jianjunhuang.me@foxmail.com
 *         create on 2017/9/17.
 */

public class EatWhatApplication extends BaseApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        FoodDbOpenHelper.init(this);
    }
}
