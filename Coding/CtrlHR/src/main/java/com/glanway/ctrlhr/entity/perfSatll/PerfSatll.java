package com.glanway.ctrlhr.entity.perfSatll;

import com.glanway.ctrlhr.entity.BaseEntity;

public class PerfSatll extends BaseEntity {

	private static final long serialVersionUID = -6200013494900426363L;
	private String name; // 绩效档位名称
	private String salaryNum; // 薪资数目
	private String deleted; // 是否删除(0.否，1.是)

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSalaryNum() {
		return salaryNum;
	}

	public void setSalaryNum(String salaryNum) {
		this.salaryNum = salaryNum;
	}

	public String getDeleted() {
		return deleted;
	}

	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}

}
