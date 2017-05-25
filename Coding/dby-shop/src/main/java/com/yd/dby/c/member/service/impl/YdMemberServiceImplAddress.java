package com.yd.dby.c.member.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yd.dby.c.member.entity.Address;
import com.yd.dby.c.member.mapper.YdMemberMapperAddress;
import com.yd.dby.c.member.service.YdMemberServiceAddress;
import com.yd.dby.utils.YdUtilResponse;
import com.yd.dby.utils.date.YdUtilDate;


@Service("_YdMemberServiceAddress")
public class YdMemberServiceImplAddress implements YdMemberServiceAddress {
	@Autowired
	private HttpSession session;
	
	@Autowired
	private HttpServletRequest YdRequest;
	
	@Autowired
	private YdMemberMapperAddress ydMemberMapperAddress;
	
	@Override
	public Map<String, Object> index(HashMap<String, Object> request) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		request.put("user_id", session.getAttribute("user_id") );
		map.put("data", ydMemberMapperAddress.index(request));
		return map;
	}
	
	
	@Override
	public Map<String, Object> store(HashMap<String, Object> request) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		request.put("user_id", session.getAttribute("user_id") );
		request.put("ad_created_time", df.format(new Date()) );
		request.put("data", ydMemberMapperAddress.store(request));
		return index(request);
	}
	
	@Override
	public Object storeEntity(HashMap<String,Object> request) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		request.put("user_id" ,Integer.valueOf(( session.getAttribute("user_id").toString() )) );
		request.put("ad_created_time", df.format(new Date()) );
		try {
			ydMemberMapperAddress.storeEntity(request);
			return YdUtilResponse.success(null, "新增成功!");
		} catch (Exception e) {
			return YdUtilResponse.success("", "新增成功!");
		}
	}
	
	
	@Override
	public Map<String, Object> update(HashMap<String, Object> request) {
		request.put("user_id", session.getAttribute("user_id") );
		request.put("data", ydMemberMapperAddress.update(request));
		return index(request);
	}
	
	
	@Override
	public Map<String, Object> setDefault(HashMap<String, Object> request) {
		request.put("user_id", session.getAttribute("user_id") );
		request.put("data", ydMemberMapperAddress.setDefault(request));
		return index(request);
	}
	
	
	@Override
	public Map<String, Object> destroy(HashMap<String, Object> request) {
		request.put("user_id", session.getAttribute("user_id") );
		ydMemberMapperAddress.destroy(request);
		return index(request);
	}


	@Override
	public Object storePayPage(Address address) {
		address.setUser_id( Integer.parseInt( session.getAttribute("user_id").toString() ) );
		address.setAd_created_time(YdUtilDate.getFormat());
		try {
			ydMemberMapperAddress.storePayPage(address);
			return YdUtilResponse.success(null, "新增成功!");
		} catch (Exception e) {
			return YdUtilResponse.success("", "新增成功!");
		}
	}
}