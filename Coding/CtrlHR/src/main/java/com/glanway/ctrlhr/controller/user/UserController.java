package com.glanway.ctrlhr.controller.user;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.glanway.ctrlhr.common.HttpCode;
import com.glanway.ctrlhr.common.JsonResult;
import com.glanway.ctrlhr.entity.user.User;
import com.glanway.ctrlhr.service.user.UserService;
import com.glanway.ctrlhr.util.Base64;
import com.glanway.ctrlhr.util.IPUtil;

@Controller
@RequestMapping("/api/user")
public class UserController {
	@Autowired
	private UserService userService;

	/**
	 * 用户登录.
	 *
	 * @author fuqihao
	 * @param name
	 * @param pwd
	 * @param request
	 * @return
	 * @since 1.0-20170425
	 */
	@ResponseBody
	@RequestMapping(value = "login")
	public JsonResult userLogin(String name, String pwd, HttpServletRequest request) {
		JsonResult jsonResult = new JsonResult();

		if (StringUtils.isEmpty(name)) {
			jsonResult.setCode(HttpCode.BAD_REQUEST);
			jsonResult.setMsg("用户名不能为空!");
			return jsonResult;
		}
		if (StringUtils.isEmpty(pwd)) {
			jsonResult.setCode(HttpCode.BAD_REQUEST);
			jsonResult.setMsg("密码不能为空!");
			return jsonResult;
		}

		try {
			String ip = IPUtil.getIp(request);

			User user = userService.doLogin(name, pwd, ip);
			if (null == user) {
				jsonResult.setCode(HttpCode.NO_CONTENT);
				jsonResult.setMsg("用户名或者密码错误!");
				return jsonResult;
			}

			jsonResult.setData(user.getRealName());
			String token = Base64.encodeToString((user.getName() + user.getPwd()).getBytes());
			// 用户登录成功,保存token
			request.getSession().setAttribute("token", token);
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setCode(HttpCode.INTERNAL_SERVER_ERROR);
			jsonResult.setMsg("操作失败!");
		}

		return jsonResult;
	}

	/**
	 * 用户登出功能.
	 *
	 * @author fuqihao
	 * @param request
	 * @since 1.0-20170425
	 */
	@ResponseBody
	@RequestMapping(value = "loginOut")
	public JsonResult userLoginOut(HttpServletRequest request) {
		JsonResult jsonResult = new JsonResult();

		try {
			request.getSession().setAttribute("token", "");
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setCode(HttpCode.INTERNAL_SERVER_ERROR);
			jsonResult.setMsg("操作失败!");
		}

		return jsonResult;
	}
}
