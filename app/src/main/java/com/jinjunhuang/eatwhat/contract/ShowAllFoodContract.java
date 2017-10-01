package com.jinjunhuang.eatwhat.contract;

import com.demo.jianjunhuang.mvptools.mvp.IModel;
import com.demo.jianjunhuang.mvptools.mvp.IView;

import java.util.List;

/**
 * @author jianjunhuang.me@foxmail.com
 *         create on 2017/10/1.
 */

public interface ShowAllFoodContract {

    public interface View extends IView {
//        public void loadSuccess();
//
//        public void loadFailed();
    }

    public interface Model<T> extends IModel {
        public List<T> loadData();
    }

}
