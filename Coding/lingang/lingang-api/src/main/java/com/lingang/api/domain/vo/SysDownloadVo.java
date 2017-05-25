package com.lingang.api.domain.vo;

import com.lingang.api.domain.entity.SysDownload;

public class SysDownloadVo extends SysDownload {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4895853204084835727L;
	
	private String imgPath;

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

}
