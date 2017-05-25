package com.lingang.api.domain.vo;

import java.util.ArrayList;
import java.util.List;

import com.lingang.api.domain.entity.SysIndustry;
import com.lingang.api.domain.entity.SysIndustryUmanager;
import com.lingang.api.domain.entity.SysLabel;
import com.lingang.api.domain.entity.SysPark;
import com.lingang.api.domain.entity.SysUmanager;

public class SysIndustryVo extends SysIndustry {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6592133884180036928L;
	private String imgPath;
	private Integer imgId;
	private Integer parkImgId;
	private String parkImgPath;

	private Integer industryImgId;
	private String industryImgPath;

	// 集群缩略图ID(本来SysIndustry里面有的，但selectSysIndustryVoById查询语句里的子查询覆盖了父级minImgId，后来扩展使用时定义了iminImgId)
	private Integer iminImgId;

	private List<SysPark> park = new ArrayList<SysPark>();

	private List<SysIndustryVo> parks = new ArrayList<SysIndustryVo>();
	private List<SysLabel> labels = new ArrayList<SysLabel>();
	private List<SysLabel> label = new ArrayList<SysLabel>();
	
	private List<SysIndustryUmanager> iumans=new ArrayList<SysIndustryUmanager>();

	private List<SysUmanager> umanagers = new ArrayList<SysUmanager>();

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	public Integer getImgId() {
		return imgId;
	}

	public void setImgId(Integer imgId) {
		this.imgId = imgId;
	}

	public Integer getParkImgId() {
		return parkImgId;
	}

	public void setParkImgId(Integer parkImgId) {
		this.parkImgId = parkImgId;
	}

	public String getParkImgPath() {
		return parkImgPath;
	}

	public void setParkImgPath(String parkImgPath) {
		this.parkImgPath = parkImgPath;
	}

	public Integer getIndustryImgId() {
		return industryImgId;
	}

	public void setIndustryImgId(Integer industryImgId) {
		this.industryImgId = industryImgId;
	}

	public String getIndustryImgPath() {
		return industryImgPath;
	}

	public void setIndustryImgPath(String industryImgPath) {
		this.industryImgPath = industryImgPath;
	}

	public List<SysPark> getPark() {
		return park;
	}

	public void setPark(List<SysPark> park) {
		this.park = park;
	}

	public List<SysIndustryVo> getParks() {
		return parks;
	}

	public void setParks(List<SysIndustryVo> parks) {
		this.parks = parks;
	}

	public List<SysLabel> getLabels() {
		return labels;
	}

	public void setLabels(List<SysLabel> labels) {
		this.labels = labels;
	}

	public List<SysLabel> getLabel() {
		return label;
	}

	public void setLabel(List<SysLabel> label) {
		this.label = label;
	}

	public Integer getIminImgId() {
		return iminImgId;
	}

	public void setIminImgId(Integer iminImgId) {
		this.iminImgId = iminImgId;
	}

	public List<SysIndustryUmanager> getIumans() {
		return iumans;
	}

	public void setIumans(List<SysIndustryUmanager> iumans) {
		this.iumans = iumans;
	}

	public List<SysUmanager> getUmanagers() {
		return umanagers;
	}

	public void setUmanagers(List<SysUmanager> umanagers) {
		this.umanagers = umanagers;
	}

}
