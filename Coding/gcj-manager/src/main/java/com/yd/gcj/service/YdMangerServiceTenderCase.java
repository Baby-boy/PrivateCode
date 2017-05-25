package com.yd.gcj.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;

import com.yd.gcj.entity.YdMangerTenderCase;

public interface YdMangerServiceTenderCase {
	
	
	/***
	 * 查询指定投标信息的案例关联信息
	 * @param tc_tenderid
	 * @return
	 */
	List<YdMangerTenderCase> $queryByTenderId(@Param("tc_tenderid") Integer tc_tenderid);
	
	/***
	 * 查询指定投标信息所使用的案例信息数量
	 * @param tc_tenderid
	 * @return
	 */
	Integer $queryCountNumByTenderId(@Param("tc_tenderid") Integer tc_tenderid);
	
	/***
	 * 添加新的投标案例信息
	 * @param tc
	 * @return
	 */
	@Transactional
	Integer $insert(YdMangerTenderCase tc);
	
	/***
	 * 删除投标信息里指定的案例
	 * @param tc_tenderid
	 * @param tc_caseid
	 * @return
	 */
	@Transactional
	Integer $delete(@Param("tc_tenderid") Integer tc_tenderid,@Param("tc_caseid") Integer tc_caseid);
	
}
