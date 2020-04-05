package com.example.myapplication;

import android.graphics.drawable.Drawable;

public class RecItem {

    private Drawable image;
    private String title;
    private String duration;
    private String dday;

    public Drawable getImage(){
        return image;
    }

    public void setImage(Drawable image){
        this.image = image;
    }

    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public String getDuration(){
        return duration;
    }

    public void setDuration(String duration){
        this.duration = duration;
    }

    public String getDday(){
        return dday;
    }
    public void setDday(String dday){
        this.dday = dday;
    }
}

