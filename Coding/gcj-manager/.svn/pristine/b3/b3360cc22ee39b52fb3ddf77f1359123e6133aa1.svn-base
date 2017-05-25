package com.yd.gcj.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.yd.gcj.entity.YdMangerMessage;
import com.yd.gcj.entity.vo.YdMangerMessageVo;

public interface YdMangerServiceMessage {

	/***
	 * 按条件查询
	 * @param map
	 * @return
	 */
	List<YdMangerMessageVo> $queryByUserIdAndByPageNum(Integer userId,Integer type,Integer userType);
	
	
	/***
	 * 按条件查询相应消息信息数量
	 * @param map
	 * @return
	 */
	Object $queryCountNumByUserId(HashMap<String, Object> map);
	
	
	/***
	 * 添加新消息
	 * @param map
	 * @return
	 */
	@Transactional
	Object $insert(YdMangerMessage message);
	
	
	/***
	 * 删除指定消息
	 * @param map
	 * @return
	 */
	@Transactional
	Object $delete(HashMap<String, Object> map);
	
	/***
	 * 获取各类型未读消息数量
	 * @param userId
	 * @return
	 */
	Object $queryAllTypeMsgSize(Integer userId);
	
}
