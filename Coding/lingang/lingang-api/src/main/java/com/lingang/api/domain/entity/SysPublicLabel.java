package com.lingang.api.domain.entity;

import java.io.Serializable;

public class SysPublicLabel implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 9040362367503372974L;

	private Integer publicLabelId;

    private Integer labelId;

    private Integer publicId;

    public Integer getPublicLabelId() {
        return publicLabelId;
    }

    public void setPublicLabelId(Integer publicLabelId) {
        this.publicLabelId = publicLabelId;
    }

    public Integer getLabelId() {
        return labelId;
    }

    public void setLabelId(Integer labelId) {
        this.labelId = labelId;
    }

    public Integer getPublicId() {
        return publicId;
    }

    public void setPublicId(Integer publicId) {
        this.publicId = publicId;
    }
}