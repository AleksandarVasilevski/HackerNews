package com.aleksandarvasilevski.hackernews;



public class News {

    private String mTitle;
    private String mDescription;
    private String mSource;

    public News(String title, String description, String source){
        mTitle = title;
        mDescription = description;
        mSource = source;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getDescription() {
        return mDescription;
    }

    public String getSource() { return mSource; }
}