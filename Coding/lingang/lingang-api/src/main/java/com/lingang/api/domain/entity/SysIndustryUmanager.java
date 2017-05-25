package com.lingang.api.domain.entity;

import java.io.Serializable;

public class SysIndustryUmanager implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -5374997396156404244L;

	private Integer industryUmanagerId;

    private Integer industryId;

    private String userAccount;

    public Integer getIndustryUmanagerId() {
        return industryUmanagerId;
    }

    public void setIndustryUmanagerId(Integer industryUmanagerId) {
        this.industryUmanagerId = industryUmanagerId;
    }

    public Integer getIndustryId() {
        return industryId;
    }

    public void setIndustryId(Integer industryId) {
        this.industryId = industryId;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount == null ? null : userAccount.trim();
    }
}