package com.lingang.api.domain.vo;

import java.util.ArrayList;
import java.util.List;

import com.lingang.api.domain.entity.SysCompany;
import com.lingang.api.domain.entity.SysLabel;
import com.lingang.api.domain.entity.SysPark;
import com.lingang.api.domain.entity.SysPartner;
import com.lingang.api.domain.entity.SysPartnerUmanager;
import com.lingang.api.domain.entity.SysUmanager;

public class SysPartnerVo extends SysPartner {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7894234648382315228L;

	// 收藏的状态码
	private Integer collectState;// 1:收藏 , 0:未收藏

	private Integer collectId;// 收藏ID

	private String imgPath;

	private String basicsName;

	private Integer minImgId;

	// 新添加字段
	private String typeName;

	private List<SysPark> parks = new ArrayList<SysPark>();

	private List<SysLabel> labels = new ArrayList<SysLabel>();
	
	private List<SysPartnerUmanager> pumans=new ArrayList<SysPartnerUmanager>();

	private List<SysUmanager> umanagers = new ArrayList<SysUmanager>();

	// 添加的关联(一对多关系_一个合作伙伴由多家公司维护)
	private List<SysCompany> companys = new ArrayList<SysCompany>();

	// 签署单位
	private List<SysCompany> signCompanys = new ArrayList<SysCompany>();

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

	public String getBasicsName() {
		return basicsName;
	}

	public void setBasicsName(String basicsName) {
		this.basicsName = basicsName;
	}

	public Integer getMinImgId() {
		return minImgId;
	}

	public void setMinImgId(Integer minImgId) {
		this.minImgId = minImgId;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public List<SysPark> getParks() {
		return parks;
	}

	public void setParks(List<SysPark> parks) {
		this.parks = parks;
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

	public List<SysCompany> getCompanys() {
		return companys;
	}

	public void setCompanys(List<SysCompany> companys) {
		this.companys = companys;
	}

	public List<SysCompany> getSignCompanys() {
		return signCompanys;
	}

	public void setSignCompanys(List<SysCompany> signCompanys) {
		this.signCompanys = signCompanys;
	}

	public List<SysPartnerUmanager> getPumans() {
		return pumans;
	}

	public void setPumans(List<SysPartnerUmanager> pumans) {
		this.pumans = pumans;
	}

}
