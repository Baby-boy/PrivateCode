package com.yd.dby.app.entity;

import java.util.Date;

public class YdWish {
    private Integer wishId;

    private Integer userId;

    private Date wishCreatedTime;

    private String wishContent;

    public Integer getWishId() {
        return wishId;
    }

    public void setWishId(Integer wishId) {
        this.wishId = wishId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getWishCreatedTime() {
        return wishCreatedTime;
    }

    public void setWishCreatedTime(Date wishCreatedTime) {
        this.wishCreatedTime = wishCreatedTime;
    }

    public String getWishContent() {
        return wishContent;
    }

    public void setWishContent(String wishContent) {
        this.wishContent = wishContent == null ? null : wishContent.trim();
    }
}