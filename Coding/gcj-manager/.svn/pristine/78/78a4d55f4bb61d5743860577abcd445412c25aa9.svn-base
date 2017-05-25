package com.yd.gcj.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yd.gcj.entity.vo.YdMangerBankTRVo;
import com.yd.gcj.system.mapper.YdMangerMapperSystemBankroll;
import com.yd.gcj.system.service.YdMangerServiceSystemBankroll;

@Service("ydMangerServiceSystemBankroll")
public class YdMangerServiceImplSystemBankroll implements YdMangerServiceSystemBankroll {

	@Autowired
	private YdMangerMapperSystemBankroll ydMangerMapperSystemBankroll;
	
	//查询所有用户的充值记录
	@Override
	public List<YdMangerBankTRVo> queryUserRechargeRecord(String user_name,String user_phone) {
		List<YdMangerBankTRVo> bankTRVoList = ydMangerMapperSystemBankroll.queryUserRechargeRecord(user_name,user_phone);
		return bankTRVoList;
	}


}
