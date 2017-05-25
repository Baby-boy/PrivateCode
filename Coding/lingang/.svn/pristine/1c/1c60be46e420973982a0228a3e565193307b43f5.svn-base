package com.lingang.consumer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lingang.api.domain.basic.JsonResult;
import com.lingang.api.domain.basic.StateCodeConstant;

/**
 *@Description: (主要业务控制)
 *@Author: lgl(lgl1012dream@foxmail.com)
 *@date:2016年12月2日 下午2:21:52
 *@Version:1.0
 */
@Controller
@RequestMapping("main")
public class MainController {

	/**
	* @Description: (token异常)
	* @return    
	* @author lgl(lgl1012dream@foxmail.com)
	* @date: 2016年12月2日 下午2:22:28
	 */
	@RequestMapping("tokenError.do")
	@ResponseBody
	public JsonResult tokenError(){
		JsonResult jsonResult=new JsonResult();
		jsonResult.setStateCode(StateCodeConstant.ERROR_CODE_TOKEN);
		jsonResult.setMessage("token身份异常");
		return jsonResult;
	}
}
