package com.demo.jianjunhuang.mvptools.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * @author jianjunhuang.me@foxmail.com
 * @since 2017/2/10.
 */

public abstract class CommonAdapter<T> extends BaseAdapter {

    protected Context context;
    protected List<T> mList;
    protected int itemLayoutId;
    protected LayoutInflater inflater;

    public CommonAdapter(Context context, List<T> mList, int itemLayoutId) {
        this.context = context;
        this.mList = mList;
        this.itemLayoutId = itemLayoutId;
        inflater.from(context);
    }

    public void setDataChange(List<T> mList){
        this.mList = mList;
        notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public T getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final CommonViewHolder commonViewHolder = getViewHolder(position, convertView, parent);
        convert(commonViewHolder, getItem(position), position);
        return commonViewHolder.getConvertView();
    }

    /**
     * set resources
     *
     * @param viewHolder ViewHolder
     * @param bean       data bean
     * @param position   position
     */
    public abstract void convert(CommonViewHolder viewHolder, T bean, int position);

    private CommonViewHolder getViewHolder(int position, View convertView, ViewGroup parent) {
        return CommonViewHolder.get(context, convertView, parent, itemLayoutId, position);
    }

}
