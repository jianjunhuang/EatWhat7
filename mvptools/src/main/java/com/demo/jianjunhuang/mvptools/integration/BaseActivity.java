package com.demo.jianjunhuang.mvptools.integration;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.demo.jianjunhuang.mvptools.utils.ToastUtils;

/**
 * @author jianjunhuang.me@foxmail.com
 * @since 2017/5/24.
 * <p>
 * 1. 注入 ActivityLifecycle
 * 2. findView
 * 3.
 */

public abstract class BaseActivity extends AppCompatActivity {
    /**
     * 沉浸式标签 默认开启
     */
    private boolean isSetStatusBar = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (isSetStatusBar) {
            steepStatusBar();
        }
        setContentView(getLayoutId());
        initView();
        initListener();
    }

    protected abstract int getLayoutId();

    /**
     * 初始化 ui
     */
    protected abstract void initView();

    /**
     * 初始化 listener
     */
    protected abstract void initListener();

    /**
     * 开启沉浸式
     */
    private void steepStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }

    /**
     * 设置沉浸式（默认开启）
     *
     * @param isSetStatusBar 默认 false
     */
    protected void setSteepStatuBar(boolean isSetStatusBar) {
        this.isSetStatusBar = isSetStatusBar;
    }

    /**
     * 实例化 view ，不用再强制转换
     *
     * @param id  view's id
     * @param <T> view
     * @return view
     */
    protected <T extends View> T findView(int id) {
        View view = findViewById(id);
        return (T) view;
    }

    public static void showShort(String msg) {
        ToastUtils.show(msg);
    }

    public static void showLong(String msg) {
        ToastUtils.show(msg, Toast.LENGTH_LONG);
    }

    public static void showShort(int msg) {
        ToastUtils.show(msg);
    }

    public static void showLong(int msg) {
        ToastUtils.show(msg,Toast.LENGTH_LONG);
    }

    /**
     * 跳转 带参数
     *
     * @param clz    activity class
     * @param bundle data
     */
    public void startActivity(Class<?> clz, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(this, clz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    /**
     * 字符串是否为空
     *
     * @param str string
     * @return true - str is empty or null
     */
    public boolean isEmptyOrNull(String str) {
        return str == null || str.equals("");
    }

    /**
     * 跳转 不带参数
     *
     * @param clz activity class
     */
    public void startActivity(Class<?> clz) {
        startActivity(clz, null);
    }

}
