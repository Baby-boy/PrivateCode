package com.glanway.ctrlhr.service.dept.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.glanway.ctrlhr.dao.dept.DeptDao;
import com.glanway.ctrlhr.entity.dept.Dept;
import com.glanway.ctrlhr.entity.vo.SimpleDeptVo;
import com.glanway.ctrlhr.service.BaseServiceImpl;
import com.glanway.ctrlhr.service.dept.DeptService;

@Transactional
@Service("deptService")
public class DeptServiceImpl extends BaseServiceImpl<Dept> implements DeptService{

	@Autowired
	private DeptDao deptDao;
	
	@Override
	public List<SimpleDeptVo> findsimpleList(String sn) {
		
		return deptDao.findDeptList(sn);
	}

}
