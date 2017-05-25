package com.yd.dby.a.sys.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yd.dby.utils.oss.YdOssUtil;
import com.yd.dby.utils.response.YdUtilResponse;
import com.yd.dby.utils.servletrequest.YdServletRequest;

@Controller
@RequestMapping(value = "/api/v1.0/oss")
public class YdSysControllerOss {

	@RequestMapping(value = "/test", method = { RequestMethod.GET, RequestMethod.POST })
	public String test() {
		return "/upload";
	}

	@ResponseBody
	@RequestMapping(value = "/generate", method = { RequestMethod.GET, RequestMethod.POST })
	public Object createPolicy() {
		try {
			return YdOssUtil.generateToken();
		} catch (Exception e) {
			return YdUtilResponse.failure(e.getMessage());
		}
	}

	@ResponseBody
	@RequestMapping(value = "/verify", method = { RequestMethod.GET, RequestMethod.POST })
	public Object ossCallback(HttpServletRequest request, HttpServletResponse response) {
		try {
			String authorization = request.getHeader("authorization");
			String x_oss_pub_key_url = request.getHeader("x-oss-pub-key-url");
			String paramsString = YdServletRequest.getParameters(request);
			String requestURI = request.getRequestURI();
			YdOssUtil.verifyCallback(authorization, x_oss_pub_key_url, requestURI, paramsString);
			return YdUtilResponse.success("ok");
		} catch (Exception e) {
			return YdUtilResponse.failure(e.getMessage());
		}
	}

}
