package com.integrate.mylibrary.nethelper.recyclerviewhelper.listener;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.integrate.mylibrary.nethelper.recyclerviewhelper.base.RViewAdapter;


/**
 * Created by 梁明伟 on 2019/6/4.
 * Copyright © 2018年 CETC. All rights reserved.
 */
public interface RViewCreate<T> {

    SwipeRefreshLayout createSwipRefresh();

    int[] colorRes();

    RecyclerView createRecyclerView();

    RViewAdapter<T> createRecyclerViewAdapter();

    RecyclerView.LayoutManager createLayoutManager();

    RecyclerView.ItemDecoration createItemDecoration();

    int startPageNumber();

    boolean isSupportPaging();

}
