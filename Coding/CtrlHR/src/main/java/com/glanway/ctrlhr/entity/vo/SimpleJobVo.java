package com.glanway.ctrlhr.entity.vo;

public class SimpleJobVo {
	private Long id; // ID
	private String name;// 职位名称
	private Long jobTypeId;// 职位类型
	private Long jobGradeId;// 职位等级

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

}
