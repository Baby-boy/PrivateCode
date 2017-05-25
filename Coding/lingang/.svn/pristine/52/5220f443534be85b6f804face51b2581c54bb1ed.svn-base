package com.lingang.consumer.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import com.lingang.common.util.UuidUtil;

public class BaseController {
	
	@Resource
	protected RedisTemplate<String, Object> redisTemplate;
	
	
	/**
	* @Description: (redis存储)
	* @param key 存储的key
	* @param val 存储的value  
	* @author lgl(lgl1012dream@foxmail.com)
	* @date: 2017年1月5日 下午5:11:19
	 */
	protected void redisSet(String key,Object val) throws Exception{
		ValueOperations<String, Object> valueops = redisTemplate.opsForValue();
		valueops.set(key, val);
	}
	
	/**
	* @Description: (redis 根据key获取value)
	* @param key
	* @return    
	* @author lgl(lgl1012dream@foxmail.com)
	* @date: 2017年1月5日 下午5:11:55
	 */
	protected Object redisGet(String key) throws Exception{
		ValueOperations<String, Object> valueops = redisTemplate.opsForValue();
		return valueops.get(key);
	}

	/**
	 * @Description: (获取Token)
	 * @return
	 * @author lgl(lgl1012dream@foxmail.com)
	 * @date: 2016年12月1日 上午11:57:49
	 */
	protected String getToken(Object data,HttpServletRequest request) {
		String token = UuidUtil.get32UUID().toUpperCase();
		HttpSession session=request.getSession();
		session.setAttribute("token", token);
		session.setAttribute(token, data);
		return token;
	}

	/**
	 * @Description: (获取缓存信息)
	 * @param request
	 * @return
	 * @author lgl(lgl1012dream@foxmail.com)
	 * @date: 2016年12月3日 上午10:22:44
	 */
	protected Object getTokenData(HttpServletRequest request) {
		HttpSession session=request.getSession();
		Object tokenData = session.getAttribute((String) session.getAttribute("token"));
		return tokenData;
	}

	/**
	 * @Description: (计功能查询时 时间处理)
	 * @param createYear
	 * @param createMonth
	 * @param createQuarter
	 * @return 参数注释 
	 * @author fqh (qihao.fu@outlook.com)
	 * @date: 2017年1月3日 下午3:05:46 
	 */
	protected Map<String, Object> disposeTime(String createYear, String createMonth, String createQuarter) {
		Map<String, Object> map = new HashMap<String, Object>();
		// 统计功能查询时 时间处理
		String beninTime = "";
		String endTime = "";
		if ((createYear != null && !createYear.equals("")) && (createMonth != null && !createMonth.equals(""))) {
			beninTime = createYear + "-" + createMonth + "-01";
			Integer IntMonth = Integer.parseInt(createMonth);
			if (IntMonth != 12) {
				endTime = createYear + "-" + (IntMonth + 1) + "-01";
			} else {
				Integer IntYear = Integer.parseInt(createYear);
				endTime = (IntYear + 1) + "-01-01";
			}
		} else if ((createYear != null && !createYear.equals(""))
				&& (createQuarter != null && !createQuarter.equals(""))) {
			Integer IntQuarter = Integer.parseInt(createQuarter);
			switch (IntQuarter) {
			case 1:
				beninTime = createYear + "-01-01";
				endTime = createYear + "-04-01";
				break;
			case 2:
				beninTime = createYear + "-04-01";
				endTime = createYear + "-07-01";
				break;
			case 3:
				beninTime = createYear + "-07-01";
				endTime = createYear + "-10-01";
				break;
			case 4:
				beninTime = createYear + "-10-01";
				Integer IntYear = Integer.parseInt(createYear);
				endTime = (IntYear + 1) + "-01-01";
				break;
			}
		}
		map.put("beninTime", beninTime);
		map.put("endTime", endTime);
		return map;
	}
}
