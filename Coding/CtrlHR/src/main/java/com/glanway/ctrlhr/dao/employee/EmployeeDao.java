package com.glanway.ctrlhr.dao.employee;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.glanway.ctrlhr.dao.BaseDao;
import com.glanway.ctrlhr.entity.employee.Employee;
import com.glanway.ctrlhr.entity.para.BasePara;
import com.glanway.ctrlhr.entity.para.DeviceToEmployeePara;
import com.glanway.ctrlhr.entity.para.EmployeePara;
import com.glanway.ctrlhr.entity.para.KeywordPara;
import com.glanway.ctrlhr.entity.vo.DeviceToEmployeeVo;
import com.glanway.ctrlhr.entity.vo.EmployeeSignInfoVo;
import com.glanway.ctrlhr.entity.vo.EmployeeVo;
import com.glanway.ctrlhr.entity.vo.SimpleEmployeeVo;

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

	/** 根据职位获取该职位下的员工集合 */
	public List<Employee> findEmployeeByJob(Long jobId);

	/** 查询此设备上员工总数 */
	public int findListDeviceToEmployeeCount(@Param("para") DeviceToEmployeePara para);

	/** 查询此设备上员工列表 */
	public List<DeviceToEmployeeVo> findDeviceToEmployeeList(@Param("para") DeviceToEmployeePara para);

	/** 根据员工代号查询员工是否存在 */
	public Employee findByCode(@Param("code") String code);

	/** 员工考勤采集信息 */
	public EmployeeSignInfoVo findSignInfo(@Param("id") Long id, @Param("code") String code);

	/** 设置员工离职 */
	public void updateQuitDateAndJobState(@Param("codes") String[] codes, @Param("quitDate") Date quitDate,
			@Param("jobState") Integer jobState);

	/** 查询员工列表 */
	public List<EmployeeVo> findByCodeAndJobState(@Param("codes") String[] codes,
			@Param("jobStates") Integer[] jobStates);

	/** 考勤群组新建选择员工时,部门对应的所有员工列表 */
	public List<SimpleEmployeeVo> findSimpleListByDeptId(@Param("deptIdList") String[] deptIdList,
			@Param("para") KeywordPara para);

	/** 要导出的员工信息列表 */
	public List<EmployeeVo> findMany(@Param("para") EmployeePara para);

	/** 设置管理员权限 */
	public void remoteSetManager(@Param("pri") String pri, @Param("pwd") String pwd,
			@Param("codeArr") String[] codeArr);

	/** 查询所有的管理员 */
	public List<Employee> findAllManager(@Param("codeArr") String[] codeArr);

}