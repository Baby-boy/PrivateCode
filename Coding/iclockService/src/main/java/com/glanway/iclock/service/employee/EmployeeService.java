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
     * 根据职员ID查询信息.
     *
     * @author zs
     * @param id
     * @return
     * @since 1.0-20170414
     */
    Employee getInfo(Long id);

    /**
     * 
     * 根据员工代码,查询员工信息.
     * 
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

    /**
     * 根据职员代码查询员工是否离职
     *
     * @author fuqihao
     * @param employeeCode
     * @return
     * @since 1.0-20170531
     */
    public Employee findOne(String employeeCode);

}
