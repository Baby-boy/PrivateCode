package com.yd.gcj.system.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yd.gcj.entity.YdMangerCount;

public interface YdMangerMapperSystemCount {

	/**
	 * description(查询当月的数据)
	 * @param
	 * @return
	 */
	List<YdMangerCount> queryAllInfomation(@Param("firstDay")Date monthFirstDay,@Param("lastDay")Date monthLastDay);

	/**
	 * description(根据当前的日期int类型查询统计信息)
	 * @param
	 * @param parseFirstTime
	 * @param parseLastTime
	 * @return
	 */
	List<YdMangerCount> queryAllInfomationByCountNum(@Param("day")int parseFirstTime,@Param("endDay")int parseLastTime);

}
