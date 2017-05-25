package com.yd.dby.app.controller;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

import com.yd.dby.app.util.YdGsonUtils;
import com.yd.dby.app.util.YdUtilUUID;

public class BaseController {

	// @Resource
	// protected RedisTemplate<String, Object> redisTemplate;

	@Autowired
	protected StringRedisTemplate redisTemplate;

	/**
	 * 说明: redis存储
	 * 
	 * @param key
	 * @param val
	 * @throws Exception
	 * @author lgl(lgl@zwzyd.com)
	 * @date: 2017年2月12日 下午5:26:58
	 */
	protected void redisSet(String key, Object data, long timeLong) throws Exception {

		// 存入数据必须为 String
		redisTemplate.opsForValue().set(key, YdGsonUtils.toJson(data), timeLong, TimeUnit.SECONDS);
	}

	/**
	 * 说明: redis 根据key获取value
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 * @author lgl(lgl@zwzyd.com)
	 * @date: 2017年2月12日 下午5:27:20
	 */
	protected <T> Object redisGet(String keyStr, Class<T> classOfT) throws Exception {

		// 必须指定返回数据类型
		return YdGsonUtils.fromJson(redisTemplate.opsForValue().get(keyStr), classOfT);
	}

	/**
	 * 说明: redis 删除数据
	 * 
	 * @param key
	 * @param data
	 * @param request
	 * @author lgl(lgl@zwzyd.com)
	 * @date: 2017年2月9日 下午4:14:29
	 */
	protected void redisDel(String key) throws Exception {
		redisTemplate.delete(key);
	}

	/**
	 * 说明: 获取token，存储基本信息
	 * 
	 * @param data
	 * @param request
	 * @return
	 * @author lgl(lgl@zwzyd.com)
	 * @date: 2017年2月7日 下午8:10:22
	 */
	protected String getToken(Object user) throws Exception {
		String token = YdUtilUUID.generate().toUpperCase();

		// 基本信息存储时间为一个月
		long dataTime = 60 * 60 * 24 * 30;
		redisSet(token, 1, dataTime);
		redisSet(token + "User", user, dataTime);

		return token;
	}

	/**
	 * 说明: 存储验证码信息
	 * 
	 * @param key
	 * @param data
	 * @throws Exception
	 * @author lgl(lgl@zwzyd.com)
	 * @date: 2017年2月12日 下午9:09:25
	 */
	protected String setVerifyCode(String mobile, String data) throws Exception {
		String verifyStr = YdUtilUUID.generate().toUpperCase();

		// 验证码信息存储时间为10分钟
		long dataTime = 60 * 10;
		redisSet(mobile + verifyStr, data, dataTime);

		return verifyStr;
	}

	/**
	 * 说明: 产生随机码(4位数字)
	 * 
	 * @return
	 * @author lgl(lgl@zwzyd.com)
	 * @date: 2017年2月9日 上午11:37:06
	 */
	protected String randomCode() {
		Random random = new Random();

		String codeStr = "";
		boolean is = true;
		while (is) {
			codeStr += random.nextInt(10);
			if (codeStr.length() == 4) {
				is = false;
			}
		}

		return codeStr;
	}

	/**
	 * 说明: 产生随机混合字符串+数字(6)
	 * 
	 * @return
	 * @author lgl(lgl@zwzyd.com)
	 * @date: 2017年2月9日 下午2:02:11
	 */
	protected String randomStr() {
		Random random = new Random();
		char chars[] = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's',
				't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N',
				'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8',
				'9' };

		String randomStr = "";
		boolean is = true;
		while (is) {
			randomStr += chars[random.nextInt(62)];
			if (randomStr.length() == 6) {
				is = false;
			}
		}

		return randomStr;
	}

}
