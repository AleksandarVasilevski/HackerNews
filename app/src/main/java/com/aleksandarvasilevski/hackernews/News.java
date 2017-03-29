package com.aleksandarvasilevski.hackernews;



public class News {

    private String mTitle;
    private String mDescription;

    public News(String title, String description){
        mTitle = title;
        mDescription = description;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getDescription() {
        return mDescription;
    }
}