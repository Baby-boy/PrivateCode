package com.lingang.api.domain.pfvo;

import com.lingang.api.domain.entity.SysPark;

public class SysParkPfVo extends SysPark {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1273536268095397511L;

	private String regionName;

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
