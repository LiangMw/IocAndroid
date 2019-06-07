package com.integrate.mylibrary.nethelper.recyclerviewhelper;

import android.support.annotation.ColorRes;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;

/**
 * Created by 梁明伟 on 2019/6/4.
 * Copyright © 2018年 CETC. All rights reserved.
 */
public class SwipeRefreshHelper {
    private SwipeRefreshLayout swipeRefresh;
    private SwipRefreshListener listener;
    static SwipeRefreshHelper createSwipeRefreshHelper(SwipeRefreshLayout swipeRefresh, @ColorRes int... colorResIds) {
        return new SwipeRefreshHelper(swipeRefresh,colorResIds);
    }

    private SwipeRefreshHelper(@Nullable SwipeRefreshLayout swipeRefresh,@ColorRes int... colorResIds){
        this.swipeRefresh = swipeRefresh;
        init(colorResIds);
    }

    private void init(@ColorRes int... colorResIds) {
        if(colorResIds == null || colorResIds.length ==0) {
            swipeRefresh.setColorSchemeResources(android.R.color.holo_orange_dark,android.R.color.holo_green_dark,android.R.color.holo_blue_dark);

        }else{
            swipeRefresh.setColorSchemeResources(colorResIds);
        }
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if(listener !=null) {
                    listener.onRefresh();
                }
            }
        });

    }

    public void setListener(SwipRefreshListener listener) {
        this.listener = listener;
    }

    public interface SwipRefreshListener {
        void onRefresh();
    }
}
