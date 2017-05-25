package com.yd.dby.app.controller.user;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yd.dby.app.basic.Page;
import com.yd.dby.app.common.HttpCode;
import com.yd.dby.app.common.JsonResult;
import com.yd.dby.app.common.ServiceResult;
import com.yd.dby.app.controller.BaseController;
import com.yd.dby.app.entity.YdFeedback;
import com.yd.dby.app.entity.YdPointsLog;
import com.yd.dby.app.entity.YdUser;
import com.yd.dby.app.entity.YdWish;
import com.yd.dby.app.entity.para.BasePara;
import com.yd.dby.app.entity.para.UserPara;
import com.yd.dby.app.entity.vo.UserCenterVo;
import com.yd.dby.app.service.YdUserService;
import com.yd.dby.app.util.YdMd5Util;

@RestController
@RequestMapping(value = "/user", produces = { "application/json;charset=UTF-8" })
public class UserController extends BaseController {

	@Autowired
	private YdUserService ydUserService;

	/**
	 * 说明:用户查询(分页)
	 * 
	 * @param para
	 * @return
	 * @author lgl(lgl@zwzyd.com)
	 * @date: 2017年2月7日 上午11:15:50
	 */
	@RequestMapping(value = "/queryUserPageList", method = RequestMethod.POST)
	public JsonResult queryUserPageList(@RequestBody UserPara para) {
		JsonResult jsonResult = new JsonResult();

		ServiceResult<Page<YdUser>> result = ydUserService.selectUserPageList(para);
		jsonResult.setStatusCode(result.getStatusCode());
		jsonResult.setMsg(result.getMsg());
		jsonResult.setData(result.getData());

		return jsonResult;
	}

	/**
	 * 说明: 修改用户信息
	 * 
	 * @param para
	 * @return
	 * @author lgl(lgl@zwzyd.com)
	 * @date: 2017年2月12日 下午3:12:41
	 */
	@RequestMapping(value = "updateUser", method = RequestMethod.POST)
	public JsonResult updateUser(@RequestBody YdUser para, HttpServletRequest request) throws Exception {
		JsonResult jsonResult = new JsonResult();

		// 获取当前用户
		YdUser user = (YdUser) redisGet(request.getHeader("token") + "User", YdUser.class);

		para.setUserId(user.getUserId());

		ServiceResult<YdUser> result = ydUserService.updateUser(para);
		jsonResult.setStatusCode(result.getStatusCode());
		jsonResult.setMsg(result.getMsg());

		return jsonResult;
	}

	/**
	 * 说明: 用户中心
	 * 
	 * @return
	 * @author fqh(fqh@zwzyd.com)
	 * @throws Exception
	 * @date: 2017年2月13日 下午2:24:18
	 */
	@RequestMapping(value = "userCenter", method = RequestMethod.POST)
	public JsonResult queryUserCenter(HttpServletRequest request) throws Exception {
		JsonResult jsonResult = new JsonResult();

		// 获取当前用户
		YdUser user = (YdUser) redisGet(request.getHeader("token") + "User", YdUser.class);

		ServiceResult<UserCenterVo> result = ydUserService.selectUserCenter(user);
		jsonResult.setMsg(result.getMsg());
		jsonResult.setData(result.getData());
		jsonResult.setStatusCode(result.getStatusCode());
		return jsonResult;
	}

	/**
	 * 说明: 添加用户许愿信息
	 * 
	 * @param ydWish
	 * @return
	 * @throws Exception
	 * @author fqh(fqh@zwzyd.com)
	 * @date: 2017年2月13日 下午8:56:43
	 */
	@RequestMapping(value = "addWish", method = RequestMethod.POST)
	public JsonResult addUserWish(@RequestBody YdWish ydWish, HttpServletRequest request) throws Exception {
		JsonResult jsonResult = new JsonResult();

		if (StringUtils.isEmpty(ydWish.getWishContent())) {
			jsonResult.setStatusCode(HttpCode.BAD_REQUEST);
			jsonResult.setMsg("请求参数异常!");
			return jsonResult;
		}

		// 获取当前用户
		YdUser user = (YdUser) redisGet(request.getHeader("token") + "User", YdUser.class);

		ydWish.setWishId(null);// 防止恶意传参
		ydWish.setUserId(user.getUserId());
		ydWish.setWishCreatedTime(new Date());

		ServiceResult<Object> result = ydUserService.insertUserWish(ydWish);
		jsonResult.setMsg(result.getMsg());
		jsonResult.setStatusCode(result.getStatusCode());
		return jsonResult;
	}

