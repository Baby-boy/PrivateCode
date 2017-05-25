package com.yd.gcj.entity;

public class YdMangerTPMsg {
    private Integer tpmsgId;

    private Integer userId;

    private String tpmsgType;

    private String tpmsgOpenId;

    private String tpmsgNikname;

    private String tpmsgAvatar;

    public Integer getTpmsgId() {
        return tpmsgId;
    }

    public void setTpmsgId(Integer tpmsgId) {
        this.tpmsgId = tpmsgId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getTpmsgType() {
        return tpmsgType;
    }

    public void setTpmsgType(String tpmsgType) {
        this.tpmsgType = tpmsgType == null ? null : tpmsgType.trim();
    }

    public String getTpmsgOpenId() {
        return tpmsgOpenId;
    }

    public void setTpmsgOpenId(String tpmsgOpenId) {
        this.tpmsgOpenId = tpmsgOpenId == null ? null : tpmsgOpenId.trim();
    }

    public String getTpmsgNikname() {
        return tpmsgNikname;
    }

    public void setTpmsgNikname(String tpmsgNikname) {
        this.tpmsgNikname = tpmsgNikname == null ? null : tpmsgNikname.trim();
    }

    public String getTpmsgAvatar() {
        return tpmsgAvatar;
    }

    public void setTpmsgAvatar(String tpmsgAvatar) {
        this.tpmsgAvatar = tpmsgAvatar == null ? null : tpmsgAvatar.trim();
    }
}