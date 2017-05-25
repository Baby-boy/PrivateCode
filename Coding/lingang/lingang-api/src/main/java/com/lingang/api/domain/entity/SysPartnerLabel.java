package com.lingang.api.domain.entity;

import java.io.Serializable;

public class SysPartnerLabel implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -2328995409155154866L;

	private Integer partnerLabelId;

    private Integer partnerId;

    private Integer labelId;

    public Integer getPartnerLabelId() {
        return partnerLabelId;
    }

    public void setPartnerLabelId(Integer partnerLabelId) {
        this.partnerLabelId = partnerLabelId;
    }

    public Integer getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(Integer partnerId) {
        this.partnerId = partnerId;
    }

    public Integer getLabelId() {
        return labelId;
    }

    public void setLabelId(Integer labelId) {
        this.labelId = labelId;
    }
}