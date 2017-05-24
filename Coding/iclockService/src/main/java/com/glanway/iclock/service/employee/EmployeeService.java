package com.glanway.iclock.service.employee;

import com.glanway.iclock.entity.employee.Employee;
import com.glanway.iclock.service.BaseService;

/**
 * 
 * @author zs
 *
 */
public interface EmployeeService extends BaseService<Employee> {

	

	public void saveEmployee(Employee employee);

	/**
	 * 
	 * @param id
	 * 
	 * @return
	 * 
	 * @author zs
	 * @date 2017-04-14
	 */
	Employee getInfo(Long id);
	
	
	/**
	 * 
	 * 说明 : 
	 *  根据员工代码,查询员工信息
	 * @param code 员工代码
	 * @return
	 * @author zhangshaung
	 * @dateTime 2017年4月19日 上午10:03:17
	 */
	Employee getInfoByCode(String code);
	
	

	/**
	 * 
	 * @param employee
	 * @return
	 * @author zhangshaung
	 * 
	 */
	int updateEmployee(Employee employee);

}
