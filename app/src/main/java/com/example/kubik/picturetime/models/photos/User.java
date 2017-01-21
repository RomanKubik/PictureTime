package com.example.kubik.picturetime.models.photos;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Kubik on 1/12/17.
 */
public class User {

    @SerializedName("id")
    private String id;
    @SerializedName("username")
    private String username;
    @SerializedName("bio")
    private String bio;
    @SerializedName("profile_image")
    private ProfileImage profileImage;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public ProfileImage getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(ProfileImage profileImage) {
        this.profileImage = profileImage;
    }
}
