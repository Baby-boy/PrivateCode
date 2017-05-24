/**
 * @author zhangshuang
 * 2017年4月19日 下午7:08:39
 */
package com.glanway.iclock.service.employee.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.glanway.iclock.dao.employee.FingerFaceTemplateDao;
import com.glanway.iclock.entity.employee.FingerFaceTemplate;
import com.glanway.iclock.service.BaseServiceImpl;
import com.glanway.iclock.service.employee.FingerFaceTemplateService;

/**
 * 说明 : 员工指纹模板和脸纹模板 实现类
 * 
 * @author zhangshaung
 * @version 1.0.0
 * @dateTime 2017年4月19日 下午7:08:39
 */
@Transactional
@Service("fingerFaceTemplateService")
public class FingerFaceTemplateServiceImpl extends BaseServiceImpl<FingerFaceTemplate>
		implements FingerFaceTemplateService {

	@Autowired
	private FingerFaceTemplateDao fingerFaceTemplateDao;

	/**
	 * 
	 * 说明 : 根据员工代码,查询员工的指纹模板或脸纹模板
	 * 
	 * @param employeeCode
	 *            员工代码
	 * @param type
	 *            类型{1:指纹,2:脸纹}
	 * @return List<FingerFaceTemplate>
	 * @author zhangshaung
	 * @dateTime 2017年4月19日 下午7:12:15
	 */
	@Override
	public List<FingerFaceTemplate> selectByEmployeeCodeAndType(String employeeCode, Integer type) {
		// TODO Auto-generated method stub
		if (null == employeeCode || null == type) {
			return null;
		}
		Map<String, Object> params = new HashMap<String, Object>();

		params.put("employeeCode", employeeCode);
		params.put("type", type);

		return fingerFaceTemplateDao.selectByEmployeeCodeAndType(params);
	}

	/**
	 * 
	 * 说明 : 根据员工代码(EMPLOYEE_CODE)和指纹标号脸纹标号(FID),查询员工的指纹模板或脸纹模板
	 * 
	 * @param employeeCode:员工代码
	 * @param fid:指纹标号或脸纹标号
	 * @param type:类型{1:指纹,2:脸纹}
	 * @return FingerFaceTemplate
	 * @author zhangshaung
	 * @dateTime 2017年4月19日 下午7:12:15
	 */
	@Override
	public FingerFaceTemplate findInfoByEmployeeCodeAndTypeAndFid(String employeeCode, String fid, Integer type) {
		// TODO Auto-generated method stub
		if (null == employeeCode || null == fid || null == type) {
			return null;
		}
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("employeeCode", employeeCode);
		params.put("fid", fid);
		params.put("type", type);
		return fingerFaceTemplateDao.findInfoByEmployeeCodeAndTypeAndFid(params);
	}
}
