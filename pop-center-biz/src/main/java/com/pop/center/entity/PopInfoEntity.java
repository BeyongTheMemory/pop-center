package com.pop.center.entity;

import java.util.Date;

/**
 * Created by xugang on 16/8/8.
 */
public class PopInfoEntity {
    private long popId;
    private String imgUrl;
    private String message;
    private String userName;
    private long userId;
    private String userIntroduction;
    private String userHeadUrl;
    private Date updateTime;
    private Date createTime;
    public int onlyOnce;

    public long getPopId() {
        return popId;
    }

    public void setPopId(long popId) {
        this.popId = popId;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
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

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getOnlyOnce() {
        return onlyOnce;
    }

    public void setOnlyOnce(int onlyOnce) {
        this.onlyOnce = onlyOnce;
    }
}
