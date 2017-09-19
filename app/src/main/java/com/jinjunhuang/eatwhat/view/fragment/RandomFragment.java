package com.jinjunhuang.eatwhat.view.fragment;

import android.graphics.Rect;
import android.support.design.widget.FloatingActionButton;
import android.view.TouchDelegate;
import android.view.View;
import android.view.animation.AnticipateOvershootInterpolator;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.demo.jianjunhuang.mvptools.integration.BaseFragment;
import com.demo.jianjunhuang.mvptools.utils.ToastUtils;
import com.jinjunhuang.eatwhat.R;
import com.jinjunhuang.eatwhat.weidge.AnimCircleView;

/**
 * @author jianjunhuang.me@foxmail.com
 *         create on 2017/9/17.
 */

public class RandomFragment extends BaseFragment implements View.OnClickListener {

    private AnimCircleView animCircleView;

    private CheckBox canteenOneCb;
    private CheckBox canteenTwoCb;
    private CheckBox canteenThreeCb;
    private CheckBox canteenThourCb;

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
    }

    @Override
    protected void initListener() {
        animCircleView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.show("Click");
            }
        });
    }

    @Override
    public void onClick(View v) {

    }
}
