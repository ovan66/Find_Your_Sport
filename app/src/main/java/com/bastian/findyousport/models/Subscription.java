package com.bastian.findyousport.models;

/**
 * Created by cutiko on 19-01-17.
 */

public class Subscription {

    private String uid, mail;

    public Subscription() {
    }

    public Subscription(String uid, String mail) {
        this.uid = uid;
        this.mail = mail;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
