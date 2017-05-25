package com.lingang.api.domain.entity;

import java.io.Serializable;

public class SysStationIndustry implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 7394608073130714264L;

	private Integer stationIndustryId;

    private Integer stationId;

    private Integer industryId;

    public Integer getStationIndustryId() {
        return stationIndustryId;
    }

    public void setStationIndustryId(Integer stationIndustryId) {
        this.stationIndustryId = stationIndustryId;
    }

    public Integer getStationId() {
        return stationId;
    }

    public void setStationId(Integer stationId) {
        this.stationId = stationId;
    }

    public Integer getIndustryId() {
        return industryId;
    }

    public void setIndustryId(Integer industryId) {
        this.industryId = industryId;
    }
}