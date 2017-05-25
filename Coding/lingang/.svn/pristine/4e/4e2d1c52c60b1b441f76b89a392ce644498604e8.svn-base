package com.lingang.api.domain.vo;

import java.util.ArrayList;
import java.util.List;

import com.lingang.api.domain.entity.SysCompany;
import com.lingang.api.domain.entity.SysLabel;
import com.lingang.api.domain.entity.SysPark;
import com.lingang.api.domain.entity.SysStation;
import com.lingang.api.domain.entity.SysStationUmanager;
import com.lingang.api.domain.entity.SysUmanager;

public class SysStationVo extends SysStation {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4976369767451195602L;

	// 收藏的状态码
	private Integer collectState;// 1:收藏 , 0:未收藏

	private Integer collectId;// 收藏ID

	private String imgPath;

	private List<SysPark> parks = new ArrayList<SysPark>();
	private List<SysParkVo> parksvo = new ArrayList<SysParkVo>();
	private List<SysIndustryVo> industrys = new ArrayList<SysIndustryVo>();
	private List<SysLabel> labels = new ArrayList<SysLabel>();
	
	private List<SysStationUmanager> sumans=new ArrayList<SysStationUmanager>();
	private List<SysUmanager> umanagers = new ArrayList<SysUmanager>();

	// 添加的关联(一对多关系_一个合作伙伴由多家公司维护)
	private List<SysCompany> companys = new ArrayList<SysCompany>();

	public Integer getCollectState() {
		return collectState;
	}

	public void setCollectState(Integer collectState) {
		this.collectState = collectState;
	}

	public Integer getCollectId() {
		return collectId;
	}

	public void setCollectId(Integer collectId) {
		this.collectId = collectId;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	public List<SysPark> getParks() {
		return parks;
	}

	public void setParks(List<SysPark> parks) {
		this.parks = parks;
	}

	public List<SysIndustryVo> getIndustrys() {
		return industrys;
	}

	public void setIndustrys(List<SysIndustryVo> industrys) {
		this.industrys = industrys;
	}

	public List<SysLabel> getLabels() {
		return labels;
	}

	public void setLabels(List<SysLabel> labels) {
		this.labels = labels;
	}

	public List<SysUmanager> getUmanagers() {
		return umanagers;
	}

	public void setUmanagers(List<SysUmanager> umanagers) {
		this.umanagers = umanagers;
	}

	public List<SysParkVo> getParksvo() {
		return parksvo;
	}

	public void setParksvo(List<SysParkVo> parksvo) {
		this.parksvo = parksvo;
	}

	public List<SysCompany> getCompanys() {
		return companys;
	}

	public void setCompanys(List<SysCompany> companys) {
		this.companys = companys;
	}

	public List<SysStationUmanager> getSumans() {
		return sumans;
	}

	public void setSumans(List<SysStationUmanager> sumans) {
		this.sumans = sumans;
	}

}
