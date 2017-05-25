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
import com.lingang.api.domain.vo.SysCompanyVo;
import com.lingang.api.domain.vo.SysNewAddStatisticsVo;
import com.lingang.api.domain.vo.SysParkStatisticsVo;
import com.lingang.api.service.SysBasicsService;
import com.lingang.api.service.SysParkService;
import com.lingang.api.service.SysPublicService;

/**
 *@Description: (公共平台统计)
 *@Author: lgl(lgl1012dream@foxmail.com)
 *@date:2016年12月10日 下午7:01:30
 *@Version:1.0
 */
@Controller
@RequestMapping("/publicStatistics")
public class PublicStatisticsController {
	
	@Resource
	private SysParkService sysParkService;
	
	@Resource
	private SysPublicService sysPublicService;
	
	@Resource
	private SysBasicsService sysBasicsService;

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
		ServiceResult<Page<SysParkStatisticsVo>> result=sysParkService.selectParkPublicStatisticsList(pageIndex, pageSize);
		jsonResult.setStateCode(result.getStateCode());
		jsonResult.setData(result.getData());
		jsonResult.setDataMap(result.getDataMap());
		return jsonResult;
	}
	
	/**
	* @Description: (新增平台)
	* @return    
	* @author lgl(lgl1012dream@foxmail.com)
	* @date: 2016年12月9日 下午5:13:31
	 */
	@RequestMapping(value="/newPublicStatisticsList.do",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public JsonResult newPublicStatisticsList( ){
		JsonResult jsonResult=new JsonResult();
		ServiceResult<List<SysNewAddStatisticsVo>> result=sysPublicService.selectNewPublicStatisticsList( );
		jsonResult.setStateCode(result.getStateCode());
		jsonResult.setData(result.getData());
		jsonResult.setDataMap(result.getDataMap());
		return jsonResult;
	}
	
	
	/**
	* @Description: (类别分布)
	* @return    
	* @author lgl(lgl1012dream@foxmail.com)
	* @date: 2016年12月9日 下午5:13:31
	 */
	@RequestMapping(value="/typePublicStatisticsList.do",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public JsonResult typePublicStatisticsList(){
		JsonResult jsonResult=new JsonResult();
		ServiceResult<List<SysBasicsVo>> result=sysBasicsService.selectTypePublicStatisticsList();
		jsonResult.setStateCode(result.getStateCode());
		jsonResult.setData(result.getData());
		jsonResult.setDataMap(result.getDataMap());
		return jsonResult;
	}
	
	/**
	 * @Description: (公共平台-创建单位接口)
	 * @return    
	 * @author cnk(chenningkang@adinnet.cn)
	 * @date: 2016年12月15日 下午5:20:31
	 */
	@RequestMapping(value="/selectPublicCompany.do",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public JsonResult selectStationCompany(){
		JsonResult jsonResult=new JsonResult();
		ServiceResult<List<SysCompanyVo>> result=sysPublicService.selectPublicCompany();
		jsonResult.setStateCode(result.getStateCode());
		jsonResult.setData(result.getData());
		jsonResult.setDataMap(result.getDataMap());
		return jsonResult;
		
	}
}
