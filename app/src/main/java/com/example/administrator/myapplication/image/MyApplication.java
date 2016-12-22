package com.example.administrator.myapplication.image;

import android.app.Application;

import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

/**
 * Created by Administrator on 2016/12/19.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
//        ImageLoaderConfiguration configuration = ImageLoaderConfiguration
//                .createDefault(this);
        ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(this)
                .writeDebugLogs()
//                .memoryCache(new WeakMemoryCache())
                .build();

        ImageLoader.getInstance().init(configuration);
    }
}
