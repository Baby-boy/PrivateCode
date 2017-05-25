package com.yd.gcj.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yd.gcj.entity.YdMangerBanner;
import com.yd.gcj.system.mapper.YdMangerMapperSystemPicture;
import com.yd.gcj.system.service.YdMangerServiceSystemPicture;
@Service("ydMangerServiceSystemPicture")
public class YdMangerServiceImplSystemPicture implements YdMangerServiceSystemPicture {

	@Autowired
	private YdMangerMapperSystemPicture ydMangerMapperSystemPicture; 
	
	//查询所有的图片信息
	@Override
	public List<YdMangerBanner> queryAllPicture(String banner_title) {
		List<YdMangerBanner> bannerList = ydMangerMapperSystemPicture.queryAllPicture(banner_title);
		return bannerList;
	}

	//添加图片
	@Override
	public Integer addPicture(YdMangerBanner ydMangerBanner) {
		Integer addNum = ydMangerMapperSystemPicture.addPicture(ydMangerBanner);
		return addNum;
	}

	//删除图片
	@Override
	public Integer deletePictureByBannerId(Integer banner_id) {
		Integer delNum = ydMangerMapperSystemPicture.deletePictureByBannerid(banner_id);
		return delNum;
	}

	//查询指定的图片信息
	@Override
	public YdMangerBanner queryBannerByBannerId(Integer banner_id) {
		YdMangerBanner ydMangerBanner = ydMangerMapperSystemPicture.queryPictureByBannerId(banner_id);
		return ydMangerBanner;
	}

	//查询所有的图片类型
	@Override
	public List<YdMangerBanner> queryAllBannerType() {
		List<YdMangerBanner> bannerTypeList = ydMangerMapperSystemPicture.queryAllBannerType();
		return bannerTypeList;
		
	}
	
}
