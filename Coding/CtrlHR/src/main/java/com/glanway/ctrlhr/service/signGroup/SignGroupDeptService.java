package com.glanway.ctrlhr.service.signGroup;

import com.glanway.ctrlhr.entity.signGroup.SignGroupDept;
import com.glanway.ctrlhr.service.BaseService;

public interface SignGroupDeptService extends BaseService<SignGroupDept> {

	/**
	 * 说明 : 保存特定员工和考勤组关联
	 * 
	 * @param id
	 * @param employeeIdArr
	 * @author 付其浩
	 * @dateTime 2017年4月21日 下午8:06:01
	 */
	public void saveEmployeeAndSignGroup(Long id, String[] employeeIdArr);

	/**
	 * 说明 : 保存职位部门和考勤组关联
	 * 
	 * @param id
	 * @param jobDeptIdArr
	 * @param jobIdArr
	 * @author 付其浩
	 * @dateTime 2017年4月21日 下午8:08:03
	 */
	public void saveJobDeptAndSignGroup(Long id, String[] jobDeptIdArr, String[] jobIdArr);

	/**
	 * 说明 : 编辑特定员工和考勤组关联
	 * 
	 * @param id
	 * @param employeeIdArr
	 * @author 付其浩
	 * @dateTime 2017年4月23日 下午10:36:30
	 */
	public void updateEmployeeAndSignGroup(Long id, String[] employeeIdArr);

	/**
	 * 说明 : 编辑职位部门和考勤组关联
	 * 
	 * @param id
	 * @param jobDeptIdArr
	 * @param jobIdArr
	 * @author 付其浩
	 * @dateTime 2017年4月23日 下午10:37:49
	 */
	public void updateJobDeptAndSignGroup(Long id, String[] jobDeptIdArr, String[] jobIdArr);

	/**
	 * 说明 : 根据考勤群组ID批量删除关联表数据(逻辑删除)
	 * 
	 * @param idArr
	 * @author 付其浩
	 * @dateTime 2017年4月23日 下午11:36:57
	 */
	public void delete(String[] idArr);

}
