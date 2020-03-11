package com.integrate.mylibrary.nethelper.myrecyclerview.base;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.integrate.mylibrary.nethelper.myrecyclerview.holder.RViewHolder;
import com.integrate.mylibrary.nethelper.myrecyclerview.listener.IItemListener;
import com.integrate.mylibrary.nethelper.myrecyclerview.listener.RViewItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 梁明伟 on 2019/6/11.
 * Copyright © 2018年 CETC. All rights reserved.
 */
public class RViewAdapter<T> extends RecyclerView.Adapter<RViewHolder> {

    private List<T> datas;
    private RViewItemManager itemStyles;
    private IItemListener listener;

    //多样式
    public RViewAdapter(List<T> datas) {
        if(datas == null) {
            this.datas = new ArrayList<>();
        }else{
            this.datas = datas;
        }
        itemStyles = new RViewItemManager();
    }

    //单样式
    public RViewAdapter(List<T> datas, RViewItem<T> item) {
        if(datas == null) {
            this.datas = new ArrayList<>();
        }else{
            this.datas = datas;
        }
        itemStyles = new RViewItemManager();
        addStyle(item);

    }

    /**
     *
     *添加一种样式
     *
     */
    public void addStyle(RViewItem<T> item) {
        if(item !=null) {
            itemStyles.addStyle(item);
        }
    }


    public void addData(List<T> datas){
        if(datas != null) {
            this.datas.addAll(datas);
            notifyDataSetChanged();
        }
    }

    public void updateData(List<T> datas){
        if(datas != null) {
            this.datas.clear();
            this.datas = datas;
            notifyDataSetChanged();
        }
    }

    @NonNull
    @Override
    public RViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        RViewItem itemView = getItemView(viewType);
        int layoutId = itemView.getItemLayoutId();
        RViewHolder holder = RViewHolder.createViewHolder(viewGroup.getContext(),viewGroup,layoutId);
        if(itemView.isOpenClick()) {
            setListener(holder);
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RViewHolder holder, int position) {
        convert(holder,datas.get(position),position);
    }

    private void setListener(final RViewHolder holder) {
        holder.getmConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener != null) {
                    int position = holder.getAdapterPosition();
                    listener.onItemClickListner(v,datas.get(position),position);
                }
            }
        });

        holder.getmConvertView().setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if(listener != null) {
                    int position = holder.getAdapterPosition();
                    return listener.onItemLongClickListener(v,datas.get(position),position);
                }
                return false;

            }
        });
    }

    private RViewItem getItemView(int viewType) {
       return itemStyles.getViewItem(viewType);
    }

    @Override
    public int getItemViewType(int position) {
        if(hasMulitType()) {
           return itemStyles.getItemViewType(datas.get(position),position);
        }
        return super.getItemViewType(position);
    }

    private boolean hasMulitType(){
        return itemStyles.getItemStylesCount()>1;
    }

    private void convert(RViewHolder viewHolder, T t,int position) {
        itemStyles.convert(viewHolder,t,position);
    }

    @Override
    public int getItemCount() {
        return datas == null?0:datas.size();
    }
}
