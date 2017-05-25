package com.yd.gcj.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yd.gcj.entity.YdMangerMessage;
import com.yd.gcj.entity.YdMangerMsgSize;
import com.yd.gcj.entity.vo.YdMangerMessageVo;

public interface YdMangerMapperMessage {
	
	/***
	 * 按条件查询
	 * @param user_id
	 * @param startPageNum
	 * @param queryPageNum
	 * @param msg_isdel 是否被删除 0未删除 1已删除 -1全部
	 * @param msg_type 类型
	 * @return
	 */
	List<YdMangerMessageVo> $queryByUserIdAndByPageNum(@Param("userId") Integer userId,@Param("startPageNum") Integer startPageNum,@Param("queryPageNum") Integer queryPageNum,@Param("type") Integer type,@Param("userType") Integer userType);
	
	
	/***
	 * 按条件查询相应消息信息数量
	 * @param user_id
	 * @param startPageNum
	 * @param queryPageNum
	 * @param msg_isdel 是否被删除 0未删除 1已删除 -1全部
	 * @param msg_type
	 * @return
	 */
	Integer $queryCountNumByUserId(@Param("user_id") Integer user_id,@Param("startPageNum") Integer startPageNum,
			@Param("queryPageNum") Integer queryPageNum,@Param("msg_isdel") Integer msg_isdel,@Param("msg_type") Integer msg_type);
	
	
	/***
	 * 添加新消息
	 * @param msg
	 * @return
	 */
	Integer $insert(YdMangerMessage msg);
	
	
	/***
	 * 删除指定消息
	 * @param msg_id
	 * @return
	 */
	Integer $delete(@Param("msg_id") Integer msg_id);
	
	/***
	 * 查询指定用户各个类型未读信息数量
	 * @return
	 */
	YdMangerMsgSize $queryAllTypeMsgSize(@Param("userId") Integer userId);
	
}
