package com.integrate.mylibrary.nethelper.nethelper;

import com.alibaba.fastjson.JSON;

import java.io.UnsupportedEncodingException;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * Created by 梁明伟 on 2019/6/1.
 * Copyright © 2018年 CETC. All rights reserved.
 */
public class HttpTask<T> implements Runnable, Delayed {

    private IHttpRequest iHttpRequest= null;

    public HttpTask(String url,T requestData,IHttpRequest httpRequest,CallBackListener callBackListener){
        iHttpRequest = httpRequest;
        httpRequest.setUrl(url);
        httpRequest.setListener(callBackListener);
        String content  = JSON.toJSONString(requestData);
        try {
            httpRequest.setData(content.getBytes("utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            iHttpRequest.execute();
        }catch (Exception e){
            ThreadPoolManager.getInatance().addDelayTask(this);
        }
    }

    private long  delayTime;
    private int retrycount;

    public long getDelayTime() {
        return delayTime ;
    }

    public void setDelayTime(long delayTime) {
        this.delayTime = System.currentTimeMillis() +delayTime;
    }

    public int getRetrycount() {
        return retrycount;
    }

    public void setRetrycount(int retrycount) {
        this.retrycount = retrycount;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(this.delayTime - System.currentTimeMillis(),TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        return 0;
    }
}
