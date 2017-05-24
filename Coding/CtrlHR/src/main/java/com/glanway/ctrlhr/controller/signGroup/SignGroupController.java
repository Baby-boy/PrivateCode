package com.glanway.ctrlhr.controller.signGroup;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.glanway.ctrlhr.common.HttpCode;
import com.glanway.ctrlhr.common.JsonResult;
import com.glanway.ctrlhr.common.Page;
import com.glanway.ctrlhr.entity.para.BasePara;
import com.glanway.ctrlhr.entity.para.SignGroupPara;
import com.glanway.ctrlhr.entity.vo.SignGroupVo;
import com.glanway.ctrlhr.entity.vo.SimpleSignGroupVo;
import com.glanway.ctrlhr.service.signGroup.SignGroupService;

/**
 * 说明 : 考勤群组管理相关
 * 
 * @author 付其浩
 * @version 1.0.0
 * @dateTime 2017年4月21日 上午10:25:08
 */
@Controller
@RequestMapping("/api/signGroup")
public class SignGroupController {

	@Autowired
	private SignGroupService signGroupService;

	/**
	 * 说明 : 查询考勤群组列表
	 * 
	 * @param para
	 * @return
	 * @author 付其浩
	 * @dateTime 2017年4月21日 上午10:32:13
	 */
	@ResponseBody
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public JsonResult list(BasePara para) {
		JsonResult jsonResult = new JsonResult();

		try {
			Page<SignGroupVo> page = signGroupService.findList(para);
			jsonResult.setData(page);
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setCode(HttpCode.INTERNAL_SERVER_ERROR);
			jsonResult.setMsg("操作失败!");
		}

		return jsonResult;
	}

	/**
	 * 说明 : 新增考勤群组
	 * 
	 * @param para
	 * @return
	 * @author 付其浩
	 * @dateTime 2017年4月21日 下午4:59:15
	 */
	@ResponseBody
	@RequestMapping(value = "add", method = RequestMethod.POST)
	public JsonResult save(SignGroupPara para) {
		JsonResult jsonResult = new JsonResult();

		if (StringUtils.isEmpty(para.getName())) {
			jsonResult.setCode(HttpCode.BAD_REQUEST);
			jsonResult.setMsg("考勤群组名称不能为空!");
			return jsonResult;
		}

		if (StringUtils.isEmpty(para.getEmployeeIds())
				&& (StringUtils.isEmpty(para.getJobDeptIds()) || StringUtils.isEmpty(para.getJobIds()))) {
			jsonResult.setCode(HttpCode.BAD_REQUEST);
			jsonResult.setMsg("职位部门和人员必须选择一项!");
			return jsonResult;
		}
		if (StringUtils.isNotEmpty(para.getEmployeeIds())
				&& (StringUtils.isNotEmpty(para.getJobDeptIds()) || StringUtils.isNotEmpty(para.getJobIds()))) {
			jsonResult.setCode(HttpCode.BAD_REQUEST);
			jsonResult.setMsg("职位部门和人员只能选择一项!");
			return jsonResult;
		}

		if (StringUtils.isEmpty(para.getSignPointIds()) && StringUtils.isEmpty(para.getPlaceDeptIds())) {
			jsonResult.setCode(HttpCode.BAD_REQUEST);
			jsonResult.setMsg("考勤点和组织架构必须选择一项!");
			return jsonResult;
		}
		if (StringUtils.isNotEmpty(para.getSignPointIds()) && StringUtils.isNotEmpty(para.getPlaceDeptIds())) {
			jsonResult.setCode(HttpCode.BAD_REQUEST);
			jsonResult.setMsg("考勤点和组织架构只能选择一项!");
			return jsonResult;
		}

		try {
			signGroupService.save(para);
		} catch (RuntimeException e) {
			e.printStackTrace();
			jsonResult.setCode(HttpCode.BAD_REQUEST);
			jsonResult.setMsg("所选择的部门不是一个组织架构的部门!");
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setCode(HttpCode.INTERNAL_SERVER_ERROR);
			jsonResult.setMsg("操作失败!");
		}

		return jsonResult;
	}

	/**
	 * 说明 : 根据ID获取考勤群组信息
	 * 
	 * @param id
	 * @return
	 * @author 付其浩
	 * @dateTime 2017年4月23日 下午10:08:19
	 */
	@ResponseBody
	@RequestMapping(value = "getInfo", method = RequestMethod.GET)
	public JsonResult getInfo(Long id) {
		JsonResult jsonResult = new JsonResult();

		try {
			SignGroupVo signGroupVo = signGroupService.getInfo(id);
			jsonResult.setData(signGroupVo);
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setCode(HttpCode.INTERNAL_SERVER_ERROR);
			jsonResult.setMsg("操作失败!");
		}

		return jsonResult;
	}

