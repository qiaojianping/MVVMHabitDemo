package com.jianping.mymvvm.app;

import android.app.Application;

import me.goldze.mvvmhabit.utils.Utils;

public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Utils.init(this);
    }
}
