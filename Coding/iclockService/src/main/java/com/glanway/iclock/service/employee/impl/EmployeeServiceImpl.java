package com.glanway.iclock.service.employee.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.glanway.iclock.dao.employee.EmployeeDao;
import com.glanway.iclock.entity.employee.Employee;
import com.glanway.iclock.service.BaseServiceImpl;
import com.glanway.iclock.service.employee.EmployeeService;

@Transactional
@Service("employeeService")
public class EmployeeServiceImpl extends BaseServiceImpl<Employee> implements EmployeeService {

    @Autowired
    private EmployeeDao employeeDao;

    /**
     * 根据ID获得用户信息.
     * 
     * @param id
     * @return
     * @author zs
     * @date 2017-04-14
     */
    @Override
    public Employee getInfo(Long id) {
        return employeeDao.selectByPrimaryKey(id);
    }

    /**
     * 修改员工信息
     * 
     * @param employee
     * @return
     * @author zhangshaung 2017年4月14日 下午3:44:59
     */
    @Override
    public int updateEmployee(Employee employee) {

        employee.setLastModifiedDate(new Date());

        return employeeDao.updateByPrimaryKeySelective(employee);

    }

    /**
     * 新增员工
     * 
     * @author gwn
     * @date 2017-04-13
     * 
     * @param employee
     * @return
     */
    @Override
    public void saveEmployee(Employee employee) {
        employee.setCreatedDate(new Date());
        employee.setCreatedBy(1l);
        employee.setLastModifiedBy(1l);
        employee.setLastModifiedDate(new Date());
        employeeDao.insertSelective(employee);
    }

    /**
     * 
     * 根据员工代码,查询员工信息.
     * 
     * @param code 员工代码
     * @return
     * @author zhangshaung
     * @dateTime 2017年4月19日 上午10:03:17
     */
    @Override
    public Employee getInfoByCode(String code) {
        return employeeDao.selectByCode(code);
    }

    @Override
    public Employee findOne(String employeeCode) {
        return employeeDao.findOneByEmployeeCode(employeeCode);
    }

}
