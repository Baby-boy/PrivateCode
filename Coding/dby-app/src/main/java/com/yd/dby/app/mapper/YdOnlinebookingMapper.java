package com.yd.dby.app.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yd.dby.app.entity.YdOnlinebooking;
import com.yd.dby.app.entity.YdOnlinebookingKey;
import com.yd.dby.app.entity.para.BasePara;

public interface YdOnlinebookingMapper {
	public int deleteByPrimaryKey(YdOnlinebookingKey key);

	public int insert(YdOnlinebooking record);

	public int insertSelective(YdOnlinebooking record);

	public YdOnlinebooking selectByPrimaryKey(YdOnlinebookingKey key);

	public int updateByPrimaryKeySelective(YdOnlinebooking record);

	public int updateByPrimaryKey(YdOnlinebooking record);

	/** 线上预约总数 */
	public int selectCirculationOnlinebookingPageListCount(@Param("userId") Integer userId);

	/** 线上预约列表 */
	public List<YdOnlinebooking> selectCirculationOnlinebookingPageList(@Param("para") BasePara para, @Param("userId") Integer userId);

	/** 线上预约详情 */
	public YdOnlinebooking selectCirculationOnlinebookingDetails(@Param("id") Integer id);
}