package com.yd.dby.c.member.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.yd.dby.c.member.entity.User;
import com.yd.dby.c.member.mapper.YdMemberMapperCart;
import com.yd.dby.c.member.mapper.YdMemberMapperInfo;
import com.yd.dby.c.member.mapper.YdMemberMapperMoneyRecord;
import com.yd.dby.c.member.mapper.YdMemberMapperUser;
import com.yd.dby.c.member.service.YdMemberServiceInfo;
import com.yd.dby.utils.YdUtilTokenOnlyExpire;
import com.yd.dby.utils.md5.YdMd5Util;

@Service("_YdWebServiceMemberInfo")
public class YdMemberServiceImplInfo implements YdMemberServiceInfo {

	@Autowired
	private HttpSession session;

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@Autowired
	private YdMemberMapperInfo YdMemberMapperInfo;

	@Autowired
	private YdMemberMapperCart YdMemberMapperCart;

	@Autowired
	private YdMemberMapperUser YdMemberMapperUser;

	@Autowired
	private YdMemberMapperMoneyRecord YdMemberMapperMoneyRecord;

	@Autowired
	private StringRedisTemplate redis;

	// 懒鱼查看我的账户信息
	@Override
	public Map<String, Object> info(HashMap<String, Object> request) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		User user = YdMemberMapperInfo.info(Integer.parseInt(session.getAttribute("user_id").toString()));
		String userBirthday = user.getUser_birthday();
		String user_birthday = null;
		if (userBirthday == null) {
			user_birthday = "";
		} else {
			userBirthday.substring(0, 19);
		}
		user.setUser_birthday(userBirthday);
		map.put("data", user);
		return map;
	}

	// 懒鱼我的账户信息更新界面
	@Override
	public Map<String, Object> update(HashMap<String, Object> request) throws ParseException {
		HashMap<String, Object> map = new HashMap<String, Object>();
		request.put("user_id", session.getAttribute("user_id"));

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String year = (String) request.get("year");
		String month = (String) request.get("month");
		String day = (String) request.get("day");
		String user_birthday = year + "-" + month + "-" + day + " " + "00:00:00";
		Date date = format.parse(user_birthday);
		request.put("user_birthday", date);
		YdMemberMapperInfo.update(request);
		User user = YdMemberMapperInfo.info(Integer.parseInt(session.getAttribute("user_id").toString()));
		map.put("data", user);
		return map;
	}

	@Override
	public Map<String, Object> editPassword(HashMap<String, Object> request) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		Integer user_id = (Integer) session.getAttribute("user_id");
		request.put("user_id", user_id);
		map.put("data", YdMemberMapperInfo.info(user_id));
		map.put("typeStr", request.get("type").equals("login") ? "登录" : "支付");
		map.put("typeCode", request.get("type"));
		return map;
	}

	// 修改密码-验证手机号
	@Override
	public Map<String, Object> editPasswordVerifyMobile(HashMap<String, Object> request) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		Integer user_id = (Integer) session.getAttribute("user_id");

		if (stringRedisTemplate.hasKey(YdMd5Util.GetMD5Code(request.get("mobileCodeToken").toString()))) {
			map.put("code", 0);
			map.put("msg", "手机验证码已经过期");
			return map;
		}

		// if ( stringRedisTemplate.hasKey( YdMd5Util.GetMD5Code(
		// request.get("imageCodeToken").toString() ) ) ) {
		// map.put("code", 0);
		// map.put("msg", "图形验证码已经过期");
		// return map;
		// }

		map.put("typeStr", request.get("type").equals("login") ? "登录" : "支付");
		map.put("typeCode", request.get("type"));

		request.put("user_id", user_id);

		try {
			map.put("data", YdMemberMapperInfo.info(user_id));
			map.put("code", 200);
			YdUtilTokenOnlyExpire.verification(request.get("mobileCodeToken").toString(), request.get("mobile_code").toString());
			// YdUtilTokenOnlyExpire.verification(request.get("imageCodeToken").toString(),
			// request.get("image_code").toString().toUpperCase());
			stringRedisTemplate.opsForValue().set(YdMd5Util.GetMD5Code(request.get("mobileCodeToken").toString()), request.get("mobile_code").toString());
			stringRedisTemplate.expire(YdMd5Util.GetMD5Code(request.get("mobileCodeToken").toString()), 60 * 6, TimeUnit.SECONDS);

			// stringRedisTemplate.opsForValue().set( YdMd5Util.GetMD5Code(
			// request.get("imageCodeToken").toString() ),
			// request.get("image_code").toString());
			// stringRedisTemplate.expire( YdMd5Util.GetMD5Code(
			// request.get("imageCodeToken").toString() ), 60*6,
			// TimeUnit.SECONDS );
			return map;
		} catch (Exception e) {
			map.put("code", 0);
			map.put("msg", "验证码错误");
			return map;
		}
	}

	@Override
	public Map<String, Object> editPasswordSubPass(HashMap<String, Object> request) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("typeStr", request.get("type").equals("login") ? "登录" : "支付");
		map.put("typeCode", request.get("type"));

		try {
			map.put("code", 200);
			request.put("user_id", session.getAttribute("user_id"));

			// 图形验证码是否正确
			if (!redis.opsForValue().get(request.get("ydCaptchaCodeKey")).equals(request.get("image_code"))) {
				map.put("code", 0);
				map.put("msg", "验证码错误");
				return map;
			}

			// YdUtilTokenOnlyExpire.verification(request.get("imageCodeToken").toString(),
			// request.get("image_code").toString().toUpperCase());

			request.put("password", YdMd5Util.GetMD5Code(request.get("password").toString()));
			if (request.get("type").equals("login")) {
				YdMemberMapperInfo.editLoginPassword(request);
			} else {
				YdMemberMapperInfo.editPayPassword(request);
			}

			return map;
		} catch (Exception e) {
			map.put("code", 0);
			map.put("msg", "验证码错误");
			return map;
		}
	}

	@Override
	public Map<String, Object> jumpEditPassword(HashMap<String, Object> request) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("typeStr", request.get("type").equals("login") ? "登录" : "支付");
		map.put("typeCode", request.get("type"));
		return map;
	}

	@Override
	public Map<String, Object> jumpEidtPasswordSuccess(HashMap<String, Object> request) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("typeStr", request.get("type").equals("login") ? "登录" : "支付");
		map.put("typeCode", request.get("type"));
		return map;
	}

	@Override
	public Map<String, Object> balance(HashMap<String, Object> request) {
		Integer user_id = (Integer) session.getAttribute("user_id");

		Integer perPage = 5;

		HashMap<String, Object> map = new HashMap<String, Object>();
		request.put("user_id", user_id);
		// 用户信息
		map.put("data", YdMemberMapperInfo.info(user_id));

		// 银行卡
		map.put("dataCard", YdMemberMapperCart.lists(request));

		Integer total = YdMemberMapperMoneyRecord.total(request);
		if (total % perPage == 0) {
			map.put("totalPage", Math.ceil(total / perPage));
		} else {
			map.put("totalPage", Math.ceil(total / perPage) + 1);
		}

		request.put("to", (Integer.valueOf(request.get("p").toString()) - 1) * perPage);

		request.put("perPage", perPage);

		// 账单
		map.put("dataBill", YdMemberMapperMoneyRecord.lists(request));

		map.put("p", request.get("p"));

		return map;
	}

	/**
	 * 验证手机号
	 */
	@Override
	public Map<String, Object> verifyMobile(HashMap<String, Object> request) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		request.put("user_id", session.getAttribute("user_id"));
		return map;
	}

	/**
	 * 修改手机号
	 */
	@Override
	public Map<String, Object> editMobile(HashMap<String, Object> request) {
		Integer user_id = (Integer) session.getAttribute("user_id");
		HashMap<String, Object> map = new HashMap<String, Object>();
		request.put("user_id", user_id);
		map.put("data", YdMemberMapperInfo.info(user_id));
		// map.put("typeStr", request.get("type").equals("login") ? "登录" :
		// "支付");
		// map.put("typeCode", request.get("type"));
		return map;
	}

	/**
	 * 验证手机号-提交
	 */
	@Override
	public Map<String, Object> editMobileSub(HashMap<String, Object> request) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		request.put("user_id", session.getAttribute("user_id"));

		if (stringRedisTemplate.hasKey(YdMd5Util.GetMD5Code(request.get("mobileCodeToken").toString()))) {
			map.put("code", 0);
			map.put("msg", "手机验证码已经过期");
			return map;
		}

		User user = YdMemberMapperUser.findByMobile(request.get("mobile").toString());
		if (user != null) {
			map.put("code", 0);
			map.put("msg", "此手机号码已经存在");
			return map;
		}

		try {
			YdUtilTokenOnlyExpire.verification(request.get("mobileCodeToken").toString(), request.get("mobile_code").toString().toUpperCase());
			Integer data = YdMemberMapperUser.updateMobile(request);
			if (data.equals(0)) {
				map.put("code", 0);
				map.put("msg", "修改失败");
			} else {
				map.put("code", 200);
				map.put("msg", "修改成功");
			}
			return map;
		} catch (Exception e) {
			map.put("code", 0);
			map.put("msg", "验证码错误");
			return map;
		}
	}

	/**
	 * 验证手机号-验证码提交
	 */
	@Override
	public Map<String, Object> verifyMobileSub(HashMap<String, Object> request) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		request.put("user_id", session.getAttribute("user_id"));
		return map;
	}

	// 跳转设置支付密码页面
	/**
	 * @author 作者 tpf
	 * @version 创建时间：2017年2月12日 下午8:51:24
	 */
	@Override
	public Map<String, Object> setPayPassword(HashMap<String, Object> request) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		Integer user_id = (Integer) session.getAttribute("user_id");
		request.put("user_id", user_id);
		map.put("data", YdMemberMapperInfo.info(user_id));
		return map;
	}

	// 设置支付密码-手机验证
	@Override
	public Map<String, Object> setPayPasswordTwo(HashMap<String, Object> request) {
		// HashMap<String, Object> map = new HashMap<String, Object>();
		// request.put("user_id", session.getAttribute("user_id"));
		// return map;
		HashMap<String, Object> map = new HashMap<String, Object>();
		Integer user_id = (Integer) session.getAttribute("user_id");

		if (stringRedisTemplate.hasKey(YdMd5Util.GetMD5Code(request.get("mobileCodeToken").toString()))) {
			map.put("code", 0);
			map.put("msg", "手机验证码已经过期");
			return map;
		}

		// if ( stringRedisTemplate.hasKey( YdMd5Util.GetMD5Code(
		// request.get("imageCodeToken").toString() ) ) ) {
		// map.put("code", 0);
		// map.put("msg", "图形验证码已经过期");
		// return map;
		// }

		request.put("user_id", user_id);

		try {
			map.put("data", YdMemberMapperInfo.info(user_id));
			map.put("code", 200);
			YdUtilTokenOnlyExpire.verification(request.get("mobileCodeToken").toString(), request.get("mobile_code").toString());
			// YdUtilTokenOnlyExpire.verification(request.get("imageCodeToken").toString(),
			// request.get("image_code").toString().toUpperCase());
			stringRedisTemplate.opsForValue().set(YdMd5Util.GetMD5Code(request.get("mobileCodeToken").toString()), request.get("mobile_code").toString());
			stringRedisTemplate.expire(YdMd5Util.GetMD5Code(request.get("mobileCodeToken").toString()), 60 * 6, TimeUnit.SECONDS);

			// stringRedisTemplate.opsForValue().set( YdMd5Util.GetMD5Code(
			// request.get("imageCodeToken").toString() ),
			// request.get("image_code").toString());
			// stringRedisTemplate.expire( YdMd5Util.GetMD5Code(
			// request.get("imageCodeToken").toString() ), 60*6,
			// TimeUnit.SECONDS );
			return map;
		} catch (Exception e) {
			map.put("code", 0);
			map.put("msg", "手机验证码错误");
			return map;
		}
	}

	@Override
	public Map<String, Object> setPayPasswordTwoTwo(HashMap<String, Object> request) {
		return request;
	}

	// 设置支付密码-提交密码更改跳转成功界面
	@Override
	public Object setPayPasswordThree(HashMap<String, Object> request) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		// map.put("typeStr", request.get("type").equals("login") ? "登录" :
		// "支付");
		// map.put("typeCode", request.get("type"));

		try {
			map.put("code", 200);
			request.put("user_id", session.getAttribute("user_id"));
			String user_payment_password = request.get("user_payment_password").toString();
			String user_payment_password1 = request.get("user_payment_password1").toString();

			if (user_payment_password == null || user_payment_password == "" || !user_payment_password.equals(user_payment_password1)) {
				map.put("code", 0);
				map.put("msg", "两次密码不一致");
				return map;
			}

			if (user_payment_password.length() < 6) {
				map.put("code", 0);
				map.put("msg", "密码必须大于6位");
				return map;
			}
			// // 图形验证码是否正确
			// if
			// (!redis.opsForValue().get(request.get("ydCaptchaCodeKey")).equals(request.get("image_code")))
			// {
			// map.put("code", 0);
			// map.put("msg", "验证码错误");
			// return map;
			// }

			// YdUtilTokenOnlyExpire.verification(request.get("imageCodeToken").toString(),
			// request.get("image_code").toString().toUpperCase());

			request.put("password", YdMd5Util.GetMD5Code(user_payment_password));
			YdMemberMapperInfo.editPayPassword(request);
			return map;
		} catch (Exception e) {
			map.put("code", 0);
			map.put("msg", "验证码错误");
			e.printStackTrace();
			return map;
		}
	}

	/**
	 * 找回密码手机号-提交
	 */
	@Override
	public Map<String, Object> reset_password(HashMap<String, Object> request) {
		HashMap<String, Object> map = new HashMap<String, Object>();

		try {
			if (stringRedisTemplate.hasKey(YdMd5Util.GetMD5Code(request.get("mobileCodeToken").toString()))) {
				map.put("code", 0);
				map.put("msg", "手机验证码已经过期");
				return map;
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		User user = YdMemberMapperUser.findByMobile(request.get("mobile").toString());
		if (user == null) {
			map.put("code", 0);
			map.put("msg", "此手机号码还未注册");
			return map;
		}

		try {
			YdUtilTokenOnlyExpire.verification(request.get("mobileCodeToken").toString(), request.get("mobile_code").toString().toUpperCase());
			Integer data = YdMemberMapperUser.updateMobile(request);
			if (data.equals(0)) {
				map.put("code", 0);
				map.put("msg", "操作失败");
			} else {
				map.put("code", 200);
				map.put("msg", "操作成功");
			}
			return map;
		} catch (Exception e) {
			map.put("code", 0);
			map.put("msg", "验证码错误");
			return map;
		}
	}

}