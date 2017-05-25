package com.yd.gcj.system.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yd.gcj.entity.YdMangerTaskTemp;

public interface YdMangerMapperSystemWorkHall {

	/**
	 * description(查询所有工作大厅的数据)
	 * @param
	 * @return
	 */
	List<YdMangerTaskTemp> queryAllWorkHall(@Param("tasktemp_num")String tasktemp_num);

	/**
	 * description(添加工作大厅数据)
	 * @param
	 * @param ydMangerTaskTemp
	 * @return
	 */
	Integer addWorkHall(YdMangerTaskTemp ydMangerTaskTemp);

	/**
	 * description(根据指定的tasktemp_id删除工作大厅数据)
	 * @param
	 * @param tasktemp_id
	 * @return
	 */
	Integer deleteWorkHallByTaskTempId(Integer tasktemp_id);

	/**
	 * description(修改之前先根据tasktemp_id查到当前工作大厅的数据)
	 * @param
	 * @param tasktemp_id
	 * @return
	 */
	YdMangerTaskTemp queryWorkHallByTaskTempId(Integer tasktemp_id);

	/**
	 * description(根据指定的tasktemp_id修改工作大厅的数据)
	 * @param
	 * @param ydMangerTaskTemp
	 * @return
	 */
	Integer updateWorkHallTaskTempId(YdMangerTaskTemp ydMangerTaskTemp);

}
