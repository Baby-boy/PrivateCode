package com.yd.dby.app.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yd.dby.app.basic.Page;
import com.yd.dby.app.common.HttpCode;
import com.yd.dby.app.common.ServiceResult;
import com.yd.dby.app.entity.YdBanner;
import com.yd.dby.app.entity.YdCtc;
import com.yd.dby.app.entity.YdCtcClass;
import com.yd.dby.app.entity.YdDict;
import com.yd.dby.app.entity.YdUser;
import com.yd.dby.app.entity.para.BasePara;
import com.yd.dby.app.entity.para.CtcHomePara;
import com.yd.dby.app.entity.para.CtcMessagePara;
import com.yd.dby.app.entity.para.CtcPara;
import com.yd.dby.app.entity.vo.CtcClassifyVo;
import com.yd.dby.app.entity.vo.CtcCommentVo;
import com.yd.dby.app.entity.vo.CtcGoodsDetailsVo;
import com.yd.dby.app.entity.vo.CtcGoodsListVo;
import com.yd.dby.app.entity.vo.CtcMessageListVo;
import com.yd.dby.app.mapper.YdBannerMapper;
import com.yd.dby.app.mapper.YdCtcClassMapper;
import com.yd.dby.app.mapper.YdCtcCommentMapper;
import com.yd.dby.app.mapper.YdCtcMapper;
import com.yd.dby.app.mapper.YdDictMapper;
import com.yd.dby.app.mapper.YdMessageMapper;
import com.yd.dby.app.mapper.YdUserMapper;
import com.yd.dby.app.service.YdCtcService;

@Service("ydCtcService")
public class YdCtcServiceImpl implements YdCtcService {

	@Autowired
	private YdCtcClassMapper ydCtcClassMapper;

	@Autowired
	private YdCtcMapper ydCtcMapper;

	@Autowired
	private YdCtcCommentMapper ydCtcCommentMapper;// 评论

	@Autowired
	private YdMessageMapper ydMessageMapper;// 留言

	@Autowired
	private YdUserMapper ydUserMapper;

	@Autowired
	private YdBannerMapper ydBannerMapper;

	@Autowired
	private YdDictMapper ydDictMapper;

	@Override
	public ServiceResult<List<CtcClassifyVo>> selectCtcClassifyList() {
		ServiceResult<List<CtcClassifyVo>> result = new ServiceResult<>();

		List<CtcClassifyVo> list = ydCtcClassMapper.selectCtcClassifyList();
		result.setData(list);
		result.setStatusCode(HttpCode.OK);

		return result;
	}

	@Override
	public ServiceResult<List<YdDict>> selectQualityList() {
		ServiceResult<List<YdDict>> result = new ServiceResult<>();

		List<YdDict> list = ydDictMapper.selectQualityListByDictType("ctc_quality");
		result.setData(list);
		result.setStatusCode(HttpCode.OK);
		
		return result;
	}

	@Override
	public ServiceResult<Object> insertCtcGoods(YdCtc ydCtc) {
		ServiceResult<Object> result = new ServiceResult<>();

		int info = ydCtcMapper.insertSelective(ydCtc);
		if (info == 1) {
			result.setMsg("发布成功!");
			result.setStatusCode(HttpCode.OK);
		} else {
			result.setMsg("发布失败!");
			result.setStatusCode(HttpCode.INTERNAL_SERVER_ERROR);
		}

		return result;
	}

	@Override
	public ServiceResult<Page<CtcGoodsListVo>> selectUserCtcGoodsPageList(YdUser user, BasePara para) {
		ServiceResult<Page<CtcGoodsListVo>> result = new ServiceResult<>();

		int countRecord = ydCtcMapper.selectUserCtcGoodsPageListCount(user.getUserId());
		Page<CtcGoodsListVo> page = new Page<>(para.getCurrentPage(), countRecord, para.getOnePageCount());
		if (para.getCurrentPage() <= page.getCountPage()) {
			para.setStartIndex(page.getStartIndex());
			List<CtcGoodsListVo> list = ydCtcMapper.selectUserCtcGoodsPageList(para, user.getUserId());
			page.setList(list);
		}
		result.setData(page);
		result.setMsg("查询成功!");
		result.setStatusCode(HttpCode.OK);

		return result;
	}

	@Override
	public ServiceResult<Object> updateUserCtcGoods(YdCtc ydCtc) {
		ServiceResult<Object> result = new ServiceResult<>();

		int info = ydCtcMapper.updateByPrimaryKeySelective(ydCtc);
		if (info == 1) {
			result.setMsg("编辑成功!");
			result.setStatusCode(HttpCode.OK);
		} else {
			result.setMsg("编辑失败!");
			result.setStatusCode(HttpCode.INTERNAL_SERVER_ERROR);
		}

		return result;
	}

	@Override
	public ServiceResult<Object> deleteUserCtcGoods(CtcPara para) {
		ServiceResult<Object> result = new ServiceResult<>();

		// 对需要删除的多个商品ID进行处理
		String[] str = StringUtils.split(para.getCtcIds(), ",");
		List<Integer> ctcIdList = new ArrayList<>();
		for (String ctcId : str) {
			ctcIdList.add(Integer.parseInt(ctcId));
		}

		int info = ydCtcMapper.deleteUserCtcGoods(ctcIdList);
		if (info > 0) {
			result.setMsg("删除成功!");
			result.setStatusCode(HttpCode.OK);
		} else {
			result.setMsg("删除失败!");
			result.setStatusCode(HttpCode.INTERNAL_SERVER_ERROR);
		}

		return result;
	}

