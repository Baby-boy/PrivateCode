package com.lingang.platform.controller.basics;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lingang.api.domain.basic.JsonResult;
import com.lingang.api.domain.basic.Page;
import com.lingang.api.domain.basic.ServiceResult;
import com.lingang.api.domain.basic.StateCodeConstant;
import com.lingang.api.domain.entity.SysIndustryLabel;
import com.lingang.api.domain.entity.SysLabel;
import com.lingang.api.domain.entity.SysManager;
import com.lingang.api.domain.entity.SysParkLabel;
import com.lingang.api.domain.entity.SysPartnerLabel;
import com.lingang.api.domain.entity.SysPublicLabel;
import com.lingang.api.domain.entity.SysServiceLabel;
import com.lingang.api.domain.entity.SysStationLabel;
import com.lingang.api.domain.para.SysLabelPara;
import com.lingang.api.service.SysIndustryService;
import com.lingang.api.service.SysLabelService;
import com.lingang.api.service.SysLogService;
import com.lingang.api.service.SysParkService;
import com.lingang.api.service.SysPartnerService;
import com.lingang.api.service.SysPublicService;
import com.lingang.api.service.SysServiceService;
import com.lingang.api.service.SysStationService;
import com.lingang.common.util.IpAddressUtil;
/**
 *@Description: (管理标签)
 *@Author: zf(18817590078@163.com)
 *@date:2016年12月8日 下午3:57:18
 *@Version:1.0
 */
@Controller
@RequestMapping("/sysLabel")
public class SysLabelController {
	
	@Resource
	private SysLogService sysLogService;
	
	@Resource
	private SysLabelService sysLabelService;
	
	@Resource
	private SysParkService sysParkService;
	
	@Resource
	private SysIndustryService sysIndustryService;
	
	@Resource
	private SysServiceService sysServiceService;
	
	@Resource
	private SysStationService sysStationService;
	
	@Resource
	private SysPublicService sysPublicService;
	
	@Resource
	private SysPartnerService sysPartnerService;
	
