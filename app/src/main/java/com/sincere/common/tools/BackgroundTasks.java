/**
 * 主线程工具
 */
package com.sincere.common.tools;

import android.os.Handler;

public class BackgroundTasks {

    private Handler mHandler = new Handler();


    public void runOnUiThread(Runnable runnable) {
        mHandler.post(runnable);
    }


    private static BackgroundTasks instance;

    public static BackgroundTasks getInstance() {
        return instance;
    }

    public void delay(Runnable runnable, long delay) {
        mHandler.postDelayed(runnable, delay);
    }


    // 需要在主线程中初始化
    public static void initInstance() {
        instance = new BackgroundTasks();
    }


}
