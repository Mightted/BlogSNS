package com.mightted.blogsns.Utils;

import android.app.Application;
import android.content.Context;

/**
 * Created by 晓深 on 2017/4/10.
 */

public class MyApplication extends Application {

    public static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
    }

    public static Context getContext() {
        return mContext;
    }
}
