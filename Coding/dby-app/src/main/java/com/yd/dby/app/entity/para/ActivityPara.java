package com.yd.dby.app.entity.para;

/**
 * 说明: 活动参数
 * 
 * @Author: fqh(fqh@zwzyd.com)
 * @date:2017年2月14日 下午5:39:09
 * @Version:1.0
 */
public class ActivityPara extends BasePara {

	private Integer bannerId;// banner图ID

	private Integer bannerOpenType;// 打开类型 0:自定义链接 1:活动

	public Integer getBannerId() {
		return bannerId;
	}

	public void setBannerId(Integer bannerId) {
		this.bannerId = bannerId;
	}

	public Integer getBannerOpenType() {
		return bannerOpenType;
	}

	public void setBannerOpenType(Integer bannerOpenType) {
		this.bannerOpenType = bannerOpenType;
	}

}
