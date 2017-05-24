package com.glanway.iclock.dao.employee;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.glanway.iclock.dao.BaseDao;
import com.glanway.iclock.entity.employee.Employee;


public interface EmployeeDao extends BaseDao<Employee> {

	int deleteByPrimaryKey(Long id);

	int insert(Employee record);

	int insertSelective(Employee record);

	Employee selectByPrimaryKey(Long id);
	
	Employee selectByCode(String code);

	int updateByPrimaryKeySelective(Employee record);

	int updateByPrimaryKey(Employee record);

}