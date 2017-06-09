package com.glanway.ctrlhr.dao.salaryStall;

import java.util.List;

import com.glanway.ctrlhr.dao.BaseDao;
import com.glanway.ctrlhr.entity.salaryStall.SalaryStall;
import com.glanway.ctrlhr.entity.vo.SimpleSalaryStall;

public interface SalaryStallDao extends BaseDao<SalaryStall>{
	List<SimpleSalaryStall> findSimpleByJobGradeId(String jobGradeId);
}
