package com.jinjunhuang.eatwhat.model;

import com.jinjunhuang.eatwhat.common.db.dao.FoodDbDao;
import com.jinjunhuang.eatwhat.contract.ShowAllFoodContract;

import java.util.List;

/**
 * @author jianjunhuang.me@foxmail.com
 *         create on 2017/10/1.
 */

public class ShowAllFoodModel implements ShowAllFoodContract.Model<FoodBean> {

    private FoodDbDao dao = new FoodDbDao();

    @Override
    public void onDestroy() {
        dao = null;
    }

    @Override
    public List<FoodBean> loadData() {
        return dao.getAllFoods();
    }
}
