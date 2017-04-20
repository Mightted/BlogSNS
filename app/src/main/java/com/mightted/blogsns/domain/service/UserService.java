package com.mightted.blogsns.domain.service;

import com.mightted.blogsns.domain.entity.User;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by 晓深 on 2017/4/19.
 */

public interface UserService {
    @FormUrlEncoded
    @POST("login/login_login")
    Observable<User> getLogin(@Field("userName")String username,@Field("password")String password);


}
