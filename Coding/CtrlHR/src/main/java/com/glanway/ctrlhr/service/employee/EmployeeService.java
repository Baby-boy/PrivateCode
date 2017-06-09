package com.glanway.ctrlhr.service.employee;

import com.glanway.ctrlhr.common.Page;
import com.glanway.ctrlhr.entity.employee.Employee;
import com.glanway.ctrlhr.entity.para.DeviceToEmployeePara;
import com.glanway.ctrlhr.entity.para.EmployeePara;
import com.glanway.ctrlhr.entity.para.KeywordPara;
import com.glanway.ctrlhr.entity.vo.DeviceToEmployeeVo;
import com.glanway.ctrlhr.entity.vo.EmployeeSignInfoVo;
import com.glanway.ctrlhr.entity.vo.EmployeeVo;
import com.glanway.ctrlhr.entity.vo.SimpleEmployeeVo;
import com.glanway.ctrlhr.service.BaseService;

import java.util.Date;
import java.util.List;

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
	 * 说明 : 根据职位获取该职位下的员工集合
	 *
	 * @param jobId
	 * @return
	 * @author 于瑞智
	 * @dateTime 2017年5月18日 上午11:47:17
	 */
	public List<Employee> getEmployeeByJob(Long jobId);

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

	/**
	 * 
	 * 说明 : 设置员工离职时间(多个员工以","分割)
	 *
	 * @param ids
	 * @param quitDate
	 * @author 高伟南
	 * @dateTime 2017年5月13日 下午1:28:32
	 */
	public void dimission(String codes, Date quitDate);

	/**
	 * 
	 * 说明 : 查询在职人员.
	 *
	 * @param ids
	 * @author 高伟南
	 * @dateTime 2017年5月13日 下午2:25:54
	 */
	public List<EmployeeVo> findStaff(String ids);

	/**
	 * 考勤群组新建选择员工时,部门对应的所有员工列表.
	 *
	 * @author fuqihao
	 * @param deptIds
	 * @param para
	 * @return
	 * @since 1.0-20170526
	 */
	public List<SimpleEmployeeVo> findSimpleListByDeptId(String deptIds, KeywordPara para);

	/**
	 * 员工信息导出.
	 *
	 * @author fuqihao
	 * @param para
	 * @return
	 * @since 1.0-20170526
	 */
	public List<EmployeeVo> findMany(EmployeePara para);

	/**
	 * 远程设置管理员.
	 *
	 * @author fuqihao
	 * @param codes
	 * @param pwd
	 * @param sns
	 * @since 1.0-20170531
	 */
	public void remoteSetManager(String codes, String pwd, String sns);

}
