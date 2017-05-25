package com.lingang.platform.controller.message;

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
import com.lingang.api.domain.entity.SysReply;
import com.lingang.api.domain.para.SysMessagePara;
import com.lingang.api.domain.pfvo.SysMessagePfvo;
import com.lingang.api.service.SysLogService;
import com.lingang.api.service.SysMessageService;
import com.lingang.common.util.IpAddressUtil;

@Controller
@RequestMapping("/sysMessage")
public class SysMessageConstroller {
	
	@Resource
	private SysLogService sysLogService;
	
	@Resource
	private SysMessageService sysMessageService;
	
	/**
	* @Description: (信息纠错)
	* @param pageIndex
	* @param pageSize
	* @param messageType
	* @return    
	* @author gsh(15136390655@163.com)
	* @date: 2016年12月8日 上午12:09:55
	 */
	@RequestMapping(value="/sysMessagePagePageListE.do",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public JsonResult sysMessagePagePageListE(@RequestBody SysMessagePara para){
		JsonResult jsonResult = new JsonResult();
		ServiceResult<Page<SysMessagePfvo>> result=sysMessageService.selectMessageAll(para);
		jsonResult.setStateCode(result.getStateCode());
		jsonResult.setData(result.getData());
		return jsonResult;
	};
	
	/**
	* @Description: (意见反馈)
	* @param pageIndex
	* @param pageSize
	* @param messageType
	* @return    
	* @author gsh(15136390655@163.com)
	* @date: 2016年12月8日 上午12:09:55
	 */
	@RequestMapping(value="/sysMessagePagePageList.do",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public JsonResult sysMessagePagePageList(@RequestBody SysMessagePara para){
		JsonResult jsonResult = new JsonResult();
		ServiceResult<Page<SysMessagePfvo>> result=sysMessageService.selectMessageAll(para);
		jsonResult.setStateCode(result.getStateCode());
		jsonResult.setData(result.getData());
		return jsonResult;
	};
	/**
	 * 
	* @Description: (修改回复状态)
	* @param para
	* @return    
	* @author gsh(15136390655@163.com)
	* @date: 2016年12月9日 下午1:53:11
	 */
	@RequestMapping(value="/sysMessageReply.do",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public JsonResult sysMessageReply(@RequestBody SysMessagePara para,HttpServletRequest request){
		JsonResult jsonResult = new JsonResult();
		Date newDate = new Date();
		
		// 后台管理员为了测试,传入定值
		para.setManagerId(1);
		
		ServiceResult<SysReply> result=sysMessageService.updateMessageType(para);
		jsonResult.setStateCode(result.getStateCode());
		jsonResult.setData(result.getData());
		
		// 记录日志
		if (result.getStateCode().equals(StateCodeConstant.SUCCESS_CODE)) {
			HttpSession session = request.getSession();
			SysManager manager = (SysManager) session.getAttribute("manager");
			sysLogService.insertSysLog(manager.getManagerAccount(), IpAddressUtil.getIpAddress(request), newDate,
					"回复信息");
		}
		return jsonResult;
	};
	/**
	* @Description: (删除)
	* @param para
	* @return    
	* @author gsh(15136390655@163.com)
	* @date: 2016年12月9日 下午5:32:56
	 */
	@RequestMapping(value="/delMessage.do",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public JsonResult delMessage(@RequestBody SysMessagePara para){
		JsonResult jsonResult = new JsonResult();
		ServiceResult<Integer> result=sysMessageService.delMessage(para);
		jsonResult.setStateCode(result.getStateCode());
		jsonResult.setData(result.getData());
		return jsonResult;
	};

}
