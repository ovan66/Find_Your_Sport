package com.bastian.findyousport.models;

/**
 * Created by santo_000 on 29-11-2016.
 */

public class User {

    public String name, email, user_id;

    public User() {
    }

    public User(String name, String email, String user_id) {
        this.name = name;
        this.email = email;
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}
