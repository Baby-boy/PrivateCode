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
import com.lingang.api.domain.entity.SysBasics;
import com.lingang.api.domain.entity.SysManager;
import com.lingang.api.domain.para.SysBasicsPara;
import com.lingang.api.service.SysBasicsService;
import com.lingang.api.service.SysLogService;
import com.lingang.common.util.IpAddressUtil;

/**
 * @Description: (基础设置信息控制)
 * @Author: zf(18817590078@163.com)
 * @date:2016年12月7日 上午12:09:38
 * @Version:1.0
 */
@Controller
@RequestMapping("/sysBasics")
public class SysBasicsController {

	@Resource
	private SysLogService sysLogService;

	@Resource
	private SysBasicsService sysBasicsService;

	/**
	 * @Description: (基础设置列表 针对权限使用)
	 * @param para
	 * @return
	 * @author lgl(lgl1012dream@foxmail.com)
	 * @date: 2017年1月18日 下午7:35:40
	 */
	@RequestMapping(value = "/sysBasicsPagePageListP.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public JsonResult sysBasicsPagePageListPower(@RequestBody SysBasicsPara para) {
		JsonResult jsonResult = new JsonResult();
		ServiceResult<Page<SysBasics>> result = sysBasicsService.selectSysBasicsPageList(para);
		jsonResult.setStateCode(result.getStateCode());
		jsonResult.setData(result.getData());
		return jsonResult;
	}

	/**
	 * @Description: (基础设置列表)
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 * @author zf(18817590078@163.com)
	 * @date: 2016年12月6日 下午7:43:42
	 */
	@RequestMapping(value = "/sysBasicsPagePageList.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public JsonResult sysBasicsPagePageList(@RequestBody SysBasicsPara para) {
		JsonResult jsonResult = new JsonResult();
		ServiceResult<Page<SysBasics>> result = sysBasicsService.selectSysBasicsPageList(para);
		jsonResult.setStateCode(result.getStateCode());
		jsonResult.setData(result.getData());
		return jsonResult;
	}

	/**
	 * @Description: (基础设置列表的修改)
	 * @param para(基础设置修改之后的数据)
	 * @return
	 * @author zf(18817590078@163.com)
	 * @date: 2016年12月7日 下午7:39:21
	 */
	@RequestMapping(value = "/updateSysBasics.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public JsonResult updateSysBasics(@RequestBody SysBasics basics,HttpServletRequest request) {
		JsonResult jsonResult = new JsonResult();
		Date newDate = new Date();
		basics.setUpdateTime(newDate);

		ServiceResult<SysBasics> result = sysBasicsService.updateSysBasics(basics);
		jsonResult.setStateCode(result.getStateCode());
		jsonResult.setMessage(result.getMessage());
		jsonResult.setData(result.getData());

		// 记录日志
		if (result.getStateCode().equals(StateCodeConstant.SUCCESS_CODE)) {
//			String typeStr="";
//			if(basics.getBasicsType()==1){
//				typeStr="服务信息";
//			}else if(basics.getBasicsType()==2){
//				typeStr="合作层级";
//			}else if(basics.getBasicsType()==3){
//				typeStr="开放层级";
//			}else{
//				return jsonResult;
//			}
			HttpSession session = request.getSession();
			SysManager manager = (SysManager) session.getAttribute("manager");
			sysLogService.insertSysLog(manager.getManagerAccount(), IpAddressUtil.getIpAddress(request), newDate,
					"修改基础类型");
		}

		return jsonResult;
	}

	/**
	 * @Description: (添加基础设置)
	 * @param basics
	 * @return
	 * @author zf(18817590078@163.com)
	 * @date: 2016年12月9日 下午5:39:01
	 */
	@RequestMapping(value = "/sysBasicsAdd.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public JsonResult sysBasicsAdd(@RequestBody SysBasics basics,HttpServletRequest request) {
		JsonResult jsonResult = new JsonResult();
		Date newDate = new Date();
		basics.setCreateTime(newDate);
		ServiceResult<SysBasics> result = sysBasicsService.addSysBasics(basics);
		jsonResult.setStateCode(result.getStateCode());
		jsonResult.setMessage(result.getMessage());
		jsonResult.setData(result.getData());
		
		// 记录日志
		if (result.getStateCode().equals(StateCodeConstant.SUCCESS_CODE)) {
			HttpSession session = request.getSession();
			SysManager manager = (SysManager) session.getAttribute("manager");
			sysLogService.insertSysLog(manager.getManagerAccount(), IpAddressUtil.getIpAddress(request), newDate,
					"添加基础类型");
		}
		return jsonResult;
	}
}
