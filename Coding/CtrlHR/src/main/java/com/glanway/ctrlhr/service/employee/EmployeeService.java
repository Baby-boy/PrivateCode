package com.glanway.ctrlhr.service.employee;

import com.glanway.ctrlhr.common.Page;
import com.glanway.ctrlhr.entity.employee.Employee;
import com.glanway.ctrlhr.entity.para.DeviceToEmployeePara;
import com.glanway.ctrlhr.entity.para.EmployeePara;
import com.glanway.ctrlhr.entity.vo.DeviceToEmployeeVo;
import com.glanway.ctrlhr.entity.vo.EmployeeSignInfoVo;
import com.glanway.ctrlhr.entity.vo.EmployeeVo;
import com.glanway.ctrlhr.service.BaseService;

public interface EmployeeService extends BaseService<Employee> {

	/**
	 * 说明 : 查询员工分页列表
	 * 
	 * @param para
	 * @return
	 * @author 付其浩
	 * @dateTime 2017年4月18日 下午6:11:18
	 */
	public Page<EmployeeVo> findList(EmployeePara para);

	/**
	 * 说明 : 新增员工
	 * 
	 * @param employee
	 * @author 付其浩
	 * @dateTime 2017年4月20日 下午2:58:49
	 */
	public void saveEmployee(Employee employee);

	/**
	 * 说明 : 根据ID获取员工信息
	 * 
	 * @param id
	 * @return
	 * @author 张爽
	 * @dateTime 2017年4月20日 下午2:58:17
	 */
	public EmployeeVo getInfo(Long id);

	/**
	 * 说明 : 编辑员工信息
	 * 
	 * @param employee
	 * @return
	 * @author 张爽
	 * @dateTime 2017年4月20日 下午3:03:08
	 */
	public void updateEmployee(Employee employee);

	/**
	 * 说明 : 查询设备下员工列表
	 * 
	 * @param para
	 * @return
	 * @author 高伟南
	 * @dateTime 2017年4月18日 上午10:11:18
	 */
	public Page<DeviceToEmployeeVo> findDeviceToEmployeeList(DeviceToEmployeePara para);

	/**
	 * 说明 : 员工考勤采集信息
	 * 
	 * @param id
	 * @param code
	 * @return
	 * @author 付其浩
	 * @dateTime 2017年4月24日 下午3:08:14
	 */
	public EmployeeSignInfoVo findSignInfo(Long id, String code);

}
