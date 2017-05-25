package com.lingang.platform.controller.system;

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
import com.lingang.api.domain.entity.SysLog;
import com.lingang.api.domain.entity.SysManager;
import com.lingang.api.domain.entity.SysPower;
import com.lingang.api.domain.para.SysLogPara;
import com.lingang.api.domain.para.SysManagerPara;
import com.lingang.api.domain.pfvo.SysManagerPfVo;
import com.lingang.api.service.SysLogService;
import com.lingang.api.service.SysManagerService;
import com.lingang.common.util.IpAddressUtil;

/**
 * @Description: (管理员信息控制)
 * @Author: lgl(lgl1012dream@foxmail.com)
 * @date:2016年12月6日 下午7:32:04
 * @Version:1.0
 */
@Controller
@RequestMapping("/sysManager")
public class SysManagerController {
	
	@Resource
	private SysLogService sysLogService;
	
	@Resource
	private SysManagerService sysManagerService;

	/**
	* @Description: (管理员列表)
	* @param pageIndex
	* @param pageSize
	* @return    
	* @author lgl(lgl1012dream@foxmail.com)
	* @date: 2016年12月6日 下午7:43:42
	 */
	@RequestMapping(value="/sysManagerPagePageList.do",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public JsonResult sysManagerPagePageList(@RequestBody SysManagerPara para) {
		JsonResult jsonResult = new JsonResult();
		ServiceResult<Page<SysManagerPfVo>> result=sysManagerService.selectSysManagerPageList(para);
		jsonResult.setStateCode(result.getStateCode());
		jsonResult.setData(result.getData());
		return jsonResult;
	}
	
	/**
	* @Description: (修改管理员信息)
	* @param manager
	* @return    
	* @author lgl(lgl1012dream@foxmail.com)
	* @date: 2016年12月7日 下午8:08:12
	 */
	@RequestMapping(value="/updateSysManager.do",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public JsonResult updateSysManager(@RequestBody SysManager manager,HttpServletRequest request){
		JsonResult jsonResult = new JsonResult();
		Date newDate = new Date();
		manager.setUpdateTime(newDate);
		ServiceResult<SysManager> result=sysManagerService.updateSysManager(manager);
		jsonResult.setStateCode(result.getStateCode());
		jsonResult.setMessage(result.getMessage());
		jsonResult.setData(result.getData());
		
		// 记录日志
		if (result.getStateCode().equals(StateCodeConstant.SUCCESS_CODE)) {
			HttpSession session = request.getSession();
			SysManager manager2 = (SysManager) session.getAttribute("manager");
			sysLogService.insertSysLog(manager2.getManagerAccount(), IpAddressUtil.getIpAddress(request), newDate,
					"修改管理员信息");
		}
		return jsonResult;
	}
	
	/**
	 * @Description: (添加管理员)
	 * @param manager
	 * @return 参数注释 
	 * @author fqh (qihao.fu@outlook.com)
	 * @date: 2016年12月20日 上午11:42:45 
	 */
	@ResponseBody
	@RequestMapping(value="/addSysManager.do",method={RequestMethod.GET,RequestMethod.POST})
	public JsonResult addSysManager(@RequestBody SysManager manager,HttpServletRequest request){
		JsonResult jsonResult = new JsonResult();
		Date newDate = new Date();
		manager.setCreateTime(newDate);
		ServiceResult<SysManager> result = sysManagerService.addSysManager(manager);
		jsonResult.setStateCode(result.getStateCode());
		jsonResult.setMessage(result.getMessage());
		
		// 记录日志
		if (result.getStateCode().equals(StateCodeConstant.SUCCESS_CODE)) {
			HttpSession session = request.getSession();
			SysManager manager2 = (SysManager) session.getAttribute("manager");
			sysLogService.insertSysLog(manager2.getManagerAccount(), IpAddressUtil.getIpAddress(request), newDate,
					"添加管理员信息");
		}
		return jsonResult;
	}
	
	/**
	* @Description: (修改权限)
	* @param power
	* @return    
	* @author lgl(lgl1012dream@foxmail.com)
	* @date: 2017年1月17日 下午6:41:04
	 */
	@ResponseBody
	@RequestMapping(value="/updatePower.do",method={RequestMethod.GET,RequestMethod.POST})
	public JsonResult updatePower(@RequestBody SysPower power,HttpServletRequest request){
		JsonResult jsonResult = new JsonResult();
		if(power.getPowerId()==null){
			ServiceResult<SysPower> result = sysManagerService.addPower(power);
			if(result.getStateCode().equals(StateCodeConstant.SUCCESS_CODE)){
				result=updateSessionPower(result, request);
			}
			jsonResult.setStateCode(result.getStateCode());
			jsonResult.setMessage(result.getMessage());
		}else{
			ServiceResult<SysPower> result = sysManagerService.updatePower(power);
			if(result.getStateCode().equals(StateCodeConstant.SUCCESS_CODE)){
				result=updateSessionPower(result, request);
			}
			jsonResult.setStateCode(result.getStateCode());
			jsonResult.setMessage(result.getMessage());
		}
		
		// 记录日志
		if (jsonResult.getStateCode().equals(StateCodeConstant.SUCCESS_CODE)) {
			HttpSession session = request.getSession();
			SysManager manager = (SysManager) session.getAttribute("manager");
			sysLogService.insertSysLog(manager.getManagerAccount(), IpAddressUtil.getIpAddress(request), new Date(),
					"修改管理员权限");
		}
		return jsonResult;
	}
	
	
	/**
	* @Description: (登录账号权限发生变化，更新缓存)
	* @param result
	* @param request
	* @return    
	* @author lgl(lgl1012dream@foxmail.com)
	* @date: 2017年1月18日 下午2:57:56
	 */
	private ServiceResult<SysPower> updateSessionPower(ServiceResult<SysPower> result,HttpServletRequest request){
		HttpSession session=request.getSession();
		String pId= session.getAttribute("pId").toString();
		SysManager manager=(SysManager) session.getAttribute("manager");
		SysPower power=result.getData();
		if(power.getPowerId().equals(pId) || 
				power.getManagerId().equals(manager.getManagerId())){
			session.setAttribute("pId", power.getPowerId());
			session.setAttribute("p", power.getPowerModularStr());
//			result.setStateCode(StateCodeConstant.ERROR_CODE_POWER_UPDATE);
//			result.setMessage("您的权限已发生更改，请重新登录");
		}
		return result;
	}
	
	/**
	* @Description: (操作日志列表)
	* @param para
	* @return    
	* @author lgl(lgl1012dream@foxmail.com)
	* @date: 2017年1月19日 下午5:05:49
	 */
	@RequestMapping(value="/selectLogPageList.do",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public JsonResult selectLogPageList(@RequestBody SysLogPara para){
		JsonResult jsonResult = new JsonResult();
		ServiceResult<Page<SysLog>> result=sysLogService.selectSysLogPageList(para);
		jsonResult.setStateCode(result.getStateCode());
		jsonResult.setData(result.getData());
		return jsonResult;
	} 
}
