package com.yd.dby.app.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yd.dby.app.entity.YdCtcComment;
import com.yd.dby.app.entity.para.CtcPara;
import com.yd.dby.app.entity.vo.CtcCommentVo;

public interface YdCtcCommentMapper {
	public int deleteByPrimaryKey(Integer ctcCommentId);

	public int insert(YdCtcComment record);

	public int insertSelective(YdCtcComment record);

	public YdCtcComment selectByPrimaryKey(Integer ctcCommentId);

	public int updateByPrimaryKeySelective(YdCtcComment record);

	public int updateByPrimaryKey(YdCtcComment record);

	/** 计算懒鱼用户评论平均分 */
	public CtcCommentVo selectCtcUserAvgScore(@Param("sellerId") Integer sellerId);

	/** 他人主页评论总数 */
	public int selectCtcOthersHomepageCommentPageListCount(CtcPara para);

	/** 他人主页评论列表 */
	public List<CtcCommentVo> selectCtcOthersHomepageCommentPageList(CtcPara para);
}