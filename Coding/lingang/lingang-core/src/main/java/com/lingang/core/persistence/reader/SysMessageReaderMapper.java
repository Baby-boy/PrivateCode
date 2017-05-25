package com.lingang.core.persistence.reader;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.lingang.api.domain.entity.SysMessage;
import com.lingang.api.domain.pfvo.SysMessagePfvo;

public interface SysMessageReaderMapper {

	SysMessage selectByPrimaryKey(Integer messageId);

	int selectMessageCount(@Param("map") Map<String, Object> map);

	// List<SysMessage> selectMessagePageList(@Param("map") Map<String, Object> map);
	/** 到以下(逻辑改变的修改) */
	List<SysMessage> queryMessageAll(@Param("map") Map<String, Object> map);

	/** 到以上(逻辑改变的修改) */

	/** 后台逻辑 */
	int queryMessageCount(@Param("map") Map<String, Object> map);

	List<SysMessagePfvo> selectMessageAllByMessageState(@Param("map") Map<String, Object> map);

	List<SysMessagePfvo> selectMessageByMessageStateASC(@Param("map") Map<String, Object> map);

	List<SysMessagePfvo> selectMessageByMessageState(@Param("map") Map<String, Object> map);

	/** 意见反馈总数 */
	Integer selectOpinionCount(@Param("userId") Integer userId);

	/** 错误信息总数 */
	Integer selectErrorCount(@Param("userId") Integer userId);

	/** 所有未读消息 */
	List<SysMessage> selectAllUnreadMessage(@Param("map") Map<String, Object> map);

}