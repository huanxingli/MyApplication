package com.example.administrator.myapplication;


public class LokApp extends android.app.Application {

    private static LokApp mApplication = null;

    public static LokApp getInstance() {
        return mApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mApplication = this;

    }

}
