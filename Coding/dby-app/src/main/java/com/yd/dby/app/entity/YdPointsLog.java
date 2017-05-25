package com.yd.dby.app.entity;

public class YdPointsLog {
    private Integer plId;

    private Integer plUserid;

    private String plUsername;

    private Integer plAdminid;

    private String plAdminname;

    private Integer plPoints;

    private String plAddtime;

    private String plDesc;

    private String plStage;

    public Integer getPlId() {
        return plId;
    }

    public void setPlId(Integer plId) {
        this.plId = plId;
    }

    public Integer getPlUserid() {
        return plUserid;
    }

    public void setPlUserid(Integer plUserid) {
        this.plUserid = plUserid;
    }

    public String getPlUsername() {
        return plUsername;
    }

    public void setPlUsername(String plUsername) {
        this.plUsername = plUsername == null ? null : plUsername.trim();
    }

    public Integer getPlAdminid() {
        return plAdminid;
    }

    public void setPlAdminid(Integer plAdminid) {
        this.plAdminid = plAdminid;
    }

    public String getPlAdminname() {
        return plAdminname;
    }

    public void setPlAdminname(String plAdminname) {
        this.plAdminname = plAdminname == null ? null : plAdminname.trim();
    }

    public Integer getPlPoints() {
        return plPoints;
    }

    public void setPlPoints(Integer plPoints) {
        this.plPoints = plPoints;
    }

    public String getPlAddtime() {
        return plAddtime;
    }

    public void setPlAddtime(String plAddtime) {
        this.plAddtime = plAddtime == null ? null : plAddtime.trim();
    }

    public String getPlDesc() {
        return plDesc;
    }

    public void setPlDesc(String plDesc) {
        this.plDesc = plDesc == null ? null : plDesc.trim();
    }

    public String getPlStage() {
        return plStage;
    }

    public void setPlStage(String plStage) {
        this.plStage = plStage == null ? null : plStage.trim();
    }
}