/**
 * @author zhangshuang
 * 2017年4月19日 下午5:55:57
 */
package com.glanway.iclock.dao.employee;

import java.util.List;
import java.util.Map;

import com.glanway.iclock.dao.BaseDao;
import com.glanway.iclock.entity.employee.FingerFaceTemplate;

/**
 * 说明 : 
 * 员工指纹模板和脸纹模板
 * @author zhangshaung
 * @version 1.0.0
 * @dateTime 2017年4月19日 下午5:55:57
 */
public interface FingerFaceTemplateDao extends BaseDao<FingerFaceTemplate> {
	
	/**
	 * 
	 * 说明 : 
	 * 根据员工代码,查询员工的指纹模板或脸纹模板
	 * @param  params 参数{employeeCode:员工代码,type:类型{1:指纹,2:脸纹}}
	 * @return
	 * @author zhangshaung
	 * @dateTime 2017年4月19日 下午7:12:15
	 */
	List<FingerFaceTemplate> selectByEmployeeCodeAndType(Map<String, Object> params);
	
	/**
	 * 
	 * 说明 : 
	 * 根据员工代码(EMPLOYEE_CODE)和指纹标号脸纹标号(FID),查询员工的指纹模板或脸纹模板
	 * @param  params 参数{employeeCode:员工代码,type:类型{1:指纹,2:脸纹},fid:指纹标号或脸纹标号}
	 * @return
	 * @author zhangshaung
	 * @dateTime 2017年4月19日 下午7:12:15
	 */
	FingerFaceTemplate findInfoByEmployeeCodeAndTypeAndFid(Map<String, Object> params);
}
