package com.glanway.ctrlhr.dao.api;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.glanway.ctrlhr.entity.para.api.ApiDeptPara;
import com.glanway.ctrlhr.entity.para.api.ApiEmployeePara;
import com.glanway.ctrlhr.entity.para.api.ApiJobPara;
import com.glanway.ctrlhr.entity.vo.api.ApiDeptVo;
import com.glanway.ctrlhr.entity.vo.api.ApiEmployeeVo;
import com.glanway.ctrlhr.entity.vo.api.ApiJobVo;

/**
 * 说明 : 接口服务Dao层
 * 
 * @author fuqihao
 * @version 1.0.0
 * @dateTime 2017年6月13日 下午4:57:21
 */
public interface ApiDao {

	public int findEmployeeListCount(@Param("para") ApiEmployeePara para);

	public List<ApiEmployeeVo> findEmployeeList(@Param("para") ApiEmployeePara para);

	public int findDeptListCount(@Param("para") ApiDeptPara para);

	public List<ApiDeptVo> findDeptList(@Param("para") ApiDeptPara para);

	public int findJobListCount(@Param("para") ApiJobPara para);

	public List<ApiJobVo> findJobList(@Param("para") ApiJobPara para);

}
