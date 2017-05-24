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

@Controller
@RequestMapping("/api/user")
public class UserController {
	@Autowired
	private UserService userService;

	/**
	 * 说明 :
	 * 
	 * @param name
	 * @param pwd
	 * @param request
	 * @param token
	 * @return
	 * @author 付其浩
	 * @dateTime 2017年4月25日 下午2:18:05
	 */
	@RequestMapping(value = "login")
	@ResponseBody
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

		User user;
		try {
			user = userService.doLogin(name, pwd);
			if (null == user) {
				jsonResult.setCode(HttpCode.NO_CONTENT);
				jsonResult.setMsg("用户名或者密码错误!");
				return jsonResult;
			}

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

}
