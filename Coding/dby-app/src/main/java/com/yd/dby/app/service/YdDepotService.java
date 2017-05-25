package com.yd.dby.app.service;

import java.util.List;

import com.yd.dby.app.basic.Page;
import com.yd.dby.app.common.ServiceResult;
import com.yd.dby.app.entity.YdSeckill;
import com.yd.dby.app.entity.para.GroupBuyPara;
import com.yd.dby.app.entity.para.SeckillPara;
import com.yd.dby.app.entity.vo.GroupBuyDetailsVo;
import com.yd.dby.app.entity.vo.GroupBuyVo;
import com.yd.dby.app.entity.vo.SeckillDetailsVo;
import com.yd.dby.app.entity.vo.SeckillVo;

public interface YdDepotService {

	/**
	 * 说明: 团购列表
	 * 
	 * @param para
	 * @return
	 * @author lgl(lgl@zwzyd.com)
	 * @date: 2017年3月9日 上午10:10:34
	 */
	ServiceResult<Page<GroupBuyVo>> selectGroupBuyPageList(GroupBuyPara para);

	/**
	 * 说明: 团购商品详情
	 * 
	 * @param depotId
	 * @return
	 * @author lgl(lgl@zwzyd.com)
	 * @date: 2017年3月9日 下午1:38:36
	 */
	ServiceResult<GroupBuyDetailsVo> selectGroupBuyDetails(Integer depotId);

	/**
	 * 说明: 秒杀列表
	 * 
	 * @param para
	 * @return
	 * @author lgl(lgl@zwzyd.com)
	 * @date: 2017年3月10日 上午11:25:07
	 */
	ServiceResult<Page<SeckillVo>> selectSeckillPageList(SeckillPara para);
	
	/**
	 * 说明: 秒杀商品详情
	 * 
	 * @param depotId
	 * @return
	 * @author lgl(lgl@zwzyd.com)
	 * @date: 2017年3月10日 上午11:38:26
	 */
	ServiceResult<SeckillDetailsVo> selectSeckillDetails(Integer depotId);
	
	/**
	 * 说明: 查询秒杀时间
	 * 
	 * @return
	 * @author lgl(lgl@zwzyd.com)
	 * @date: 2017年3月10日 下午1:39:51
	 */
	ServiceResult<List<YdSeckill>> selectSeckillTimeList();
}
