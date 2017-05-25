package com.yd.dby.a.sys.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.yd.dby.YdMain;
import com.yd.dby.a.sys.entity.YdSysUserJwt;

@Controller
@RequestMapping(value = "")
public class YdSysControllerSecurity {

	@Autowired
	private HttpSession session;

	/**
	 * 用户中心
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/member", method = RequestMethod.GET)
	public String get_member_login(HttpServletRequest request) {
		// YdSysUserJwt user = (YdSysUserJwt) request.getAttribute("user");
		// return user == null ? "member/security/login" :
		// "member/security/login_success";
		return "member/security/my";
	}

	/**
	 * 登录页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(HttpServletRequest request) {
		return "member/security/login";
	}

	/**
	 * 登录提交
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView POST_member_login(@RequestBody Map<String, HashMap<String, Object>> request, HttpServletResponse response) {
		return YdMain.MAV("_security", "login", request);
	}

	/**
	 * 退出登录
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/member/logout", method = RequestMethod.POST)
	public ModelAndView logout() {
		session.removeAttribute("user");
		session.removeAttribute("user_id");
		session.removeAttribute("store");
		session.removeAttribute("store_id");
		ModelAndView mav = new ModelAndView("member/security/login");
		return mav;
	}

	@RequestMapping(value = "member/autologin", method = RequestMethod.POST)
	public ModelAndView autologin(HttpServletRequest request, HttpServletResponse response) {
		YdSysUserJwt user = (YdSysUserJwt) request.getAttribute("user");
		if (user == null) {
			response.setStatus(401);
			return new ModelAndView(new MappingJackson2JsonView());
		}

		ModelAndView mav = new ModelAndView("member/security/login_success");
		mav.addObject("uid", 1);
		return mav;
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String POST_member_register(HttpServletRequest request) {
		YdSysUserJwt user = (YdSysUserJwt) request.getAttribute("user");
		return user == null ? "member/security/register" : "/member";
	}

	@RequestMapping(value = "/register_member_sms", method = RequestMethod.POST)
	public ModelAndView register_member_sms(@RequestBody Map<String, HashMap<String, Object>> request) {
		return YdMain.MAV("_security", "register_member_sms", request);
	}
	
	@RequestMapping(value = "/forgetPasswordSms", method = RequestMethod.POST)
	public ModelAndView forgetPasswordSms(@RequestBody Map<String, HashMap<String, Object>> request) {
		return YdMain.MAV("_security", "forgetPasswordSms", request);
	}

	@RequestMapping(value = "/register_member", method = RequestMethod.POST)
	public ModelAndView register_member_submit(@RequestBody Map<String, HashMap<String, Object>> request) {
		return YdMain.MAV("_security", "register_member", request);
	}
	
	@RequestMapping(value = "/forgetPassword", method = RequestMethod.POST)
	public ModelAndView forgetPassword(@RequestBody Map<String, HashMap<String, Object>> request) {
		return YdMain.MAV("_security", "forgetPassword", request);
	}

	@RequestMapping(value = "/register_seller", method = RequestMethod.POST)
	public ModelAndView register_seller_submit(@RequestBody Map<String, HashMap<String, Object>> request) {
		return YdMain.MAV("_security", "register_seller", request);
	}

	/**
	 * 商家首页
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/seller", method = RequestMethod.POST)
	public String seller(HttpServletRequest request) {
		YdSysUserJwt user = (YdSysUserJwt) request.getAttribute("user");
		return user == null ? "seller/temp/index" : "seller/temp/index";
	}

	/**
	 * 商家登录
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/SellerLogin", method = RequestMethod.POST)
	public ModelAndView SellerLogin(HttpServletRequest request, HttpServletResponse response) {
		YdSysUserJwt user = (YdSysUserJwt) request.getAttribute("user");
		if (user == null) {
			response.setStatus(401);
			return new ModelAndView(new MappingJackson2JsonView());
		}

		ModelAndView mav = new ModelAndView("seller/index");
		mav.addObject("uid", 1);
		return mav;
	}

	//
	// /**
	// * 商家验证登录
	// * @return
	// */
	// @RequestMapping(value = "/SellerTokenLogin", method = RequestMethod.POST)
	// public ModelAndView SellerTokenLogin() {
	// System.out.println(ydRequest.getHeader("Authorization"));
	//
	// // String ok =
	// //
	// ydWebServiceSystemTokenVerification.text(ydRequest.getHeader("Authorization"));
	// String ok;
	// try {
	// ok =
	// ydWebServiceSystemTokenVerification.text(YdUtilToken.generate("1.seller.1"));
	// if (ok == null) {
	// return new ModelAndView(new MappingJackson2JsonView());
	// }
	// } catch (Exception e) {
	// // TODO Auto-generated catch block
	//
	// }
	// ModelAndView mav = new ModelAndView("seller/temp/index::main");
	// mav.addObject("uid", ydRequest.getAttribute("uid"));
	// return mav;
	// // return new ModelAndView("seller/index");
	// }

	/**
	 * 跳转忘记密码界面
	 * 
	 * @return 方法作用(忘记密码页面)
	 */
	@RequestMapping(value = "forgetPwd", method = RequestMethod.GET)
	public Object forgetPwd() {
		return "member/security/forgetPwd";
	}

}
