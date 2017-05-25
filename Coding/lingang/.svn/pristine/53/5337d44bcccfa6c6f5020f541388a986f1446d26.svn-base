package com.lingang.consumer.controller.other;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lingang.api.domain.basic.JsonResult;
import com.lingang.api.domain.basic.ServiceResult;
import com.lingang.api.service.SysLabelService;
@Controller
@RequestMapping("/label")
public class SysLabelController {
	@Resource
	private SysLabelService sysLabelService;
	/**
	* @Description: (标签：入住产业标签)
	* @return    
	* @author gsh(15136390655@163.com)
	* @date: 2016年12月7日 下午12:01:57
	 */
	@RequestMapping(value = "/labelList.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public JsonResult LabelList(){
		JsonResult jsonResult = new JsonResult();
		ServiceResult<Map<String,Object>> result = sysLabelService.selectSysLabelByType();
		jsonResult.setStateCode(result.getStateCode());
		jsonResult.setMessage(result.getMessage());
		jsonResult.setDataMap(result.getDataMap());
		return jsonResult;
	}

}
