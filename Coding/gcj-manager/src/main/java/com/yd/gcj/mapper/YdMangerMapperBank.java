package com.yd.gcj.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yd.gcj.entity.YdMangerBank;

public interface YdMangerMapperBank {
	
	/***
	 * 查询指定用户的银行卡信息
	 * @return
	 */
	List<YdMangerBank> $queryAll(@Param("bank_uid") Integer bank_uid);
	
	/***
	 * 根据id查询指定银行卡信息
	 * @param bank_id
	 * @return
	 */
	YdMangerBank $queryById(@Param("bank_id") Integer bank_id);
	
	/***
	 * 为用户添加新银行卡信息
	 * @param bank
	 * @return
	 */
	Integer $insert(YdMangerBank bank);
	
	/***
	 * 查询用户银行卡信息数量
	 * @param bank_uid
	 * @return
	 */
	Integer $queryCountNum(@Param("bank_uid") Integer bank_uid);
	
	/***
	 * 根据银行卡信息id和用户所属用户id对银行卡信息进行删除
	 * @param bank_id
	 * @param bank_uid
	 * @return
	 */
	Integer $delBankMsg(@Param("bank_id") Integer bank_id,@Param("bank_uid") Integer bank_uid);
	
}
