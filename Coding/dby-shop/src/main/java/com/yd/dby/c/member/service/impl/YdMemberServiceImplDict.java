package com.yd.dby.c.member.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yd.dby.c.member.mapper.YdMemberMapperDict;
import com.yd.dby.c.member.service.YdMemberServiceDict;

@Service("_YdWebServiceDict")
public class YdMemberServiceImplDict implements YdMemberServiceDict {
	@Autowired
	private YdMemberMapperDict ydWebMapperDict;
	
	@Override
	public List<Object> listByCode(String dt_code) {
		return ydWebMapperDict.listByCode(dt_code);
	}

	@Override
	public List<HashMap<String, Object>> list(String dict_type) {
		return ydWebMapperDict.get_many(dict_type);
	}

	@Override
	public List<HashMap<String, Object>> list(String dict_type, String dict_pid) {
		return ydWebMapperDict.get_many_simple(dict_type, dict_pid);
	}
}