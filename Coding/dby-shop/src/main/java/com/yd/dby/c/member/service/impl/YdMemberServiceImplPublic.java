package com.yd.dby.c.member.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpSession;
import javax.websocket.server.ServerEndpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.yd.dby.c.member.mapper.YdMemberMapperInfo;
import com.yd.dby.c.member.service.YdMemberServicePublic;
import com.yd.dby.utils.YdUtilTokenOnlyExpire;
import com.yd.dby.utils.md5.YdMd5Util;

/**
 * @author 作者 E-mail:
 * @version 创建时间：2017年2月13日 下午2:00:49
 * 
 */
@Service("_YdWebServiceMemberPublic")
public class YdMemberServiceImplPublic implements YdMemberServicePublic {

	@Autowired
	private HttpSession session;

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@Autowired
	private YdMemberMapperInfo YdMemberMapperInfo;

	/* 验证手机号 */
	@Override
	public Map<String, Object> verifyMobileCode(HashMap<String, Object> request) {
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
			map.put("msg", "验证码错误");
			return map;
		}
	}

	/* 验证手机号-成功后跳转页面 */
	@Override
	public Map<String, Object> verifyMobileSuccess(HashMap<String, Object> request) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		if ( request.get("type") != null ) {
			map.put("typeStr", request.get("type").equals("login") ? "登录" : "支付");			
		}
		map.put("typeCode", request.get("type"));
		return map;
	}

}
