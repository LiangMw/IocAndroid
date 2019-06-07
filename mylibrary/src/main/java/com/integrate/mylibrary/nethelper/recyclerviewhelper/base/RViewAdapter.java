package com.integrate.mylibrary.nethelper.recyclerviewhelper.base;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.integrate.mylibrary.nethelper.recyclerviewhelper.holder.RViewHolder;
import com.integrate.mylibrary.nethelper.recyclerviewhelper.listener.ItemListener;
import com.integrate.mylibrary.nethelper.recyclerviewhelper.listener.RViewItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 梁明伟 on 2019/6/3.
 * Copyright © 2018年 CETC. All rights reserved.
 * RecyclerView 的适配器
 */
public class RViewAdapter<T> extends RecyclerView.Adapter<RViewHolder> {

    private RViewItemManager<T> itemStyle;//item类型管理
    private ItemListener<T> itemListener;//item的点击监听
    private List<T> datas;//数据源

    public void addDatas(List<T> datas) {
        if(datas ==null) {
            return;
        }
        this.datas.addAll(datas);
        notifyDataSetChanged();
    }

    public void updateDatas(List<T> datas) {
        if(datas ==null) {
            return;
        }
        this.datas =datas;
        notifyDataSetChanged();
    }


    //单样式
    public RViewAdapter(List<T> datas){
        if(datas == null) {
            this.datas = new ArrayList<>();
        }
        this.datas = datas;
        itemStyle = new RViewItemManager<>();
    }

    //多样式
    public RViewAdapter(List<T> datas, RViewItem<T> item){
        if(datas == null) {
            this.datas = new ArrayList<>();
        }
        this.datas = datas;
        itemStyle = new RViewItemManager<>();
        addItemStyles(item);
    }

    @Override
    public int getItemViewType(int position) {
        if(hasMultiStyle()) {
            return itemStyle.getItemViewType(datas.get(position),position);
        }
        return super.getItemViewType(position);

    }

    //是否有多样是的recycleview的Item
    private boolean hasMultiStyle(){
        return itemStyle.getItemStylesCount()>0;
    }

    //根据不同的类型类创建不同的item的ViewHolder
    @NonNull
    @Override
    public RViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        RViewItem item = itemStyle.getRViewItem(viewType);
        int layoutId = item.getItemLayout();
        RViewHolder viewHolder = RViewHolder.createViewHolder(viewGroup.getContext(),viewGroup,layoutId);
        if(item.openClick()) {
            setListener(viewHolder);
        }
        return null;
    }

    private void setListener(final RViewHolder viewHolder) {
        viewHolder.getmConverView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(itemListener !=null) {
                    int position = viewHolder.getAdapterPosition();
                    itemListener.onItemClick(v,datas.get(position),position);

                }
            }
        });

        viewHolder.getmConverView().setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if(itemListener !=null) {
                    if(itemListener !=null) {
                        int position = viewHolder.getAdapterPosition();
                       return itemListener.onItemLongClick(v,datas.get(position),position);
                    }
                }
                return false;
            }

        });
    }

    @Override
    public void onBindViewHolder(@NonNull RViewHolder holder, int position) {

        convert(holder,datas.get(position));

    }

    private void convert(RViewHolder holder, T t) {

        itemStyle.convert(holder,t,holder.getAdapterPosition());

    }

    @Override
    public int getItemCount() {
        return datas == null?0:datas.size();
    }

    //添加一种样式
    public void addItemStyles(RViewItem<T> item) {
        itemStyle.addStyles(item);
    }

    //监听的赋值
    public void setItemListener(ItemListener<T> itemListener) {
        this.itemListener = itemListener;
    }
}
