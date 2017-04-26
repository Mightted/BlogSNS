package com.mightted.blogsns.domain.entity;

import com.bluelinelabs.logansquare.annotation.JsonObject;

/**
 * Created by 晓深 on 2017/4/25.
 */

@JsonObject(fieldDetectionPolicy = JsonObject.FieldDetectionPolicy.NONPRIVATE_FIELDS)
public class NewsItem {

    public String id;
    public String title;
    public String user;
    public String scan;
    public String time;
}
