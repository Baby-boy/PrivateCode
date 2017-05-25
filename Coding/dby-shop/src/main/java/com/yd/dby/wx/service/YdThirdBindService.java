package com.yd.dby.wx.service;

import java.util.HashMap;

/** 
 * @author 作者 E-mail:
 * @version 创建时间：2017年2月18日 下午2:18:29 
 * 
 */
public interface YdThirdBindService {

	/**
	 * @param request
	 * @return
	 * 方法作用(绑定第三方用户并注册)
	 */
	Object bind(HashMap<String,Object> request);

	/**
	 * @param request
	 * @return
	 * 方法作用(绑定已有用户)
	 */
	Object bindExistUser(HashMap<String,Object> request);
}
