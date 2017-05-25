package com.yd.dby.app.controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yd.dby.app.common.HttpCode;
import com.yd.dby.app.common.JsonResult;
import com.yd.dby.app.common.ServiceResult;
import com.yd.dby.app.entity.YdThridpartyLogin;
import com.yd.dby.app.entity.YdUser;
import com.yd.dby.app.service.YdUserService;
import com.yd.dby.app.util.PhoneCheckUtil;
import com.yd.dby.app.util.YdMd5Util;
import com.yd.dby.app.util.sms.YdMontnets;

@RestController
@RequestMapping(value = "/main", produces = { "application/json;charset=UTF-8" })
public class MainController extends BaseController {

	@Autowired
	private YdUserService ydUserService;

	/**
	 * 说明: 用户登录（普通登录）
	 * 
	 * @param account
	 * @param userPassword
	 * @return
	 * @author lgl(lgl@zwzyd.com)
	 * @date: 2017年2月7日 上午11:39:49
	 */
	@RequestMapping(value = "/userLogin", method = RequestMethod.GET)
	public JsonResult userLogin(@RequestParam(value = "account") String account,
			@RequestParam(value = "userPassword") String userPassword, HttpServletRequest request) {
		JsonResult jsonResult = new JsonResult();

		// 参数过滤
		if (StringUtils.isEmpty(account)) {
			jsonResult.setStatusCode(HttpCode.BAD_REQUEST);
			jsonResult.setMsg("账号不能为空");
			return jsonResult;
		}
		if (StringUtils.isEmpty(userPassword)) {
			jsonResult.setStatusCode(HttpCode.BAD_REQUEST);
			jsonResult.setMsg("密码不能为空");
			return jsonResult;
		}

		try {
			// 登录
			ServiceResult<YdUser> result = ydUserService.userLogin(account, YdMd5Util.GetMD5Code(userPassword));

			// 信息缓存
			if (result.getStatusCode().equals(HttpCode.OK)) {
				String token = getToken(result.getData());
				Map<String, Object> dataMap = new HashMap<String, Object>();
				dataMap.put("token", token);
				jsonResult.setDataMap(dataMap);
			}

			// 返回数据
			jsonResult.setStatusCode(result.getStatusCode());
			jsonResult.setMsg(result.getMsg());
			jsonResult.setData(result.getData());
		} catch (Exception e) {
			// 未知异常
			jsonResult.setStatusCode(HttpCode.INTERNAL_SERVER_ERROR);
			jsonResult.setMsg("系统繁忙");
			return jsonResult;
		}

		return jsonResult;
	}

	/**
	 * 说明: 第三方登录
	 * 
	 * @param tplOpenid(第三方登录成功后的识别ID)
	 * @param tplType(1:微信,2:qq,3:其他)
	 * @return
	 * @author lgl(lgl@zwzyd.com)
	 * @date: 2017年2月28日 上午10:17:30
	 */
	@RequestMapping(value = "/userLoginThird", method = RequestMethod.GET)
	public JsonResult userLoginThird(@RequestParam(value = "tplOpenid", required = true) String tplOpenid,
			@RequestParam(value = "tplType", required = true) Integer tplType) {
		JsonResult jsonResult = new JsonResult();

		try {
			// 登录
			ServiceResult<YdUser> result = ydUserService.userLoginThird(tplOpenid, tplType);

			// 信息缓存
			if (result.getStatusCode().equals(HttpCode.OK)) {
				String token = getToken(result.getData());
				Map<String, Object> dataMap = new HashMap<String, Object>();
				dataMap.put("token", token);
				jsonResult.setDataMap(dataMap);
			}

			// 返回数据
			jsonResult.setStatusCode(result.getStatusCode());
			jsonResult.setMsg(result.getMsg());
			jsonResult.setData(result.getData());
		} catch (Exception e) {	
			// 未知异常
			jsonResult.setStatusCode(HttpCode.INTERNAL_SERVER_ERROR);
			jsonResult.setMsg("系统繁忙");
			return jsonResult;
		}

		return jsonResult;
	}

