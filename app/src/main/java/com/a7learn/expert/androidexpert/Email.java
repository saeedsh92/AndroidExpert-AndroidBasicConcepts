package com.a7learn.expert.androidexpert;

/**
 * Created by user on 10/26/2017.
 */

public class Email {
    private String title;
    private String description;
    private boolean isAds;
    private int imageResourceId;
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    public void setImageResourceId(int imageResourceId) {
        this.imageResourceId = imageResourceId;
    }

    public boolean isAds() {
        return isAds;
    }

    public void setAds(boolean ads) {
        isAds = ads;
    }
}
