package com.jinjunhuang.eatwhat.model;

import com.jinjunhuang.eatwhat.model.bean.FoodBean;
import com.jinjunhuang.eatwhat.model.local.FoodLocalDataOp;
import com.jinjunhuang.eatwhat.contract.ShowAllFoodContract;

import java.util.List;

/**
 * @author jianjunhuang.me@foxmail.com
 *         create on 2017/10/1.
 */

public class ShowAllFoodModel implements ShowAllFoodContract.Model<FoodBean> {

    private FoodLocalDataOp dao = new FoodLocalDataOp();

    @Override
    public void onDestroy() {
        dao = null;
    }

    @Override
    public List<FoodBean> loadData() {
        return null;
    }
}
