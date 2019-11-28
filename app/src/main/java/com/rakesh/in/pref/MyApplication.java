package com.rakesh.in.pref;

import android.app.Application;

import com.rakesh.in.BuildConfig;

import timber.log.Timber;

public class MyApplication extends Application {

    private static MyApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        plantTimber();
    }

    private void plantTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }


    public static MyApplication getInstance() {
        return mInstance;
    }
}
