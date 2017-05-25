package com.lingang.api.domain.vo;

import java.io.Serializable;

public class SysParkRegionVo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5061859687683888117L;
	
	private Integer parkId;
	
    private String parkName;
    
    private Integer regionType;

	public Integer getParkId() {
		return parkId;
	}

	public void setParkId(Integer parkId) {
		this.parkId = parkId;
	}

	public String getParkName() {
		return parkName;
	}

	public void setParkName(String parkName) {
		this.parkName = parkName;
	}

	public Integer getRegionType() {
		return regionType;
	}

	public void setRegionType(Integer regionType) {
		this.regionType = regionType;
	}
    
}
