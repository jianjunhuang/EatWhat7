package com.demo.jianjunhuang.mvptools.utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author jianjunhuang.me@foxmail.com
 * @since 2017/5/24.
 * <p>
 * 对 Toast 简单封装,确保显示 toast 的时候没有延迟
 * 在 Application 中初始化
 */

public class ToastUtils {

    private static final String TAG = "ToastUtils";

    private static Context context;

    private static volatile Toast toast = null;

    private ToastUtils toastUtils = null;

    private static View view;

    private ToastUtils(Context context) {

    }

    /**
     * 为了避免内存泄漏，尽量在 Application 中初始化
     *
     * @param context application context
     */
    public static void init(@NonNull Context context) {
        if (toast == null) {
            synchronized (Toast.class) {
                if (toast == null) {
                    ToastUtils.context = context;
                    toast = Toast.makeText(context, "", Toast.LENGTH_SHORT);
                }
            }
        }
    }

    public static void init(@NonNull Context context, int layoutId) {
        if (toast == null) {
            synchronized (Toast.class) {
                if (toast == null) {
                    ToastUtils.context = context;
                    toast = new Toast(context);
                    LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    view = inflater.inflate(layoutId, null);
                    toast.setView(view);
                }
            }
        }
    }


    /**
     * 显示自定义 toast
     *
     * @param tvId     textview id
     * @param msg      message
     * @param duration Toast.SHORT or Toast.LONG
     */
    public static void show(int tvId, String msg, int duration) {
        check();
        if (view != null) {
            TextView tv = (TextView) view.findViewById(tvId);
            tv.setText(msg);
            toast.show();
        }
    }

    /**
     * 显示自定义 toast
     *
     * @param tvId textview id
     * @param msg  message
     */
    public static void show(int tvId, String msg) {
        show(tvId, msg, Toast.LENGTH_SHORT);
    }

    /**
     * 显示自定义 toast
     *
     * @param tvId     textview id
     * @param msgId    message id
     * @param duration Toast.SHORT or Toast.LONG
     */
    public static void show(int tvId, int msgId, int duration) {
        show(tvId, context.getString(msgId), duration);
    }

    private static void check() {
        if (toast == null) {
            try {
                throw new RuntimeException("you did'd init ToastUtils !!!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 显示默认 toast
     *
     * @param msg message
     */
    public static void show(String msg) {
        show(msg, Toast.LENGTH_SHORT);
    }

    /**
     * 显示默认 toast
     *
     * @param msgId message id
     */
    public static void show(int msgId) {
        show(msgId, Toast.LENGTH_SHORT);
    }

    /**
     * 显示默认 toast
     *
     * @param msgId    message id
     * @param duration Toast.SHORT or Toast.LONG
     */
    public static void show(int msgId, int duration) {
        show(context.getString(msgId), duration);
    }

    /**
     * 显示默认 toast
     *
     * @param msg      message
     * @param duration Toast.SHORT or Toast.LONG
     */
    public static void show(String msg, int duration) {
        check();
        if (view == null) {
            toast.setText(msg);
            toast.setDuration(duration);
        } else {
            Log.e(TAG, "show: this method is for default toast !");
        }
        toast.show();
    }
}
