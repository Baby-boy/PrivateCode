package com.glanway.ctrlhr.service.signPoint;

import java.util.List;

import com.glanway.ctrlhr.entity.signPoint.SignPointDept;
import com.glanway.ctrlhr.service.BaseService;

public interface SignPointDeptService extends BaseService<SignPointDept> {

	/**
	 * 说明 : 保存考勤点和部门的关系到关联表
	 * 
	 * @param id
	 * @param deptIdArr
	 * @author 付其浩
	 * @dateTime 2017年4月19日 下午5:16:10
	 */
	public void save(Long id, String[] deptIdArr);

	/**
	 * 说明 : 根据考勤点ID查询部门
	 * 
	 * @param signPointId
	 * @return
	 * @author 付其浩
	 * @dateTime 2017年4月19日 下午7:17:00
	 */
	public List<SignPointDept> findDept(Long signPointId);

	/**
	 * 说明 : 根据考勤点ID更新考勤点和部门关联表
	 * 
	 * @param id
	 * @param deptIdArr
	 * @author 付其浩
	 * @dateTime 2017年4月19日 下午8:08:05
	 */
	public void updateDept(Long id, String[] deptIdArr);

}
