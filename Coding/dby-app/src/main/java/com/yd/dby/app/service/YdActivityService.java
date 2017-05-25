package com.yd.dby.app.service;

import com.yd.dby.app.basic.Page;
import com.yd.dby.app.common.ServiceResult;
import com.yd.dby.app.entity.para.ActivityPara;
import com.yd.dby.app.entity.vo.ActivityVo;

public interface YdActivityService {

	/**
	 * 说明: 活动商品列表
	 * 
	 * @param para
	 * @return
	 * @author fqh(fqh@zwzyd.com)
	 * @date: 2017年2月14日 下午5:47:39
	 */
	public ServiceResult<Page<ActivityVo>> selectActivityGoodsPageList(ActivityPara para);

}
