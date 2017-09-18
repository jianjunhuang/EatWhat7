package com.demo.jianjunhuang.mvptools.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

/**
 * @author jianjunhuang.me@foxmail.com
 *         create on 2017/7/6.
 */

public abstract class RecyclerAdapter<T> extends RecyclerView.Adapter<RecyclerViewHolder> {

    protected List<T> list;

    protected int layoutId;

    protected Context context;

    public RecyclerAdapter(Context context, List<T> list, int layoutId) {
        this.list = list;
        this.context = context;
        this.layoutId = layoutId;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerViewHolder holder = RecyclerViewHolder.get(context, parent, layoutId);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        holder.setPosition(position);
        convert(holder, list.get(position));
    }

    public abstract void convert(RecyclerViewHolder viewHolder, T t);

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setOnDataChange(List<T> list) {
        this.list = list;
    }

}
