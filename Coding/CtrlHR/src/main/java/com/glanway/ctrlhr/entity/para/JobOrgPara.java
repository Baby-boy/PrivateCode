package com.glanway.ctrlhr.entity.para;

import com.glanway.ctrlhr.util.TimeUtil;

import java.util.Date;

public class JobOrgPara extends BasePara {

	private Long companyId; // 公司ID

	private String jobType; // 职位类型

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public String getJobType() {
		return jobType;
	}

	public void setJobType(String jobType) {
		this.jobType = jobType;
	}

}
