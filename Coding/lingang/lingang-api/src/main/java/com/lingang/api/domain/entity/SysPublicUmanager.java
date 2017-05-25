package com.lingang.api.domain.entity;

import java.io.Serializable;

public class SysPublicUmanager implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -3357006317357528047L;

	private Integer publicUmanagerId;

    private Integer publicId;

    private Integer umanagerId;

    private String userAccount;

    public Integer getPublicUmanagerId() {
        return publicUmanagerId;
    }

    public void setPublicUmanagerId(Integer publicUmanagerId) {
        this.publicUmanagerId = publicUmanagerId;
    }

    public Integer getPublicId() {
        return publicId;
    }

    public void setPublicId(Integer publicId) {
        this.publicId = publicId;
    }

    public Integer getUmanagerId() {
        return umanagerId;
    }

    public void setUmanagerId(Integer umanagerId) {
        this.umanagerId = umanagerId;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount == null ? null : userAccount.trim();
    }
}