package com.integrate.mylibrary.nethelper.myrecyclerview.listener;

import android.view.View;

/**
 * Created by 梁明伟 on 2019/6/11.
 * Copyright © 2018年 CETC. All rights reserved.
 */
public interface IItemListener<T> {

    void onItemClickListner(View view, T entity, int position);
    boolean onItemLongClickListener(View view, T entity, int position);
}
