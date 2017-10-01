package com.jinjunhuang.eatwhat.presenter.impl;

import com.jinjunhuang.eatwhat.contract.ShowAllFoodContract;
import com.jinjunhuang.eatwhat.model.FoodBean;
import com.jinjunhuang.eatwhat.model.ShowAllFoodModel;
import com.jinjunhuang.eatwhat.presenter.IShowAllFoodPresenter;

import java.util.List;

/**
 * @author jianjunhuang.me@foxmail.com
 *         create on 2017/10/1.
 */

public class ShowAllFoodPresenter implements IShowAllFoodPresenter<FoodBean> {


    private ShowAllFoodContract.View mView;
    private ShowAllFoodContract.Model mModel;

    public ShowAllFoodPresenter(ShowAllFoodContract.View mView) {
        this.mView = mView;
    }

    @Override
    public void onStart() {
        mModel = new ShowAllFoodModel();
    }

    @Override
    public void onDestroy() {
        mView = null;
        mModel = null;
    }

    @Override
    public List<FoodBean> loadData() {
        return mModel.loadData();
    }
}
