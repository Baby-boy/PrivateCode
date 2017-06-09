package com.glanway.ctrlhr.entity.job;

import com.glanway.ctrlhr.entity.BaseEntity;

public class JobType extends BaseEntity {

	private static final long serialVersionUID = 370977013136778732L;

	private String name; // 职位类型名称

	private String deleted; // 是否删除(0:未删除,1:删除)

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDeleted() {
		return deleted;
	}

	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}


}