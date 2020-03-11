package com.integrate.mylibrary.nethelper.recyclerviewhelper.base;

import android.support.v4.util.SparseArrayCompat;
import com.integrate.mylibrary.nethelper.recyclerviewhelper.holder.RViewHolder;
import com.integrate.mylibrary.nethelper.recyclerviewhelper.listener.RViewItem;


/**
 * Created by 梁明伟 on 2019/6/4.
 * Copyright © 2018年 CETC. All rights reserved.
 * 多类型 多样式的item管理器
 */
public class RViewItemManager <T>{

    //key:viewType  value:RViewItem
    private SparseArrayCompat<RViewItem<T>>  styles = new SparseArrayCompat<>();

    //添加一种样式
    public void addStyles(RViewItem<T> item) {
        if(item != null) {
            styles.put(styles.size(),item);

        }
    }

    //有没有多样式

    public int  getItemStylesCount() {
        return styles.size();
    }

    //根据显示的VIewtype返回RViewItem对象(集合的value)
    public RViewItem getRViewItem(int viewType) {
        return styles.get(viewType);
    }

    //根据数据源和位置返回某个item类型的ViewType（集合的key）
    public int getItemViewType(T entity,int position){
        for (int i = styles.size()-1;i>=0;i--){//样式倒序循环，防止增删集合
            //比如第1个位置（索引0）拿到的是第一类的条目样式
            RViewItem<T> item = styles.valueAt(i);
            if(item.isItemView(entity,position)) {
                return styles.keyAt(i);
            }
        }

        throw  new IllegalArgumentException("没有找到此条目类型");
    }

    public void convert(RViewHolder holder, T t, int position) {

        for (int i= styles.size()-1;i>=0;i--){
            RViewItem<T> item = styles.get(i);
            if(item.isItemView(t,position)) {
                item.convert(holder,t,position);
                return;
            }
        }
        throw new IllegalArgumentException("没有找到次条目的类型");
    }
}
