package com.glanway.ctrlhr.service.salaryStall.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.glanway.ctrlhr.dao.salaryStall.SalaryStallDao;
import com.glanway.ctrlhr.entity.vo.SimpleSalaryStall;
import com.glanway.ctrlhr.service.salaryStall.SalaryStallService;

@Transactional
@Service("salaryStallService")
public class SalaryStallServiceImpl implements SalaryStallService {
	@Autowired
	private SalaryStallDao salaryStallDao;
	
	public List<SimpleSalaryStall> findSimpleSalaryStall(String jobGradeId){
		return salaryStallDao.findSimpleByJobGradeId(jobGradeId);
	}
}
