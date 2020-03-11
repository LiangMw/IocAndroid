package com.integrate.netstudyapplication.util;

import android.os.Handler;
import android.util.Log;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by 梁明伟 on 2019/6/14.
 * Copyright © 2018年 CETC. All rights reserved.
 */
public class MyRunnable implements Runnable {
    TextView textView;
    long timestamp;
    Handler handler;
    private onStop onStop;
    public MyRunnable(TextView textView, long timestamp, Handler handler) {
        this.textView = textView;
        this.timestamp = timestamp;
        this.handler = handler;
    }

    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */

    @Override
    public void run() {
        Log.e("---mRunnable----tag",timestamp+"");
        if(timestamp<=0) {
           if(onStop != null) {
               onStop.action();
               return;
           }else{
               handler.removeCallbacks(this);
               return;
           }
        }
        textView.setText(getTime(timestamp));
        handler.postDelayed(this,1000);

    }

    private String getTime(long timestamp){
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
        long lt = timestamp-1000;
        this.timestamp -=1000;
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;

    }

    public interface onStop{
        void action();
    }

    public void setOnStop(onStop onStop){
        this.onStop = onStop;
    }
}
