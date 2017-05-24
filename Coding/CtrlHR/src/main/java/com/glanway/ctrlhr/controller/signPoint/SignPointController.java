package com.glanway.ctrlhr.controller.signPoint;

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
import com.glanway.ctrlhr.entity.para.KeywordPara;
import com.glanway.ctrlhr.entity.para.SignPointPara;
import com.glanway.ctrlhr.entity.signPoint.SignPoint;
import com.glanway.ctrlhr.entity.vo.SignPointVo;
import com.glanway.ctrlhr.entity.vo.SimpleSignPointVo;
import com.glanway.ctrlhr.service.signPoint.SignPointService;

@Controller
@RequestMapping("/api/signPoint")
public class SignPointController {

	@Autowired
	private SignPointService signPointService;

	/**
	 * 说明 : 查询考勤点列表
	 * 
	 * @param para
	 * @return
	 * @author 付其浩
	 * @dateTime 2017年4月19日 下午1:47:29
	 */
	@ResponseBody
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public JsonResult list(SignPointPara para) {
		JsonResult jsonResult = new JsonResult();

		try {
			Page<SignPoint> page = signPointService.findList(para);
			jsonResult.setData(page);
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setCode(HttpCode.INTERNAL_SERVER_ERROR);
			jsonResult.setMsg("操作失败!");
		}

		return jsonResult;
	}

	/**
	 * 说明 : 新增考勤点
	 * 
	 * @param para
	 * @return
	 * @author 付其浩
	 * @dateTime 2017年4月19日 下午3:04:45
	 */
	@ResponseBody
	@RequestMapping(value = "add", method = RequestMethod.POST)
	public JsonResult add(SignPointPara para) {
		JsonResult jsonResult = new JsonResult();

		if (StringUtils.isEmpty(para.getName())) {
			jsonResult.setCode(HttpCode.BAD_REQUEST);
			jsonResult.setMsg("考勤点名称不能为空!");
			return jsonResult;
		}

		try {
			signPointService.save(para);
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setCode(HttpCode.INTERNAL_SERVER_ERROR);
			jsonResult.setMsg("操作失败!");
		}

		return jsonResult;
	}

	/**
	 * 说明 : 根据ID获取考勤点的信息
	 * 
	 * @param id
	 * @return
	 * @author 付其浩
	 * @dateTime 2017年4月19日 下午5:33:32
	 */
	@ResponseBody
	@RequestMapping(value = "getInfo", method = RequestMethod.GET)
	public JsonResult getInfo(Long id) {
		JsonResult jsonResult = new JsonResult();

		try {
			SignPointVo signPointVo = signPointService.getInfo(id);
			if (null == signPointVo) {
				jsonResult.setCode(HttpCode.NO_CONTENT);
				jsonResult.setMsg("查无信息!");
				return jsonResult;
			}
			jsonResult.setData(signPointVo);
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setCode(HttpCode.INTERNAL_SERVER_ERROR);
			jsonResult.setMsg("操作失败!");
		}

		return jsonResult;
	}

	/**
	 * 说明 : 更新考勤点信息(备注:1.更新未绑定的设备--需要清除现在已绑定的考勤点,调整状态;2.更新部门--删除所有中间吧重新建立)
	 * 
	 * @param para
	 * @return
	 * @author 付其浩
	 * @dateTime 2017年4月19日 下午5:44:04
	 */
	@ResponseBody
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public JsonResult update(SignPointPara para) {
		JsonResult jsonResult = new JsonResult();

		try {
			signPointService.update(para);
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setCode(HttpCode.INTERNAL_SERVER_ERROR);
			jsonResult.setMsg("操作失败!");
		}

		return jsonResult;
	}

	/**
	 * 说明 : 删除考勤点(只能删除既没有部门也没有设备的考勤点)
	 * 
	 * @param para
	 * @return
	 * @author 付其浩
	 * @dateTime 2017年4月19日 下午6:02:53
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
			JsonResult result = signPointService.delete(ids);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setCode(HttpCode.INTERNAL_SERVER_ERROR);
			jsonResult.setMsg("操作失败!");
		}

		return jsonResult;
	}

	/**
	 * 说明 : 查询考勤点(精简)列表(备注: 用于新建设备绑定考勤点使用,这里的考勤点没有过滤条件)
	 * 
	 * @param para
	 * @return
	 * @author 付其浩
	 * @dateTime 2017年4月21日 上午9:39:51
	 */
	@ResponseBody
	@RequestMapping(value = "simpleList", method = RequestMethod.GET)
	public JsonResult simpleList(KeywordPara para) {
		JsonResult jsonResult = new JsonResult();

		try {
			List<SimpleSignPointVo> simpleList = signPointService.findSimpleList(para);
			jsonResult.setData(simpleList);
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setCode(HttpCode.INTERNAL_SERVER_ERROR);
			jsonResult.setMsg("操作失败!");
		}

		return jsonResult;
	}

}
