package com.pop.center.dto.pop;

import java.io.Serializable;

/**
 * Created by xugang on 16/8/9.
 * pop留言
 */
public class PopMessageDto implements Serializable{
    private long id;
    private long popId;
    private String message;
    private long userId;
    private String userName;
    private String userHeadUrl;
    private String introduction;
    private int sex;
    private double latitude;
    private double longitude;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getPopId() {
        return popId;
    }

    public void setPopId(long popId) {
        this.popId = popId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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

    public String getUserHeadUrl() {
        return userHeadUrl;
    }

    public void setUserHeadUrl(String userHeadUrl) {
        this.userHeadUrl = userHeadUrl;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
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
