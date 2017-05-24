package com.glanway.ctrlhr.controller.employee;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.glanway.ctrlhr.common.HttpCode;
import com.glanway.ctrlhr.common.JsonResult;
import com.glanway.ctrlhr.common.Page;
import com.glanway.ctrlhr.entity.employee.Employee;
import com.glanway.ctrlhr.entity.para.DeviceToEmployeePara;
import com.glanway.ctrlhr.entity.para.EmployeePara;
import com.glanway.ctrlhr.entity.vo.DeviceToEmployeeVo;
import com.glanway.ctrlhr.entity.vo.EmployeeSignInfoVo;
import com.glanway.ctrlhr.entity.vo.EmployeeVo;
import com.glanway.ctrlhr.service.employee.EmployeeService;

/**
 * 说明 : 员工管理相关
 * 
 * @author 付其浩
 * @version 1.0.0
 * @dateTime 2017年4月14日 下午2:42:30
 */
@Controller
@RequestMapping("api/employee")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	/**
	 * 说明 : 查询员工列表(分页)
	 * 
	 * @param para(请求参数)
	 * @param session(session对象)
	 * @return
	 * @author 付其浩
	 * @dateTime 2017年4月14日 下午2:42:17
	 */
	@ResponseBody
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public JsonResult list(EmployeePara para) {
		JsonResult jsonResult = new JsonResult();

		try {
			Page<EmployeeVo> page = employeeService.findList(para);
			jsonResult.setData(page);
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setCode(HttpCode.INTERNAL_SERVER_ERROR);
			jsonResult.setMsg("操作失败!");
		}

		return jsonResult;
	}

	/**
	 * 说明 : 新增员工
	 * 
	 * @param employee
	 * @return
	 * @author 高伟南
	 * @dateTime 2017年4月20日 下午3:02:26
	 */
	@ResponseBody
	@RequestMapping(value = "add", method = RequestMethod.POST)
	public JsonResult addEmployee(Employee employee) {
		JsonResult jsonResult = new JsonResult();

		if (StringUtils.isEmpty(employee.getName())) {
			jsonResult.setCode(HttpCode.BAD_REQUEST);
			jsonResult.setMsg("员工名称不能为空!");
			return jsonResult;
		}
		if (null == employee.getDeptId()) {
			jsonResult.setCode(HttpCode.BAD_REQUEST);
			jsonResult.setMsg("部门不能为空!");
			return jsonResult;
		}
		if (null == employee.getJobId()) {
			jsonResult.setCode(HttpCode.BAD_REQUEST);
			jsonResult.setMsg("职位不能为空!");
			return jsonResult;
		}

		try {
			employeeService.saveEmployee(employee);
		} catch (RuntimeException runtime) {
			jsonResult.setCode(HttpCode.BAD_REQUEST);
			jsonResult.setMsg("员工代号已存在!");
			runtime.printStackTrace();
		} catch (Exception e) {
			jsonResult.setCode(HttpCode.INTERNAL_SERVER_ERROR);
			jsonResult.setMsg("操作失敗!");
			e.printStackTrace();
		}

		return jsonResult;
	}

	/**
	 * 说明 : 根据ID获取员工信息
	 * 
	 * @param id
	 * @param session
	 * @return
	 * @author 张爽
	 * @dateTime 2017年4月20日 下午2:30:52
	 */
	@ResponseBody
	@RequestMapping(value = "getInfo", method = RequestMethod.GET)
	public JsonResult getInfo(Long id) {
		JsonResult jsonResult = new JsonResult();

		try {
			EmployeeVo employeeVo = employeeService.getInfo(id);
			if (null == employeeVo) {
				jsonResult.setCode(HttpCode.NO_CONTENT);
				jsonResult.setMsg("查无信息!");
				return jsonResult;
			}
			jsonResult.setData(employeeVo);
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setCode(HttpCode.INTERNAL_SERVER_ERROR);
			jsonResult.setMsg("操作失败!");
		}

		return jsonResult;
	}

	/**
	 * 说明 : 编辑员工信息
	 * 
	 * @param employee
	 * @return
	 * @author 张爽
	 * @dateTime 2017年4月20日 下午3:01:46
	 */
	@ResponseBody
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public JsonResult update(Employee employee) {
		JsonResult jsonResult = new JsonResult();

		if (StringUtils.isEmpty(employee.getName())) {
			jsonResult.setCode(HttpCode.BAD_REQUEST);
			jsonResult.setMsg("员工名称不能为空!");
			return jsonResult;
		}
		if (null == employee.getDeptId()) {
			jsonResult.setCode(HttpCode.BAD_REQUEST);
			jsonResult.setMsg("部门不能为空!");
			return jsonResult;
		}
		if (null == employee.getJobId()) {
			jsonResult.setCode(HttpCode.BAD_REQUEST);
			jsonResult.setMsg("职位不能为空!");
			return jsonResult;
		}

		try {
			employeeService.updateEmployee(employee);
		} catch (RuntimeException runtime) {
			runtime.printStackTrace();
			jsonResult.setCode(HttpCode.BAD_REQUEST);
			jsonResult.setMsg("员工代号已存在!");
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setCode(HttpCode.INTERNAL_SERVER_ERROR);
			jsonResult.setMsg("操作失败!");
		}

		return jsonResult;
	}

	/**
	 * 说明 : 导入CSV文件
	 * 
	 * @author 付其浩
	 * @dateTime 2017年4月20日 下午6:13:00
	 */
	@RequestMapping("export")
	public void exportCsv() {
		// TODO 员工信息导出
	}

	/**
	 * 说明 : 考勤设备下成员列表
	 * 
	 * @param para
	 * @return
	 * @author 高伟南
	 * @dateTime 2017年4月20日 下午6:13:56
	 */
	@ResponseBody
	@RequestMapping(value = "queryEmployeeList", method = RequestMethod.GET)
	public JsonResult EmployeeList(DeviceToEmployeePara para) {

		JsonResult jsonResult = new JsonResult();

		try {
			Page<DeviceToEmployeeVo> page = employeeService.findDeviceToEmployeeList(para);
			jsonResult.setData(page);
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setCode(HttpCode.INTERNAL_SERVER_ERROR);
			jsonResult.setMsg("操作失败!");
		}

		return jsonResult;
	}

	/**
	 * 说明 : 员工考勤采集信息
	 * 
	 * @param id
	 * @return
	 * @author 付其浩
	 * @dateTime 2017年4月24日 下午2:45:52
	 */
	@ResponseBody
	@RequestMapping(value = "signInfo", method = RequestMethod.GET)
	public JsonResult signInfo(Long id, String code) {

		JsonResult jsonResult = new JsonResult();

		try {
			EmployeeSignInfoVo signInfoVo = employeeService.findSignInfo(id, code);
			jsonResult.setData(signInfoVo);
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setCode(HttpCode.INTERNAL_SERVER_ERROR);
			jsonResult.setMsg("操作失败!");
		}

		return jsonResult;
	}

}
