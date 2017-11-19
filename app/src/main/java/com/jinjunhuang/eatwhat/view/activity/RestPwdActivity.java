package com.jinjunhuang.eatwhat.view.activity;

import android.support.v7.widget.Toolbar;
import android.view.View;

import com.demo.jianjunhuang.mvptools.integration.BaseActivity;
import com.jinjunhuang.eatwhat.R;

/**
 * @author jianjunhuang.me@foxmail.com
 *         create on 2017/11/19.
 */

public class RestPwdActivity extends BaseActivity {

    private Toolbar mToolbar;

    @Override
    protected int getLayoutId() {
        return R.layout.rest_pwd_activity;
    }

    @Override
    protected void initView() {
        initToolbar();
    }

    private void initToolbar() {
        mToolbar = findView(R.id.rest_toolbar);
        setSupportActionBar(mToolbar);
    }

    @Override
    protected void initListener() {
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
