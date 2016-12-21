package com.bastian.findyousport.models;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Bastian on 19-12-2016.
 */

public class Profile implements Serializable{

    private String uid, name, location, email, facebook, category, description, phoneNum, photo;
    private List<String> photos;

    public Profile(String uid, String name, String location, String email, String facebook, String category, String description, String phoneNum, List<String> photos) {
        this.uid = uid;
        this.name = name;
        this.location = location;
        this.email = email;
        this.facebook = facebook;
        this.category = category;
        this.description = description;
        this.phoneNum = phoneNum;
        this.photos = photos;
    }

    public Profile() {
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getPhotos() {
        return photos;
    }

    public void setPhotos(List<String> photos) {
        this.photos = photos;
    }

    public Profile getSimplified() {
        Profile profile = new Profile();
        profile.setName(getName());
        profile.setLocation(getLocation());
        profile.setUid(getUid());
        profile.photo = getPhotos().get(0);
        return profile;
    }
}
