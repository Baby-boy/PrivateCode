package com.lingang.api.domain.vo;

import java.io.Serializable;

public class SysRegionStatisticsVo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8633549784753108831L;
	
	private Integer regionId;

    private String regionName;

    private Integer num;

	public Integer getRegionId() {
		return regionId;
	}

	public void setRegionId(Integer regionId) {
		this.regionId = regionId;
	}

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

}
