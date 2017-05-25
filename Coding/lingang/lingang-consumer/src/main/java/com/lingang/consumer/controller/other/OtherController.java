package com.lingang.consumer.controller.other;

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
import com.lingang.api.domain.entity.SysBasics;
import com.lingang.api.domain.entity.SysIndustry;
import com.lingang.api.domain.entity.SysPartnerType;
import com.lingang.api.service.SysBasicsService;
import com.lingang.api.service.SysIndustryService;
import com.lingang.api.service.SysParkService;
import com.lingang.api.service.SysPartnerTypeService;
import com.lingang.api.service.SysRegionService;

@Controller
@RequestMapping("/other")
public class OtherController {
	
	@Resource
	private SysRegionService sysRegionService;
	
	@Resource
	private SysIndustryService sysIndustryService;
	
	@Resource
	private SysPartnerTypeService sysPartnerTypeService;
	
	@Resource
	private SysBasicsService sysBasicsService;
	
	@Resource
	private SysParkService sysParkService;

	/**
	* @Description: (地区列表)
	* @return    
	* @author lgl(lgl1012dream@foxmail.com)
	* @date: 2016年12月5日 上午9:55:34
	*/
	@RequestMapping(value="/regionList.do",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public JsonResult regionList(){
		JsonResult jsonResult=new JsonResult();
		ServiceResult<Object> result=sysRegionService.selectRegionAllList();
		jsonResult.setStateCode(result.getStateCode());
		jsonResult.setData(result.getData());
		jsonResult.setDataMap(result.getDataMap());
		return jsonResult;
	} 
	
	/**
	* @Description: (园区列表)
	* @return    
	* @author lgl(lgl1012dream@foxmail.com)
	* @date: 2016年12月5日 上午9:55:34
	*/
	@RequestMapping(value="/parkList.do",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public JsonResult parkList(){
		JsonResult jsonResult=new JsonResult();
		ServiceResult<Object> result=sysParkService.selectParkAllList();
		jsonResult.setStateCode(result.getStateCode());
		jsonResult.setData(result.getData());
		jsonResult.setDataMap(result.getDataMap());
		return jsonResult;
	} 
	
	/**
	* @Description: (产业列表)
	* @param pageIndex
	* @param pageSize
	* @return    
	* @author lgl(lgl1012dream@foxmail.com)
	* @date: 2016年12月5日 上午10:19:00
	 */
	@RequestMapping(value="/industryList.do",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public JsonResult industryList(@RequestParam(value="pageIndex",required=false,defaultValue="1")Integer pageIndex,
			  @RequestParam(value="pageSize",required=false,defaultValue="10")Integer pageSize){
		JsonResult jsonResult=new JsonResult();
		ServiceResult<Page<SysIndustry>> result=sysIndustryService.selectIndustryList(pageIndex, pageSize);
		jsonResult.setStateCode(result.getStateCode());
		jsonResult.setData(result.getData());
		return jsonResult;
	}
	
	/**
	* @Description: (产业集团列表)
	* @param pageIndex
	* @param pageSize
	* @return    
	* @author lgl(lgl1012dream@foxmail.com)
	* @date: 2016年12月5日 上午11:30:43
	 */
	@RequestMapping(value="/partnerTypeList.do",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public JsonResult partnerTypeList(@RequestParam(value="pageIndex",required=false,defaultValue="1")Integer pageIndex,
			  @RequestParam(value="pageSize",required=false,defaultValue="10")Integer pageSize){
		JsonResult jsonResult=new JsonResult();
		ServiceResult<Page<SysPartnerType>> result=sysPartnerTypeService.selectPartnerTypeList(pageIndex, pageSize);
		jsonResult.setStateCode(result.getStateCode());
		jsonResult.setData(result.getData());
		return jsonResult;
	}
	
	/**
	* @Description: (合作层级/服务信息)
	* @param basicsType
	* @return    
	* @author lgl(lgl1012dream@foxmail.com)
	* @date: 2016年12月10日 上午11:22:07
	 */
	@RequestMapping(value="/basicsList.do",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public JsonResult basicsList(@RequestParam(value="basicsType",required=true)Integer basicsType
			){
		JsonResult jsonResult=new JsonResult();
		ServiceResult<List<SysBasics>> result=sysBasicsService.selectBasicsListByBasicsType(basicsType);
		jsonResult.setStateCode(result.getStateCode());
		jsonResult.setData(result.getData());
		return jsonResult;
	}
}
