/**
 * @author zhangshuang
 * 2017年4月19日 下午7:05:12
 */
package com.glanway.iclock.service.employee;

import java.util.List;

import com.glanway.iclock.entity.employee.FingerFaceTemplate;
import com.glanway.iclock.service.BaseService;

/**
 * 说明 : 
 * 员工指纹模板和脸纹模板 Service
 * @author zhangshaung
 * @version 1.0.0
 * @dateTime 2017年4月19日 下午7:05:12
 */
public interface FingerFaceTemplateService extends BaseService<FingerFaceTemplate> {
	
	/**
	 * 
	 * 说明 : 
	 * 根据员工代码,查询员工的指纹模板或脸纹模板
	 * @param employeeCode 员工代码
	 * @param type 类型{1:指纹,2:脸纹}
	 * @return
	 * @author zhangshaung
	 * @dateTime 2017年4月19日 下午7:12:15
	 */
	List<FingerFaceTemplate> selectByEmployeeCodeAndType(String employeeCode,Integer type);
	
	/**
	 * 
	 * 说明 : 
	 * 根据员工代码(EMPLOYEE_CODE)和指纹标号脸纹标号(FID),查询员工的指纹模板或脸纹模板
	 * @param employeeCode:员工代码
	 * @param fid:指纹标号或脸纹标号
	 * @param type:类型{1:指纹,2:脸纹}
	 * @return
	 * @author zhangshaung
	 * @dateTime 2017年4月19日 下午7:12:15
	 */
	FingerFaceTemplate findInfoByEmployeeCodeAndTypeAndFid(String employeeCode,String fid,Integer type);
	
}
