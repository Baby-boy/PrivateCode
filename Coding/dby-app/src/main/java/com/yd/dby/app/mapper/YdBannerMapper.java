package com.yd.dby.app.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yd.dby.app.entity.YdBanner;
import com.yd.dby.app.entity.vo.HomeBannerVo;

public interface YdBannerMapper {
	public int deleteByPrimaryKey(Integer bannerId);

	public int insert(YdBanner record);

	public int insertSelective(YdBanner record);

	public YdBanner selectByPrimaryKey(Integer bannerId);

	public int updateByPrimaryKeySelective(YdBanner record);

	public int updateByPrimaryKey(YdBanner record);

	public List<HomeBannerVo> selectHomeBannerListByType(String bannerType);

	/** 查询流通置换首页banner图 */
	public List<YdBanner> selectBannerByBannerType(@Param("bannerType") String bannerType);
}