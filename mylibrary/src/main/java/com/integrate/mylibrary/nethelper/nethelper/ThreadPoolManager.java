package com.integrate.mylibrary.nethelper.nethelper;

import android.util.Log;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by 梁明伟 on 2019/6/1.
 * Copyright © 2018年 CETC. All rights reserved.
 */
public class ThreadPoolManager {

    private static ThreadPoolManager threadPoolManager = null;

    public  static  ThreadPoolManager getInatance(){

        if(threadPoolManager == null) {
            synchronized (ThreadPoolManager.class){
                if(threadPoolManager == null) {
                    threadPoolManager = new ThreadPoolManager();
                }
            }
        }
        return  threadPoolManager;
    }

    /**
     *队列排序模式 先进先出
     */
    private LinkedBlockingQueue<Runnable> linkedBlockingQueue = new LinkedBlockingQueue();

    /**
     * 延时队列
     *
     */

    private DelayQueue<HttpTask> mDelayQueue = new DelayQueue<>();


    /**
     * 将异步任务添加到队列中
     * @param runnable
     */
    public void addTask(Runnable runnable){
        if(runnable != null) {
            try {
                linkedBlockingQueue.put(runnable);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void addDelayTask(HttpTask task){
        if(task != null) {
            task.setDelayTime(3000);
            mDelayQueue.offer(task);
        }
    }

    /**
     * 创建线程池
     */

    private ThreadPoolExecutor threadPoolExecutor;

    /**
     * 在构造函数中初始化线程池
     */
    private ThreadPoolManager(){
        threadPoolExecutor = new ThreadPoolExecutor(3, 10, 15, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(4), new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                addTask(r);
            }
        });
        //线程池需要处理核心线程
        threadPoolExecutor.execute(coreThread);
        threadPoolExecutor.execute(delayThread);
    }

    /**
     * 创建核心线程，不停地去队列中获取请求交给线程池处理
     */

    public Runnable coreThread = new Runnable() {
        @Override
        public void run() {
            Runnable rrun = null;
            while (true){
                try {
                    rrun = linkedBlockingQueue.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                threadPoolExecutor.execute(rrun);
            }
        }
    };

    public Runnable delayThread = new Runnable() {
        HttpTask ht = null;
        @Override
        public void run() {
            while (true){
                try {
                    ht = mDelayQueue.take();
                    if(ht.getRetrycount()<3) {
                        threadPoolExecutor.execute(ht);
                        ht.setRetrycount(ht.getRetrycount()+1);
                        Log.e("===重试机制==>",ht.getRetrycount()+""+System.currentTimeMillis());
                    }else{
                        Log.e("===重试机制==>","失败次数过多 放弃");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
//                threadPoolExecutor.execute(ht);

            }
        }
    };

}
