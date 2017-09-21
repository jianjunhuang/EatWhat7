package com.jinjunhuang.eatwhat.common.db.dao;

import com.jinjunhuang.eatwhat.model.FoodBean;

import java.util.List;
import java.util.Map;

/**
 * @author jianjunhuang.me@foxmail.com
 *         create on 2017/9/21.
 */

public interface IFoodDbDao<T,E> {

    public List<T> getSpecifyFoods(E e);

    public List<T> getAllFoods();

    public List<T> getIEatFoods();

    public boolean addFood(T t);

}
