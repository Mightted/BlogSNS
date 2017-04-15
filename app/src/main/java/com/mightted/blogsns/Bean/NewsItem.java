package com.mightted.blogsns.Bean;

/**
 * Created by 晓深 on 2017/4/12.
 */

public class NewsItem {

    private String title;
    private String user;
    private int scan;
    private int comment;

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

    public int getScan() {
        return scan;
    }

    public void setScan(int scan) {
        this.scan = scan;
    }

    public int getComment() {
        return comment;
    }

    public void setComment(int comment) {
        this.comment = comment;
    }
}
