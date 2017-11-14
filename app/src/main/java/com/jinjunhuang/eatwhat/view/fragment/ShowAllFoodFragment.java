package com.jinjunhuang.eatwhat.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.demo.jianjunhuang.mvptools.adapter.RecyclerAdapter;
import com.demo.jianjunhuang.mvptools.adapter.RecyclerViewHolder;
import com.demo.jianjunhuang.mvptools.integration.BaseFragment;
import com.demo.jianjunhuang.mvptools.widget.RefreshRecyclerView;
import com.jinjunhuang.eatwhat.R;
import com.jinjunhuang.eatwhat.contract.ShowAllFoodContract;
import com.jinjunhuang.eatwhat.model.bean.FoodBean;
import com.jinjunhuang.eatwhat.presenter.impl.ShowAllFoodPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jianjunhuang.me@foxmail.com
 *         create on 2017/9/17.
 *         显示所有的食物
 */

public class ShowAllFoodFragment extends BaseFragment implements ShowAllFoodContract.View, SwipeRefreshLayout.OnRefreshListener, RefreshRecyclerView.OnRefresh {

    private ShowAllFoodPresenter mPresenter;

    private List<FoodBean> data = new ArrayList<>();

    private RefreshRecyclerView foodRv;

    private SwipeRefreshLayout refreshLayout;

    private ShowAllFoodAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mPresenter = new ShowAllFoodPresenter(this);
        mPresenter.onStart();
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.show_all_food_fragment;
    }

    @Override
    protected void initView(View view) {
        foodRv = findView(R.id.show_all_food_rv);
        refreshLayout = findView(R.id.show_all_food_srl);
        refreshLayout.setColorSchemeResources(R.color.colorAccent);

        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        foodRv.setLayoutManager(manager);
        mAdapter = new ShowAllFoodAdapter(getContext(), data, R.layout.show_all_food_item_layout);
        foodRv.setAdapter(mAdapter);
    }

    @Override
    protected void initListener() {
        foodRv.setOnRefresh(this);
        refreshLayout.setOnRefreshListener(this );
    }

    @Override
    public void onResume() {
        super.onResume();
        initData();
    }

    private void initData() {
        data.clear();
        data.addAll(mPresenter.loadData());
        mAdapter.setOnDataChange(data);
        if (refreshLayout.isRefreshing()) {
            refreshLayout.setRefreshing(false);
        }
    }

    @Override
    public void onRefresh() {
        initData();
    }

    @Override
    public void enableRefresh(boolean canRefresh) {
        if (!refreshLayout.isRefreshing()) {
            refreshLayout.setEnabled(canRefresh);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.onDestroy();
    }

    private class ShowAllFoodAdapter extends RecyclerAdapter<FoodBean> {
        public ShowAllFoodAdapter(Context context, List<FoodBean> list, int layoutId) {
            super(context, list, layoutId);
        }

        @Override
        public void convert(RecyclerViewHolder viewHolder, FoodBean bean) {

            viewHolder.setText(R.id.show_all_canteen_tv, coverToCanteen(bean.getCanteen()));
            viewHolder.setText(R.id.show_all_name_tv, bean.getName());
            viewHolder.setText(R.id.show_all_price_tv, bean.getPrice() + "");
            viewHolder.setText(R.id.show_all_kind_tv, coverToKind(bean.getKind()));
            viewHolder.setText(R.id.show_all_score_tv, bean.getScore() + "");

        }

        private String coverToCanteen(int canteenTag) {
            switch (canteenTag) {
                case 0: {
                    return "一饭";
                }
                case 1: {
                    return "二饭";
                }
                case 2: {
                    return "三饭";
                }
                case 3: {
                    return "四饭";
                }
            }
            return "";
        }

        private String coverToKind(int kindTag) {
            switch (kindTag) {
                case 0: {
                    return "饭";
                }
                case 1: {
                    return "面";
                }
                case 2: {
                    return "粥";
                }
            }
            return "";
        }
    }

}
