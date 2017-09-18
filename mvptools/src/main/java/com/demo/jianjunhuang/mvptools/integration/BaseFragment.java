package com.demo.jianjunhuang.mvptools.integration;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.demo.jianjunhuang.mvptools.utils.ToastUtils;

/**
 * @author jianjunhuang.me@foxmail.com
 * @since 2017/5/24.
 */

public abstract class BaseFragment extends Fragment {
    private View view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("fragment", getClass().getSimpleName() + " onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Log.i("fragment", getClass().getSimpleName() + " onCreateView");
        view = inflater.inflate(getLayoutId(), container, false);
        initView(view);
        initListener();
        return view;
    }

    protected abstract int getLayoutId();

    protected abstract void initView(View view);

    protected abstract void initListener();

    protected <T extends View> T findView(int id) {
        View v = view.findViewById(id);
        return (T) v;
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
        ToastUtils.show(msg, Toast.LENGTH_LONG);
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
     * 跳转 带参数
     *
     * @param clz    activity class
     * @param bundle data
     */
    public void startActivity(Class<?> clz, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(getActivity(), clz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    /**
     * 跳转 不带参数
     *
     * @param clz activity class
     */
    public void startActivity(Class<?> clz) {
        startActivity(clz, null);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (!isVisibleToUser) {
            //JAnalyticsInterface.onPageStart(getActivity(), getClass().getCanonicalName());
            //Log.i("fragment",getClass().getCanonicalName());
        } else {
            //Log.i("fragment",getClass().getCanonicalName());
            //JAnalyticsInterface.onPageStart(getActivity(), getClass().getCanonicalName());
        }
    }
}
