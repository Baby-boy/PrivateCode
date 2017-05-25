package com.yd.dby.app.mapper;

import java.util.List;

import com.yd.dby.app.entity.YdActivity;
import com.yd.dby.app.entity.para.ActivityPara;
import com.yd.dby.app.entity.vo.ActivityVo;

public interface YdActivityMapper {
	public int deleteByPrimaryKey(Integer activityId);

	public int insert(YdActivity record);

	public int insertSelective(YdActivity record);

	public YdActivity selectByPrimaryKey(Integer activityId);

	public int updateByPrimaryKeySelective(YdActivity record);

	public int updateByPrimaryKeyWithBLOBs(YdActivity record);

	public int updateByPrimaryKey(YdActivity record);

	/** 活动商品总数 */
	public int selectActivityGoodsPageListCount(ActivityPara para);

	/** 活动商品列表 */
	public List<ActivityVo> selectActivityGoodsPageList(ActivityPara para);
}