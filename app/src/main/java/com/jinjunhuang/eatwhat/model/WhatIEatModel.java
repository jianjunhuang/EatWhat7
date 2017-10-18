package com.jinjunhuang.eatwhat.model;

import com.jinjunhuang.eatwhat.model.local.FoodLocalDataOp;
import com.jinjunhuang.eatwhat.contract.WhatIEatContract;

import java.util.List;

/**
 * @author jianjunhuang.me@foxmail.com
 *         create on 2017/10/2.
 */

public class WhatIEatModel implements WhatIEatContract.Model<FoodBean> {

    FoodLocalDataOp dao = new FoodLocalDataOp();

    @Override
    public void onDestroy() {
        dao = null;
    }

    @Override
    public List<FoodBean> loadData() {
        return dao.getIEatFoods();
    }

    @Override
    public void changeScore(float score, FoodBean bean) {

    }
}
