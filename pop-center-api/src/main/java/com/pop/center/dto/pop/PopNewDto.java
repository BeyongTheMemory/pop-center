package com.pop.center.dto.pop;

import java.io.Serializable;

/**
 * Created by xugang on 16/8/9.
 */
public class PopNewDto implements Serializable{
    private int type;
    private double longitude;
    private double latitude;
    private double altitude;
    private long userId;
    private String userName;
    private int model;
    private int isShowy;
    private String imgUrl;
    private String message;
    private String userIntroduction;
    private String userHeadUrl;
    public int onlyOnce;
    private String introduction;
    private int sex;


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

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUserIntroduction() {
        return userIntroduction;
    }

    public void setUserIntroduction(String userIntroduction) {
        this.userIntroduction = userIntroduction;
    }

    public String getUserHeadUrl() {
        return userHeadUrl;
    }

    public void setUserHeadUrl(String userHeadUrl) {
        this.userHeadUrl = userHeadUrl;
    }

    public int getIsShowy() {
        return isShowy;
    }

    public void setIsShowy(int isShowy) {
        this.isShowy = isShowy;
    }

    public int getOnlyOnce() {
        return onlyOnce;
    }

    public void setOnlyOnce(int onlyOnce) {
        this.onlyOnce = onlyOnce;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }
}