	/**
	 * 说明: 许愿池列表
	 * 
	 * @param para(请求参数)
	 * @return
	 * @author fqh(fqh@zwzyd.com)
	 * @throws Exception
	 * @date: 2017年2月13日 下午8:57:38
	 */
	@RequestMapping(value = "wishPageList", method = RequestMethod.POST)
	public JsonResult queryUserWish(@RequestBody BasePara para, HttpServletRequest request) throws Exception {
		JsonResult jsonResult = new JsonResult();

		// 获取当前用户
		YdUser user = (YdUser) redisGet(request.getHeader("token") + "User", YdUser.class);

		ServiceResult<Page<YdWish>> result = ydUserService.selectUserWishPageList(para, user);
		jsonResult.setMsg(result.getMsg());
		jsonResult.setData(result.getData());
		jsonResult.setStatusCode(result.getStatusCode());
		return jsonResult;
	}

	/**
	 * 说明: 积分中心
	 * 
	 * @param para(请求参数)
	 * @return
	 * @author fqh(fqh@zwzyd.com)
	 * @throws Exception
	 * @date: 2017年2月14日 上午10:09:53
	 */
	@RequestMapping(value = "pointsCenter", method = RequestMethod.POST)
	public JsonResult queryUserPointsCenter(@RequestBody BasePara para, HttpServletRequest request) throws Exception {
		JsonResult jsonResult = new JsonResult();

		// 获取当前用户
		YdUser user = (YdUser) redisGet(request.getHeader("token") + "User", YdUser.class);

		ServiceResult<Page<YdPointsLog>> result = ydUserService.selectUserPointsCenter(para, user);
		jsonResult.setMsg(result.getMsg());
		jsonResult.setData(result.getData());
		jsonResult.setStatusCode(result.getStatusCode());
		return jsonResult;
	}

	/**
	 * 说明: 修改密码
	 * 
	 * @param oldPassword
	 * @param newPassword
	 * @param request
	 * @return
	 * @author lgl(lgl@zwzyd.com)
	 * @date: 2017年2月9日 下午2:59:47
	 */
	@RequestMapping(value = "/updatePwd", method = RequestMethod.GET)
	public JsonResult updatePwd(@RequestParam(value = "oldPassword", required = true) String oldPassword, @RequestParam(value = "newPassword", required = true) String newPassword, HttpServletRequest request) throws Exception {
		JsonResult jsonResult = new JsonResult();

		// 参数过滤
		if (StringUtils.isEmpty(newPassword)) {
			jsonResult.setStatusCode(HttpCode.BAD_REQUEST);
			jsonResult.setMsg("密码不能为空");
			return jsonResult;
		}
		if (newPassword.length() < 6 || newPassword.length() > 20) {
			jsonResult.setStatusCode(HttpCode.BAD_REQUEST);
			jsonResult.setMsg("密码长度6~20位");
			return jsonResult;
		}

		// 获取用户信息
		YdUser user = (YdUser) redisGet(request.getHeader("token") + "User", YdUser.class);

		// 修改密码
		YdUser user2 = new YdUser();
		user2.setUserId(user.getUserId());
		user2.setUserPassword(YdMd5Util.GetMD5Code(newPassword));
		ServiceResult<YdUser> result = ydUserService.updateUserPwd(user2, YdMd5Util.GetMD5Code(oldPassword));

		// 信息缓存
		if (result.getStatusCode().equals(HttpCode.OK)) {
			String tokenNew = getToken(jsonResult.getData());
			Map<String, Object> dataMap = new HashMap<String, Object>();
			dataMap.put("token", tokenNew);
			jsonResult.setDataMap(dataMap);
		}

		// 返回数据
		jsonResult.setStatusCode(result.getStatusCode());
		jsonResult.setMsg(result.getMsg());

		return jsonResult;
	}

	/**
	 * 说明: 添加用户意见反馈
	 * 
	 * @param ydFeedback(请求参数)
	 * @param request
	 * @return
	 * @throws Exception
	 * @author fqh(fqh@zwzyd.com)
	 * @date: 2017年2月15日 上午10:19:20
	 */
	@RequestMapping(value = "addFeedback", method = RequestMethod.POST)
	public JsonResult addUserFeedback(@RequestBody YdFeedback ydFeedback, HttpServletRequest request) throws Exception {
		JsonResult jsonResult = new JsonResult();

		if (StringUtils.isEmpty(ydFeedback.getFeedbackContent())) {
			jsonResult.setStatusCode(HttpCode.BAD_REQUEST);
			jsonResult.setMsg("请求参数异常!");
			return jsonResult;
		}

		// 获取用户信息
		YdUser user = (YdUser) redisGet(request.getHeader("token") + "User", YdUser.class);

		ydFeedback.setFeedbackId(null);
		ydFeedback.setUserId(user.getUserId());
		ydFeedback.setFeedbackCreatedTime(new Date());

		ServiceResult<Object> result = ydUserService.insertUserFeedback(ydFeedback);
		jsonResult.setMsg(result.getMsg());
		jsonResult.setStatusCode(result.getStatusCode());
		return jsonResult;
	}
}
