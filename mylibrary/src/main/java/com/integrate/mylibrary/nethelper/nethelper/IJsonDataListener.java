package com.integrate.mylibrary.nethelper.nethelper;

/**
 * Created by 梁明伟 on 2019/6/1.
 * Copyright © 2018年 CETC. All rights reserved.
 */
public interface IJsonDataListener<T> {
    void onSuccess(T m);
    void onFailure();
}