	/**
	 * 说明: 第三方账号绑定
	 * 
	 * @param tplOpenid
	 * @param tplType
	 * @return
	 * @author lgl(lgl@zwzyd.com)
	 * @date: 2017年2月28日 上午10:42:56
	 */
	@RequestMapping(value = "/userThirdBinding", method = RequestMethod.GET)
	public JsonResult addUserThirdBinding(@RequestParam(value = "tplOpenid", required = true) String tplOpenid,
			@RequestParam(value = "tplType", required = true) Integer tplType,
			@RequestParam(value = "mobile", required = true) String mobile,
			@RequestParam(value = "verifyCode", required = true) String verifyCode,
			@RequestParam(value = "verifyStr", required = true) String verifyStr, HttpServletRequest request) {
		JsonResult jsonResult = new JsonResult();

		try {
			// 验证码(短信商爆炸，暂时不用)
			// if (redisGet(mobile + verifyStr, String.class) == null) {
			// jsonResult.setStatusCode(HttpCode.BAD_REQUEST);
			// jsonResult.setMsg("验证码不正确");
			// return jsonResult;
			// }
			// String codeStr = redisGet(mobile + verifyStr,
			// String.class).toString();
			// if (!codeStr.equals(verifyCode)) {
			// jsonResult.setStatusCode(HttpCode.BAD_REQUEST);
			// jsonResult.setMsg("验证码不正确");
			// return jsonResult;
			// }
			// 查询用户
			YdUser user = ydUserService.selectByMobile(mobile);
			if (user == null) {
				jsonResult.setStatusCode(HttpCode.NO_CONTENT);
				jsonResult.setMsg("用户不存在");
				return jsonResult;
			}

			// 绑定
			YdThridpartyLogin thridpartyLogin = new YdThridpartyLogin();
			thridpartyLogin.setTplUserId(user.getUserId());
			thridpartyLogin.setTplType(tplType);
			thridpartyLogin.setTplOpenid(tplOpenid);
			thridpartyLogin.setTplCreatedTime(new Date());
			ServiceResult<Object> result = ydUserService.insertYdThridpartyLogin(thridpartyLogin);

			// 返回数据
			jsonResult.setStatusCode(result.getStatusCode());
			jsonResult.setMsg(result.getMsg());
			jsonResult.setData(result.getData());
		} catch (Exception e) {
			// 未知异常
			jsonResult.setStatusCode(HttpCode.INTERNAL_SERVER_ERROR);
			jsonResult.setMsg("系统繁忙");
			return jsonResult;
		}

		return jsonResult;
	}

	/**
	 * 说明: token 身份异常
	 * 
	 * @return
	 * @author lgl(lgl@zwzyd.com)
	 * @date: 2017年2月13日 下午9:25:04
	 */
	@RequestMapping(value = "/tokenError")
	public JsonResult tokenError() {
		JsonResult jsonResult = new JsonResult();
		jsonResult.setStatusCode(HttpCode.NON_AUTHORITATIVE_INFORMATION);
		jsonResult.setMsg("token身份异常");
		return jsonResult;
	}

