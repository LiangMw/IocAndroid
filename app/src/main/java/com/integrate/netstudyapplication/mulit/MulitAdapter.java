package com.integrate.netstudyapplication.mulit;

import android.content.Context;

import com.integrate.mylibrary.nethelper.myrecyclerview.base.RViewAdapter;
import com.integrate.netstudyapplication.bean.UserInfo;

import java.util.List;

/**
 * Created by 梁明伟 on 2019/6/6.
 * Copyright © 2018年 CETC. All rights reserved.
 */
public class MulitAdapter extends RViewAdapter<UserInfo> {

    public MulitAdapter(Context mContext,List<UserInfo> datas) {
        super(datas);
        addStyle(new AItem(mContext));
        addStyle(new BItem());
        addStyle(new CItem());
        addStyle(new DItem());
//        addItemStyles(new AItem(mContext));
//        addItemStyles(new BItem());
//        addItemStyles(new CItem());
//        addItemStyles(new DItem());
    }

//    public MulitAdapter(List<UserInfo> datas) {
//
//
//    }
}
