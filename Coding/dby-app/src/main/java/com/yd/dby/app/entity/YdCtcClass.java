package com.yd.dby.app.entity;

public class YdCtcClass {
    private Integer ccId;

    private Integer ccPid;

    private Integer ccSort;

    private String ccName;

    private String ccImgsrc;

    private Boolean ccIsRecommend;

    private Integer ccTotalGoods;

    public Integer getCcId() {
        return ccId;
    }

    public void setCcId(Integer ccId) {
        this.ccId = ccId;
    }

    public Integer getCcPid() {
        return ccPid;
    }

    public void setCcPid(Integer ccPid) {
        this.ccPid = ccPid;
    }

    public Integer getCcSort() {
        return ccSort;
    }

    public void setCcSort(Integer ccSort) {
        this.ccSort = ccSort;
    }

    public String getCcName() {
        return ccName;
    }

    public void setCcName(String ccName) {
        this.ccName = ccName == null ? null : ccName.trim();
    }

    public String getCcImgsrc() {
        return ccImgsrc;
    }

    public void setCcImgsrc(String ccImgsrc) {
        this.ccImgsrc = ccImgsrc == null ? null : ccImgsrc.trim();
    }

    public Boolean getCcIsRecommend() {
        return ccIsRecommend;
    }

    public void setCcIsRecommend(Boolean ccIsRecommend) {
        this.ccIsRecommend = ccIsRecommend;
    }

    public Integer getCcTotalGoods() {
        return ccTotalGoods;
    }

    public void setCcTotalGoods(Integer ccTotalGoods) {
        this.ccTotalGoods = ccTotalGoods;
    }
}