package com.lingang.api.domain.vo;

import com.lingang.api.domain.entity.SysUser;

public class SysUserVo extends SysUser{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6554503007363380862L;

	private String imgPath;

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
}
