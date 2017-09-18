package com.demo.jianjunhuang.mvptools.adapter;

/**
 * @author jianjunhuang.me@foxmail.com
 *         create on 2017/7/10.
 */

public interface MultiItemTypeSupport<T> {
    int getLayoutId(int itemType);

    int getItemViewType(int position, T t);
}