	@Override
	public ServiceResult<Page<CtcGoodsListVo>> selectCtcHome(CtcHomePara para) {
		ServiceResult<Page<CtcGoodsListVo>> result = new ServiceResult<>();

		// 查询懒鱼首页banner图
		List<YdBanner> ydBanners = ydBannerMapper.selectBannerByBannerType("appCtc");

		// 查询懒鱼首页商品分类
		List<YdCtcClass> ctcGoodsClassifyList = ydCtcClassMapper.selectCtcGoodsClassify(para);
		if (null != ctcGoodsClassifyList && ctcGoodsClassifyList.size() > 0) {
			if (null == para.getCcPid()) {
				para.setCcPid(ctcGoodsClassifyList.get(0).getCcPid());
			}
			if (null == para.getCcId()) {
				para.setCcId(ctcGoodsClassifyList.get(0).getCcId());
			}

			int countRecord = ydCtcMapper.selectCtcGoodsPageListCount(para);
			Page<CtcGoodsListVo> page = new Page<>(para.getCurrentPage(), countRecord, para.getOnePageCount());
			if (para.getCurrentPage() <= page.getCountPage()) {
				para.setStartIndex(page.getStartIndex());
				List<CtcGoodsListVo> list = ydCtcMapper.selectCtcGoodsPageList(para);
				page.setList(list);
			}

			Map<String, Object> map = new HashMap<>();
			if (null != ydBanners && ydBanners.size() > 0) {
				map.put("banner", ydBanners);
			}
			map.put("classify", ctcGoodsClassifyList);

			result.setMsg("查询成功!");
			result.setStatusCode(HttpCode.OK);
			result.setData(page);
			result.setDataMap(map);
		} else {
			result.setMsg("未查询到商品!");
			result.setStatusCode(HttpCode.NO_CONTENT);
		}

		return result;
	}

	@Override
	public ServiceResult<CtcGoodsDetailsVo> selectCtcGoodsDetails(Integer ctcId, YdUser user) {
		ServiceResult<CtcGoodsDetailsVo> result = new ServiceResult<>();

		// 查询商品详情
		CtcGoodsDetailsVo ctcGoodsDetailsVo = ydCtcMapper.selectCtcGoodsDetails(ctcId, user.getUserId());

		// 查询留言内容(只显示两条)
		CtcMessagePara para = new CtcMessagePara();
		para.setCtcId(ctcId);
		para.setStartIndex(0);
		para.setOnePageCount(2);// 控制显示两条
		List<CtcMessageListVo> messageList = ydMessageMapper.selectCtcMessagePageList(para);

		Map<String, Object> map = new HashMap<>();
		map.put("messageList", messageList);

		result.setMsg("查询成功!");
		result.setData(ctcGoodsDetailsVo);
		result.setDataMap(map);
		result.setStatusCode(HttpCode.OK);

		return result;
	}

	@Override
	public ServiceResult<Page<CtcGoodsListVo>> selectCtcOthersHomepage(CtcPara para) {
		ServiceResult<Page<CtcGoodsListVo>> result = new ServiceResult<>();

		// 查询用户基本信息
		YdUser ydUser = ydUserMapper.selectByPrimaryKey(para.getUserId());
		if (null == ydUser) {
			result.setMsg("用户身份异常!");
			result.setStatusCode(HttpCode.FORBIDDEN);
			return result;
		}

		// 计算该用户的评论平均分
		CtcCommentVo ctcCommentVo = ydCtcCommentMapper.selectCtcUserAvgScore(para.getUserId());
		if (null == ctcCommentVo.getAvgScore()) {
			ctcCommentVo.setAvgScore(0);
		}

		int countRecord = ydCtcMapper.selectCtcOthersHomepageCount(para);
		Page<CtcGoodsListVo> page = new Page<>(para.getCurrentPage(), countRecord, para.getOnePageCount());
		if (para.getCurrentPage() <= page.getCountPage()) {
			para.setStartIndex(page.getStartIndex());
			List<CtcGoodsListVo> list = ydCtcMapper.selectCtcOthersHomepage(para);
			page.setList(list);
		}

		Map<String, Object> map = new HashMap<>();
		map.put("avgScore", ctcCommentVo.getAvgScore());
		map.put("totalComment", ctcCommentVo.getTotalComment());
		map.put("userNickname", ydUser.getUserNickname());
		map.put("userAvatar", ydUser.getUserAvatar());
		map.put("userSex", ydUser.getUserSex());
		map.put("userPca", ydUser.getUserPca());

		result.setData(page);
		result.setMsg("查询成功!");
		result.setStatusCode(HttpCode.OK);
		result.setDataMap(map);

		return result;
	}

	@Override
	public ServiceResult<Page<CtcCommentVo>> selectCtcOthersHomepageCommentPageList(CtcPara para) {
		ServiceResult<Page<CtcCommentVo>> result = new ServiceResult<>();

		int countRecord = ydCtcCommentMapper.selectCtcOthersHomepageCommentPageListCount(para);
		Page<CtcCommentVo> page = new Page<>(para.getCurrentPage(), countRecord, para.getOnePageCount());
		if (para.getCurrentPage() <= page.getCountPage()) {
			para.setStartIndex(page.getStartIndex());
			List<CtcCommentVo> list = ydCtcCommentMapper.selectCtcOthersHomepageCommentPageList(para);
			page.setList(list);
		}
		result.setData(page);
		result.setMsg("查询成功!");
		result.setStatusCode(HttpCode.OK);

		return result;
	}

}
