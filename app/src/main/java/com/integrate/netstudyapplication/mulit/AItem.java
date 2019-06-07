package com.integrate.netstudyapplication.mulit;

import android.widget.TextView;

import com.integrate.mylibrary.nethelper.recyclerviewhelper.holder.RViewHolder;
import com.integrate.mylibrary.nethelper.recyclerviewhelper.listener.RViewItem;
import com.integrate.netstudyapplication.R;
import com.integrate.netstudyapplication.bean.UserInfo;

/**
 * Created by 梁明伟 on 2019/6/6.
 * Copyright © 2018年 CETC. All rights reserved.
 */
public class AItem implements RViewItem<UserInfo> {

    @Override
    public int getItemLayout() {
        return R.layout.layout_aitem;
    }

    @Override
    public boolean openClick() {
        return false;
    }

    @Override
    public boolean isItemView(UserInfo entity, int position) {
        return entity.getType() == 1;
    }

    @Override
    public void convert(RViewHolder holder, UserInfo entity, int position) {

        TextView t= holder.getView(R.id.tv_account);
        t.setText(entity.getAccount());
    }
}
