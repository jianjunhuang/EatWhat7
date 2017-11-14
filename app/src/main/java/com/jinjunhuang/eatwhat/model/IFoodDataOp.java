package com.jinjunhuang.eatwhat.model;

import com.jinjunhuang.eatwhat.model.bean.FilterBean;
import com.jinjunhuang.eatwhat.model.bean.FoodBean;

import java.util.List;

/**
 * @author jianjunhuang.me@foxmail.com
 *         create on 2017/9/21.
 */

public interface IFoodDataOp {

    interface LoadFoodCallback {
        void onFoodLoaded(List<FoodBean> food);

        void onDataNotAvilable();
    }

    interface GetFoodCallback {
        void onFoodLoaded(FoodBean foodBean);

        void onDataNotAvilable();
    }

    void getSpecifyFoods(FilterBean bean, LoadFoodCallback callback);

    void getAllFoods(LoadFoodCallback callback);

    void getIEatFoods(LoadFoodCallback callback);

    void addFood(FoodBean food);

    void changeScore(FoodBean food);


}
