package com.yd.dby.c.member.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yd.dby.c.member.mapper.YdMemberMapperCtcInMsg;
import com.yd.dby.c.member.service.YdMemberServiceCtcInMsg;

/**
 * @author 作者 E-mail:
 * @version 创建时间：2017年2月16日 下午6:14:43
 * 
 */
@Service("_YdMemberServiceMsg")
public class YdMemberServiceImplCtcInMsg implements YdMemberServiceCtcInMsg {

	@Autowired
	private YdMemberMapperCtcInMsg ydMemberMapperCtcInMsg;

	public List<HashMap<String, Object>> oneCtcMsg(Integer ctc_id, Integer to) {
		return ydMemberMapperCtcInMsg.oneCtcMsg(ctc_id, to);
	}

	/**
	 * 查询留言回复内容
	 */
	@Override
	public List<HashMap<String, Object>> querySellerMessage(Integer ctc_id) {
		List<HashMap<String,Object>> recevieList = ydMemberMapperCtcInMsg.querySellerMessage(ctc_id);
		return recevieList;
	}

}
