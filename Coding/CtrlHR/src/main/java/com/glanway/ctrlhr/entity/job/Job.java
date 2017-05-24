package com.glanway.ctrlhr.entity.job;

import com.glanway.ctrlhr.entity.BaseEntity;

public class Job extends BaseEntity {

	private static final long serialVersionUID = 370977013136778732L;

	private String name;// 职位名称

	private Long jobTypeId;// 职位类型

	private Long jobGradeId;// 职位等级

	private Integer state;// 状态(1:开启,2:关闭)

	private String deleted;// 是否删除(0:未删除,1:删除)

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getJobTypeId() {
		return jobTypeId;
	}

	public void setJobTypeId(Long jobTypeId) {
		this.jobTypeId = jobTypeId;
	}

	public Long getJobGradeId() {
		return jobGradeId;
	}

	public void setJobGradeId(Long jobGradeId) {
		this.jobGradeId = jobGradeId;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getDeleted() {
		return deleted;
	}

	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}

}