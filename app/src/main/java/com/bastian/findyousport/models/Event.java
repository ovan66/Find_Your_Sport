package com.bastian.findyousport.models;

import java.io.Serializable;
import java.util.List;

/**
 * Created by santo_000 on 29-11-2016.
 */

    public class Event implements Serializable {

    private String uid, name, start, end;
    private int price, vacants;
    private List<String> days;

    public Event() {
    }

    public Event(String uid, String name, String start, String end, int price, int vacants, List<String> days) {
        this.uid = uid;
        this.name = name;
        this.start = start;
        this.end = end;
        this.price = price;
        this.vacants = vacants;
        this.days = days;
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

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getVacants() {
        return vacants;
    }

    public void setVacants(int vacants) {
        this.vacants = vacants;
    }

    public List<String> getDays() {
        return days;
    }

    public void setDays(List<String> days) {
        this.days = days;
    }
}
