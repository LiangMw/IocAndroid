package com.integrate.mylibrary.nethelper.myrecyclerview.holder;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by 梁明伟 on 2019/6/11.
 * Copyright © 2018年 CETC. All rights reserved.
 */
public class RViewHolder extends RecyclerView.ViewHolder {

    private SparseArray<View> mViews;
    private View mConvertView;

    public RViewHolder(@NonNull View itemView) {
        super(itemView);
        mViews = new SparseArray<>();
        this.mConvertView = itemView;
    }

    public static RViewHolder createViewHolder(Context context, ViewGroup parent,int layoutid){
        View itemView = LayoutInflater.from(context).inflate(layoutid,parent,false);
        return new RViewHolder(itemView);
    }

    public <T extends View> T getView(int viewId){
        View view = mViews.get(viewId);

        if(view == null) {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId,view);
        }
        return (T) view;
    }

    public View getmConvertView() {
        return mConvertView;
    }
}
