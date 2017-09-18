package com.jinjunhuang.eatwhat.view.activity;

import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.demo.jianjunhuang.mvptools.integration.BaseActivity;
import com.demo.jianjunhuang.mvptools.utils.ToastUtils;
import com.jinjunhuang.eatwhat.R;
import com.jinjunhuang.eatwhat.view.fragment.AddFoodFragment;
import com.jinjunhuang.eatwhat.view.fragment.ImportAndExportFragment;
import com.jinjunhuang.eatwhat.view.fragment.RandomFragment;
import com.jinjunhuang.eatwhat.view.fragment.ShowAllFoodFragment;
import com.jinjunhuang.eatwhat.view.fragment.WhatIEatFragment;

/**
 * @author jianjunhuang.me@foxmail.com
 *         create on 2017/9/17.
 */

public class HomepageActivity extends BaseActivity implements Toolbar.OnMenuItemClickListener, NavigationView.OnNavigationItemSelectedListener {

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private NavigationView navigationView;

    private AddFoodFragment addFoodFragment;
    private ImportAndExportFragment importAndExportFragment;
    private RandomFragment randomFragment;
    private ShowAllFoodFragment showAllFoodFragment;
    private WhatIEatFragment whatIEatFragment;

    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;

    private boolean isDoubleClick = false;

    private Handler handler = new Handler();

    @Override
    public void onBackPressed() {
        if (isDoubleClick) {
            finish();
            return;
        }
        if (!isDoubleClick) {
            isDoubleClick = true;
            ToastUtils.show("再按一次退出");
        }
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                isDoubleClick = false;
            }
        }, 1000);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.homepage_activity;
    }

    @Override
    protected void initView() {
        initToolbar();
        initFragment();
    }

    @Override
    protected void initListener() {
        navigationView.setNavigationItemSelectedListener(this);
        toolbar.setOnMenuItemClickListener(this);
    }

    private void initFragment() {
        fragmentManager = getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();
        randomFragment = new RandomFragment();
        transaction.add(R.id.homepage_fl, randomFragment);
        transaction.commit();
    }

    private void initToolbar() {
        toolbar = findView(R.id.homepage_actionbar);
        drawerLayout = findView(R.id.homepage_dl);
        navigationView = findView(R.id.homepage_nav);
        setSupportActionBar(toolbar);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.app_name, R.string.app_name);
        toggle.syncState();
        drawerLayout.addDrawerListener(toggle);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {

        return false;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        drawerLayout.closeDrawer(Gravity.LEFT);
        return false;
    }
}
