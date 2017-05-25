package com.lingang.consumer.controller.other;

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
@RequestMapping("/basics")
public class SysBasicsController {
	@Resource
	private SysBasicsService sysBasicsService;
	/**
	* @Description: (开放层级)
	* @return    
	* @author gsh(15136390655@163.com)
	* @date: 2016年12月7日 下午4:31:02
	 */
	@RequestMapping(value="/basicsList",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public JsonResult basicsList(){
		JsonResult jsonResult = new JsonResult();
		ServiceResult<List<SysBasics>> result = sysBasicsService.selectSysBasics();
		jsonResult.setStateCode(result.getStateCode());
		jsonResult.setMessage(result.getMessage());
		jsonResult.setData(result.getData());
		return jsonResult;
	};

}
