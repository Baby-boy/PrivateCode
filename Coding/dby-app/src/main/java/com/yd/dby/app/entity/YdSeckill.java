package com.yd.dby.app.entity;

public class YdSeckill {
    private Integer seckillId;

    private String seckillTitle;

    private String seckillSubtitle;

    private String seckillLogo;

    private String seckillCover;

    private Byte seckillSort;

    private Byte seckillState;

    private String startTime;

    private String endTime;

    private String createTime;

    public Integer getSeckillId() {
        return seckillId;
    }

    public void setSeckillId(Integer seckillId) {
        this.seckillId = seckillId;
    }

    public String getSeckillTitle() {
        return seckillTitle;
    }

    public void setSeckillTitle(String seckillTitle) {
        this.seckillTitle = seckillTitle == null ? null : seckillTitle.trim();
    }

    public String getSeckillSubtitle() {
        return seckillSubtitle;
    }

    public void setSeckillSubtitle(String seckillSubtitle) {
        this.seckillSubtitle = seckillSubtitle == null ? null : seckillSubtitle.trim();
    }

    public String getSeckillLogo() {
        return seckillLogo;
    }

    public void setSeckillLogo(String seckillLogo) {
        this.seckillLogo = seckillLogo == null ? null : seckillLogo.trim();
    }

    public String getSeckillCover() {
        return seckillCover;
    }

    public void setSeckillCover(String seckillCover) {
        this.seckillCover = seckillCover == null ? null : seckillCover.trim();
    }

    public Byte getSeckillSort() {
        return seckillSort;
    }

    public void setSeckillSort(Byte seckillSort) {
        this.seckillSort = seckillSort;
    }

    public Byte getSeckillState() {
        return seckillState;
    }

    public void setSeckillState(Byte seckillState) {
        this.seckillState = seckillState;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime == null ? null : startTime.trim();
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime == null ? null : endTime.trim();
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }
}