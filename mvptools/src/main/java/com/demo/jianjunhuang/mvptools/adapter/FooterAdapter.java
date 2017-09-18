package com.demo.jianjunhuang.mvptools.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.demo.jianjunhuang.mvptoos.R;

import java.util.List;

/**
 * @author jianjunhuang.me@foxmail.com
 *         create on 2017/9/6.
 */

public abstract class FooterAdapter<T> extends RecyclerAdapter<T> {

    public static final int FOOTER_TYPE = -1;

    public static final int FINISH = 1;
    public static final int START_LOADING = 0;
    public static final int NO_MORE_DATA = 2;

    protected boolean isLoading = false;

    protected int status = FINISH;

    public FooterAdapter(Context context, List list, int layoutId) {
        super(context, list, layoutId);
    }

    @Override
    public int getItemCount() {
        //多了一个footerview
        return list.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == list.size()) {
            return FOOTER_TYPE;
        }
        return position;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerViewHolder holder = null;
        if (viewType == FOOTER_TYPE) {
            layoutId = R.layout.footerview;
        }
        holder = RecyclerViewHolder.get(context, parent, layoutId);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        if (position == list.size()) {
            switch (status) {
                case START_LOADING: {
                    holder.getView(R.id.footerView_ly).setVisibility(View.VISIBLE);
                    holder.setText(R.id.footerView_tv, "正在加载...");
                    break;
                }
                case FINISH: {
                    holder.getView(R.id.footerView_ly).setVisibility(View.GONE);
                    break;
                }
                case NO_MORE_DATA: {
                    holder.getView(R.id.footerView_ly).setVisibility(View.VISIBLE);
                    holder.setText(R.id.footerView_tv, "没有更多的数据了");
                    break;
                }
            }
        } else {
            super.onBindViewHolder(holder, position);
        }
    }

    public void setOnStatusChange(int status) {
        this.status = status;
        if (status == START_LOADING) {
            isLoading = true;
        } else {
            isLoading = false;
        }
        notifyDataSetChanged();
    }

    public class FooterScrollerListener extends RecyclerView.OnScrollListener {

        private int lastVisibleItem;


        private FooterAdapter.RecyclerFooterListener footerListener;

        public void setFooterListener(FooterAdapter.RecyclerFooterListener footerListener) {
            this.footerListener = footerListener;
        }


        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 1 == getItemCount() && !isLoading) {
                setOnStatusChange(MultiItemFooterAdapter.START_LOADING);
                if (footerListener != null) {
                    footerListener.onFooterLoading();
                }
            }
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            lastVisibleItem = ((LinearLayoutManager) recyclerView.getLayoutManager()).findLastVisibleItemPosition();
        }
    }

    public interface RecyclerFooterListener {

        void onFooterLoading();
    }

}
