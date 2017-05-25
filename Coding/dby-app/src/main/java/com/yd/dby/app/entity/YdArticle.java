package com.yd.dby.app.entity;

import java.util.Date;

public class YdArticle {
    private Integer articleId;

    private Integer acId;

    private String articleUrl;

    private Boolean articleShow;

    private Byte articleSort;

    private String articleTitle;

    private Date articleTime;

    private String articleContent;

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public Integer getAcId() {
        return acId;
    }

    public void setAcId(Integer acId) {
        this.acId = acId;
    }

    public String getArticleUrl() {
        return articleUrl;
    }

    public void setArticleUrl(String articleUrl) {
        this.articleUrl = articleUrl == null ? null : articleUrl.trim();
    }

    public Boolean getArticleShow() {
        return articleShow;
    }

    public void setArticleShow(Boolean articleShow) {
        this.articleShow = articleShow;
    }

    public Byte getArticleSort() {
        return articleSort;
    }

    public void setArticleSort(Byte articleSort) {
        this.articleSort = articleSort;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle == null ? null : articleTitle.trim();
    }

    public Date getArticleTime() {
        return articleTime;
    }

    public void setArticleTime(Date articleTime) {
        this.articleTime = articleTime;
    }

    public String getArticleContent() {
        return articleContent;
    }

    public void setArticleContent(String articleContent) {
        this.articleContent = articleContent == null ? null : articleContent.trim();
    }
}