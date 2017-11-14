package com.jinjunhuang.eatwhat.model;

import com.jinjunhuang.eatwhat.model.bean.FilterBean;
import com.jinjunhuang.eatwhat.model.bean.FoodBean;
import com.jinjunhuang.eatwhat.model.local.FoodLocalDataOp;
import com.jinjunhuang.eatwhat.model.remote.FoodRemoteDataOp;

import java.util.List;

/**
 * @author jianjunhuang.me@foxmail.com
 *         create on 2017/10/17.
 */

public class DataCenter implements IFoodDataOp{

    private FoodLocalDataOp localDataOp;
    private FoodRemoteDataOp remoteDataOp;


    @Override
    public void getSpecifyFoods(FilterBean bean, LoadFoodCallback callback) {

    }

    @Override
    public void getAllFoods(LoadFoodCallback callback) {

    }

    @Override
    public void getIEatFoods(LoadFoodCallback callback) {

    }

    @Override
    public void addFood(FoodBean food) {

    }

    @Override
    public void changeScore(FoodBean food) {

    }
}
