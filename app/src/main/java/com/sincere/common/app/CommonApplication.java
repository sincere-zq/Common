package com.sincere.common.app;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.sincere.common.tools.BackgroundTasks;


public class CommonApplication extends Application {
    private static CommonApplication application;

    public static Context getApplication() {
        return application.getApplicationContext();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        BackgroundTasks.initInstance();
    }
}
