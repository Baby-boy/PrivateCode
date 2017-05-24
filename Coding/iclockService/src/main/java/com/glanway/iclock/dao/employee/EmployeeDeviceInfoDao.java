package com.glanway.iclock.dao.employee;

import com.glanway.iclock.dao.BaseDao;
import com.glanway.iclock.entity.employee.EmployeeDeviceInfo;

public interface EmployeeDeviceInfoDao extends BaseDao<EmployeeDeviceInfo>{
    int deleteByPrimaryKey(Long id);

    int insert(EmployeeDeviceInfo record);

    int insertSelective(EmployeeDeviceInfo record);

    EmployeeDeviceInfo selectByPrimaryKey(Long id);
    
    EmployeeDeviceInfo selectByEmployeeCode(String employeeCode);

    int updateByPrimaryKeySelective(EmployeeDeviceInfo record);

    int updateByPrimaryKey(EmployeeDeviceInfo record);
}