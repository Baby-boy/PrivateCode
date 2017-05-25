package com.yd.gcj.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yd.gcj.entity.YdMangerAwards;

public interface YdMangerMapperAwards {
	
	/***
	 * 查询指定案例奖项信息集合
	 * @param awards_caseid
	 * @return
	 */
	List<YdMangerAwards> $queryAll(@Param("awards_caseid") Integer awards_caseid);
	
	/***
	 * 查询指定奖项信息
	 * @param awards_id
	 * @return
	 */
	YdMangerAwards $queryById(@Param("awards_id") Integer awards_id);
	
	/***
	 * 查询指定案例奖项数量
	 * @param awards_caseid
	 * @return
	 */
	Integer $queryCountNum(@Param("awards_caseid") Integer awards_caseid);
	
	/***
	 * 为案例添加新的奖项
	 * @param awards
	 * @return
	 */
	Integer $insert(YdMangerAwards awards);
	
	/***
	 * 修改指定奖项信息
	 * @param awards
	 * @return
	 */
	Integer $update(YdMangerAwards awards);
	
	/***
	 * 删除指定奖项
	 * @param awards_id
	 * @return
	 */
	Integer $delete(@Param("awards_id") Integer awards_id);
	
}
