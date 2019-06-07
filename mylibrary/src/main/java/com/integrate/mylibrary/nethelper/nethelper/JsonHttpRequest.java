package com.integrate.mylibrary.nethelper.nethelper;

import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by 梁明伟 on 2019/6/1.
 * Copyright © 2018年 CETC. All rights reserved.
 */
public class JsonHttpRequest implements IHttpRequest {

    private String url;
    private byte[] data;
    private CallBackListener mCallbacklistener;
    private HttpURLConnection urlConnection;

    @Override
    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public void setData(byte[] bytes) {
        this.data = bytes;
    }

    @Override
    public void setListener(CallBackListener callBackListener) {
        this.mCallbacklistener = callBackListener;
    }

    @Override
    public void execute() {
        //执行具体的访问网络的操作
        URL url = null;
            try {
                url = new URL(this.url);
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setConnectTimeout(6000);
                urlConnection.setUseCaches(false);
                urlConnection.setInstanceFollowRedirects(true);
                urlConnection.setReadTimeout(3000);
                urlConnection.setDoInput(true);
                urlConnection.setDoOutput(true);
                urlConnection.setRequestMethod("POST");
                urlConnection.setRequestProperty("Content-Type","application/json;charset=UTF-8");
                urlConnection.connect();
                OutputStream out = urlConnection.getOutputStream();
                BufferedOutputStream bos = new BufferedOutputStream(out);
                bos.write(data);
                bos.flush();
                out.close();
                bos.close();

                //字节流写入数据
                if(urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    InputStream in = urlConnection.getInputStream();
                    mCallbacklistener.onSuccess(in);
                }else{
                    mCallbacklistener.onFailure("失败");
                    throw new RuntimeException("请求失败");
                }
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("请求失败");
            }finally {
                urlConnection.disconnect();
            }
    }
}
