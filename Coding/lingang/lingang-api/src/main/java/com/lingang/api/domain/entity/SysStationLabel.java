package com.lingang.api.domain.entity;

import java.io.Serializable;

public class SysStationLabel implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -7268527939006796347L;

	private Integer stationLabelId;

    private Integer stationId;

    private Integer labelId;

    public Integer getStationLabelId() {
        return stationLabelId;
    }

    public void setStationLabelId(Integer stationLabelId) {
        this.stationLabelId = stationLabelId;
    }

    public Integer getStationId() {
        return stationId;
    }

    public void setStationId(Integer stationId) {
        this.stationId = stationId;
    }

    public Integer getLabelId() {
        return labelId;
    }

    public void setLabelId(Integer labelId) {
        this.labelId = labelId;
    }
}