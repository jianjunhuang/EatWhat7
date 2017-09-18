package com.demo.jianjunhuang.mvptools.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.demo.jianjunhuang.mvptoos.R;

import java.util.List;

/**
 * @author jianjunhuang.me@foxmail.com
 *         create on 2017/8/10.
 */

public abstract class MultiItemFooterAdapter<T> extends FooterAdapter<T> {

    protected MultiItemTypeSupport<T> multiItemTypeSupport;

    public MultiItemFooterAdapter(Context context, List<T> list, MultiItemTypeSupport<T> multiItemTypeSupport) {
        super(context, list, -1);
        this.multiItemTypeSupport = multiItemTypeSupport;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == list.size()) {
            return FOOTER_TYPE;
        }
        return multiItemTypeSupport.getItemViewType(position, list.get(position));
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int layoutId;
        RecyclerViewHolder holder = null;
        if (viewType == FOOTER_TYPE) {
            layoutId = R.layout.footerview;
        } else {
            layoutId = multiItemTypeSupport.getLayoutId(viewType);
        }
        holder = RecyclerViewHolder.get(context, parent, layoutId);
        return holder;
    }
}
