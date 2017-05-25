package com.yd.dby.app.entity;

public class YdCtcComment {
    private Integer ctcCommentId;

    private Integer userId;

    private Integer sellerId;

    private Integer ctcOrderId;

    private Integer ctcId;

    private Integer ctcCommentGrade;

    private Integer ctcCommentScore;

    private String ctcCommentContent;

    private String ctcCommentPics;

    private Integer ctcCommentIsName;

    private Integer ctcCommentIsPic;

    private Integer ctcCommentTotalLike;

    private Integer ctcCommentTotalReply;

    private String ctcCommentCreatedTime;

    private String ctcCommentBuyTime;

    private String sellerReplayContent;

    public Integer getCtcCommentId() {
        return ctcCommentId;
    }

    public void setCtcCommentId(Integer ctcCommentId) {
        this.ctcCommentId = ctcCommentId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getSellerId() {
        return sellerId;
    }

    public void setSellerId(Integer sellerId) {
        this.sellerId = sellerId;
    }

    public Integer getCtcOrderId() {
        return ctcOrderId;
    }

    public void setCtcOrderId(Integer ctcOrderId) {
        this.ctcOrderId = ctcOrderId;
    }

    public Integer getCtcId() {
        return ctcId;
    }

    public void setCtcId(Integer ctcId) {
        this.ctcId = ctcId;
    }

    public Integer getCtcCommentGrade() {
        return ctcCommentGrade;
    }

    public void setCtcCommentGrade(Integer ctcCommentGrade) {
        this.ctcCommentGrade = ctcCommentGrade;
    }

    public Integer getCtcCommentScore() {
        return ctcCommentScore;
    }

    public void setCtcCommentScore(Integer ctcCommentScore) {
        this.ctcCommentScore = ctcCommentScore;
    }

    public String getCtcCommentContent() {
        return ctcCommentContent;
    }

    public void setCtcCommentContent(String ctcCommentContent) {
        this.ctcCommentContent = ctcCommentContent == null ? null : ctcCommentContent.trim();
    }

    public String getCtcCommentPics() {
        return ctcCommentPics;
    }

    public void setCtcCommentPics(String ctcCommentPics) {
        this.ctcCommentPics = ctcCommentPics == null ? null : ctcCommentPics.trim();
    }

    public Integer getCtcCommentIsName() {
        return ctcCommentIsName;
    }

    public void setCtcCommentIsName(Integer ctcCommentIsName) {
        this.ctcCommentIsName = ctcCommentIsName;
    }

    public Integer getCtcCommentIsPic() {
        return ctcCommentIsPic;
    }

    public void setCtcCommentIsPic(Integer ctcCommentIsPic) {
        this.ctcCommentIsPic = ctcCommentIsPic;
    }

    public Integer getCtcCommentTotalLike() {
        return ctcCommentTotalLike;
    }

    public void setCtcCommentTotalLike(Integer ctcCommentTotalLike) {
        this.ctcCommentTotalLike = ctcCommentTotalLike;
    }

    public Integer getCtcCommentTotalReply() {
        return ctcCommentTotalReply;
    }

    public void setCtcCommentTotalReply(Integer ctcCommentTotalReply) {
        this.ctcCommentTotalReply = ctcCommentTotalReply;
    }

    public String getCtcCommentCreatedTime() {
        return ctcCommentCreatedTime;
    }

    public void setCtcCommentCreatedTime(String ctcCommentCreatedTime) {
        this.ctcCommentCreatedTime = ctcCommentCreatedTime == null ? null : ctcCommentCreatedTime.trim();
    }

    public String getCtcCommentBuyTime() {
        return ctcCommentBuyTime;
    }

    public void setCtcCommentBuyTime(String ctcCommentBuyTime) {
        this.ctcCommentBuyTime = ctcCommentBuyTime == null ? null : ctcCommentBuyTime.trim();
    }

    public String getSellerReplayContent() {
        return sellerReplayContent;
    }

    public void setSellerReplayContent(String sellerReplayContent) {
        this.sellerReplayContent = sellerReplayContent == null ? null : sellerReplayContent.trim();
    }
}