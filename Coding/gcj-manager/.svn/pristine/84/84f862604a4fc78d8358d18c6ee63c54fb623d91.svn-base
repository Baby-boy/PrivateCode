package com.yd.gcj.service;

import java.util.HashMap;

import org.springframework.transaction.annotation.Transactional;

public interface YdMangerServiceBank {

	/***
	 * 查询指定用户的银行卡信息
	 * @param map
	 * @return
	 */
	Object $queryAll(HashMap<String, Object> map);
	
	/***
	 * 根据id查询指定银行卡信息
	 * @param map
	 * @return
	 */
	Object $queryById(HashMap<String, Object> map);
	
	/***
	 * 查询用户银行卡信息数量
	 * @param map
	 * @return
	 */
	Object $queryCountNum(HashMap<String, Object> map);
	
	/***
	 * 为用户添加新银行卡信息
	 * @param map
	 * @return
	 */
	@Transactional
	Object $addBankMsg(HashMap<String, Object> map);
	
	/***
	 * 删除用户指定的银行卡信息
	 * @param map
	 * @return
	 */
	@Transactional
	Object $delBankMsg(HashMap<String, Object> map);
	
}