	/**
	 * 说明 : 编辑考勤群组
	 * 
	 * @param para
	 * @return
	 * @author 付其浩
	 * @dateTime 2017年4月23日 下午10:22:12
	 */
	@ResponseBody
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public JsonResult update(SignGroupPara para) {
		JsonResult jsonResult = new JsonResult();

		if (StringUtils.isEmpty(para.getName())) {
			jsonResult.setCode(HttpCode.BAD_REQUEST);
			jsonResult.setMsg("考勤群组名称不能为空!");
			return jsonResult;
		}

		if (StringUtils.isEmpty(para.getEmployeeIds())
				&& (StringUtils.isEmpty(para.getJobDeptIds()) || StringUtils.isEmpty(para.getJobIds()))) {
			jsonResult.setCode(HttpCode.BAD_REQUEST);
			jsonResult.setMsg("职位部门和人员必须选择一项!");
			return jsonResult;
		}
		if (StringUtils.isNotEmpty(para.getEmployeeIds())
				&& (StringUtils.isNotEmpty(para.getJobDeptIds()) || StringUtils.isNotEmpty(para.getJobIds()))) {
			jsonResult.setCode(HttpCode.BAD_REQUEST);
			jsonResult.setMsg("职位部门和人员只能选择一项!");
			return jsonResult;
		}

		if (StringUtils.isEmpty(para.getSignPointIds()) && StringUtils.isEmpty(para.getPlaceDeptIds())) {
			jsonResult.setCode(HttpCode.BAD_REQUEST);
			jsonResult.setMsg("考勤点和组织架构必须选择一项!");
			return jsonResult;
		}
		if (StringUtils.isNotEmpty(para.getSignPointIds()) && StringUtils.isNotEmpty(para.getPlaceDeptIds())) {
			jsonResult.setCode(HttpCode.BAD_REQUEST);
			jsonResult.setMsg("考勤点和组织架构只能选择一项!");
			return jsonResult;
		}

		try {
			signGroupService.update(para);
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setCode(HttpCode.INTERNAL_SERVER_ERROR);
			jsonResult.setMsg("操作失败!");
		}

		return jsonResult;
	}

	/**
	 * 说明 : 删除考勤群组
	 * 
	 * @param ids
	 * @return
	 * @author 付其浩
	 * @dateTime 2017年4月23日 下午11:19:37
	 */
	@ResponseBody
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	public JsonResult delete(String ids) {
		JsonResult jsonResult = new JsonResult();

		if (StringUtils.isEmpty(ids)) {
			jsonResult.setCode(HttpCode.BAD_REQUEST);
			jsonResult.setMsg("参数异常!");
			return jsonResult;
		}

		try {
			signGroupService.delete(ids);
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setCode(HttpCode.INTERNAL_SERVER_ERROR);
			jsonResult.setMsg("操作失败!");
		}

		return jsonResult;
	}

	/**
	 * 说明 : 冻结和开启考勤群组
	 * 
	 * @param id
	 * @return
	 * @author 付其浩
	 * @dateTime 2017年4月21日 下午7:08:54
	 */
	@ResponseBody
	@RequestMapping(value = "state", method = RequestMethod.GET)
	public JsonResult updateState(Long id) {
		JsonResult jsonResult = new JsonResult();

		try {
			signGroupService.updateState(id);
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setCode(HttpCode.INTERNAL_SERVER_ERROR);
			jsonResult.setMsg("操作失败!");
		}

		return jsonResult;
	}

	/**
	 * 说明 : 查询此设备属于的考勤组(精简)
	 *
	 * @param sn
	 * @return
	 * @author 高伟南
	 * @dateTime 2017年4月21日 下午2:00:27
	 */
	@ResponseBody
	@RequestMapping(value = "simpleList", method = RequestMethod.GET)
	public JsonResult simpleList(String sn) {
		JsonResult jsonResult = new JsonResult();
		try {
			List<SimpleSignGroupVo> simpleList = signGroupService.findsimpleList(sn);
			jsonResult.setData(simpleList);
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setMsg("操作失败");
			jsonResult.setCode(HttpCode.INTERNAL_SERVER_ERROR);
		}

		return jsonResult;

	}

}
