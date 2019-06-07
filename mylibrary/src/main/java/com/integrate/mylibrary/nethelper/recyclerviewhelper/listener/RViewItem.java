package com.integrate.mylibrary.nethelper.recyclerviewhelper.listener;

import com.integrate.mylibrary.nethelper.recyclerviewhelper.holder.RViewHolder;

/**
 * Created by 梁明伟 on 2019/6/3.
 * Copyright © 2018年 CETC. All rights reserved.
 * java bean 对象
 * 某一类item得对象接口
 */
public interface RViewItem<T> {

    //获得item布局
    int getItemLayout();

    //是否开启点击
    boolean openClick();

    //是否为当前item布局
    boolean isItemView(T entity, int position);

    //item控件和数据绑定
    void convert(RViewHolder holder, T entity, int position);

}
