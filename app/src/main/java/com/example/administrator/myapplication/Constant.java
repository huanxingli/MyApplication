package com.example.administrator.myapplication;

import android.os.Environment;

import java.io.File;

/**
 * Created by Administrator on 2016/12/19.
 */

public class Constant {
    public static String FirstFolder = "xunai";//一级目录
    public static String SecondFolder = "photos";//二级目录
    public final static String ALBUM_XUNAI_PATH = Environment.getExternalStorageDirectory() + File.separator + FirstFolder + File.separator;
    public final static String ALBUM_PHOTOS_PATH = ALBUM_XUNAI_PATH + SecondFolder + File.separator;

    public static final int PHOTO_ACTION_CAMERA = 0x10;
    public static final int PHOTO_ACTION_CROP = 0x13;
}
