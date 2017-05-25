package com.yd.dby.c.member.service;

import java.util.HashMap;
import java.util.Map;

public interface YdMemberServiceUserBank {
	
	/* 银行卡列表 */
	Map<String, Object> lists(HashMap<String, Object> request);
}
