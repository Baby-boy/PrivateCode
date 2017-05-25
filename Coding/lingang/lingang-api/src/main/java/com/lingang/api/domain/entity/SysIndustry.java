package com.lingang.api.domain.entity;

import java.io.Serializable;
import java.util.Date;

public class SysIndustry implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 3192898317604857908L;
	
	private Integer industryId;

    private Integer minImgId;

    private Integer maxImgId;

    private String industryTitle;

    private String industrySimple;

    private String industryLink;

    private Integer industryState;

    private Date createTime;

    private Date updateTime;

    private String industryContent;

    public Integer getIndustryId() {
        return industryId;
    }

    public void setIndustryId(Integer industryId) {
        this.industryId = industryId;
    }

    public Integer getMinImgId() {
        return minImgId;
    }

    public void setMinImgId(Integer minImgId) {
        this.minImgId = minImgId;
    }

    public Integer getMaxImgId() {
        return maxImgId;
    }

    public void setMaxImgId(Integer maxImgId) {
        this.maxImgId = maxImgId;
    }

    public String getIndustryTitle() {
        return industryTitle;
    }

    public void setIndustryTitle(String industryTitle) {
        this.industryTitle = industryTitle == null ? null : industryTitle.trim();
    }

    public String getIndustrySimple() {
        return industrySimple;
    }

    public void setIndustrySimple(String industrySimple) {
        this.industrySimple = industrySimple == null ? null : industrySimple.trim();
    }

    public String getIndustryLink() {
        return industryLink;
    }

    public void setIndustryLink(String industryLink) {
        this.industryLink = industryLink == null ? null : industryLink.trim();
    }

    public Integer getIndustryState() {
        return industryState;
    }

    public void setIndustryState(Integer industryState) {
        this.industryState = industryState;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getIndustryContent() {
        return industryContent;
    }

    public void setIndustryContent(String industryContent) {
        this.industryContent = industryContent == null ? null : industryContent.trim();
    }
}