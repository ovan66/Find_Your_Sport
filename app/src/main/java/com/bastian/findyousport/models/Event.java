package com.bastian.findyousport.models;

import android.widget.EditText;

import com.bastian.findyousport.views.create.partials.InputEmail;
import com.bastian.findyousport.views.create.partials.InputNumber;
import com.bastian.findyousport.views.create.partials.InputText;
import com.bastian.findyousport.views.create.partials.SpinnerCategories;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by santo_000 on 29-11-2016.
 */

    public class Event implements Serializable {

    private String uid, nameLocal, sportName, schedules, location, email, facebook, key, category;
    private int price, phoneNum;

    public Event() {
    }

    public Event(String uid, String nameLocal, String sportName, int price, String category, String schedules, String location, int phoneNum, String email, String facebook, String key) {
        this.uid = uid;
        this.nameLocal = nameLocal;
        this.sportName = sportName;
        this.price = price;
        this.schedules = schedules;
        this.location = location;
        this.phoneNum = phoneNum;
        this.email = email;
        this.facebook = facebook;
        this.key = key;
        this.category = category;
    }

    public Event(String uid, InputText institutionName, InputText sportName, InputNumber price, SpinnerCategories category, EditText schedules, EditText location, InputNumber phoneNum, InputEmail email, EditText facebook, String key) {
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

    public String getSportName() {
        return sportName;
    }

    public void setSportName(String sportName) {
        this.sportName = sportName;
    }


    public String getSchedules() {
        return schedules;
    }

    public void setSchedules(String schedules) {
        this.schedules = schedules;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(int phoneNum) {
        this.phoneNum = phoneNum;
    }
}
