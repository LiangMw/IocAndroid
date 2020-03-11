package com.integrate.netstudyapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.integrate.mylibrary.nethelper.recyclerviewhelper.RViewHelper;
import com.integrate.mylibrary.nethelper.recyclerviewhelper.SwipeRefreshHelper;
import com.integrate.mylibrary.nethelper.recyclerviewhelper.listener.RViewCreate;

import java.util.List;

/**
 * Created by 梁明伟 on 2019/6/6.
 * Copyright © 2018年 CETC. All rights reserved.
 */
public abstract class BaseRecyclerViewActivity extends AppCompatActivity implements RViewCreate, SwipeRefreshHelper.SwipRefreshListener {

    protected RViewHelper helper;
//    private List<UserInfo> datas = new ArrayList<>();
//    private RViewAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);

//        initData();
    }

    @Override
    public void onRefresh() {

    }

//    private void setListener() {
//
//        adapter.setItemListener(new ItemListener<UserInfo>() {
//            @Override
//            public void onItemClick(View view, UserInfo entity, int position) {
//
//            }
//
//            @Override
//            public boolean onItemLongClick(View view, UserInfo entity, int position) {
//                return false;
//            }
//        });
//    }

//    @Override
//    public SwipeRefreshLayout createSwipRefresh() {
//        return null;
//    }

    @Override
    public int[] colorRes() {
        return new int[0];
    }
//
//    @Override
//    public RecyclerView createRecyclerView() {
//       return null;
//    }

//    @Override
//    public RViewAdapter createRecyclerViewAdapter() {
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
//        Log.e("-----datas:",datas.size()+"");
//        adapter = new MulitAdapter(datas);
//        return null;
//    }

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
