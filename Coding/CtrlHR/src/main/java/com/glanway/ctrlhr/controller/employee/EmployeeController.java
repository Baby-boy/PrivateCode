package com.glanway.ctrlhr.controller.employee;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.glanway.ctrlhr.common.HttpCode;
import com.glanway.ctrlhr.common.JsonResult;
import com.glanway.ctrlhr.common.Page;
import com.glanway.ctrlhr.controller.BaseController;
import com.glanway.ctrlhr.entity.employee.Employee;
import com.glanway.ctrlhr.entity.para.DeviceToEmployeePara;
import com.glanway.ctrlhr.entity.para.EmployeePara;
import com.glanway.ctrlhr.entity.para.KeywordPara;
import com.glanway.ctrlhr.entity.vo.DeviceToEmployeeVo;
import com.glanway.ctrlhr.entity.vo.EmployeeSignInfoVo;
import com.glanway.ctrlhr.entity.vo.EmployeeVo;
import com.glanway.ctrlhr.entity.vo.SimpleEmployeeVo;
import com.glanway.ctrlhr.service.employee.EmployeeService;
import com.glanway.ctrlhr.service.server.IClockServerService;
import com.glanway.ctrlhr.util.TimeUtil;
import com.glanway.ctrlhr.util.TimestampDateEditor;

/**
 * 说明 : 员工管理相关
 * 
 * @author 付其浩
 * @version 1.0.0
 * @dateTime 2017年4月14日 下午2:42:30
 */
@Controller
@RequestMapping("api/employee")
public class EmployeeController extends BaseController {

	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private IClockServerService iClockServerService;

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
		if (StringUtils.isNotEmpty(para.getEntryDateFrom())) {
			para.setEntryDateFrom(TimeUtil.format(new Date(Long.valueOf(para.getEntryDateFrom()))));
		}
		if (StringUtils.isNotEmpty(para.getEntryDateTo())) {
			para.setEntryDateTo(
					TimeUtil.format(new Date(Long.valueOf(para.getEntryDateTo())), TimeUtil.FORMAT_YYYY_MM_DD)
							+ TimeUtil.END_TIME);
		}
		if (StringUtils.isNotEmpty(para.getFormalDateFrom())) {
			para.setFormalDateFrom(TimeUtil.format(new Date(Long.valueOf(para.getFormalDateFrom()))));
		}
		if (StringUtils.isNotEmpty(para.getFormalDateTo())) {
			para.setFormalDateTo(
					TimeUtil.format(new Date(Long.valueOf(para.getFormalDateTo())), TimeUtil.FORMAT_YYYY_MM_DD)
							+ TimeUtil.END_TIME);
		}
		if (StringUtils.isNotEmpty(para.getQuitDateFrom())) {
			para.setQuitDateFrom(TimeUtil.format(new Date(Long.valueOf(para.getQuitDateFrom()))));
		}
		if (StringUtils.isNotEmpty(para.getQuitDateTo())) {
			para.setQuitDateTo(TimeUtil.format(new Date(Long.valueOf(para.getQuitDateTo())), TimeUtil.FORMAT_YYYY_MM_DD)
					+ TimeUtil.END_TIME);
		}

		try {
			Page<EmployeeVo> page = employeeService.findList(para);
			jsonResult.setData(page);
		} catch (Exception e) {
			jsonResult.setCode(HttpCode.INTERNAL_SERVER_ERROR);
			jsonResult.setMsg("操作失败!");
			LOGGER.info("查询员工列表时异常信息为: {}", e.getMessage());
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
			LOGGER.info("添加员工时异常信息为: {}", e.getMessage());
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
			jsonResult.setCode(HttpCode.INTERNAL_SERVER_ERROR);
			jsonResult.setMsg("操作失败!");
			LOGGER.info("根据ID获取员工信息时异常信息为: {}", e.getMessage());
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
			jsonResult.setCode(HttpCode.INTERNAL_SERVER_ERROR);
			jsonResult.setMsg("操作失败!");
			LOGGER.info("编辑员工信息时异常信息为: {}", e.getMessage());
		}

