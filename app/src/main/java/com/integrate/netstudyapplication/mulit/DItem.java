package com.integrate.netstudyapplication.mulit;

import android.widget.TextView;

import com.integrate.mylibrary.nethelper.myrecyclerview.holder.RViewHolder;
import com.integrate.mylibrary.nethelper.myrecyclerview.listener.RViewItem;
import com.integrate.netstudyapplication.R;
import com.integrate.netstudyapplication.bean.UserInfo;

/**
 * Created by 梁明伟 on 2019/6/6.
 * Copyright © 2018年 CETC. All rights reserved.
 */
public class DItem implements RViewItem<UserInfo> {

//    @Override
//    public int getItemLayout() {
//        return R.layout.layout_ditem;
//    }
//
//    @Override
//    public boolean openClick() {
//        return false;
//    }
//
//    @Override
//    public boolean isItemView(UserInfo entity, int position) {
//        return entity.getType() == 4;
//    }

    @Override
    public int getItemLayoutId() {
        return R.layout.layout_ditem;
    }

    @Override
    public boolean isOpenClick() {
        return false;
    }

    @Override
    public boolean isViewType(UserInfo enterty, int position) {
        return enterty.getType() == 4;
    }

    @Override
    public void convert(RViewHolder holder, UserInfo entity, int position) {

        TextView t= holder.getView(R.id.tv_account);
        t.setText(entity.getAccount());
    }
}
