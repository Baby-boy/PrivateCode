package com.lingang.api.domain.entity;

import java.io.Serializable;
import java.util.Date;

public class SysPolicy implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 3958703555673357667L;

	private Integer policyId;

    private Integer imgId;

    private String policyTitle;

    private Date createTime;

    private Integer policyState;

    private String policyContent;

    public Integer getPolicyId() {
        return policyId;
    }

    public void setPolicyId(Integer policyId) {
        this.policyId = policyId;
    }

    public Integer getImgId() {
        return imgId;
    }

    public void setImgId(Integer imgId) {
        this.imgId = imgId;
    }

    public String getPolicyTitle() {
        return policyTitle;
    }

    public void setPolicyTitle(String policyTitle) {
        this.policyTitle = policyTitle == null ? null : policyTitle.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getPolicyState() {
        return policyState;
    }

    public void setPolicyState(Integer policyState) {
        this.policyState = policyState;
    }

    public String getPolicyContent() {
        return policyContent;
    }

    public void setPolicyContent(String policyContent) {
        this.policyContent = policyContent == null ? null : policyContent.trim();
    }
}