package com.yd.dby.app.service;

import com.yd.dby.app.basic.Page;
import com.yd.dby.app.common.ServiceResult;
import com.yd.dby.app.entity.para.CommentPara;
import com.yd.dby.app.entity.para.GoodsPara;
import com.yd.dby.app.entity.para.HomePara;
import com.yd.dby.app.entity.vo.GoodsDetailsCommentVo;
import com.yd.dby.app.entity.vo.GoodsDetailsVo;
import com.yd.dby.app.entity.vo.GoodsListVo;

public interface YdGoodsService {

	/**
	 * 说明: 首页查询
	 * 
	 * @param para
	 * @return
	 * @author lgl(lgl@zwzyd.com)
	 * @date: 2017年2月7日 下午3:22:01
	 */
	ServiceResult<Page<GoodsListVo>> selectHomePageList(HomePara para);

	/**
	 * 说明: 商品列表
	 * 
	 * @param para
	 * @return
	 * @author lgl(lgl@zwzyd.com)
	 * @date: 2017年2月8日 下午1:41:24
	 */
	ServiceResult<Page<GoodsListVo>> selectClassifyGoodsPageList(GoodsPara para);

	/**
	 * 说明: 商品详情
	 * 
	 * @param goodsId
	 * @return
	 * @author lgl(lgl@zwzyd.com)
	 * @date: 2017年2月8日 下午2:39:27
	 */
	ServiceResult<GoodsDetailsVo> selectGoodsDetails(Integer goodsId);

	/**
	 * 说明: 全部评论
	 * 
	 * @param para
	 * @return
	 * @author lgl(lgl@zwzyd.com)
	 * @date: 2017年2月8日 下午3:48:35
	 */
	ServiceResult<Page<GoodsDetailsCommentVo>> selectCommentPageList(CommentPara para);

	/**
	 * 说明: 首页搜索 商品列表
	 * 
	 * @param para
	 * @return
	 * @author lgl(lgl@zwzyd.com)
	 * @date: 2017年2月9日 上午10:01:17
	 */
	ServiceResult<Page<GoodsListVo>> selectHomeGoodsPageList(HomePara para);
}