	/**
	 * 说明: 用户注册
	 * 
	 * @param mobile
	 * @param userPassword
	 * @param verifyCode
	 * @param request
	 * @return
	 * @author lgl(lgl@zwzyd.com)
	 * @date: 2017年2月9日 下午1:42:48
	 */
	@RequestMapping(value = "/userRegister", method = RequestMethod.GET)
	public JsonResult userRegister(@RequestParam(value = "mobile", required = true) String mobile,
			@RequestParam(value = "userPassword", required = true) String userPassword,
			@RequestParam(value = "verifyCode", required = true) String verifyCode,
			@RequestParam(value = "verifyStr", required = true) String verifyStr, HttpServletRequest request)
			throws Exception {
		JsonResult jsonResult = new JsonResult();

		// 参数过滤
		if (StringUtils.isEmpty(mobile)) {
			jsonResult.setStatusCode(HttpCode.BAD_REQUEST);
			jsonResult.setMsg("手机号不能为空");
			return jsonResult;
		}
		if (StringUtils.isEmpty(userPassword)) {
			jsonResult.setStatusCode(HttpCode.BAD_REQUEST);
			jsonResult.setMsg("密码不能为空");
			return jsonResult;
		}
		if (userPassword.length() < 6 || userPassword.length() > 20) {
			jsonResult.setStatusCode(HttpCode.BAD_REQUEST);
			jsonResult.setMsg("密码长度6~20位");
			return jsonResult;
		}
		if (!PhoneCheckUtil.isChinaPhoneLegal(mobile)) {
			jsonResult.setStatusCode(HttpCode.BAD_REQUEST);
			jsonResult.setMsg("非法手机号");
			return jsonResult;
		}
		if (StringUtils.isEmpty(verifyStr)) {
			jsonResult.setStatusCode(HttpCode.BAD_REQUEST);
			jsonResult.setMsg("参数信息错误");
			return jsonResult;
		}

		if (redisGet(mobile + verifyStr, String.class) == null) {
			jsonResult.setStatusCode(HttpCode.BAD_REQUEST);
			jsonResult.setMsg("验证码不正确");
			return jsonResult;
		}
		String codeStr = redisGet(mobile + verifyStr, String.class).toString();
		if (!codeStr.equals(verifyCode)) {
			jsonResult.setStatusCode(HttpCode.BAD_REQUEST);
			jsonResult.setMsg("验证码不正确");
			return jsonResult;
		}

		// 默认数据
		Date nowDate = new Date();
		YdUser user = new YdUser();
		user.setUserMobile(mobile);
		user.setUserPassword(YdMd5Util.GetMD5Code(userPassword));
		user.setUserSex(0);
		user.setUserNickname(randomStr());
		user.setUserAvatar("baBaeewTZB5dX1tUQiCejg==.png");
		user.setUserBirthday(nowDate);
		user.setUserGrade(0);
		user.setUserMoney(new BigDecimal(0));
		user.setUserRole("buyer");
		user.setUserAccountBalance(new BigDecimal(0));
		user.setUserIntegration(0);
		user.setUserTotalCoupon(0);
		user.setUserTotalBankcard(0);
		user.setUserLoginTime(nowDate);
		user.setUserOldLoginTime(nowDate);
		user.setUserState(false);
		user.setUserCreatedTime(nowDate);

		// 注册
		ServiceResult<YdUser> result = ydUserService.userRegister(user);

		// 清空验证码缓存
		if (result.getStatusCode().equals(HttpCode.OK)) {
			redisDel(verifyStr);
		}

		// 返回数据
		jsonResult.setStatusCode(result.getStatusCode());
		jsonResult.setMsg(result.getMsg());

		return jsonResult;
	}

	/**
	 * 说明: 忘记密码
	 * 
	 * @param mobile(手机号)
	 * @param userPassword(新密码)
	 * @param verifyCode(验证码)
	 * @param request
	 * @return
	 * @author lgl(lgl@zwzyd.com)
	 * @date: 2017年2月9日 下午2:40:04
	 */
	@RequestMapping(value = "/forgetPwd", method = RequestMethod.GET)
	public JsonResult forgetPwd(@RequestParam(value = "mobile", required = true) String mobile,
			@RequestParam(value = "newPassword", required = true) String newPassword,
			@RequestParam(value = "verifyCode", required = true) String verifyCode,
			@RequestParam(value = "verifyStr", required = true) String verifyStr, HttpServletRequest request)
			throws Exception {
		JsonResult jsonResult = new JsonResult();

		// 参数过滤
		if (StringUtils.isEmpty(mobile)) {
			jsonResult.setStatusCode(HttpCode.BAD_REQUEST);
			jsonResult.setMsg("手机号不能为空");
			return jsonResult;
		}
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
		if (!PhoneCheckUtil.isChinaPhoneLegal(mobile)) {
			jsonResult.setStatusCode(HttpCode.BAD_REQUEST);
			jsonResult.setMsg("非法手机号");
			return jsonResult;
		}
		if (StringUtils.isEmpty(verifyStr)) {
			jsonResult.setStatusCode(HttpCode.BAD_REQUEST);
			jsonResult.setMsg("参数信息错误");
			return jsonResult;
		}

		if (redisGet(mobile + verifyStr, String.class) == null) {
			jsonResult.setStatusCode(HttpCode.BAD_REQUEST);
			jsonResult.setMsg("验证码不正确");
			return jsonResult;
		}
		String codeStr = redisGet(mobile + verifyStr, String.class).toString();
		if (!codeStr.equals(verifyCode)) {
			jsonResult.setStatusCode(HttpCode.BAD_REQUEST);
			jsonResult.setMsg("验证码不正确");
			return jsonResult;
		}

		YdUser user = new YdUser();
		user.setUserMobile(mobile);
		user.setUserPassword(YdMd5Util.GetMD5Code(newPassword));

		// 修改密码
		ServiceResult<YdUser> result = ydUserService.updateUserPwdForget(user);

		// 信息缓存
		if (result.getStatusCode().equals(HttpCode.OK)) {
			String token = getToken(jsonResult.getData());
			Map<String, Object> dataMap = new HashMap<String, Object>();
			dataMap.put("token", token);
			jsonResult.setDataMap(dataMap);
		}

		// 清空验证码缓存
		if (result.getStatusCode().equals(HttpCode.OK)) {
			redisDel(verifyStr);
		}

		// 返回数据
		jsonResult.setStatusCode(result.getStatusCode());
		jsonResult.setMsg(result.getMsg());

		return jsonResult;
	}

