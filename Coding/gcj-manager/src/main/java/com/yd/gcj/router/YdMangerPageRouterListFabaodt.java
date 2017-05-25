package com.yd.gcj.router;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yd.gcj.entity.vo.YdMangerUserDetail;
import com.yd.gcj.entity.vo.YdMangerUserVo;
import com.yd.gcj.service.YdMangerServiceUser;
import com.yd.gcj.tool.ObjectMapperFactory;

@Controller
@RequestMapping("/list-fabaodt")
public class YdMangerPageRouterListFabaodt {

	private static final String pageFiles = "list-fabaodt/";

	@Autowired
	private YdMangerServiceUser serviceUser;

	@RequestMapping("/fabaodating")
	public String fabaodating(HttpServletRequest request) {
		YdMangerUserVo userVo = (YdMangerUserVo) request.getSession().getAttribute("user");
		if (userVo != null && userVo.getUser_type() == 0) {
			Integer isVerified = serviceUser.$queryVerifiedByUserId(userVo.getUser_id());
			if (isVerified != 1) {
				return "public/noverified";
			}
			return pageFiles + "fabaodating";
		}
		return "list-login/login-guzhu";
	}

	@RequestMapping("/rencaidating/{userSId}")
	public String rencaidating(@PathVariable Integer userSId, Model model, HttpServletRequest request) {
		YdMangerUserVo userVo = (YdMangerUserVo) request.getSession().getAttribute("user");
		YdMangerUserDetail userDetail = new YdMangerUserDetail();
		if (userVo != null && userVo.getUser_type() == 0) {
			userDetail = serviceUser.$queryDetailById(userSId, userVo.getUser_id());
		} else {
			userDetail = serviceUser.$queryDetailById(userSId, 0);
		}
		ObjectMapperFactory.doIt(userDetail);
		model.addAttribute("userDetail", userDetail);
		return pageFiles + "rencaidating";
	}

}
