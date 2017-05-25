package com.lingang.api.service;

import com.lingang.api.domain.basic.ServiceResult;
import com.lingang.api.domain.entity.SysImages;

public interface SysImagesService {

	/**
	* @Description: (添加图片)
	* @param images
	* @return    
	* @author lgl(lgl1012dream@foxmail.com)
	* @date: 2016年12月12日 上午10:22:17
	 */
	ServiceResult<SysImages> addSysImages(SysImages images);
	
	/**
	* @Description: (查询图片)
	* @param imgType 图片类型
	* @param objId 对应ID
	* @return    
	* @author lgl(lgl1012dream@foxmail.com)
	* @date: 2016年12月12日 下午1:36:22
	 */
	ServiceResult<SysImages> selectSysImagesByImgTypeAndObjId(Integer imgType,Integer objId);
	
	SysImages selectSysImagesByImgId(Integer imgId);
}