	/**
	 * 说明: 获取注册验证码
	 * 
	 * @param mobile
	 * @return
	 * @author lgl(lgl@zwzyd.com)
	 * @date: 2017年2月9日 上午11:21:07
	 */
	@RequestMapping(value = "/getRegCode", method = RequestMethod.GET)
	public JsonResult getRegCode(@RequestParam(value = "mobile", required = true) String mobile,
			HttpServletRequest request) throws Exception {
		JsonResult jsonResult = new JsonResult();

		try {
			// 产生随机码
			String codeStr = randomCode();

			// 发送短信
			String sendStr = YdMontnets.SendMultixSms(mobile, "您正在注册账号,验证码是:" + codeStr);

			// 返回发送结果
			jsonResult.setMsg(sendStr);

			// 缓存验证码
			if (sendStr.equals("OK")) {
				String verifyStr = setVerifyCode(mobile, codeStr);
				jsonResult.setStatusCode(HttpCode.OK);
				jsonResult.setData(verifyStr);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return jsonResult;
	}

	/**
	 * 说明: 获取修改密码 验证码
	 * 
	 * @param mobile
	 * @return
	 * @author lgl(lgl@zwzyd.com)
	 * @date: 2017年2月9日 上午11:21:07
	 */
	@RequestMapping(value = "/getPwdCode", method = RequestMethod.GET)
	public JsonResult getPwdCode(@RequestParam(value = "mobile", required = true) String mobile,
			HttpServletRequest request) throws Exception {
		JsonResult jsonResult = new JsonResult();

		try {
			// 产生随机码
			String codeStr = randomCode();

			// 发送短信
			String sendStr = YdMontnets.SendMultixSms(mobile, "您正在修改密码, 验证码是:" + codeStr);

			// 返回发送结果
			jsonResult.setMsg(sendStr);

			// 缓存验证码
			if (sendStr.equals("OK")) {
				String verifyStr = setVerifyCode(mobile, codeStr);
				jsonResult.setStatusCode(HttpCode.OK);
				jsonResult.setData(verifyStr);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return jsonResult;
	}

	/**
	 * 说明: 获取第三方绑定 验证码
	 * 
	 * @param mobile
	 * @return
	 * @author lgl(lgl@zwzyd.com)
	 * @date: 2017年2月9日 上午11:21:07
	 */
	@RequestMapping(value = "/getThirdCode", method = RequestMethod.GET)
	public JsonResult getThirdCode(@RequestParam(value = "mobile", required = true) String mobile,
			HttpServletRequest request) throws Exception {
		JsonResult jsonResult = new JsonResult();

		try {
			// 产生随机码
			String codeStr = randomCode();

			// 发送短信
			String sendStr = YdMontnets.SendMultixSms(mobile, "您正在绑定多宝鱼账号, 验证码是:" + codeStr);

			// 返回发送结果
			jsonResult.setMsg(sendStr);

			// 缓存验证码
			if (sendStr.equals("OK")) {
				String verifyStr = setVerifyCode(mobile, codeStr);
				jsonResult.setStatusCode(HttpCode.OK);
				jsonResult.setData(verifyStr);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return jsonResult;
	}

	/**
	 * 说明: 验证token是否有效(没什么暖意义..)
	 * 
	 * @return
	 * @author lgl(lgl@zwzyd.com)
	 * @date: 2017年2月24日 下午2:56:28
	 */
	@RequestMapping(value = "tokenVerify", method = RequestMethod.POST)
	public JsonResult tokenVerify() {
		JsonResult jsonResult = new JsonResult();

		jsonResult.setStatusCode(HttpCode.OK);

		return jsonResult;
	}

}
