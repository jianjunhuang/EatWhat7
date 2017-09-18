package com.jinjunhuang.eatwhat.view.fragment;

import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.view.animation.AnticipateOvershootInterpolator;
import android.widget.PopupWindow;

import com.demo.jianjunhuang.mvptools.integration.BaseFragment;
import com.jinjunhuang.eatwhat.R;

/**
 * @author jianjunhuang.me@foxmail.com
 *         create on 2017/9/17.
 */

public class RandomFragment extends BaseFragment implements View.OnClickListener {

    private FloatingActionButton randomFab;

    private FloatingActionButton cantentFab;
    private FloatingActionButton priceFab;
    private FloatingActionButton kindFab;

    private boolean isExpand = false;

    @Override
    protected int getLayoutId() {
        return R.layout.random_fragment;
    }

    @Override
    protected void initView(View view) {
        randomFab = findView(R.id.random_filter_fab);
        cantentFab = findView(R.id.random_filter_cantent_fab);
        priceFab = findView(R.id.random_filter_price_fab);
        kindFab = findView(R.id.random_filter_kind_fab);
    }

    @Override
    protected void initListener() {
        randomFab.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.random_filter_fab: {
                int mainWidth = randomFab.getWidth();
                int width = cantentFab.getWidth();
                int y = -(mainWidth + width);
                if (!isExpand) {
                    isExpand = true;
                } else {
                    isExpand = false;
                    y = -y;
                }
                cantentFab.animate().translationY(y).setInterpolator(new AnticipateOvershootInterpolator()).setDuration(1000).start();
                priceFab.animate().translationY(2 * y).setInterpolator(new AnticipateOvershootInterpolator()).setDuration(1000).start();
                kindFab.animate().translationY(3 * y).setInterpolator(new AnticipateOvershootInterpolator()).setDuration(1000).start();
                break;
            }
            case R.id.random_filter_cantent_fab: {
                PopupWindow popupWindow = new PopupWindow(getActivity());
                break;
            }
            case R.id.random_filter_price_fab: {

                break;
            }
            case R.id.random_filter_kind_fab: {

                break;
            }
        }
    }
}
