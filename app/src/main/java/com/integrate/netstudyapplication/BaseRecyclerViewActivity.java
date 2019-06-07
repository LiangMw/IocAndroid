package com.integrate.netstudyapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.integrate.mylibrary.nethelper.recyclerviewhelper.RViewHelper;
import com.integrate.mylibrary.nethelper.recyclerviewhelper.SwipeRefreshHelper;
import com.integrate.mylibrary.nethelper.recyclerviewhelper.base.RViewAdapter;
import com.integrate.mylibrary.nethelper.recyclerviewhelper.listener.ItemListener;
import com.integrate.mylibrary.nethelper.recyclerviewhelper.listener.RViewCreate;
import com.integrate.netstudyapplication.bean.UserInfo;
import com.integrate.netstudyapplication.mulit.MulitAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 梁明伟 on 2019/6/6.
 * Copyright © 2018年 CETC. All rights reserved.
 */
public class BaseRecyclerViewActivity extends AppCompatActivity implements RViewCreate, SwipeRefreshHelper.SwipRefreshListener {

    private RViewHelper helper;
    private List<UserInfo> datas = new ArrayList<>();
    private RViewAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        helper = new RViewHelper.Builder(this, this).build();
//        initData();
    }

    private void initData() {
        if (datas.isEmpty()) {
            for (int i = 0; i < 15; i++) {
                UserInfo u = new UserInfo();
                if (i % 1 == 1) {
                    u.setAccount("ACCOUNT》》》》》" + i);
                    u.setType(0);
                }
                if (i % 2 == 2) {
                    u.setAccount("ACCOUNT》》》》》" + i);
                    u.setType(1);
                }
                if (i % 3 == 3) {
                    u.setAccount("ACCOUNT》》》》》" + i);
                    u.setType(2);
                }
                if (i % 4 == 4) {
                    u.setAccount("ACCOUNT》》》》》" + i);
                    u.setType(3);
                }
                datas.add(u);
            }
        }
    }

    @Override
    public void onRefresh() {
        notifyAdapterDataSetChanged(datas);
    }

    private void setListener() {

        adapter.setItemListener(new ItemListener<UserInfo>() {
            @Override
            public void onItemClick(View view, UserInfo entity, int position) {

            }

            @Override
            public boolean onItemLongClick(View view, UserInfo entity, int position) {
                return false;
            }
        });
    }

    @Override
    public SwipeRefreshLayout createSwipRefresh() {
        return findViewById(R.id.srl);
    }

    @Override
    public int[] colorRes() {
        return new int[0];
    }

    @Override
    public RecyclerView createRecyclerView() {
//        Log.e("--bm-tag:",findViewById(R.id.recyclerview)+"");
        return findViewById(R.id.recyclerview);
    }

    @Override
    public RViewAdapter createRecyclerViewAdapter() {
//        adapter = new RViewAdapter<UserInfo>(datas, new RViewItem<UserInfo>() {
//            @Override
//            public int getItemLayout() {
//                return R.layout.layout_aitem;
//            }
//
//            @Override
//            public boolean openClick() {
//                return true;
//            }
//
//            @Override
//            public boolean isItemView(UserInfo entity, int position) {
//                return true;
//            }
//
//            @Override
//            public void convert(RViewHolder holder, UserInfo entity, int position) {
//                TextView t= holder.getView(R.id.tv_account);
//                t.setText(entity.getAccount());
//            }
//        });
        Log.e("-----datas:",datas.size()+"");
        adapter = new MulitAdapter(datas);
        return adapter;
    }

    @Override
    public RecyclerView.LayoutManager createLayoutManager() {
        return new LinearLayoutManager(this);
    }

    @Override
    public RecyclerView.ItemDecoration createItemDecoration() {
        return new DividerItemDecoration(this, LinearLayoutManager.VERTICAL);
    }

    @Override
    public int startPageNumber() {
        return 0;
    }

    @Override
    public boolean isSupportPaging() {
        return false;
    }

    protected void notifyAdapterDataSetChanged(List datas) {
        helper.notifyAdapterDataSetChanged(datas);
    }
}
