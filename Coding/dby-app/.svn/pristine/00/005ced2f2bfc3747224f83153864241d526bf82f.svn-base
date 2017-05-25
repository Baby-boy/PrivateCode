package com.yd.dby.app.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yd.dby.app.entity.YdPointsLog;
import com.yd.dby.app.entity.para.BasePara;

public interface YdPointsLogMapper {
	public int deleteByPrimaryKey(Integer plId);

	public int insert(YdPointsLog record);

	public int insertSelective(YdPointsLog record);

	public YdPointsLog selectByPrimaryKey(Integer plId);

	public int updateByPrimaryKeySelective(YdPointsLog record);

	public int updateByPrimaryKey(YdPointsLog record);

	/** 积分中心操作日志总数 */
	public int selectUserPointsCenterPageListCount(@Param("userId") Integer userId);

	/** 积分中心操作日志列表 */
	public List<YdPointsLog> selectUserPointsCenterPageList(@Param("para") BasePara para, @Param("userId") Integer userId);
}