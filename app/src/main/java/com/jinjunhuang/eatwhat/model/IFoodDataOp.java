package com.jinjunhuang.eatwhat.model;

import com.jinjunhuang.eatwhat.model.FoodBean;

import java.util.List;
import java.util.Map;

/**
 * @author jianjunhuang.me@foxmail.com
 *         create on 2017/9/21.
 */

public interface IFoodDataOp {

    List<FoodBean> getSpecifyFoods(FilterBean bean);

    List<FoodBean> getAllFoods();

    List<FoodBean> getIEatFoods();

    boolean addFood(FoodBean food);

    boolean changeScore(FoodBean food);


}
