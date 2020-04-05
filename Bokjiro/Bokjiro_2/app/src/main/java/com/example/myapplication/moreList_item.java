package com.example.myapplication;

public class moreList_item {
    String more_title;
    String more_content;
    String more_id;
    boolean noti =true; // 알람여부

    public moreList_item(String more_title,String more_content,String more_id,boolean noti){
        this.more_title = more_title;
        this.more_content = more_content;
        this.more_id = more_id;
        this.noti = noti;
    }

    public String getMore_title() {
        return more_title;
    }

    public void setMore_title(String more_title) {
        this.more_title = more_title;
    }

    public String getMore_content() {
        return more_content;
    }

    public void setMore_content(String more_content) {
        this.more_content = more_content;
    }

    public String getMore_id() {
        return more_id;
    }

    public void setMore_id(String more_id) {
        this.more_id = more_id;
    }

    public boolean isNoti() {
        return noti;
    }

    public void setNoti(boolean noti) {
        this.noti = noti;
    }
}
