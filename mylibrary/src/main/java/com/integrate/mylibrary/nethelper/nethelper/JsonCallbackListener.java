package com.integrate.mylibrary.nethelper.nethelper;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.alibaba.fastjson.JSON;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by 梁明伟 on 2019/6/1.
 * Copyright © 2018年 CETC. All rights reserved.
 */
public class JsonCallbackListener<T> implements CallBackListener {

    private Class<T> responseClass;
    private Handler mHandler = new Handler(Looper.getMainLooper());
    private IJsonDataListener mJsondDataListener;

    public JsonCallbackListener(Class<T> responseClass,IJsonDataListener mJsondDataListener){
        this.mJsondDataListener = mJsondDataListener;
        this.responseClass = responseClass;
    }

    @Override
    public void onSuccess(InputStream inputStream) {
        String response = getContent(inputStream);
        final T clazz = JSON.parseObject(response,responseClass);
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                mJsondDataListener.onSuccess(clazz);
            }
        });

    }

    private String getContent(InputStream inputStream) {
        String content = null;
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder sb = new StringBuilder();
            String line = null;
            try {
                while ((line = reader.readLine())!= null){
                    sb.append(line);
                }
            }catch (IOException e){
                Log.e(getClass().getName(),e.toString());
            }finally {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return sb.toString();

        }catch (Exception e){
            e.printStackTrace();
        }
        return content;
    }

    @Override
    public void onFailure(String reason) {

    }

}
