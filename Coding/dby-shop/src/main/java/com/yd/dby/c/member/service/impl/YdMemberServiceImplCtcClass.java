package com.yd.dby.c.member.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yd.dby.c.member.mapper.YdMemberMapperCtcClass;
import com.yd.dby.c.member.service.YdMemberServiceCtcClass;


@Service("_YdWebServiceMemberCtcClass")
public class YdMemberServiceImplCtcClass implements YdMemberServiceCtcClass {

	@Autowired
	private YdMemberMapperCtcClass ydMemberMapperCtcClass;
	
	@Override
	public List<Object> one() {
		return ydMemberMapperCtcClass.one();
	}
	
	@Override
	public Map<String, Object> son(HashMap<String, Object> request) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("data", ydMemberMapperCtcClass.son(request));
		return map;
	}
}