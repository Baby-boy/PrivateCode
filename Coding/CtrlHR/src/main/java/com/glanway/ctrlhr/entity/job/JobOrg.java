package com.glanway.ctrlhr.entity.job;

import com.glanway.ctrlhr.entity.BaseEntity;

import java.util.List;

public class JobOrg extends BaseEntity {

	private static final long serialVersionUID = 370977013136778732L;
	
	private Long parentId; // 上级编制ID

	private Long companyId; // 公司ID

	private Long deptId; // 部门ID

	private Long jobId; // 职位ID

	private Long organizeNum; // 编制人数

	private Long workSystemId; // 工作制ID

	private List<OrgDept> orgDeptList; // 管辖的子部门集合

	private Integer organizeState; // 状态(1:开启,2:关闭)

	private String deleted; // 是否删除(0:未删除,1:删除)

	private String deptIds; // 部门ID(备注: 多个ID使用","分隔)

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public Long getDeptId() {
		return deptId;
	}

	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}

	public Long getJobId() {
		return jobId;
	}

	public void setJobId(Long jobId) {
		this.jobId = jobId;
	}

	public Long getOrganizeNum() {
		return organizeNum;
	}

	public void setOrganizeNum(Long organizeNum) {
		this.organizeNum = organizeNum;
	}

	public Long getWorkSystemId() {
		return workSystemId;
	}

	public void setWorkSystemId(Long workSystemId) {
		this.workSystemId = workSystemId;
	}

	public List<OrgDept> getOrgDeptList() {
		return orgDeptList;
	}

	public void setOrgDeptList(List<OrgDept> orgDeptList) {
		this.orgDeptList = orgDeptList;
	}

	public Integer getOrganizeState() {
		return organizeState;
	}

	public void setOrganizeState(Integer organizeState) {
		this.organizeState = organizeState;
	}

	public String getDeleted() {
		return deleted;
	}

	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}

	public String getDeptIds() {
		return deptIds;
	}

	public void setDeptIds(String deptIds) {
		this.deptIds = deptIds;
	}

}