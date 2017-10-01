package com.jinjunhuang.eatwhat.presenter;

import com.demo.jianjunhuang.mvptools.mvp.IPresenter;

import java.util.List;

/**
 * @author jianjunhuang.me@foxmail.com
 *         create on 2017/10/1.
 */

public interface IShowAllFoodPresenter<T> extends IPresenter {
    public List<T> loadData();
}