		return jsonResult;
	}

	/**
	 * 说明 : 导出CSV文件
	 * 
	 * @author 付其浩
	 * @dateTime 2017年4月20日 下午6:13:00
	 */
	@RequestMapping("export")
	public void exportCsv(EmployeePara para, HttpServletResponse response) {
		if (StringUtils.isNotEmpty(para.getEntryDateFrom())) {
			para.setEntryDateFrom(TimeUtil.format(new Date(Long.valueOf(para.getEntryDateFrom()))));
		}
		if (StringUtils.isNotEmpty(para.getEntryDateTo())) {
			para.setEntryDateTo(
					TimeUtil.format(new Date(Long.valueOf(para.getEntryDateTo())), TimeUtil.FORMAT_YYYY_MM_DD)
							+ TimeUtil.END_TIME);
		}
		if (StringUtils.isNotEmpty(para.getFormalDateFrom())) {
			para.setFormalDateFrom(TimeUtil.format(new Date(Long.valueOf(para.getFormalDateFrom()))));
		}
		if (StringUtils.isNotEmpty(para.getFormalDateTo())) {
			para.setFormalDateTo(
					TimeUtil.format(new Date(Long.valueOf(para.getFormalDateTo())), TimeUtil.FORMAT_YYYY_MM_DD)
							+ TimeUtil.END_TIME);
		}
		if (StringUtils.isNotEmpty(para.getQuitDateFrom())) {
			para.setQuitDateFrom(TimeUtil.format(new Date(Long.valueOf(para.getQuitDateFrom()))));
		}
		if (StringUtils.isNotEmpty(para.getQuitDateTo())) {
			para.setQuitDateTo(TimeUtil.format(new Date(Long.valueOf(para.getQuitDateTo())), TimeUtil.FORMAT_YYYY_MM_DD)
					+ TimeUtil.END_TIME);
		}

		BufferedWriter bw = null;
		try {
			// 获取考勤记录的列表
			List<EmployeeVo> list = employeeService.findMany(para);

			response.setHeader("Content-Disposition", "attachment;filename=empInfo.csv");

			response.setCharacterEncoding("GBK");
			bw = new BufferedWriter(response.getWriter());
			if (null != list && list.size() > 0) {
				StringBuffer buffer = new StringBuffer();
				buffer.append("姓名").append(",");
				buffer.append("职员代码").append(",");
				buffer.append("部门名称").append(",");
				buffer.append("职位名称").append(",");
				buffer.append("在职状态").append(",");
				buffer.append("入职时间").append(",");
				buffer.append("转正时间").append(",");
				buffer.append("离职时间");
				bw.write(buffer.toString());
				bw.newLine();
				for (EmployeeVo employeeVo : list) {
					writeLine(getCvsList(employeeVo), bw);
				}
			}
			bw.flush();
		} catch (Exception e) {
			LOGGER.info("导出员工信息时异常信息为: {}", e.getMessage());
		} finally {
			if (null != bw) {
				try {
					bw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 把每一个EmployeeVo对象转换成String类型
	 *
	 * @author fuqihao
	 * @param employeeVo
	 * @return
	 * @since 1.0-20170526
	 */
	private List<String> getCvsList(EmployeeVo employeeVo) {
		List<String> cvsList = new ArrayList<>();
		cvsList.add(employeeVo.getName());
		cvsList.add(employeeVo.getCode());
		cvsList.add(employeeVo.getDeptName());
		cvsList.add(employeeVo.getJobName());
		switch (employeeVo.getJobState()) {
		case 1:
			cvsList.add("试用");
			break;
		case 2:
			cvsList.add("正式");
			break;
		case 3:
			cvsList.add("离职");
			break;
		}
		if (null == employeeVo.getEntryDate()) {
			cvsList.add(null);
		} else {
			cvsList.add(TimeUtil.format(employeeVo.getEntryDate(), TimeUtil.FORMAT_YYYY_MM_DD));
		}
		if (null == employeeVo.getFormalDate()) {
			cvsList.add(null);
		} else {
			cvsList.add(TimeUtil.format(employeeVo.getFormalDate(), TimeUtil.FORMAT_YYYY_MM_DD));
		}
		if (null == employeeVo.getQuitDate()) {
			cvsList.add(null);
		} else {
			cvsList.add(TimeUtil.format(employeeVo.getQuitDate(), TimeUtil.FORMAT_YYYY_MM_DD));
		}
		return cvsList;
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
		if (StringUtils.isEmpty(para.getSn())) {
			jsonResult.setCode(HttpCode.BAD_REQUEST);
			jsonResult.setMsg("设备序列号不能为空!");
			return jsonResult;
		}

		try {
			Page<DeviceToEmployeeVo> page = employeeService.findDeviceToEmployeeList(para);
			jsonResult.setData(page);
		} catch (Exception e) {
			jsonResult.setCode(HttpCode.INTERNAL_SERVER_ERROR);
			jsonResult.setMsg("操作失败!");
			LOGGER.info("查询设备下成员列表时异常信息为: {}", e.getMessage());
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

		if (StringUtils.isEmpty(code)) {
			jsonResult.setCode(HttpCode.BAD_REQUEST);
			jsonResult.setMsg("职员代码为空!");
			return jsonResult;
		}

		try {
			EmployeeSignInfoVo signInfoVo = employeeService.findSignInfo(id, code);
			jsonResult.setData(signInfoVo);
		} catch (Exception e) {
			jsonResult.setCode(HttpCode.INTERNAL_SERVER_ERROR);
			jsonResult.setMsg("操作失败!");
			LOGGER.info("查询员工考勤信息时异常信息为: {}", e.getMessage());
		}

		return jsonResult;
	}

	/**
	 * 
	 * 说明 : 设置员工离职时间
	 *
	 * @param ids
	 * @param quteDate
	 * @return
	 * @author 高伟南
	 * @dateTime 2017年5月13日 下午2:21:01
	 */
	@ResponseBody
	@RequestMapping(value = "dimission")
	public JsonResult dimission(String codes, Date quitDate) {
		JsonResult jsonResult = new JsonResult();
		if (StringUtils.isBlank(codes) || quitDate == null) {
			jsonResult.setCode(HttpCode.BAD_REQUEST);
			jsonResult.setMsg("操作失败!");
			return jsonResult;
		}
		try {
			employeeService.dimission(codes, quitDate);
			// 调用IClock系统
			iClockServerService.syncUserInfoByAllDevice();
		} catch (Exception e) {
			jsonResult.setCode(HttpCode.INTERNAL_SERVER_ERROR);
			jsonResult.setMsg("操作失败!");
			LOGGER.info("设置员工离职时异常信息为: {}", e.getMessage());
		}
		return jsonResult;
	}

	/**
	 * 说明 : 根据职员代码查询职员列表
	 * 
	 * @param codes
	 * @return
	 * @author fuqihao
	 * @dateTime 2017年6月3日 上午11:47:57
	 */
	@ResponseBody
	@RequestMapping(value = "staff", method = RequestMethod.GET)
	public JsonResult staff(String codes) {
		JsonResult jsonResult = new JsonResult();
		try {
			List<EmployeeVo> list = employeeService.findStaff(codes);
			jsonResult.setData(list);
		} catch (Exception e) {
			jsonResult.setCode(HttpCode.INTERNAL_SERVER_ERROR);
			jsonResult.setMsg("操作失败!");
			LOGGER.info("查询需要离职员工列表时异常信息为: {}", e.getMessage());
		}
		return jsonResult;
	}

	/**
	 * 考勤群组新建选择员工时,部门对应的所有员工列表.
	 *
	 * @author fuqihao
	 * @param deptIds
	 * @param para
	 * @return
	 * @since 1.0-20170526
	 */
	@ResponseBody
	@RequestMapping(value = "simpleList", method = RequestMethod.GET)
	public JsonResult simpleList(String deptIds, KeywordPara para) {
		JsonResult jsonResult = new JsonResult();

		try {
			List<SimpleEmployeeVo> list = employeeService.findSimpleListByDeptId(deptIds, para);
			jsonResult.setData(list);
		} catch (Exception e) {
			jsonResult.setCode(HttpCode.INTERNAL_SERVER_ERROR);
			jsonResult.setMsg("操作失败!");
			LOGGER.info("根据部门查询员工信息时异常信息为: {}", e.getMessage());
		}

		return jsonResult;
	}

	/**
	 * 远程设置管理员(传入需要被设置成管理员的职员代码).
	 *
	 * @author fuqihao
	 * @return
	 * @since 1.0-20170531
	 */
	@ResponseBody
	@RequestMapping(value = "setManager", method = RequestMethod.POST)
	public JsonResult remoteSetManager(String codes, String pwd, String sns) {
		JsonResult jsonResult = new JsonResult();

		if (StringUtils.isEmpty(codes)) {
			jsonResult.setCode(HttpCode.BAD_REQUEST);
			jsonResult.setMsg("未设置管理员!");
			return jsonResult;
		}
		if (StringUtils.isEmpty(pwd)) {
			jsonResult.setCode(HttpCode.BAD_REQUEST);
			jsonResult.setMsg("密码不能为空!");
			return jsonResult;
		}

		String regex = "^[0-9]*$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(pwd);
		if (!matcher.matches()) {
			jsonResult.setCode(HttpCode.BAD_REQUEST);
			jsonResult.setMsg("密码只能为数字!");
			return jsonResult;
		}

		try {
			employeeService.remoteSetManager(codes, pwd, sns);
			// 调用IClock系统
			iClockServerService.syncUserInfoByAllDevice();
		} catch (Exception e) {
			jsonResult.setCode(HttpCode.INTERNAL_SERVER_ERROR);
			jsonResult.setMsg("操作失败!");
			LOGGER.info("设置管理员时异常信息为: {}", e.getMessage());
		}

		return jsonResult;
	}

	/**
	 * TODO 远程取消管理员.
	 * 
	 * @return
	 * @author fuqihao
	 * @dateTime 2017年6月6日 下午1:09:15
	 */
	@ResponseBody
	@RequestMapping(value = "cancelManager", method = RequestMethod.GET)
	public JsonResult remoteCancelManager(String codes, String sns) {
		JsonResult jsonResult = new JsonResult();

		return jsonResult;
	}

	/**
	 * 时间自动格式化.
	 *
	 * @author fuqihao
	 * @param binder
	 * @since 1.0-20170526
	 */
	@InitBinder
	public void initBinder(final WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, new TimestampDateEditor(true));
	}
}
