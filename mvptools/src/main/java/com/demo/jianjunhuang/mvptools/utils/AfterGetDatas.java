package com.demo.jianjunhuang.mvptools.utils;

import java.util.List;

/**
 *
 * 联网获取数据后进行回调
 *
 * @author jianjunhuang.me@foxmail.com
 *         create on 2017/7/18.
 */

public interface AfterGetDatas<T> {
    void onErr(String reason, int errCode);
    void onSuccess(List<T> data);
}
