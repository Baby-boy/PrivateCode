package com.lingang.api.domain.vo;

import java.io.Serializable;

public class SysParkStatisticsVo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4596626639742834970L;

	private Integer parkId;
	
	private String parkName;
	
	private Integer num;
	
	private Integer tnum;
	
	private Integer fnum;

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

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public Integer getTnum() {
		return tnum;
	}

	public void setTnum(Integer tnum) {
		this.tnum = tnum;
	}

	public Integer getFnum() {
		return fnum;
	}

	public void setFnum(Integer fnum) {
		this.fnum = fnum;
	}

	
}
