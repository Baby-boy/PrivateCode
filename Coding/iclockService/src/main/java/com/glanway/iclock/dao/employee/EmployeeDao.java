package com.glanway.iclock.dao.employee;

import org.apache.ibatis.annotations.Param;

import com.glanway.iclock.dao.BaseDao;
import com.glanway.iclock.entity.employee.Employee;

public interface EmployeeDao extends BaseDao<Employee> {

    public int deleteByPrimaryKey(Long id);

    public int insert(Employee record);

    public int insertSelective(Employee record);

    public Employee selectByPrimaryKey(Long id);

    public Employee selectByCode(String code);

    public int updateByPrimaryKeySelective(Employee record);

    public int updateByPrimaryKey(Employee record);

    /** 根据职员代码查询员工是否离职 */
    public Employee findOneByEmployeeCode(@Param("employeeCode") String employeeCode);

}