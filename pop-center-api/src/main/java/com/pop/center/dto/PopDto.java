package com.pop.center.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by xugang on 16/8/6.
 */
public class PopDto implements Serializable{
    private long id;
    private int type;
    private double longitude;
    private double latitude;
    private double altitude;
    private long userId;
    private String userName;
    private int model;
    private int isShowy;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getAltitude() {
        return altitude;
    }

    public void setAltitude(double altitude) {
        this.altitude = altitude;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getModel() {
        return model;
    }

    public void setModel(int model) {
        this.model = model;
    }

    public int getIsShowy() {
        return isShowy;
    }

    public void setIsShowy(int isShowy) {
        this.isShowy = isShowy;
    }
}
