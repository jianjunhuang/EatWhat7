package com.demo.jianjunhuang.mvptools.utils;

/**
 * @author jianjunhuang.me@foxmail.com
 *         create on 2017/7/18.
 */

public interface AfterGetData<T> {
    void onDataErr(String reason, int errCode);

    void onDataSuccess(T data);
}
