package com.jinjunhuang.eatwhat.model.remote;

import com.jinjunhuang.eatwhat.model.bean.FilterBean;
import com.jinjunhuang.eatwhat.model.bean.FoodBean;
import com.jinjunhuang.eatwhat.model.IFoodDataOp;

import java.util.List;

/**
 * @author jianjunhuang.me@foxmail.com
 *         create on 2017/10/17.
 *         只同步数据库和上传
 */

public class FoodRemoteDataOp implements IFoodDataOp {

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
