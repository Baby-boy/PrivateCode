package com.lingang.platform.controller.system;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lingang.api.domain.basic.JsonResult;
import com.lingang.api.domain.basic.Page;
import com.lingang.api.domain.basic.ServiceResult;
import com.lingang.api.domain.entity.SysUser;
import com.lingang.api.domain.para.SysUserPara;
import com.lingang.api.service.SysUserService;


@Controller
@RequestMapping("/user")
public class SysUserController {

	@Resource
	private SysUserService sysUserService;

	/**
	 * @Description: (APP用户列表)
	 * @param para
	 * @return 参数注释 
	 * @author fqh (qihao.fu@outlook.com)
	 * @date: 2016年12月15日 上午10:58:32 
	 */
	@RequestMapping(value = "/sysUserPagePageList.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public JsonResult sysUserPagePageList(@RequestBody SysUserPara para) {
		JsonResult jsonResult = new JsonResult();
		ServiceResult<Page<SysUser>> result = sysUserService.selectSysUserPageList(para);
		jsonResult.setStateCode(result.getStateCode());
		jsonResult.setData(result.getData());
		return jsonResult;
	}
}
