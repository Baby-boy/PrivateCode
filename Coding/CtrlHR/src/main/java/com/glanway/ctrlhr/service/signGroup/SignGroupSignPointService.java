package com.glanway.ctrlhr.service.signGroup;

import com.glanway.ctrlhr.entity.signGroup.SignGroupSignPoint;
import com.glanway.ctrlhr.service.BaseService;

public interface SignGroupSignPointService extends BaseService<SignGroupSignPoint> {

	/**
	 * 说明 : 保存考勤点和考勤组关联
	 * 
	 * @param id
	 * @param signPointIdArr
	 * @author 付其浩
	 * @dateTime 2017年4月21日 下午8:06:49
	 */
	public void saveSignPointAndSignGroup(Long id, String[] signPointIdArr);

	/**
	 * 说明 : 保存组织架构和考勤组关联
	 * 
	 * @param id
	 * @param placeDeptIdArr
	 * @author 付其浩
	 * @dateTime 2017年4月21日 下午8:07:15
	 */
	public void savePlaceDeptAndSignGroup(Long id, String[] placeDeptIdArr);

	/**
	 * 说明 : 编辑考勤点和考勤组关联
	 * 
	 * @param id
	 * @param signPointIdArr
	 * @author 付其浩
	 * @dateTime 2017年4月23日 下午10:37:10
	 */
	public void updateSignPointAndSignGroup(Long id, String[] signPointIdArr);

	/**
	 * 说明 : 编辑组织架构和考勤组关联
	 * 
	 * @param id
	 * @param placeDeptIdArr
	 * @author 付其浩
	 * @dateTime 2017年4月23日 下午10:37:29
	 */
	public void updatePlaceDeptAndSignGroup(Long id, String[] placeDeptIdArr);

	/**
	 * 说明 : 根据考勤群组ID批量删除关联表数据(逻辑删除)
	 * 
	 * @param idArr
	 * @author 付其浩
	 * @dateTime 2017年4月23日 下午11:37:58
	 */
	public void delete(String[] idArr);

}
