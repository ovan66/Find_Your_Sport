package com.bastian.findyousport.models;

/**
 * Created by santo_000 on 29-11-2016.
 */

    public class Event {

    private String uid, nameLocal, sportName, price, schedules, location, phoneNum, email, facebook;

    public Event() {
    }

    public Event(String uid, String nameLocal, String sportName, String price, String schedules, String location, String phoneNum, String email, String facebook) {
        this.uid = uid;
        this.nameLocal = nameLocal;
        this.sportName = sportName;
        this.price = price;
        this.schedules = schedules;
        this.location = location;
        this.phoneNum = phoneNum;
        this.email = email;
        this.facebook = facebook;
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
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

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
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
}
