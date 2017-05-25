package com.lingang.platform.controller.seek;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lingang.api.domain.basic.JsonResult;
import com.lingang.api.domain.basic.ServiceResult;
import com.lingang.api.domain.entity.SysBasics;
import com.lingang.api.service.SysBasicsService;

@Controller
@RequestMapping("/sysBasics")
public class BasicsController {

	@Resource
	private SysBasicsService sysBasicsService;

	/**
	 * @Description: (查询合作层级列表_后台_合作伙伴)
	 * @return 参数注释
	 * @author fqh (qihao.fu@outlook.com)
	 * @date: 2016年12月13日 下午7:49:01
	 */
	@ResponseBody
	@RequestMapping(value = "/getPartnerBasicsAll.do", method = { RequestMethod.GET, RequestMethod.POST })
	public JsonResult getPartnerBasicsAll() {
		JsonResult jsonResult = new JsonResult();
		Integer basicsType = 2;
		ServiceResult<List<SysBasics>> result = sysBasicsService.selectBasicsListByBasicsType(basicsType);
		jsonResult.setData(result.getData());
		jsonResult.setStateCode(result.getStateCode());
		return jsonResult;
	}

	/**
	 * @Description: (查询合作层级列表_后台_服务机构)
	 * @return 参数注释
	 * @author fqh (qihao.fu@outlook.com)
	 * @date: 2016年12月14日 下午1:43:31
	 */
	@ResponseBody
	@RequestMapping(value = "/getServiceBasicsAll.do", method = { RequestMethod.GET, RequestMethod.POST })
	public JsonResult getServiceBasicsAll() {
		JsonResult jsonResult = new JsonResult();
		Integer basicsType = 1;
		ServiceResult<List<SysBasics>> result = sysBasicsService.selectBasicsListByBasicsType(basicsType);
		jsonResult.setData(result.getData());
		jsonResult.setStateCode(result.getStateCode());
		return jsonResult;
	}

	/**
	 * @Description: (查询合作层级列表_后台_公共平台)
	 * @return 参数注释
	 * @author fqh (qihao.fu@outlook.com)
	 * @date: 2016年12月14日 下午1:45:40
	 */
	@ResponseBody
	@RequestMapping(value = "/getPublicBasicsAll.do", method = { RequestMethod.GET, RequestMethod.POST })
	public JsonResult getPublicBasicsAll() {
		JsonResult jsonResult = new JsonResult();
		Integer basicsType = 3;
		ServiceResult<List<SysBasics>> result = sysBasicsService.selectBasicsListByBasicsType(basicsType);
		jsonResult.setData(result.getData());
		jsonResult.setStateCode(result.getStateCode());
		return jsonResult;
	}
}
