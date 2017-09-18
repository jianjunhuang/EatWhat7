package com.demo.jianjunhuang.mvptools.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * @author jianjunhuang.me@foxmail.com
 *         create on 2017/7/18.
 */

public class RefreshRecyclerView extends RecyclerView {

    private OnRefresh onRefresh;

    public interface OnRefresh {
        void enableRefresh(boolean canRefresh);
    }

    public void setOnRefresh(OnRefresh onRefresh) {
        this.onRefresh = onRefresh;
    }

    public RefreshRecyclerView(Context context) {
        super(context);
        add();
    }

    public RefreshRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        add();
    }


    public RefreshRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        add();
    }

    private void add() {
        addOnScrollListener(new OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (onRefresh != null) {
                    int topRowVerticalPosition =
                            (recyclerView == null || recyclerView.getChildCount() == 0) ? 0 : recyclerView.getChildAt(0).getTop();
                    onRefresh.enableRefresh(topRowVerticalPosition >= 0);
                }

            }
        });
    }


}
