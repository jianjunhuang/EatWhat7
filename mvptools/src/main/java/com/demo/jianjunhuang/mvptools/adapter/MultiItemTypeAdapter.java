package com.demo.jianjunhuang.mvptools.adapter;

import android.content.Context;
import android.view.ViewGroup;

import java.util.List;

/**
 * @author jianjunhuang.me@foxmail.com
 *         create on 2017/7/10.
 */

public abstract class MultiItemTypeAdapter<T> extends RecyclerAdapter<T> {

    protected MultiItemTypeSupport<T> multiItemTypeSupport;

    public MultiItemTypeAdapter(Context context, List<T> list, MultiItemTypeSupport<T> multilItemTypeSupport) {
        super(context, list, -1);
        this.multiItemTypeSupport = multilItemTypeSupport;
    }

    @Override
    public int getItemViewType(int position) {
        return multiItemTypeSupport.getItemViewType(position, list.get(position));
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int layoutId = multiItemTypeSupport.getLayoutId(viewType);
        RecyclerViewHolder holder = RecyclerViewHolder.get(context, parent, layoutId);
        return holder;
    }
}
