package com.lingang.consumer.controller.statistics;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lingang.api.domain.basic.JsonResult;
import com.lingang.api.domain.basic.Page;
import com.lingang.api.domain.basic.ServiceResult;
import com.lingang.api.domain.vo.SysParkStatisticsVo;
import com.lingang.api.service.SysParkService;

@Controller
@RequestMapping("/industryStatistics")
public class IndustryStatisticsController {
	
	@Resource
	private SysParkService sysParkService;

	/**
	* @Description: (园区分布)
	* @return    
	* @author lgl(lgl1012dream@foxmail.com)
	* @date: 2016年12月9日 下午5:13:31
	 */
	@RequestMapping(value="/parkStatisticsList.do",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public JsonResult parkStatisticsList(@RequestParam(value = "pageIndex", required = false, defaultValue = "1") Integer pageIndex,
			@RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize){
		JsonResult jsonResult=new JsonResult();
		ServiceResult<Page<SysParkStatisticsVo>> result=sysParkService.selectParkIndustryStatisticsList(pageIndex, pageSize);
		jsonResult.setStateCode(result.getStateCode());
		jsonResult.setData(result.getData());
		jsonResult.setDataMap(result.getDataMap());
		return jsonResult;
	}
	
}
