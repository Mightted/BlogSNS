package com.mightted.blogsns.http.retrofit;

import com.github.aurae.retrofit2.LoganSquareConverterFactory;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * Created by 晓深 on 2017/4/19.
 */

public class RetrofitClient {

    private static RetrofitClient mClient = new RetrofitClient();
    private Retrofit retrofit;

    private RetrofitClient() {
        retrofit = new Retrofit.Builder()
                .baseUrl("http://172.29.106.248:8079/blog_parent/")
                .addConverterFactory(LoganSquareConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public static RetrofitClient getInstance() {
        return mClient;
    }

    public <T>T create(Class<?> clazz) {
        return (T)retrofit.create(clazz);
    }
}
