package com.glanway.ctrlhr.controller.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.glanway.ctrlhr.common.HttpCode;
import com.glanway.ctrlhr.common.JsonResult;
import com.glanway.ctrlhr.common.Page;
import com.glanway.ctrlhr.entity.para.api.ApiDeptPara;
import com.glanway.ctrlhr.entity.para.api.ApiEmployeePara;
import com.glanway.ctrlhr.entity.para.api.ApiJobPara;
import com.glanway.ctrlhr.entity.vo.api.ApiDeptVo;
import com.glanway.ctrlhr.entity.vo.api.ApiEmployeeVo;
import com.glanway.ctrlhr.entity.vo.api.ApiJobVo;
import com.glanway.ctrlhr.service.api.ApiService;

/**
 * 说明 : 提供接口服务
 * 
 * @author fuqihao
 * @version 1.0.0
 * @dateTime 2017年6月13日 下午4:36:52
 */
@Controller
@RequestMapping("api")
public class ApiController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ApiController.class);

	@Autowired
	private ApiService apiService;

	/**
	 * 说明 : 接口服务-员工列表
	 * 
	 * @param para
	 * @return
	 * @author fuqihao
	 * @dateTime 2017年6月13日 下午4:53:08
	 */
	@ResponseBody
	@RequestMapping(value = "employeeList", method = RequestMethod.GET)
	public JsonResult employeeList(ApiEmployeePara para) {
		JsonResult jsonResult = new JsonResult();

		try {
			Page<ApiEmployeeVo> page = apiService.findEmployeeList(para);
			jsonResult.setData(page);
		} catch (Exception e) {
			jsonResult.setCode(HttpCode.INTERNAL_SERVER_ERROR);
			jsonResult.setMsg("操作失败!");
			LOGGER.info("查询员工列表时异常信息为: {}", e.getMessage());
		}

		return jsonResult;
	}

	/**
	 * 说明 : 接口服务-部门列表
	 * 
	 * @param para
	 * @return
	 * @author fuqihao
	 * @dateTime 2017年6月13日 下午5:38:42
	 */
	@ResponseBody
	@RequestMapping(value = "deptList", method = RequestMethod.GET)
	public JsonResult deptList(ApiDeptPara para) {
		JsonResult jsonResult = new JsonResult();

		try {
			Page<ApiDeptVo> page = apiService.findDeptList(para);
			jsonResult.setData(page);
		} catch (Exception e) {
			jsonResult.setCode(HttpCode.INTERNAL_SERVER_ERROR);
			jsonResult.setMsg("操作失败!");
			LOGGER.info("查询部门列表时异常信息为: {}", e.getMessage());
		}

		return jsonResult;
	}

	/**
	 * 说明 : 接口服务-职位列表
	 * 
	 * @param para
	 * @return
	 * @author fuqihao
	 * @dateTime 2017年6月13日 下午5:55:05
	 */
	@ResponseBody
	@RequestMapping(value = "jobList", method = RequestMethod.GET)
	public JsonResult jobList(ApiJobPara para) {
		JsonResult jsonResult = new JsonResult();

		try {
			Page<ApiJobVo> page = apiService.findJobList(para);
			jsonResult.setData(page);
		} catch (Exception e) {
			jsonResult.setCode(HttpCode.INTERNAL_SERVER_ERROR);
			jsonResult.setMsg("操作失败!");
			LOGGER.info("查询职位列表时异常信息为: {}", e.getMessage());
		}

		return jsonResult;
	}
}
