package com.integrate.mylibrary.nethelper.nethelper;

/**
 * Created by 梁明伟 on 2019/6/1.
 * Copyright © 2018年 CETC. All rights reserved.
 */
public class OkHttp {

    public static<T,M> void setJsonRequeset(String url,T requestData,Class<M> response,IJsonDataListener callBackListener){
        IHttpRequest httpRequest = new JsonHttpRequest();
        CallBackListener callBackListener1 = new JsonCallbackListener<>(response,callBackListener);
        HttpTask httpTask = new HttpTask(url,requestData,httpRequest,callBackListener1);
        ThreadPoolManager.getInatance().addTask(httpTask);
    }
}
