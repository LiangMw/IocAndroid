package com.integrate.mylibrary.nethelper.recyclerviewhelper.listener;

import android.view.View;

/**
 * Created by 梁明伟 on 2019/6/3.
 * Copyright © 2018年 CETC. All rights reserved.
 */
public interface ItemListener<T> {

    void onItemClick(View view, T entity, int position);

    boolean onItemLongClick(View view, T entity, int position);
}
