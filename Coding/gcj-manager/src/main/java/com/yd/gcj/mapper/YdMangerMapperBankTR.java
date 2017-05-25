package com.yd.gcj.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yd.gcj.entity.YdMangerBankTR;

public interface YdMangerMapperBankTR {
	/***
	 * 查询指定用户交易记录
	 * @param btr_uid
	 * @return
	 */
	List<YdMangerBankTR> $queryAll(@Param("userId") Integer userId,@Param("startPageNum") Integer startPageNum,@Param("queryPageNum") Integer queryPageNum);
	
	/***
	 * 根据时间查询交易记录，主要用于后台操作
	 * @param btr_date
	 * @return
	 */
	List<YdMangerBankTR> $queryByDate(@Param("btr_date") Date btr_date);
	
	/***
	 * 多条件复杂情况使用sql动态查询
	 * @param sql
	 * @return
	 */
	List<YdMangerBankTR> $queryBySql(@Param("btr_sql") String sql);
	
	/***
	 * 根据id查询指定交易记录信息
	 * @param btr_id
	 * @return
	 */
	YdMangerBankTR $queryById(@Param("btr_id") Integer btr_id);
	
	/***
	 * 根据用户id查询该用户的交易记录数量，主要用户用户查询分页
	 * @param btr_uid
	 * @return
	 */
	Integer $queryCountNum(@Param("btr_uid") Integer btr_uid);
	
	/***
	 * 查询数据库所有交易记录信息数量，主要用于后台查询分页
	 * @return
	 */
	Integer $queryCountNum();
	
	/***
	 * 生成新的交易记录信息
	 * @param banktr
	 * @return
	 */
	Integer $insert(YdMangerBankTR banktr);
	
}
