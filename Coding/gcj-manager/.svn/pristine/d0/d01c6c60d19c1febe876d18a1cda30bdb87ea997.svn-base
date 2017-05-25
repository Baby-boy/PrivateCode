package com.yd.gcj.router;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yd.gcj.entity.vo.YdMangerUserVo;
import com.yd.gcj.util.MyStaticFactory;

@Controller
@RequestMapping("/list-login")
public class YdMangerPageRouterListLogin<HttpServletRequest> {
	
	private static final String pageFiles = "list-login/";
	
	@RequestMapping("/login-guzhu")
	public String loginGuzhu() {
		return pageFiles+"login-guzhu";
	}
	
	@RequestMapping("/login")
	public String login() {
		return pageFiles+"login";
	}
	
	@GetMapping("/loginOut")
	public String loginOut(HttpSession session){
		String resultPagePath = "login";
		YdMangerUserVo userVo = (YdMangerUserVo) session.getAttribute("user");
		if(userVo != null && userVo.getUser_type()==0){
			resultPagePath = "login-guzhu";
		}
		session.setAttribute("user",null);
		session.setAttribute("nums", null);
		MyStaticFactory.queryGuzhuTask = false;
		return pageFiles+resultPagePath;
	}
	
	@RequestMapping("/shoujibangding")
	public String shoujibangding() {
		return pageFiles+"shoujibangding";
	}
	
	@RequestMapping("/wangjimima")
	public String wangjimima(Model model) {
		
		return pageFiles+"wangjimima";
	}
	
	@RequestMapping("/zhuce")
	public String zhuce() {
		return pageFiles+"zhuce";
	}
	
}
