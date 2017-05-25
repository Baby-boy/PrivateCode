package com.lingang.consumer.controller.statistics;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lingang.api.domain.basic.JsonResult;
import com.lingang.api.domain.basic.Page;
import com.lingang.api.domain.basic.ServiceResult;
import com.lingang.api.domain.vo.SysBasicsVo;
import com.lingang.api.domain.vo.SysLabelsVo;
import com.lingang.api.domain.vo.SysNewAddStatisticsVo;
import com.lingang.api.domain.vo.SysParkStatisticsVo;
import com.lingang.api.service.SysBasicsService;
import com.lingang.api.service.SysParkService;
import com.lingang.api.service.SysServiceService;

/**
 *@Description: (服务机构统计)
 *@Author: lgl(lgl1012dream@foxmail.com)
 *@date:2016年12月10日 下午4:01:31
 *@Version:1.0
 */
@Controller
@RequestMapping("/serviceStatistics")
public class ServiceStatisticsController {
	
	@Resource
	private SysBasicsService sysBasicsService;
	
	@Resource
	private SysParkService sysParkService;
	
	@Resource
	private SysServiceService sysServiceService;
	

	/**
	* @Description: (类别分布)
	* @return    
	* @author lgl(lgl1012dream@foxmail.com)
	* @date: 2016年12月9日 下午5:13:31
	 */
	@RequestMapping(value="/typeServiceStatisticsList.do",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public JsonResult typeServiceStatisticsList(){
		JsonResult jsonResult=new JsonResult();
		ServiceResult<List<SysBasicsVo>> result=sysBasicsService.selectTypeServiceStatisticsList();
		jsonResult.setStateCode(result.getStateCode());
		jsonResult.setData(result.getData());
		jsonResult.setDataMap(result.getDataMap());
		return jsonResult;
	}
	
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
		ServiceResult<Page<SysParkStatisticsVo>> result=sysParkService.selectParkServiceStatisticsList(pageIndex, pageSize);
		jsonResult.setStateCode(result.getStateCode());
		jsonResult.setData(result.getData());
		jsonResult.setDataMap(result.getDataMap());
		return jsonResult;
	}
	
	
	/**
	* @Description: (新增机构)
	* @return    
	* @author lgl(lgl1012dream@foxmail.com)
	* @date: 2016年12月9日 下午5:13:31
	 */
	@RequestMapping(value="/newServiceStatisticsList.do",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public JsonResult newServiceStatisticsList( ){
		JsonResult jsonResult=new JsonResult();
		ServiceResult<List<SysNewAddStatisticsVo>> result = sysServiceService.selectNewServiceStatisticsList( );
		jsonResult.setStateCode(result.getStateCode());
		jsonResult.setData(result.getData());
		jsonResult.setDataMap(result.getDataMap());
		return jsonResult;
	}
	/**
	* @Description: (服务机构接口)
	* @return    
	* @author cnk(chenningkang@adinnet.cn)
	* @date: 2016年12月15日 下午5:13:31
	 */
	@RequestMapping(value="/selectServiceLabel.do",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public JsonResult getServiceLabelList(){
		JsonResult jsonResult=new JsonResult();
		ServiceResult<List<SysLabelsVo>> result=sysServiceService.selectServicelabel();
		jsonResult.setStateCode(result.getStateCode());
		jsonResult.setData(result.getData());
		jsonResult.setDataMap(result.getDataMap());
		return jsonResult;
		
	}
	
}
