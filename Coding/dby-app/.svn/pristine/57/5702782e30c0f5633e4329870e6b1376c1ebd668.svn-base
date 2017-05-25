package com.yd.dby.app.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yd.dby.app.basic.Page;
import com.yd.dby.app.common.HttpCode;
import com.yd.dby.app.common.ServiceResult;
import com.yd.dby.app.entity.para.CommentPara;
import com.yd.dby.app.entity.para.GoodsPara;
import com.yd.dby.app.entity.para.HomePara;
import com.yd.dby.app.entity.vo.CommentLevelVo;
import com.yd.dby.app.entity.vo.GoodsDetailsCommentVo;
import com.yd.dby.app.entity.vo.GoodsDetailsVo;
import com.yd.dby.app.entity.vo.GoodsListVo;
import com.yd.dby.app.entity.vo.HomeBannerVo;
import com.yd.dby.app.entity.vo.HomeCtcVo;
import com.yd.dby.app.mapper.YdBannerMapper;
import com.yd.dby.app.mapper.YdCommentMapper;
import com.yd.dby.app.mapper.YdDataMapper;
import com.yd.dby.app.mapper.YdGoodsMapper;
import com.yd.dby.app.service.YdGoodsService;
import com.yd.dby.app.util.YdGsonUtils;

@Service("ydGoodsService")
public class YdGoodsServiceImpl implements YdGoodsService {

	@Autowired
	private YdBannerMapper ydBannerMapper;

	@Autowired
	private YdDataMapper ydDataMapper;

	@Autowired
	private YdGoodsMapper ydGoodsMapper;

	@Autowired
	private YdCommentMapper ydCommentMapper;

	@Override
	public ServiceResult<Page<GoodsListVo>> selectHomePageList(HomePara para) {

		ServiceResult<Page<GoodsListVo>> result = new ServiceResult<Page<GoodsListVo>>();

		// 第一个banner图
		List<HomeBannerVo> bannerList = ydBannerMapper.selectHomeBannerListByType("appIndex");

		// 第二个banner图
		List<HomeBannerVo> bannerOne = ydBannerMapper.selectHomeBannerListByType("1");

		// 二手闲置(ctc)
		List<HomeCtcVo> ctcList = ydDataMapper.selectHomeCtcListByType("3");

		// 推荐商品
		int countRecord = ydGoodsMapper.selectHomeGoodsPageCount(para);
		Page<GoodsListVo> page = new Page<GoodsListVo>(para.getCurrentPage(), countRecord, para.getOnePageCount());
		if (para.getCurrentPage() <= page.getCountPage()) {
			para.setStartIndex(page.getStartIndex());
			List<GoodsListVo> goodsList = ydGoodsMapper.selectHomeGoodsPageList(para);
			page.setList(goodsList);
		}
		result.setData(page);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("bannerList", bannerList);
		map.put("bannerOne", bannerOne);
		map.put("ctcList", ctcList);
		result.setDataMap(map);
		result.setStatusCode(HttpCode.OK);

		return result;
	}

	@Override
	public ServiceResult<Page<GoodsListVo>> selectClassifyGoodsPageList(GoodsPara para) {

		ServiceResult<Page<GoodsListVo>> result = new ServiceResult<Page<GoodsListVo>>();

		int countRecord = ydGoodsMapper.selectClassifyGoodsPageCount(para);
		Page<GoodsListVo> Page = new Page<GoodsListVo>(para.getCurrentPage(), countRecord, para.getOnePageCount());
		if (para.getCurrentPage() <= Page.getCountPage()) {
			para.setStartIndex(Page.getStartIndex());
			List<GoodsListVo> list = ydGoodsMapper.selectClassifyGoodsPageList(para);
			Page.setList(list);
		}

		result.setData(Page);
		result.setStatusCode(HttpCode.OK);

		return result;
	}

	@Override
	public ServiceResult<GoodsDetailsVo> selectGoodsDetails(Integer goodsId) {
		ServiceResult<GoodsDetailsVo> result = new ServiceResult<GoodsDetailsVo>();

		GoodsDetailsVo goodsDetailsVo = ydGoodsMapper.selectGoodsDetails(goodsId);

		if (StringUtils.isNotEmpty(goodsDetailsVo.getGoodsAttrs())) {
			List<Object> goodsAttrsList = YdGsonUtils.fromJson(goodsDetailsVo.getGoodsAttrs());
			goodsDetailsVo.setGoodsAttrsList(goodsAttrsList);
			goodsDetailsVo.setGoodsAttrs(null);
		}
		if (StringUtils.isNotEmpty(goodsDetailsVo.getGoodsAttrSelect())) {
			List<Object> goodsAttrSelectList = YdGsonUtils.fromJson(goodsDetailsVo.getGoodsAttrSelect());
			goodsDetailsVo.setGoodsAttrSelectList(goodsAttrSelectList);
			goodsDetailsVo.setGoodsAttrSelect(null);
		}

		result.setData(goodsDetailsVo);
		result.setStatusCode(HttpCode.OK);

		return result;
	}

	@Override
	public ServiceResult<Page<GoodsDetailsCommentVo>> selectCommentPageList(CommentPara para) {

		ServiceResult<Page<GoodsDetailsCommentVo>> result = new ServiceResult<Page<GoodsDetailsCommentVo>>();

		// 评论级别统计
		CommentLevelVo levelVo = ydCommentMapper.selectCommentLevelVo();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("levelNum", levelVo);

		// 评论信息
		int countRecord = ydCommentMapper.selectCommentPageCount(para);
		Page<GoodsDetailsCommentVo> page = new Page<GoodsDetailsCommentVo>(para.getCurrentPage(), countRecord, para.getOnePageCount());
		if (para.getCurrentPage() <= page.getCountPage()) {
			para.setStartIndex(page.getStartIndex());
			List<GoodsDetailsCommentVo> list = ydCommentMapper.selectCommentPageList(para);
			page.setList(list);
		}

		// 返回数据
		result.setData(page);
		result.setDataMap(map);
		result.setStatusCode(HttpCode.OK);

		return result;
	}

	@Override
	public ServiceResult<Page<GoodsListVo>> selectHomeGoodsPageList(HomePara para) {
		ServiceResult<Page<GoodsListVo>> result = new ServiceResult<Page<GoodsListVo>>();

		int countRecord = ydGoodsMapper.selectGoodsPageCount(para);
		Page<GoodsListVo> Page = new Page<GoodsListVo>(para.getCurrentPage(), countRecord, para.getOnePageCount());
		if (para.getCurrentPage() <= Page.getCountPage()) {
			para.setStartIndex(Page.getStartIndex());
			List<GoodsListVo> list = ydGoodsMapper.selectGoodsPageList(para);
			Page.setList(list);
		}

		result.setData(Page);
		result.setStatusCode(HttpCode.OK);

		return result;
	}

}
