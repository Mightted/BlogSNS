package com.mightted.blogsns.Utils;

import android.content.Context;
import android.net.ConnectivityManager;

/**
 * Created by 晓深 on 2017/4/16.
 */

public class NetworkUtil {

    private final static ConnectivityManager manager =(ConnectivityManager)MyApplication.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);


    public static boolean isNetAvailable() {
        return manager.getActiveNetworkInfo().isAvailable();
    }
}
