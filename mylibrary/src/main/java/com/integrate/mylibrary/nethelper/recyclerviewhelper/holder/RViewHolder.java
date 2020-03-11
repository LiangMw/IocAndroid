package com.integrate.mylibrary.nethelper.recyclerviewhelper.holder;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by 梁明伟 on 2019/6/3.
 * Copyright © 2018年 CETC. All rights reserved.
 */
public class RViewHolder extends RecyclerView.ViewHolder {

    private SparseArray<View> mViews;//View控件集合
    private View mConverView;


    private RViewHolder(@NonNull View itemView) {
        super(itemView);
        mConverView  = itemView;
        mViews = new SparseArray<>();
    }

    /**
     * 创建viewHolder对象
     * @param context
     * @param parent
     * @param layoutId
     * @return
     */
    public  static  RViewHolder createViewHolder(Context context, ViewGroup parent,int layoutId){
        View itemView = LayoutInflater.from(context).inflate(layoutId,parent,false);
        return new RViewHolder(itemView);
    }


    /**
     * 返回view
     * @param viewId
     * @param <T>
     * @return
     */
    public <T extends View> T getView(int viewId){
        View view = mViews.get(viewId);
        if(view == null) {
            view = mConverView.findViewById(viewId);
            mViews.put(viewId,view);
        }
        return (T) view;
    }

    public View getmConverView() {
        return mConverView;
    }
}
