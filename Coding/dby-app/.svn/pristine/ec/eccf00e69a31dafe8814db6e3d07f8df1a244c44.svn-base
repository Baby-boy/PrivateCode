package com.yd.dby.app.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yd.dby.app.entity.YdWish;
import com.yd.dby.app.entity.para.BasePara;

public interface YdWishMapper {
	public int deleteByPrimaryKey(Integer wishId);

	public int insert(YdWish record);

	public int insertSelective(YdWish record);

	public YdWish selectByPrimaryKey(Integer wishId);

	public int updateByPrimaryKeySelective(YdWish record);

	public int updateByPrimaryKeyWithBLOBs(YdWish record);

	public int updateByPrimaryKey(YdWish record);

	/** 查询许愿池总数 */
	public int selectUserWishPageListCount(@Param("userId") Integer userId);

	/** 查询许愿池列表 */
	public List<YdWish> selectUserWishPageList(@Param("para") BasePara para, @Param("userId") Integer userId);
}