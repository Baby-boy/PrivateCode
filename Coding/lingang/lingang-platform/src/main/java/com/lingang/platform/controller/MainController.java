package com.lingang.platform.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.lingang.api.domain.basic.JsonResult;
import com.lingang.api.domain.basic.ServiceResult;
import com.lingang.api.domain.basic.StateCodeConstant;
import com.lingang.api.domain.entity.SysManager;
import com.lingang.api.service.SysManagerService;
import com.lingang.common.util.StringUtils;

/**
 * @Description: (系统登录)
 * @Author: lgl(lgl1012dream@foxmail.com)
 * @date:2016年12月20日 上午9:44:49
 * @Version:1.0
 */
@Controller
public class MainController {

	@Resource
	private SysManagerService sysManagerService;

	@RequestMapping("/login.htm")
	@ResponseBody
	public JsonResult loginManager(@RequestParam(value = "userAccount", required = true) String userAccount,
			@RequestParam(value = "userPwd", required = true) String userPwd,HttpServletRequest request) {
		JsonResult jsonResult = new JsonResult();
		if(userAccount.equals("") || userPwd.equals("")){
			jsonResult.setStateCode(StateCodeConstant.ERROR_CODE_PARAM_NULL);
			jsonResult.setMessage("账户或密码不能为空");
			return jsonResult;
		}
		//MD5
		userPwd=StringUtils.MD5(userPwd + userAccount);
		//登录
		ServiceResult<SysManager> result=sysManagerService.selectSysManagerLogin(userAccount, userPwd);
		jsonResult.setStateCode(result.getStateCode());
		jsonResult.setMessage(result.getMessage());
		jsonResult.setData(result.getData());
		jsonResult.setDataMap(result.getDataMap());
		//信息缓存
		if(result.getStateCode().equals(StateCodeConstant.SUCCESS_CODE)){
			HttpSession session=request.getSession();
			session.setAttribute("manager", result.getData());
			session.setAttribute("pId", result.getDataMap().get("pId"));
			session.setAttribute("p", result.getDataMap().get("p"));
		}
		return jsonResult;
	}
	
	/**
	* @Description: (异常操作)
	* @return    
	* @author lgl(lgl1012dream@foxmail.com)
	* @date: 2017年1月18日 下午3:47:41
	 */
	@RequestMapping("/errorRequst.do")
	@ResponseBody
	public JsonResult errorRequst(){
		JsonResult jsonResult = new JsonResult();
		jsonResult.setStateCode(StateCodeConstant.ERROR_CODE_LOGIN);
		jsonResult.setMessage("请登录一下再操作哦");
		return jsonResult;
	}

}
