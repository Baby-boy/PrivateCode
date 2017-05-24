/**
 * @author zhangshuang
 * 2017年4月19日 上午11:01:53
 */
package com.glanway.iclock.service.employee;

import com.glanway.iclock.entity.employee.EmployeeDeviceInfo;
import com.glanway.iclock.service.BaseService;

/**
 * 说明 : 
 * 员工的考勤机信息(密码,指纹模板,人脸模板等),service
 * @author zhangshaung
 * @version 1.0.0
 * @dateTime 2017年4月19日 上午11:01:53
 */
public interface EmployeeDeviceInfoService extends BaseService<EmployeeDeviceInfo> {
	
	
	/**
	 * 
	 * 说明 : 
	 * 根据Id查询员工的考勤机信息
	 * @param id
	 * @return
	 * @author zhangshaung
	 * @dateTime 2017年4月19日 上午11:06:54
	 */
	EmployeeDeviceInfo getInfoById(Long id);
	
	/**
	 * 
	 * 说明 : 
	 * 根据员工代码employeeCode查询员工的考勤机信息
	 * @param employeeCode
	 * @return
	 * @author zhangshaung
	 * @dateTime 2017年4月19日 上午11:06:54
	 */
	EmployeeDeviceInfo getInfoByEmployeeCode(String employeeCode);
}
