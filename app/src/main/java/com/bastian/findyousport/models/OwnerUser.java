package com.bastian.findyousport.models;

import java.io.Serializable;

/**
 * Created by Bastian on 19-12-2016.
 */

public class OwnerUser implements Serializable{

    private String uid, nameLocal, location, email, facebook, key, category, Description, phoneNum;


    public OwnerUser() {
    }


    public OwnerUser(String uid, String nameLocal, String location, String email, String facebook, String key, String category, String description, String phoneNum) {
        this.uid = uid;
        this.nameLocal = nameLocal;
        this.location = location;
        this.email = email;
        this.facebook = facebook;
        this.key = key;
        this.category = category;
        Description = description;
        this.phoneNum = phoneNum;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getNameLocal() {
        return nameLocal;
    }

    public void setNameLocal(String nameLocal) {
        this.nameLocal = nameLocal;
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

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
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
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }
}
