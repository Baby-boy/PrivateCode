package com.yd.gcj.system.service;

import java.util.List;

import com.yd.gcj.entity.YdMangerBanner;

public interface YdMangerServiceSystemPicture {

	/**
	 * description(查询所有图片信息)
	 * @param
	 * @return
	 */
	List<YdMangerBanner> queryAllPicture(String banner_title);

	/**
	 * description(添加图片)
	 * @param
	 * @param ydMangerBanner
	 * @return
	 */
	Integer addPicture(YdMangerBanner ydMangerBanner);

	/**
	 * description(根据指定的banner_id删除图片)
	 * @param
	 * @param banner_id
	 * @return
	 */
	Integer deletePictureByBannerId(Integer banner_id);

	/**
	 * description(修改之前吸纳根据banner_id查到当前图片信息)
	 * @param
	 * @param banner_id
	 * @return
	 */
	YdMangerBanner queryBannerByBannerId(Integer banner_id);

	/**
	 * description(查询所有的图片类型)
	 * @param
	 * @return
	 */
	List<YdMangerBanner> queryAllBannerType();

}
