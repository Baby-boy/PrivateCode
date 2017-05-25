package com.yd.gcj.system.service;

import java.util.List;

import com.yd.gcj.entity.vo.YdMangerTenderVo;

public interface YdMangerServiceSystemTender {

	/**
	 * description(根据task_id查询当前所有的投标信息)
	 * @param
	 * @param task_id
	 * @return
	 */
	List<YdMangerTenderVo> queryTenderByTaskId(Integer task_id,String tender_pname,String tender_uphone);

	/**
	 * description(根据tender_id查询当前投标的详细信息)
	 * @param
	 * @param tender_id
	 * @return
	 */
	YdMangerTenderVo queryTenderByTenderId(Integer tender_id);

	/**
	 * description(条件查询)
	 * @param
	 * @param tender_pname
	 * @param tender_uphone
	 * @return
	 */
	List<YdMangerTenderVo> queryTenderByCondition(Integer task_id,String tender_uname,String tender_uphone);

}
