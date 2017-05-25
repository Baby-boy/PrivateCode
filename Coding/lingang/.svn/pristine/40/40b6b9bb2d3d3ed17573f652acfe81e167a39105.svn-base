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
import com.lingang.api.domain.entity.SysManager;
import com.lingang.api.domain.entity.SysPartnerType;
import com.lingang.api.domain.para.SysPartnerTypePara;
import com.lingang.api.service.SysLogService;
import com.lingang.api.service.SysPartnerTypeService;
import com.lingang.common.util.IpAddressUtil;

@Controller
@RequestMapping("/sysPartnerType")
public class SysPartnerTypeController {
	
	@Resource
	private SysLogService sysLogService;

	@Resource
	private SysPartnerTypeService sysPartnerTypeService;
	
	/**
	 * @Description: (查询所有合作类型_后台 权限设置)
	 * @return 参数注释
	 * @author fqh (qihao.fu@outlook.com)
	 * @date: 2017年1月9日 下午1:48:56
	 */
	@ResponseBody
	@RequestMapping(value = "/selectPartnerTypePageListP.do", method = { RequestMethod.GET, RequestMethod.POST })
	public JsonResult selectPartnerTypePageListP(@RequestBody SysPartnerTypePara para) {
		JsonResult jsonResult = new JsonResult();
		ServiceResult<Page<SysPartnerType>> result = sysPartnerTypeService.selectPartnerTypePageList(para);
		jsonResult.setData(result.getData());
		jsonResult.setStateCode(result.getStateCode());
		return jsonResult;
	}

	/**
	 * @Description: (查询所有合作类型_后台)
	 * @return 参数注释
	 * @author fqh (qihao.fu@outlook.com)
	 * @date: 2017年1月9日 下午1:48:56
	 */
	@ResponseBody
	@RequestMapping(value = "/selectPartnerTypePageList.do", method = { RequestMethod.GET, RequestMethod.POST })
	public JsonResult selectPartnerTypePageList(@RequestBody SysPartnerTypePara para) {
		JsonResult jsonResult = new JsonResult();
		ServiceResult<Page<SysPartnerType>> result = sysPartnerTypeService.selectPartnerTypePageList(para);
		jsonResult.setData(result.getData());
		jsonResult.setStateCode(result.getStateCode());
		return jsonResult;
	}

	/**
	 * @Description: (修改合作类型)
	 * @param para
	 * @return 参数注释
	 * @author fqh (qihao.fu@outlook.com)
	 * @date: 2017年1月9日 下午3:53:15
	 */
	@ResponseBody
	@RequestMapping(value = "/updatePartnerType.do", method = { RequestMethod.GET, RequestMethod.POST })
	public JsonResult updatePartnerType(@RequestBody SysPartnerType para,HttpServletRequest request) {
		JsonResult jsonResult = new JsonResult();
		Date newDate = new Date();
		para.setUpdateTime(newDate);
		ServiceResult<Object> result = sysPartnerTypeService.updatePartnerType(para);
		jsonResult.setStateCode(result.getStateCode());
		jsonResult.setMessage(result.getMessage());
		
		// 记录日志
		if (result.getStateCode().equals(StateCodeConstant.SUCCESS_CODE)) {
			HttpSession session = request.getSession();
			SysManager manager = (SysManager) session.getAttribute("manager");
			sysLogService.insertSysLog(manager.getManagerAccount(), IpAddressUtil.getIpAddress(request), newDate,
					"修改合作类型");
		}
		return jsonResult;
	}

	/**
	 * @Description: (添加合作类型)
	 * @param para
	 * @return 参数注释
	 * @author fqh (qihao.fu@outlook.com)
	 * @date: 2017年1月9日 下午3:58:47
	 */
	@ResponseBody
	@RequestMapping(value = "/addPartnerType.do", method = { RequestMethod.GET, RequestMethod.POST })
	public JsonResult addPartnerType(@RequestBody SysPartnerType para,HttpServletRequest request) {
		JsonResult jsonResult = new JsonResult();
		Date newDate = new Date();
		para.setCreateTime(newDate);
		para.setTypeId(null);// 防止恶意传参
		ServiceResult<Object> result = sysPartnerTypeService.addPartnerType(para);
		jsonResult.setStateCode(result.getStateCode());
		jsonResult.setMessage(result.getMessage());
		
		// 记录日志
		if (result.getStateCode().equals(StateCodeConstant.SUCCESS_CODE)) {
			HttpSession session = request.getSession();
			SysManager manager = (SysManager) session.getAttribute("manager");
			sysLogService.insertSysLog(manager.getManagerAccount(), IpAddressUtil.getIpAddress(request), newDate,
					"添加合作类型");
		}
		return jsonResult;
	}
}
