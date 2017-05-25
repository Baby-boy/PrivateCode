package com.lingang.api.domain.entity;

import java.io.Serializable;

public class SysParkLabel implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 6579573334706383369L;

	private Integer parkLabelId;

    private Integer parkId;

    private Integer labelId;

    public Integer getParkLabelId() {
        return parkLabelId;
    }

    public void setParkLabelId(Integer parkLabelId) {
        this.parkLabelId = parkLabelId;
    }

    public Integer getParkId() {
        return parkId;
    }

    public void setParkId(Integer parkId) {
        this.parkId = parkId;
    }

    public Integer getLabelId() {
        return labelId;
    }

    public void setLabelId(Integer labelId) {
        this.labelId = labelId;
    }
}