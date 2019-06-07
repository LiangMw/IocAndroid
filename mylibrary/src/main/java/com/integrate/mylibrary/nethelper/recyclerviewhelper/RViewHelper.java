package com.integrate.mylibrary.nethelper.recyclerviewhelper;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.integrate.mylibrary.nethelper.recyclerviewhelper.base.RViewAdapter;
import com.integrate.mylibrary.nethelper.recyclerviewhelper.listener.RViewCreate;

import java.util.List;

/**
 * Created by 梁明伟 on 2019/6/3.
 * Copyright © 2018年 CETC. All rights reserved.
 */
public class RViewHelper<T> {

    private SwipeRefreshLayout swipeRefresh;
    private SwipeRefreshHelper helper;
    private RecyclerView recyclerView;
    private RViewAdapter<T> adapter;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.ItemDecoration itemDecoration;
    private int startPageNumber;
    private boolean issupportPaging;
    private SwipeRefreshHelper.SwipRefreshListener listener;
    private int currentPageNum;

    private RViewHelper(Builder<T> builder) {
//        Log.e("--d-tag:",builder.create.createRecyclerView()+"");
        this.swipeRefresh = builder.create.createSwipRefresh();
        this.recyclerView = builder.create.createRecyclerView();
        this.adapter = builder.create.createRecyclerViewAdapter();
        this.layoutManager = builder.create.createLayoutManager();
        this.itemDecoration = builder.create.createItemDecoration();
        this.startPageNumber = builder.create.startPageNumber();
        this.issupportPaging = builder.create.isSupportPaging();
        this.listener = builder.listener;

        this.currentPageNum = this.startPageNumber;
        int[] colorRes = builder.create.colorRes();
        if(swipeRefresh != null) {
            if(colorRes == null) {
                helper = SwipeRefreshHelper.createSwipeRefreshHelper(swipeRefresh);
            }else{
                helper = SwipeRefreshHelper.createSwipeRefreshHelper(swipeRefresh,colorRes);
            }
        }
        
        init();

    }

    private void init() {

        //RecyclerView的初始化
        Log.e("---tag:",recyclerView+"");
        Log.e("---tag:","--"+layoutManager);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        if(itemDecoration != null) {
            recyclerView.addItemDecoration(itemDecoration);

        }
        recyclerView.setAdapter(adapter);

        //下拉刷新的操作
        if(helper != null) {
            helper.setListener(new SwipeRefreshHelper.SwipRefreshListener() {
                @Override
                public void onRefresh() {
                    dismissSwipeRefresh();//刷新完隐藏控件
                    currentPageNum = startPageNumber;
                    if(listener != null) {
                        listener.onRefresh();
                    }
                }
            });

        }
     }

    /**
     * 方便外面获取adapter
     * @return
     */
    public RViewAdapter<T> getAdapter() {
        return adapter;
    }

    //隐藏刷新控件
    private void dismissSwipeRefresh() {
            if(swipeRefresh != null && swipeRefresh.isRefreshing()) {
                swipeRefresh.setRefreshing(false);
            }
    }

    public void notifyAdapterDataSetChanged(List<T> datas) {

        //首次加载或者下拉刷新后都需要重置页码，所以用页面来确定是第一次虎或者刷新
        if(currentPageNum ==startPageNumber) {
            adapter.updateDatas(datas);
        }else{
            adapter.addDatas(datas);
        }

        recyclerView.setAdapter(adapter);
        //省略功能--加载更多、最后一条、空视图


    }

    public static class Builder<T> {
        private final RViewCreate<T> create;
        private final SwipeRefreshHelper.SwipRefreshListener listener;

        public Builder(RViewCreate<T> create, SwipeRefreshHelper.SwipRefreshListener listener) {
            this.create = create;
            this.listener = listener;
        }

        public RViewHelper build() {
            return new RViewHelper(this);
        }
    }
}
