package com.yd.dby.app.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yd.dby.app.entity.YdComment;
import com.yd.dby.app.entity.para.CommentPara;
import com.yd.dby.app.entity.vo.CommentLevelVo;
import com.yd.dby.app.entity.vo.GoodsDetailsCommentVo;

public interface YdCommentMapper {
	public int deleteByPrimaryKey(Integer id);

	public int insert(YdComment record);

	public int insertSelective(YdComment record);

	public YdComment selectByPrimaryKey(Integer id);

	public int updateByPrimaryKeySelective(YdComment record);

	public int updateByPrimaryKey(YdComment record);

	public CommentLevelVo selectCommentLevelVo();

	public int selectCommentPageCount(@Param("para") CommentPara para);

	public List<GoodsDetailsCommentVo> selectCommentPageList(@Param("para") CommentPara para);

	/** 查询未评价总数 */
	public int selectCommentCount(@Param("userId") Integer userId);
}