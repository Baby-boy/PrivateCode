package com.lingang.api.domain.entity;

import java.io.Serializable;

public class SysPartnerUmanager implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 8806460637597658620L;

	private Integer partnerUmanagerId;

    private Integer partnerId;

    private Integer umanagerId;

    private String userAccount;

    public Integer getPartnerUmanagerId() {
        return partnerUmanagerId;
    }

    public void setPartnerUmanagerId(Integer partnerUmanagerId) {
        this.partnerUmanagerId = partnerUmanagerId;
    }

    public Integer getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(Integer partnerId) {
        this.partnerId = partnerId;
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