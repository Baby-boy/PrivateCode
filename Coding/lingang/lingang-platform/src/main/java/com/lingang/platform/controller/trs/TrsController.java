package com.lingang.platform.controller.trs;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lingang.api.domain.basic.JsonResult;
import com.lingang.api.domain.basic.StateCodeConstant;
import com.lingang.api.domain.entity.SysManager;
import com.lingang.api.service.SysLogService;
import com.lingang.common.util.IpAddressUtil;

@Controller
@RequestMapping("/trs")
public class TrsController {
	
	@Resource
	private SysLogService sysLogService;

	@RequestMapping("/trsPost.do")
	@ResponseBody
	public JsonResult trsPost(HttpServletRequest request){
		JsonResult jsonResult = new JsonResult();
		Map<String, Object> map=new HashMap<String,Object>();
		map.put("url", "http://10.40.201.201:8124/wcm/app/login_dowith.jsp");
		map.put("userName", "admin");
		map.put("passWord", "trsadmin");
		jsonResult.setDataMap(map);
		jsonResult.setStateCode(StateCodeConstant.SUCCESS_CODE);
		
		// 记录日志
		HttpSession session = request.getSession();
		SysManager manager = (SysManager) session.getAttribute("manager");
		sysLogService.insertSysLog(manager.getManagerAccount(), IpAddressUtil.getIpAddress(request), new Date(),
				"登录站点发布系统");
		return jsonResult;
	}
}
