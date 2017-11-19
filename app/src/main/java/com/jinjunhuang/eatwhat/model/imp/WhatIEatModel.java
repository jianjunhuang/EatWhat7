package com.jinjunhuang.eatwhat.model.imp;

import com.jinjunhuang.eatwhat.model.bean.FoodBean;
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
        return null;
    }

    @Override
    public void changeScore(float score, FoodBean bean) {

    }
}
