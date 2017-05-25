package com.lingang.api.domain.vo;

import java.util.ArrayList;
import java.util.List;

import com.lingang.api.domain.entity.SysCompany;
import com.lingang.api.domain.entity.SysLabel;
import com.lingang.api.domain.entity.SysServiceUmanager;
import com.lingang.api.domain.entity.SysServiceWithBLOBs;
import com.lingang.api.domain.entity.SysUmanager;
import com.lingang.api.domain.entity.SysWay;

public class SysServiceVo extends SysServiceWithBLOBs {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3319157931435712263L;

	// 收藏的状态码
	private Integer collectState;// 1:收藏 , 0:未收藏

	private Integer collectId;// 收藏ID

	private Integer imgId;

	private String imgPath;

	private String basicsName;

	private List<SysLabel> labels = new ArrayList<SysLabel>();

	private List<SysServiceParkVo> parks = new ArrayList<SysServiceParkVo>();

	private List<SysWay> ways = new ArrayList<SysWay>();

	// 添加的关联(一对多关系_一个合作伙伴由多家公司维护)
	private List<SysCompany> companys = new ArrayList<SysCompany>();

	private List<SysServiceUmanager> sumans=new ArrayList<SysServiceUmanager>();

	private List<SysUmanager> umanagers = new ArrayList<SysUmanager>();

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

	public Integer getImgId() {
		return imgId;
	}

	public void setImgId(Integer imgId) {
		this.imgId = imgId;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	public List<SysLabel> getLabels() {
		return labels;
	}

	public void setLabels(List<SysLabel> labels) {
		this.labels = labels;
	}

	public String getBasicsName() {
		return basicsName;
	}

	public void setBasicsName(String basicsName) {
		this.basicsName = basicsName;
	}

	public List<SysServiceParkVo> getParks() {
		return parks;
	}

	public void setParks(List<SysServiceParkVo> parks) {
		this.parks = parks;
	}

	public List<SysWay> getWays() {
		return ways;
	}

	public void setWays(List<SysWay> ways) {
		this.ways = ways;
	}

	public List<SysCompany> getCompanys() {
		return companys;
	}

	public void setCompanys(List<SysCompany> companys) {
		this.companys = companys;
	}

	public List<SysUmanager> getUmanagers() {
		return umanagers;
	}

	public void setUmanagers(List<SysUmanager> umanagers) {
		this.umanagers = umanagers;
	}

	public List<SysServiceUmanager> getSumans() {
		return sumans;
	}

	public void setSumans(List<SysServiceUmanager> sumans) {
		this.sumans = sumans;
	}

}
