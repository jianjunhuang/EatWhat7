package com.demo.jianjunhuang.mvptools.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * @author jianjunhuang.me@foxmail.com
 * @since 2017/5/24.
 * <p>
 * 对 sharedpreferd 的简单封装
 * - 不要在存储大的 key 和 value
 * - 不相关的文件分开存放，文件越大，读取越慢
 * - 操作多的时候尽量一次性 apply
 */

public class SPUtils {

    private Context context;
    // 默认是应用名
    private static String fileName;
    private SharedPreferences sp;
    private static SPUtils spUtils;

    private SPUtils(Context context) {
        this.context = context;
        fileName = context.getString(context.getApplicationInfo().labelRes);
    }

    /**
     * 初始化
     * @param context Application's context
     * @return StoreInfo's instance
     */
    public static SPUtils init(Context context) {
        if (spUtils == null) {
            synchronized (SPUtils.class) {
                if (spUtils == null) {
                    spUtils = new SPUtils(context);
                }
            }
        }
        return spUtils;
    }

    /**
     * 获取实例
     *
     * @param fileName if fileName is null , fileName = "app name"
     * @return StoreInfo's instance
     */
    public static SPUtils instance(String fileName) {
        if (spUtils == null) {
            new RuntimeException("did'nt init before use");
        }
        if (fileName != null) {
            SPUtils.fileName = fileName;
        }
        return spUtils;
    }

    /**
     * 内部存储方法
     *
     * @param key   store key
     * @param value store content
     */
    private void storeInside(String key, Object value) {
        sp = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        if (value instanceof Integer) {
            editor.putInt(key, (Integer) value);
        } else if (value instanceof Long) {
            editor.putLong(key, (Long) value);
        } else if (value instanceof Boolean) {
            editor.putBoolean(key, (Boolean) value);
        } else if (value instanceof Float) {
            editor.putFloat(key, (Float) value);
        } else if (value instanceof String) {
            editor.putString(key, (String) value);
        }
        editor.apply();
    }

    /**
     * @param key   store key
     * @param value use to replace store content if it is null
     * @return store content
     */
    private Object getInfoInside(String key, Object value) {
        sp = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        if (value instanceof Integer) {
            return sp.getInt(key, (Integer) value);
        } else if (value instanceof Long) {
            return sp.getLong(key, (Long) value);
        } else if (value instanceof Boolean) {
            return sp.getBoolean(key, (Boolean) value);
        } else if (value instanceof Float) {
            return sp.getFloat(key, (Float) value);
        } else if (value instanceof String) {
            return sp.getString(key, (String) value);
        } else {
            return null;
        }
    }

    /**
     * @param key   store key
     * @param value store content
     */
    public void store(String key, String value) {
        storeInside(key, value);
    }

    /**
     * @param key   store key
     * @param value store content
     */
    public void store(String key, int value) {
        storeInside(key, value);
    }

    /**
     * @param key   store key
     * @param value store content
     */
    public void store(String key, boolean value) {
        storeInside(key, value);
    }

    /**
     * @param key   store key
     * @param value store content
     */
    public void store(String key, float value) {
        storeInside(key, value);
    }

    /**
     * @param key   store key
     * @param value store content
     */
    public void store(String key, long value) {
        storeInside(key, value);
    }

    /**
     * @param key         store key
     * @param defaultBack use to replace store content if it is null
     * @return store content
     */
    public String getInfo(String key, String defaultBack) {
        return (String) getInfoInside(key, defaultBack);
    }

    /**
     * @param key         store key
     * @param defaultBack use to replace store content if it is null
     * @return store content
     */
    public int getInfo(String key, int defaultBack) {
        return (Integer) getInfoInside(key, defaultBack);
    }

    /**
     * @param key         store key
     * @param defaultBack use to replace store content if it is null
     * @return store content
     */
    public float getInfo(String key, float defaultBack) {
        return (Float) getInfoInside(key, defaultBack);
    }

    /**
     * @param key         store key
     * @param defaultBack use to replace store content if it is null
     * @return store content
     */
    public boolean getInfo(String key, boolean defaultBack) {
        return (Boolean) getInfoInside(key, defaultBack);
    }

    /**
     * @param key         store key
     * @param defaultBack use to replace store content if it is null
     * @return store content
     */
    public long getInfo(String key, long defaultBack) {
        return (Long) getInfoInside(key, defaultBack);
    }

    public void clearAll() {
        sp = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        sp.edit().clear().apply();
    }

    public void clear(String name) {
        sp = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        sp.edit().remove(name).apply();
    }
}
