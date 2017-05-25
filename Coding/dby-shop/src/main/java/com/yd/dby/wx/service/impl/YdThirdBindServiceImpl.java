package com.yd.dby.wx.service.impl;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yd.dby.a.sys.entity.YdSysUserFull;
import com.yd.dby.a.sys.entity.YdSysUserSecurity;
import com.yd.dby.utils.YdUtilResponse;
import com.yd.dby.utils.md5.YdMd5Util;
import com.yd.dby.utils.security.YdSecurity;
import com.yd.dby.wx.mapper.YdThirdBindMapper;
import com.yd.dby.wx.service.YdThirdBindService;

/** 
 * @author 作者 E-mail:
 * @version 创建时间：2017年2月18日 下午2:21:04 
 * 
 */
@SuppressWarnings("all")
@Service("_YdSysThirdBindServcie")
public class YdThirdBindServiceImpl implements YdThirdBindService{

	@Autowired 
	private YdThirdBindMapper ydThirdBindMapper;
	
	@Autowired
	private HttpSession session;
	
	
	@Autowired
	private YdSecurity ydSecurity;
	
	/**
	 * @param request
	 * @return
	 * 方法作用(绑定第三方用户并注册)
	 */
	@Override
	public Object bind(HashMap<String, Object> request) {
		
		String member_password = (String) request.get("password");
		String tpl_type = (String) request.get("tpl_type");
		Integer tplType = Integer.valueOf(tpl_type);
		request.put("tpl_type",tplType);
		request.put("password",YdMd5Util.GetMD5Code(member_password));
		
		YdSysUserSecurity yUserSecurity = new YdSysUserSecurity();
		yUserSecurity.setUser_mobile((String)request.get("mobile"));
		yUserSecurity.setUser_username((String)request.get("user_username"));
		yUserSecurity.setUser_password((String)request.get("password"));
		
		//默认注册新用户
		Integer addNumCreate = ydThirdBindMapper.createUser(yUserSecurity);
		Integer user_id = yUserSecurity.getUser_id();
		
		//查询当前默认插入用户的user_id
		/*YdSysUserFull user = ydThirdBindMapper.queryUserIdByMobile(request);*/
		request.put("tpl_user_id", user_id);
		//绑定新用户
		Integer addNumBind = ydThirdBindMapper.bindUser(request);
		try {
			if (addNumCreate>0&&addNumBind>0) {
				//绑定成功
				
				return YdUtilResponse.success(null, "注册并绑定成功");
			} else {
				return YdUtilResponse.fail("注册并绑定失败");
			}
		} catch (Exception e) {
			return YdUtilResponse.fail("注册并绑定失败");
		}
	}

	/**
	 * 绑定已有用户
	 */
	@Override
	public Object bindExistUser(HashMap<String, Object> request) {
		
		//获取页面传递过来的手机号
		String user_mobile = (String) request.get("mobile");
		YdSysUserFull  ydSysUserFull = ydThirdBindMapper.queryUserByUserMobile(user_mobile);
		if (ydSysUserFull==null) {
			//根据手机号码没有查询到当前用户
			return YdUtilResponse.fail("用户不存在");
		}
		request.put("tpl_user_id",ydSysUserFull.getUser_id());
		//用户存在的情况
		Integer addNum = ydThirdBindMapper.bindExistUser(request);
		if (addNum>0) {
			//绑定成功
			session.setAttribute("user_id",ydSysUserFull.getUser_id());
			session.setAttribute("user",ydSysUserFull);
			return YdUtilResponse.success(null, "绑定成功");
		}else {
			return YdUtilResponse.fail("绑定失败");
		}
	}

	

}
