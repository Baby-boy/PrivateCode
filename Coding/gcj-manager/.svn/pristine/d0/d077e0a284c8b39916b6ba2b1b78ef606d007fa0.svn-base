package com.yd.gcj.system.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yd.gcj.entity.vo.YdMangerTenderVo;

public interface YdMangerMapperSystemTender {

	/**
	 * description(根据task_id查询所有的投标信息)
	 * @param
	 * @param task_id
	 * @return
	 */
	List<YdMangerTenderVo> queryTenderByTaskId(@Param("task_id")Integer task_id,@Param("tender_pname")String tender_pname,@Param("tender_uphone")String tender_uphone);

	/**
	 * description(根据tender_id查询当前投标的详细信息)
	 * @param
	 * @return
	 */
	YdMangerTenderVo queryTenderByTenderId(Integer tender_id);

	/**
	 * description(条件查询)
	 * @param
	 * @param tender_pname
	 * @param tender_pname2
	 * @return
	 */
	List<YdMangerTenderVo> queryTenderByCondition(@Param("task_id")Integer task_id,@Param("tender_uname")String tender_pname,@Param("tender_uphone")String tender_uphone);

}