	/**
	* @Description: (标签列表 针对权限处理)
	* @param para
	* @return    
	* @author zf(18817590078@163.com)
	* @date: 2016年12月8日 下午3:57:42
	 */
	@RequestMapping(value="/sysLabelPagePageListP.do",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public JsonResult sysLabelPagePageListP(@RequestBody SysLabelPara para) {
		JsonResult jsonResult = new JsonResult();
		ServiceResult<Page<SysLabel>> result=sysLabelService.selectSysLabelPageList(para);
		jsonResult.setStateCode(result.getStateCode());
		jsonResult.setData(result.getData());
		return jsonResult;
	}

	/**
	* @Description: (标签列表)
	* @param para
	* @return    
	* @author zf(18817590078@163.com)
	* @date: 2016年12月8日 下午3:57:42
	 */
	@RequestMapping(value="/sysLabelPagePageList.do",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public JsonResult sysLabelPagePageList(@RequestBody SysLabelPara para) {
		JsonResult jsonResult = new JsonResult();
		ServiceResult<Page<SysLabel>> result=sysLabelService.selectSysLabelPageList(para);
		jsonResult.setStateCode(result.getStateCode());
		jsonResult.setData(result.getData());
		return jsonResult;
	}
	
	/**
	* @Description: (标签列表的修改)
	* @param label
	* @return    
	* @author zf(18817590078@163.com)
	* @date: 2016年12月9日 下午7:23:50
	 */
	@RequestMapping(value="/updateSysLabel.do",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public JsonResult updateSysLabel(@RequestBody SysLabel label,HttpServletRequest request){
		JsonResult jsonResult = new JsonResult();
		Date newDate = new Date();
		label.setUpdateTime(newDate);
		ServiceResult<SysLabel> result=sysLabelService.updateSysLabel(label);
		jsonResult.setStateCode(result.getStateCode());
		jsonResult.setMessage(result.getMessage());
		jsonResult.setData(result.getData());
		
		// 记录日志
		if (result.getStateCode().equals(StateCodeConstant.SUCCESS_CODE)) {
			HttpSession session = request.getSession();
			SysManager manager = (SysManager) session.getAttribute("manager");
			sysLogService.insertSysLog(manager.getManagerAccount(), IpAddressUtil.getIpAddress(request), newDate,
					"修改标签信息");
		}
		return jsonResult;
	}
	
	/**
	* @Description: (添加标签)
	* @param label
	* @return    
	* @author zf(18817590078@163.com)
	* @date: 2016年12月10日 上午11:49:17
	 */
	@RequestMapping(value="/sysLabelAdd.do",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public JsonResult sysLabelAdd(@RequestBody SysLabel label,HttpServletRequest request){
		JsonResult jsonResult = new JsonResult();
		Date newDate = new Date();
		label.setCreateTime(newDate);
		ServiceResult<SysLabel> result=sysLabelService.sysLabelAdd(label);
		jsonResult.setStateCode(result.getStateCode());
		jsonResult.setMessage(result.getMessage());
		jsonResult.setData(result.getData());
		
		// 记录日志
		if (result.getStateCode().equals(StateCodeConstant.SUCCESS_CODE)) {
			HttpSession session = request.getSession();
			SysManager manager = (SysManager) session.getAttribute("manager");
			sysLogService.insertSysLog(manager.getManagerAccount(), IpAddressUtil.getIpAddress(request), newDate,
					"添加标签信息");
		}
		return jsonResult;
	}
	
	
	/**
	* @Description: (添加级联标签)
	* @return    
	* @author lgl(lgl1012dream@foxmail.com)
	* @date: 2016年12月14日 下午6:38:42
	 */
	@RequestMapping(value="/addLabelCascade.do",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public JsonResult addLabelCascade(@RequestBody SysLabelPara para,HttpServletRequest request){
		JsonResult jsonResult = new JsonResult();
		Date newDate = new Date();
		if(para.getLabelType().equals("8")){
			SysParkLabel parkLabel=new SysParkLabel();
			parkLabel.setParkId(para.getObjId());
			parkLabel.setLabelId(para.getLabelId());
			ServiceResult<Object> result=sysParkService.addLabelCascade(parkLabel);
			jsonResult.setStateCode(result.getStateCode());
			jsonResult.setMessage(result.getMessage());
		}else if(para.getLabelType().equals("1")){
			SysPartnerLabel industryLabel=new SysPartnerLabel();
			industryLabel.setPartnerId(para.getObjId());
			industryLabel.setLabelId(para.getLabelId());
			ServiceResult<Object> result=sysPartnerService.addLabelCascade(industryLabel);
			jsonResult.setStateCode(result.getStateCode());
			jsonResult.setMessage(result.getMessage());
		}else if(para.getLabelType().equals("10")){
			SysIndustryLabel industryLabel=new SysIndustryLabel();
			industryLabel.setIndustryId(para.getObjId());
			industryLabel.setLabelId(para.getLabelId());
			ServiceResult<Object> result=sysIndustryService.addLabelCascade(industryLabel);
			jsonResult.setStateCode(result.getStateCode());
			jsonResult.setMessage(result.getMessage());
		}else if(para.getLabelType().equals("11")){
			SysServiceLabel serviceLabel=new SysServiceLabel();
			serviceLabel.setServiceId(para.getObjId());
			serviceLabel.setLabelId(para.getLabelId());
			ServiceResult<Object> result=sysServiceService.addLabelCascade(serviceLabel);
			jsonResult.setStateCode(result.getStateCode());
			jsonResult.setMessage(result.getMessage());
		}else if(para.getLabelType().equals("3,4,5,6,7")){
			SysStationLabel stationLabel=new SysStationLabel();
			stationLabel.setStationId(para.getObjId());
			stationLabel.setLabelId(para.getLabelId());
			ServiceResult<Object> result=sysStationService.addLabelCascade(stationLabel);
			jsonResult.setStateCode(result.getStateCode());
			jsonResult.setMessage(result.getMessage());
		}else if(para.getLabelType().equals("9")){
			SysPublicLabel publicLabel=new SysPublicLabel();
			publicLabel.setPublicId(para.getObjId());
			publicLabel.setLabelId(para.getLabelId());
			ServiceResult<Object> result=sysPublicService.addLabelCascade(publicLabel);
			jsonResult.setStateCode(result.getStateCode());
			jsonResult.setMessage(result.getMessage());
		}
		
		// 记录日志
		if (jsonResult.getStateCode().equals(StateCodeConstant.SUCCESS_CODE)) {
			HttpSession session = request.getSession();
			SysManager manager = (SysManager) session.getAttribute("manager");
			sysLogService.insertSysLog(manager.getManagerAccount(), IpAddressUtil.getIpAddress(request), newDate,
					"添加级联标签");
		}
		return jsonResult;
	}
}
