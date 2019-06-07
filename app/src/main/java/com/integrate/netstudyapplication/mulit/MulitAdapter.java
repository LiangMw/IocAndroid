package com.integrate.netstudyapplication.mulit;

import com.integrate.mylibrary.nethelper.recyclerviewhelper.base.RViewAdapter;
import com.integrate.netstudyapplication.bean.UserInfo;

import java.util.List;

/**
 * Created by 梁明伟 on 2019/6/6.
 * Copyright © 2018年 CETC. All rights reserved.
 */
public class MulitAdapter extends RViewAdapter<UserInfo> {

    public MulitAdapter(List<UserInfo> datas) {
        super(datas);
        addItemStyles(new AItem());
        addItemStyles(new BItem());
        addItemStyles(new CItem());
        addItemStyles(new DItem());
    }
}
