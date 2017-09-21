package com.jinjunhuang.eatwhat.view.fragment;

import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.demo.jianjunhuang.mvptools.integration.BaseFragment;
import com.demo.jianjunhuang.mvptools.utils.ToastUtils;
import com.jinjunhuang.eatwhat.R;
import com.jinjunhuang.eatwhat.common.weidge.AnimCircleView;

/**
 * @author jianjunhuang.me@foxmail.com
 *         create on 2017/9/17.
 */

public class RandomFragment extends BaseFragment implements View.OnClickListener {

    private AnimCircleView animCircleView;

    private CheckBox canteenOneCb;
    private CheckBox canteenTwoCb;
    private CheckBox canteenThreeCb;
    private CheckBox canteenFourCb;

    private EditText priceStartEdt;
    private EditText priceEndEdt;

    private CheckBox richCb;
    private CheckBox noondleCb;
    private CheckBox porridgeCb;

    private TextView randomTv;

    @Override
    protected int getLayoutId() {
        return R.layout.random_fragment;
    }

    @Override
    protected void initView(View view) {
        animCircleView = findView(R.id.random_fragment_anim_view);
        canteenOneCb = findView(R.id.random_fragment_canteen_one_cb);
        canteenTwoCb = findView(R.id.random_fragment_canteen_two_cb);
        canteenThreeCb = findView(R.id.random_fragment_canteen_three_cb);
        canteenFourCb = findView(R.id.random_fragment_canteen_four_cb);

        priceStartEdt = findView(R.id.random_fragment_price_start_cb);
        priceEndEdt = findView(R.id.random_fragment_price_end_cb);

        richCb = findView(R.id.random_fragment_kind_rich_cb);
        noondleCb = findView(R.id.random_fragment_kind_noodle_cb);
        porridgeCb = findView(R.id.random_fragment_kind_porridge_cb);

        randomTv = findView(R.id.random_fragment_tv);
    }

    @Override
    protected void initListener() {
        animCircleView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.random_fragment_anim_view) {
            ToastUtils.show("click");
        }
    }



}
