package com.lingang.api.domain.para;

public class CompanyPara extends BasePara{

	/**
	 * 
	 */
	private static final long serialVersionUID = 105622717774222405L;
	
	private Integer objId;
	
	private Integer companyId;
	
	private Integer companyType;
	
	private Integer assortType;
	
	private String companyName;

	public Integer getObjId() {
		return objId;
	}

	public void setObjId(Integer objId) {
		this.objId = objId;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public Integer getCompanyType() {
		return companyType;
	}

	public void setCompanyType(Integer companyType) {
		this.companyType = companyType;
	}

	public Integer getAssortType() {
		return assortType;
	}

	public void setAssortType(Integer assortType) {
		this.assortType = assortType;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
}
