package com.integrate.netstudyapplication.mulit;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.integrate.mylibrary.nethelper.myrecyclerview.holder.RViewHolder;
import com.integrate.mylibrary.nethelper.myrecyclerview.listener.RViewItem;
import com.integrate.netstudyapplication.R;
import com.integrate.netstudyapplication.bean.UserInfo;

/**
 * Created by 梁明伟 on 2019/6/6.
 * Copyright © 2018年 CETC. All rights reserved.
 */
public class AItem implements RViewItem<UserInfo> {

    private Context mContext;

    public AItem(Context mContext) {
        this.mContext = mContext;
    }

//    @Override
//    public int getItemLayout() {
//        return R.layout.layout_aitem;
//    }
//
//    @Override
//    public boolean openClick() {
//        return true;
//    }
//
//    @Override
//    public boolean isItemView(UserInfo entity, int position) {
//        return entity.getType() == 1;
//    }

    @Override
    public int getItemLayoutId() {
        return R.layout.layout_aitem;
    }

    @Override
    public boolean isOpenClick() {
        return true;
    }

    @Override
    public boolean isViewType(UserInfo enterty, int position) {
        return enterty.getType() == 1;
    }

    @Override
    public void convert(RViewHolder holder, UserInfo entity, int position) {

        TextView t= holder.getView(R.id.tv_account);
        t.setText(entity.getAccount());
        t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext,"点击了第一种类型中的文字",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
