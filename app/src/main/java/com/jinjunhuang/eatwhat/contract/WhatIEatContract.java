package com.jinjunhuang.eatwhat.contract;

import com.demo.jianjunhuang.mvptools.mvp.IModel;
import com.demo.jianjunhuang.mvptools.mvp.IView;

import java.util.List;

/**
 * @author jianjunhuang.me@foxmail.com
 *         create on 2017/10/2.
 */

public interface WhatIEatContract {

    public interface View extends IView {
        public void onChageScore();
    }

    public interface Model<T> extends IModel {
        public List<T> loadData();

        public void changeScore(float score, T t);
    }
}
