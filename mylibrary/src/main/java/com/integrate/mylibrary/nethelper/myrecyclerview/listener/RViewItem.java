package com.integrate.mylibrary.nethelper.myrecyclerview.listener;

import com.integrate.mylibrary.nethelper.myrecyclerview.holder.RViewHolder;

/**
 * Created by 梁明伟 on 2019/6/11.
 * Copyright © 2018年 CETC. All rights reserved.
 */
public interface RViewItem<T> {

    int getItemLayoutId();

    boolean isOpenClick();

    boolean isViewType(T enterty,int position);

    void convert(RViewHolder holder,T enterty, int position);

}
