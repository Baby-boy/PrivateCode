package com.glanway.ctrlhr.service.api.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.glanway.ctrlhr.common.Page;
import com.glanway.ctrlhr.dao.api.ApiDao;
import com.glanway.ctrlhr.entity.para.api.ApiDeptPara;
import com.glanway.ctrlhr.entity.para.api.ApiEmployeePara;
import com.glanway.ctrlhr.entity.para.api.ApiJobPara;
import com.glanway.ctrlhr.entity.vo.api.ApiDeptVo;
import com.glanway.ctrlhr.entity.vo.api.ApiEmployeeVo;
import com.glanway.ctrlhr.entity.vo.api.ApiJobVo;
import com.glanway.ctrlhr.service.api.ApiService;

@Service("apiService")
public class ApiServiceImpl implements ApiService {

	@Autowired
	private ApiDao apiDao;

	@Override
	public Page<ApiEmployeeVo> findEmployeeList(ApiEmployeePara para) {

		if (StringUtils.isNotEmpty(para.getJobStates())) {
			String[] jobStateList = StringUtils.split(para.getJobStates(), ",");
			para.setJobStateList(jobStateList);
		}
		if (StringUtils.isNotEmpty(para.getJobIds())) {
			String[] jobIdList = StringUtils.split(para.getJobIds(), ",");
			para.setJobIdList(jobIdList);
		}
		if (StringUtils.isNotEmpty(para.getDeptIds())) {
			String[] deptIdList = StringUtils.split(para.getDeptIds(), ",");
			para.setDeptIdList(deptIdList);
		}

		int count = apiDao.findEmployeeListCount(para);

		Page<ApiEmployeeVo> page = new Page<>(para.getPage(), count, para.getPageSize());
		if (para.getPage() <= page.getCountPage()) {
			para.setStartIndex(page.getStartIndex());
			List<ApiEmployeeVo> list = apiDao.findEmployeeList(para);
			page.setList(list);
		}

		return page;
	}

	@Override
	public Page<ApiDeptVo> findDeptList(ApiDeptPara para) {
		int count = apiDao.findDeptListCount(para);

		Page<ApiDeptVo> page = new Page<>(para.getPage(), count, para.getPageSize());
		if (para.getPage() <= page.getCountPage()) {
			para.setStartIndex(page.getStartIndex());
			List<ApiDeptVo> list = apiDao.findDeptList(para);
			page.setList(list);
		}

		return page;
	}

	@Override
	public Page<ApiJobVo> findJobList(ApiJobPara para) {

		if (StringUtils.isNotEmpty(para.getDeptIds())) {
			String[] deptIdList = StringUtils.split(para.getDeptIds(), ",");
			para.setDeptIdList(deptIdList);
		}

		int count = apiDao.findJobListCount(para);

		Page<ApiJobVo> page = new Page<>(para.getPage(), count, para.getPageSize());
		if (para.getPage() <= page.getCountPage()) {
			para.setStartIndex(page.getStartIndex());
			List<ApiJobVo> list = apiDao.findJobList(para);
			page.setList(list);
		}

		return page;
	}

}
