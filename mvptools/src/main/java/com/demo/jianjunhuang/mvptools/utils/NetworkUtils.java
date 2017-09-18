package com.demo.jianjunhuang.mvptools.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * @author jianjunhuang.me@foxmail.com
 *         create on 2017/7/8.
 */

public class NetworkUtils {
    private static NetworkUtils networkUtils;
    private static Context context;

    private NetworkUtils() {

    }

    public static void init(Context context) {
        NetworkUtils.context = context;
    }

    private static void check() {
        if (context == null) {
            throw new RuntimeException("you need to init in application first !");
        }
    }

    /**
     * 获取网络状态，若没网则为 -1
     *
     * @return ConnectivityManager.TYPE_XXX
     */
    public static int getNetworkStatus() {
        check();
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        if (networkInfo == null) {
            return -1;
        }
        return networkInfo.getType();
    }

    /**
     * 检查是否连接
     *
     * @return 是否连接
     */
    public static boolean isConnected() {
        check();
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        if (networkInfo != null) {
            return networkInfo.isConnected();
        }
        return false;
    }

    public static void setOnNetworkStatus(OnNetworkStatus onNetworkStatus) {
        if (isConnected()) {
            int tag = getNetworkStatus();
            switch (tag) {
                case ConnectivityManager.TYPE_WIFI: {
                    onNetworkStatus.onWifiStatus();
                    break;
                }
                case ConnectivityManager.TYPE_MOBILE: {
                    onNetworkStatus.onMobileStatus();
                    break;
                }
                case ConnectivityManager.TYPE_MOBILE_DUN: {

                    break;
                }
                case ConnectivityManager.TYPE_VPN: {

                    break;
                }
                case ConnectivityManager.TYPE_WIMAX: {

                    break;
                }
                default: {
                    onNetworkStatus.onNoConnectiveStatus();
                }
            }
        } else {
            onNetworkStatus.onNoConnectiveStatus();
        }
    }

    public interface OnNetworkStatus {
        void onWifiStatus();

        void onMobileStatus();

        void onNoConnectiveStatus();
    }
}
