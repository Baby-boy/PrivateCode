package com.lingang.api.domain.vo;

import java.util.ArrayList;
import java.util.List;

import com.lingang.api.domain.entity.SysCompany;
import com.lingang.api.domain.entity.SysLabel;
import com.lingang.api.domain.entity.SysPublic;
import com.lingang.api.domain.entity.SysPublicUmanager;
import com.lingang.api.domain.entity.SysUmanager;
import com.lingang.api.domain.entity.SysWay;

public class SysPublicVo extends SysPublic {

	private static final long serialVersionUID = 4409203622196932441L;

	// 收藏的状态码
	private Integer collectState;// 1:收藏 , 0:未收藏

	private Integer collectId;// 收藏ID

	private Integer imgId;

	private String imgPath;

	private String parkName;

	private List<SysLabel> labels = new ArrayList<SysLabel>();

	private List<SysWay> ways = new ArrayList<SysWay>();
	// 公共平台详情字段属性
	// private Integer wayId;
	// private String wayTel;
	private String basicsName;

	private List<SysParkVo> parks = new ArrayList<SysParkVo>();

	// 添加的关联(一对多关系_一个合作伙伴由多家公司维护)
	private List<SysCompany> companys = new ArrayList<SysCompany>();
	
	private List<SysPublicUmanager> pumans=new ArrayList<SysPublicUmanager>();

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

	public String getParkName() {
		return parkName;
	}

	public void setParkName(String parkName) {
		this.parkName = parkName;
	}

	public List<SysLabel> getLabels() {
		return labels;
	}

	public void setLabels(List<SysLabel> labels) {
		this.labels = labels;
	}

	public List<SysWay> getWays() {
		return ways;
	}

	public void setWays(List<SysWay> ways) {
		this.ways = ways;
	}

	public String getBasicsName() {
		return basicsName;
	}

	public void setBasicsName(String basicsName) {
		this.basicsName = basicsName;
	}

	public List<SysParkVo> getParks() {
		return parks;
	}

	public void setParks(List<SysParkVo> parks) {
		this.parks = parks;
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

	public List<SysPublicUmanager> getPumans() {
		return pumans;
	}

	public void setPumans(List<SysPublicUmanager> pumans) {
		this.pumans = pumans;
	}

}
