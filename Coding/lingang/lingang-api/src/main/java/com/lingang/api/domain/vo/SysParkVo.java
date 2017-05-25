package com.lingang.api.domain.vo;

import java.util.ArrayList;
import java.util.List;

import com.lingang.api.domain.entity.SysCompany;
import com.lingang.api.domain.entity.SysLabel;
import com.lingang.api.domain.entity.SysPark;
import com.lingang.api.domain.entity.SysParkUmanager;
import com.lingang.api.domain.entity.SysUmanager;
import com.lingang.api.domain.entity.SysWay;

public class SysParkVo extends SysPark {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5663360930640100320L;

	// 收藏的状态码
	private Integer collectState;// 1:收藏 , 0:未收藏

	private Integer collectId;// 收藏ID

	private Integer imgId;

	private String imgPath;

	private Integer parkImgId;
	private String parkImgPath;
	// 产业园区详情属性字段
	private String maxImgPath;

	private String regionName;

	// private Integer wayId;

	// private String wayTel;

	// 该字段是根据需求添加
	private String parkCompany;
	// 添加的关联(一对多关系_一个产业园区由多家公司维护)
	private List<SysCompany> companys = new ArrayList<SysCompany>();

	private List<SysWay> ways = new ArrayList<SysWay>();

	private List<SysLabel> labels = new ArrayList<SysLabel>();

	private List<SysIndustryVo> industrys = new ArrayList<SysIndustryVo>();

	private List<SysFileAdImgVo> files = new ArrayList<SysFileAdImgVo>();
	
	private List<SysParkUmanager> pumans=new ArrayList<SysParkUmanager>();

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

	public String getMaxImgPath() {
		return maxImgPath;
	}

	public void setMaxImgPath(String maxImgPath) {
		this.maxImgPath = maxImgPath;
	}

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	public String getParkCompany() {
		return parkCompany;
	}

	public void setParkCompany(String parkCompany) {
		this.parkCompany = parkCompany;
	}

	public List<SysCompany> getCompanys() {
		return companys;
	}

	public void setCompanys(List<SysCompany> companys) {
		this.companys = companys;
	}

	public List<SysWay> getWays() {
		return ways;
	}

	public void setWays(List<SysWay> ways) {
		this.ways = ways;
	}

	public List<SysLabel> getLabels() {
		return labels;
	}

	public void setLabels(List<SysLabel> labels) {
		this.labels = labels;
	}

	public List<SysIndustryVo> getIndustrys() {
		return industrys;
	}

	public void setIndustrys(List<SysIndustryVo> industrys) {
		this.industrys = industrys;
	}

	public List<SysFileAdImgVo> getFiles() {
		return files;
	}

	public void setFiles(List<SysFileAdImgVo> files) {
		this.files = files;
	}

	public List<SysParkUmanager> getPumans() {
		return pumans;
	}

	public void setPumans(List<SysParkUmanager> pumans) {
		this.pumans = pumans;
	}

	public List<SysUmanager> getUmanagers() {
		return umanagers;
	}

	public void setUmanagers(List<SysUmanager> umanagers) {
		this.umanagers = umanagers;
	}

}
