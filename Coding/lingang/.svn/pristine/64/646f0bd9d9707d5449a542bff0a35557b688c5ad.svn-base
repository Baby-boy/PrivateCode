package com.lingang.platform.controller.home;

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
public class TopBasicsController {
	@Resource
	private SysBasicsService sysBasicsService;
	@ResponseBody
	@RequestMapping(value = "/getTopPartnerBasicsAll.do", method = { RequestMethod.GET, RequestMethod.POST })
	public JsonResult getPartnerBasicsAll() {
		JsonResult jsonResult = new JsonResult();
		Integer basicsType = 2;
		ServiceResult<List<SysBasics>> result = sysBasicsService.selectBasicsListByBasicsType(basicsType);
		jsonResult.setData(result.getData());
		jsonResult.setStateCode(result.getStateCode());
		return jsonResult;
	}
}
