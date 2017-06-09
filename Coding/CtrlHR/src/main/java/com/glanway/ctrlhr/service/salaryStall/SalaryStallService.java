package com.glanway.ctrlhr.service.salaryStall;

import java.util.List;

import com.glanway.ctrlhr.entity.vo.SimpleSalaryStall;

public interface SalaryStallService {
	List<SimpleSalaryStall> findSimpleSalaryStall(String jobGradeId);
}
