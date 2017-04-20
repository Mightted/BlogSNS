package com.mightted.blogsns.Bean;

/**
 * Created by 晓深 on 2017/4/12.
 */

public class NewsItem {

    String id;
    private String title;
    private String user;
    private String scan;
    private String comment;

    public NewsItem(String title,String id) {
        this.title = title;
        this.id = id;
        user = "user";
        scan = "-";
        comment = "-";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getScan() {
        return scan;
    }

    public void setScan(String scan) {
        this.scan = scan;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
