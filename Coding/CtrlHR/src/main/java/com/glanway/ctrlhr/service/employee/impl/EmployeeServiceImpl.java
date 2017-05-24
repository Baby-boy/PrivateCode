package com.glanway.ctrlhr.service.employee.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.glanway.ctrlhr.common.Page;
import com.glanway.ctrlhr.dao.employee.EmployeeDao;
import com.glanway.ctrlhr.entity.employee.Employee;
import com.glanway.ctrlhr.entity.para.DeviceToEmployeePara;
import com.glanway.ctrlhr.entity.para.EmployeePara;
import com.glanway.ctrlhr.entity.vo.DeviceToEmployeeVo;
import com.glanway.ctrlhr.entity.vo.EmployeeSignInfoVo;
import com.glanway.ctrlhr.entity.vo.EmployeeVo;
import com.glanway.ctrlhr.service.BaseServiceImpl;
import com.glanway.ctrlhr.service.employee.EmployeeService;

/**
 * 说明 :
 *
 * @author 高伟南
 * @version 1.0.0
 * @dateTime 2017年4月21日 上午9:25:03
 */
@Transactional
@Service("employeeService")
public class EmployeeServiceImpl extends BaseServiceImpl<Employee> implements EmployeeService {

	@Autowired
	private EmployeeDao employeeDao;

	@Override
	public Page<EmployeeVo> findList(EmployeePara para) {
		if (StringUtils.isNotEmpty(para.getJobStates())) {
			String[] jobStateList = StringUtils.split(para.getJobStates(), ",");
			para.setJobStateList(jobStateList);
		}
		if (StringUtils.isNotEmpty(para.getDeptIds())) {
			String[] deptIdList = StringUtils.split(para.getDeptIds(), ",");
			para.setDeptIdList(deptIdList);
		}
		int count = employeeDao.findListCount(para);
		Page<EmployeeVo> page = new Page<>(para.getPage(), count, para.getPageSize());
		if (para.getPage() <= page.getCountPage()) {
			para.setStartIndex(page.getStartIndex());
			List<EmployeeVo> list = employeeDao.findList(para);
			page.setList(list);
		}

		return page;
	}

	@Override
	public void saveEmployee(Employee employee) {
		Employee emp = employeeDao.findByCode(employee.getCode());
		if (null != emp) {
			throw new RuntimeException();
		}
		employee.setId(null);
		employee.setBatchDate(new Date());
		// TODO 创建人ID写死,后期需要更改
		employee.setCreatedBy(1L);
		employee.setCreatedDate(new Date());
		// TODO 创建程序ID写死,后期需要更改
		employee.setCreProId(1L);
		// TODO 最后更新人ID写死,后期需要更改
		employee.setLastModifiedBy(1L);
		employee.setLastModifiedDate(employee.getCreatedDate());
		// TODO 更新程序ID写死,后期需要更改
		employee.setModProId(1L);

		employeeDao.insertSelective(employee);
	}

	@Override
	public EmployeeVo getInfo(Long id) {
		return employeeDao.findOne(id);
	}

	@Override
	public void updateEmployee(Employee employee) {
		if (StringUtils.isNotEmpty(employee.getCode())) {
			Employee emp = employeeDao.findByCode(employee.getCode());
			if (null != emp) {
				throw new RuntimeException();
			}
		}

		// TODO 最后更新人ID写死,后期需要更改
		employee.setLastModifiedBy(1L);
		employee.setLastModifiedDate(new Date());
		// TODO 更新程序ID写死,后期需要更改
		employee.setModProId(1L);

		employeeDao.updateByPrimaryKeySelective(employee);

	}

	@Override
	public Page<DeviceToEmployeeVo> findDeviceToEmployeeList(DeviceToEmployeePara para) {

		int count = employeeDao.findListDeviceToEmployeeCount(para);
		Page<DeviceToEmployeeVo> page = new Page<>(para.getPage(), count, para.getPageSize());
		if (para.getPage() <= page.getCountPage()) {
			para.setStartIndex(page.getStartIndex());
			List<DeviceToEmployeeVo> list = employeeDao.findDeviceToEmployeeList(para);
			page.setList(list);
		}
		return page;

	}

	@Override
	public EmployeeSignInfoVo findSignInfo(Long id, String code) {
		return employeeDao.findSignInfo(id, code);
	}

}
