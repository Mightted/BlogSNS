package com.mightted.blogsns.domain.entity;

import com.bluelinelabs.logansquare.annotation.JsonObject;

/**
 * Created by 晓深 on 2017/4/19.
 */

@JsonObject(fieldDetectionPolicy = JsonObject.FieldDetectionPolicy.NONPRIVATE_FIELDS)
public class User {

    public String status; //Ok
    public String userName;
    public String email;
    public String headImgPath;
}
