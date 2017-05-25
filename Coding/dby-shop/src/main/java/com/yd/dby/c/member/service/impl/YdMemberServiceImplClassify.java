package com.yd.dby.c.member.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yd.dby.c.member.mapper.YdMemberMapperClassify;
import com.yd.dby.c.member.service.YdMemberServiceClassify;


@Service("_YdWebServiceClassify")
public class YdMemberServiceImplClassify implements YdMemberServiceClassify {
	
	@Autowired
	private YdMemberMapperClassify ydWebMapperClassify;
	
	@Override
	public Map<String, Object> son(HashMap<String, Object> request) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("data", ydWebMapperClassify.son(request));
		return map;
	}

}