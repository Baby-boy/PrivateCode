package com.lingang.api.domain.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SysFileParkVo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2392456258917530734L;
	
	private Integer parkId;
	
	private String parkName;
	
	private List<SysFileVo> files=new ArrayList<SysFileVo>();

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

	public List<SysFileVo> getFiles() {
		return files;
	}

	public void setFiles(List<SysFileVo> files) {
		this.files = files;
	}
	
}
