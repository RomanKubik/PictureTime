package com.example.kubik.picturetime.models.photos;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Kubik on 1/12/17.
 */

public class PhotoDetails {

    @SerializedName("id")
    private String id;
    @SerializedName("likes")
    private int likes;
    @SerializedName("liked_by_user")
    private boolean isLiked;
    @SerializedName("exif")
    private CameraSettings settings;
    @SerializedName("location")
    private PhotoLocation location;
    @SerializedName("user")
    private User user;
    @SerializedName("urls")
    private Urls urls;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public boolean isLiked() {
        return isLiked;
    }

    public void setLiked(boolean liked) {
        isLiked = liked;
    }

    public CameraSettings getSettings() {
        return settings;
    }

    public void setSettings(CameraSettings settings) {
        this.settings = settings;
    }

    public PhotoLocation getLocation() {
        return location;
    }

    public void setLocation(PhotoLocation location) {
        this.location = location;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Urls getUrls() {
        return urls;
    }

    public void setUrls(Urls urls) {
        this.urls = urls;
    }

}
