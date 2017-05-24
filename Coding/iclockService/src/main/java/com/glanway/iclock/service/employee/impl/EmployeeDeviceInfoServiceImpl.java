/**
 * @author zhangshuang
 * 2017年4月19日 上午11:04:21
 */
package com.glanway.iclock.service.employee.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.glanway.iclock.dao.employee.EmployeeDeviceInfoDao;
import com.glanway.iclock.entity.employee.EmployeeDeviceInfo;
import com.glanway.iclock.service.BaseServiceImpl;
import com.glanway.iclock.service.employee.EmployeeDeviceInfoService;

/**
 * 说明 : 
 * 员工的考勤机信息(密码,指纹模板,人脸模板等),service的实现类
 * @author zhangshaung
 * @version 1.0.0
 * @dateTime 2017年4月19日 上午11:04:21
 */
@Transactional
@Service("employeeDeviceInfoService")
public class EmployeeDeviceInfoServiceImpl extends BaseServiceImpl<EmployeeDeviceInfo>
		implements EmployeeDeviceInfoService {

	@Autowired
	private EmployeeDeviceInfoDao employeeDeviceInfoDao;

	/**
	 * 
	 * 说明 : 
	 * 根据Id查询员工的考勤机信息
	 * @param id
	 * @return
	 * @author zhangshaung
	 * @dateTime 2017年4月19日 上午11:06:54
	 */
	@Override
	public EmployeeDeviceInfo getInfoById(Long id) {
		// TODO Auto-generated method stub
		return employeeDeviceInfoDao.selectByPrimaryKey(id);
	}

	/**
	 * 
	 * 说明 : 
	 * 根据员工代码employeeCode查询员工的考勤机信息
	 * @param employeeCode
	 * @return
	 * @author zhangshaung
	 * @dateTime 2017年4月19日 上午11:06:54
	 */
	@Override
	public EmployeeDeviceInfo getInfoByEmployeeCode(String employeeCode) {
		// TODO Auto-generated method stub
		return employeeDeviceInfoDao.selectByEmployeeCode(employeeCode);
	}
}
