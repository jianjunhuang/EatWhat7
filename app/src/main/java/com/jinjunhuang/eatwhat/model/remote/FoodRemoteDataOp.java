package com.jinjunhuang.eatwhat.model.remote;

import com.jinjunhuang.eatwhat.model.FilterBean;
import com.jinjunhuang.eatwhat.model.FoodBean;
import com.jinjunhuang.eatwhat.model.IFoodDataOp;

import java.util.List;

/**
 * @author jianjunhuang.me@foxmail.com
 *         create on 2017/10/17.
 */

public class FoodRemoteDataOp implements IFoodDataOp {

    @Override
    public List<FoodBean> getSpecifyFoods(FilterBean filterBean) {
        return null;
    }

    @Override
    public List<FoodBean> getAllFoods() {
        return null;
    }

    @Override
    public List<FoodBean> getIEatFoods() {
        return null;
    }

    @Override
    public boolean addFood(FoodBean bean) {
        return false;
    }

    @Override
    public boolean changeScore(FoodBean bean) {
        return false;
    }
}
