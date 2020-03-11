package com.integrate.mylibrary.nethelper.myrecyclerview.base;

import android.util.SparseArray;

import com.integrate.mylibrary.nethelper.myrecyclerview.holder.RViewHolder;
import com.integrate.mylibrary.nethelper.myrecyclerview.listener.RViewItem;

/**
 * Created by 梁明伟 on 2019/6/11.
 * Copyright © 2018年 CETC. All rights reserved.
 */
public class RViewItemManager<T> {

    private SparseArray<RViewItem> styles = new SparseArray<>();

    /**
     * 添加一种样式
     *
     * @param item
     */
    public void addStyle(RViewItem<T> item) {
        if (item != null) {
            styles.put(styles.size(), item);
        }
    }

    /**
     * 获取itemType对应的类型
     *
     * @param viewType
     * @return
     */
    public RViewItem getViewItem(int viewType) {
        return styles.get(viewType);
    }

    public void convert(RViewHolder viewHolder, T t,int position) {
        for (int i = styles.size()-1;i>=0;i--){
            RViewItem<T> item = styles.get(i);
            if(item.isViewType(t,position)) {
                item.convert(viewHolder,t,position);
                return;
            }
        }
        throw  new IllegalArgumentException("未找到此条目类型");
    }

    public int getItemStylesCount() {

        return styles == null ? 0 : styles.size();
    }

    public int getItemViewType(T t, int position) {
        for (int i = styles.size()-1;i>=0;i--){
            RViewItem<T> item = styles.get(i);
            if(item.isViewType(t,position)) {
                return styles.keyAt(i);
            }
        }
        throw  new IllegalArgumentException("未找到此条目类型1");
    }
}
