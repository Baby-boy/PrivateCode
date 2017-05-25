package com.yd.dby.app.mapper;

import java.util.List;

import com.yd.dby.app.entity.YdCtcClass;
import com.yd.dby.app.entity.para.CtcHomePara;
import com.yd.dby.app.entity.vo.CtcClassifyVo;

public interface YdCtcClassMapper {
	public int deleteByPrimaryKey(Integer ccId);

	public int insert(YdCtcClass record);

	public int insertSelective(YdCtcClass record);

	public YdCtcClass selectByPrimaryKey(Integer ccId);

	public int updateByPrimaryKeySelective(YdCtcClass record);

	public int updateByPrimaryKey(YdCtcClass record);

	/** 懒鱼筛选分类 */
	public List<CtcClassifyVo> selectCtcClassifyList();

	/** 查询懒鱼首页商品分类 */
	public List<YdCtcClass> selectCtcGoodsClassify(CtcHomePara para);
}