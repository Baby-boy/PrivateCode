package com.yd.dby.app.service;

import com.yd.dby.app.basic.Page;
import com.yd.dby.app.common.ServiceResult;
import com.yd.dby.app.entity.para.HomePara;
import com.yd.dby.app.entity.vo.GoodsListVo;

public interface YdMaintainService {

	/**
	 * 说明: 查询养护商品列表
	 * 
	 * @param para
	 * @return
	 * @author fqh(fqh@zwzyd.com)
	 * @date: 2017年2月23日 下午7:55:34
	 */
	public ServiceResult<Page<GoodsListVo>> selectMaintainGoodsPageList(HomePara para);

}
