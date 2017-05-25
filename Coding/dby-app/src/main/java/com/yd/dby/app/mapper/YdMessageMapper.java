package com.yd.dby.app.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yd.dby.app.entity.YdMessage;
import com.yd.dby.app.entity.para.BasePara;
import com.yd.dby.app.entity.para.CtcMessagePara;
import com.yd.dby.app.entity.vo.CtcMessageListVo;
import com.yd.dby.app.entity.vo.MessageVo;

public interface YdMessageMapper {
	public int deleteByPrimaryKey(Integer messageId);

	public int insert(YdMessage record);

	public int insertSelective(YdMessage record);

	public YdMessage selectByPrimaryKey(Integer messageId);

	public int updateByPrimaryKeySelective(YdMessage record);

	public int updateByPrimaryKey(YdMessage record);

	/** 查询用户消息中心消息总数 */
	public int selectUserMessageCenterCount(@Param("userId") Integer userId);

	/** 查询用户消息中心消息分页列表 */
	public List<MessageVo> selectUserMessageCenterPageList(@Param("para") BasePara para, @Param("userId") Integer userId);

	/** 懒鱼商品全部留言总数 */
	public int selectCtcMessagePageListCount(CtcMessagePara para);

	/** 懒鱼商品全部留言列表 */
	public List<CtcMessageListVo> selectCtcMessagePageList(CtcMessagePara para);

}