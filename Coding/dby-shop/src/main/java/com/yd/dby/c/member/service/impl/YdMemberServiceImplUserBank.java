package com.yd.dby.c.member.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yd.dby.c.member.service.YdMemberServiceUserBank;


@Service("_YdWebServiceMemberCard")
public class YdMemberServiceImplUserBank implements YdMemberServiceUserBank {

	@Autowired
	private HttpServletRequest YdRequest;
	

	@Override
	public Map<String, Object> lists(HashMap<String, Object> request) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		return map;
	}

}