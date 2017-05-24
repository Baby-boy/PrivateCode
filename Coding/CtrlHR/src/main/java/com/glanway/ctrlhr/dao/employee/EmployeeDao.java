package com.glanway.ctrlhr.dao.employee;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.glanway.ctrlhr.dao.BaseDao;
import com.glanway.ctrlhr.entity.employee.Employee;
import com.glanway.ctrlhr.entity.para.BasePara;
import com.glanway.ctrlhr.entity.para.DeviceToEmployeePara;
import com.glanway.ctrlhr.entity.vo.DeviceToEmployeeVo;
import com.glanway.ctrlhr.entity.vo.EmployeeSignInfoVo;
import com.glanway.ctrlhr.entity.vo.EmployeeVo;

public interface EmployeeDao extends BaseDao<Employee> {

	public int deleteByPrimaryKey(Long id);

	public int insert(Employee record);

	public int insertSelective(Employee record);

	public Employee selectByPrimaryKey(Long id);

	public int updateByPrimaryKeySelective(Employee record);

	public int updateByPrimaryKey(Employee record);

	/** 查询员工总数 */
	public int findListCount(@Param("para") BasePara para);

	/** 查询员工列表 */
	public List<EmployeeVo> findList(@Param("para") BasePara para);

	/** 根据ID获取员工信息 */
	public EmployeeVo findOne(@Param("id") Long id);

	/** 查询此设备上员工总数 */
	public int findListDeviceToEmployeeCount(@Param("para") DeviceToEmployeePara para);

	/** 查询此设备上员工列表 */
	public List<DeviceToEmployeeVo> findDeviceToEmployeeList(@Param("para") DeviceToEmployeePara para);

	/** 根据员工代号查询员工是否存在 */
	public Employee findByCode(@Param("code") String code);

	/** 员工考勤采集信息 */
	public EmployeeSignInfoVo findSignInfo(@Param("id") Long id, @Param("code") String code);

}