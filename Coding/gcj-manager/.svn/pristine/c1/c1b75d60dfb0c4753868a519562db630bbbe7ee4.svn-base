package com.yd.gcj.system.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yd.gcj.entity.YdMangerBanner;

public interface YdMangerMapperSystemPicture {

	/**
	 * description(查询所有的图片信息)
	 * @param
	 * @return
	 */
	List<YdMangerBanner> queryAllPicture(@Param("banner_title")String banner_title);

	/**
	 * description(添加图片)
	 * @param
	 * @param ydMangerBanner
	 * @return
	 */
	Integer addPicture(YdMangerBanner ydMangerBanner);

	/**
	 * description(根据指定banner_id删除图片)
	 * @param
	 * @param banner_id
	 * @return
	 */
	Integer deletePictureByBannerid(Integer banner_id);

	/**
	 * description(修改图片之前先格局banner_id查询到当前图片信息)
	 * @param
	 * @param banner_id
	 * @return
	 */
	YdMangerBanner queryPictureByBannerId(Integer banner_id);

	/**
	 * description(查询图片的所有类型)
	 * @param
	 * @return
	 */
	List<YdMangerBanner> queryAllBannerType();

}
