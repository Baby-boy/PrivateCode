package com.lingang.core.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lingang.api.domain.basic.Page;
import com.lingang.api.domain.basic.ServiceResult;
import com.lingang.api.domain.basic.StateCodeConstant;
import com.lingang.api.domain.entity.SysMessage;
import com.lingang.api.domain.entity.SysMessageReply;
import com.lingang.api.domain.entity.SysReply;
import com.lingang.api.domain.para.SysMessagePara;
import com.lingang.api.domain.pfvo.SysMessagePfvo;
import com.lingang.api.domain.vo.SysReplyVo;
import com.lingang.api.service.SysMessageService;
import com.lingang.common.constants.SystemConstants;
import com.lingang.core.persistence.reader.SysMessageReaderMapper;
import com.lingang.core.persistence.reader.SysMessageReplyReaderMapper;
import com.lingang.core.persistence.reader.SysReplyReaderMapper;
import com.lingang.core.persistence.writer.SysMessageReplyWriterMapper;
import com.lingang.core.persistence.writer.SysMessageWriterMapper;
import com.lingang.core.persistence.writer.SysReplyWriterMapper;

@Service("sysMessageService")
public class SysMessageServiceImpl implements SysMessageService {

	@Resource
	private SysMessageReaderMapper sysMessageReaderMapper;
	@Resource
	private SysMessageWriterMapper sysMessageWriterMapper;
	@Resource
	private SysMessageReplyReaderMapper sysMessageReplyReaderMapper;

	@Resource
	private SysReplyReaderMapper sysReplyReaderMapper;
	@Resource
	private SysReplyWriterMapper sysReplyWriterMapper;
	@Resource
	private SysMessageReplyWriterMapper sysMessageReplyWriterMapper;

	@Override
	public ServiceResult<Page<SysMessage>> selectMessagePageList(Integer pageIndex, Integer pageSize,
			Integer messageType, Integer userId) {
		// ServiceResult<Page<SysMessage>> result = new
		// ServiceResult<Page<SysMessage>>();
		// // 查询条件
		// Map<String, Object> map = new HashMap<String, Object>();
		// map.put("messageType", messageType);
		// map.put("userId", userId);
		// int countRecord = sysMessageReaderMapper.selectMessageCount(map);
		// Page<SysMessage> page = new Page<SysMessage>(pageIndex, countRecord,
		// pageSize);
		// if (countRecord > 0) {
		// map.put("startIndex", page.getStartIndex());
		// map.put("onePageCount", pageSize);
		// List<SysMessage> list =
		// sysMessageReaderMapper.selectMessagePageList(map);
		// page.setList(list);
		// }
		// // 返回信息
		// result.setStateCode(StateCodeConstant.SUCCESS_CODE);
		// result.setData(page);

		/** 由于逻辑改变,重写该方法 */
		ServiceResult<Page<SysMessage>> result = new ServiceResult<>();
		// 查询条件
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("messageType", messageType);
		map.put("userId", userId);
		int countRecord = sysMessageReaderMapper.selectMessageCount(map);
		Page<SysMessage> page = new Page<>(pageIndex, countRecord, pageSize);
		if (countRecord > 0) {
			// 未读消息
			map.put("messageState", SystemConstants.SYS_NUM_TWO);
			List<SysMessage> unreadList = sysMessageReaderMapper.queryMessageAll(map);
			// 已读
			map.put("messageState", SystemConstants.SYS_NUM_THREE);
			List<SysMessage> readList = sysMessageReaderMapper.queryMessageAll(map);
			// 未回复
			map.put("messageState", SystemConstants.SYS_NUM_ONE);
			List<SysMessage> noReplyList = sysMessageReaderMapper.queryMessageAll(map);

			List<SysMessage> pageList = new ArrayList<>();

			unreadList.addAll(noReplyList);
			unreadList.addAll(readList);

			Integer index = pageSize * pageIndex;
			if (unreadList.size() < pageSize) {
				index = unreadList.size();
			}
			if (pageIndex == page.getCountPage()) {
				index = unreadList.size();
			}
			for (int i = page.getStartIndex() - 1; i < index; i++) {
				pageList.add(unreadList.get(i));
			}
			page.setList(pageList);
		}
		result.setStateCode(StateCodeConstant.SUCCESS_CODE);
		result.setData(page);
		return result;
	}

	@Override
	public ServiceResult<SysReplyVo> selectSysReply(Integer messageId) {
		ServiceResult<SysReplyVo> result = new ServiceResult<SysReplyVo>();
		SysReplyVo replyVo = sysReplyReaderMapper.selectSysReplyByMessageId(messageId);
		// 返回信息
		result.setStateCode(StateCodeConstant.SUCCESS_CODE);
		result.setData(replyVo);
		return result;
	}

	/**
	 * 信息纠错/意见反馈列表（后台）
	 */
	@Override
	public ServiceResult<Page<SysMessagePfvo>> selectMessageAll(SysMessagePara para) {
		ServiceResult<Page<SysMessagePfvo>> result = new ServiceResult<Page<SysMessagePfvo>>();
		// 查询条件
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("messageType", para.getMessageType());
		map.put("linkType", para.getLinkType());
		map.put("messageState", para.getMessageState());
		int countRecord = sysMessageReaderMapper.queryMessageCount(map);
		Page<SysMessagePfvo> page = new Page<SysMessagePfvo>(para.getCurrentPage(), countRecord,
				para.getOnePageCount());
		map.put("startIndex", page.getStartIndex());
		map.put("onePageCount", para.getOnePageCount());
		if (countRecord > 0) {
			if (null == para.getMessageState() || "".equals(para.getMessageState())) {
				List<SysMessagePfvo> pageList = new ArrayList<>();
				// 查询对应状态的所有消息
				map.put("messageState", SystemConstants.SYS_NUM_ONE);
				List<SysMessagePfvo> noReplyList = sysMessageReaderMapper.selectMessageAllByMessageState(map);
				map.put("messageState", SystemConstants.SYS_NUM_TWO);
				List<SysMessagePfvo> unreadList = sysMessageReaderMapper.selectMessageAllByMessageState(map);
				map.put("messageState", SystemConstants.SYS_NUM_THREE);
				List<SysMessagePfvo> readList = sysMessageReaderMapper.selectMessageAllByMessageState(map);
				map.put("messageState", SystemConstants.SYS_NUM_FOUR);
				List<SysMessagePfvo> delList = sysMessageReaderMapper.selectMessageAllByMessageState(map);
				noReplyList.addAll(unreadList);
				noReplyList.addAll(readList);
				noReplyList.addAll(delList);

				// 代码分页
				Integer index = para.getOnePageCount() * para.getCurrentPage();
				if (noReplyList.size() < para.getOnePageCount()) {
					index = unreadList.size();
				}
				if (para.getCurrentPage() == page.getCountPage()) {
					index = noReplyList.size();
				}
				for (int i = page.getStartIndex() - 1; i < index; i++) {
					pageList.add(noReplyList.get(i));
				}
				/** 这里是消息的一对多关系处理,现阶段只需要实现一对一的效果,后期需要一对多的关系只需要删除以下代码 **/
				for (SysMessagePfvo sysMessagePfvo : pageList) {
					if (null != sysMessagePfvo.getReplys() && sysMessagePfvo.getReplys().size() > 0) {
						sysMessagePfvo.setReplyContent(sysMessagePfvo.getReplys().get(0).getReplyContent());
					}
				}
				/** 到这里(同时需要删除sysMessagePfvo类中的replyContent属性) **/
				page.setList(pageList);
			} else if (SystemConstants.SYS_NUM_ONE == para.getMessageState()) {
				List<SysMessagePfvo> list = sysMessageReaderMapper.selectMessageByMessageStateASC(map);
				/** 这里是消息的一对多关系处理,现阶段只需要实现一对一的效果,后期需要一对多的关系只需要删除以下代码 **/
				for (SysMessagePfvo sysMessagePfvo : list) {
					if (null != sysMessagePfvo.getReplys() && sysMessagePfvo.getReplys().size() > 0) {
						sysMessagePfvo.setReplyContent(sysMessagePfvo.getReplys().get(0).getReplyContent());
					}
				}
				/** 到这里(同时需要删除sysMessagePfvo类中的replyContent属性) **/
				page.setList(list);
			} else {
				List<SysMessagePfvo> list = sysMessageReaderMapper.selectMessageByMessageState(map);
				/** 这里是消息的一对多关系处理,现阶段只需要实现一对一的效果,后期需要一对多的关系只需要删除以下代码 **/
				for (SysMessagePfvo sysMessagePfvo : list) {
					if (null != sysMessagePfvo.getReplys() && sysMessagePfvo.getReplys().size() > 0) {
						sysMessagePfvo.setReplyContent(sysMessagePfvo.getReplys().get(0).getReplyContent());
					}
				}
				/** 到这里(同时需要删除sysMessagePfvo类中的replyContent属性) **/
				page.setList(list);
			}
		}
		// 返回信息
		result.setStateCode(StateCodeConstant.SUCCESS_CODE);
		result.setData(page);
		return result;
	}

	/**
	 * 修改回复状态
	 */
	@Override
	public ServiceResult<SysReply> updateMessageType(SysMessagePara para) {
		ServiceResult<SysReply> result = new ServiceResult<SysReply>();
		Integer messageId = para.getMessageId();
		SysReply reply = new SysReply();
		reply.setReplyId(para.getReplyId());
		reply.setManagerId(para.getManagerId());
		reply.setReplyContent(para.getReplyContent());
		reply.setCreateTime(new Date());
		// Date date = new Date();
		// SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		// String stirngDate = format.format(date);
		// Date parseDate;
		// try {
		// parseDate = format.parse(stirngDate);
		// reply.setCreateTime(parseDate);
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		// 添加回复表
		Integer num = sysReplyWriterMapper.insertSelective(reply);
		int replyId = reply.getReplyId();
		if (num == 1) {
			// 添加关系表
			SysMessageReply mr = new SysMessageReply();
			mr.setMessageReplyId(null);
			mr.setMessageId(messageId);
			mr.setReplyId(replyId);
			int messageReply = sysMessageReplyWriterMapper.insertSelective(mr);
			if (messageReply > 0) {
				// 添加回复成功，修改状态
				Integer info = sysMessageWriterMapper.updateMessageByKey(messageId);
				if (info > 0) {
					result.setStateCode(StateCodeConstant.SUCCESS_CODE);
					result.setMessage("修改成功");
					result.setData(reply);
				}
			}
		} else {
			result.setMessage(StateCodeConstant.ERROR_CODE);
			result.setMessage("修改失败");
		}
		return result;
	}

	/**
	 * 删除
	 */
	@Override
	public ServiceResult<Integer> delMessage(SysMessagePara para) {
		ServiceResult<Integer> result = new ServiceResult<Integer>();
		Integer messageId = para.getMessageId();
		// 拿到所要删除的回复表的id
		List<Object> replyId = sysMessageReplyReaderMapper.selectByMessageKey(messageId);
		// 删除中间表
		sysMessageReplyWriterMapper.deleteByPrimaryKey(messageId);
		// 删除回复表
		int num = sysReplyWriterMapper.deleteByPrimaryKey(replyId);
		if (num > 0) {
			int i = sysMessageWriterMapper.deleteByPrimaryKey(messageId);
			if (i > 0) {
				result.setStateCode(StateCodeConstant.SUCCESS_CODE);
				result.setMessage("删除成功");
			} else {
				result.setStateCode(StateCodeConstant.ERROR_CODE);
				result.setMessage("删除失败");
			}
		} else {
			result.setStateCode(StateCodeConstant.ERROR_CODE);
			result.setMessage("删除失败");
		}
		return result;
	}

	@Override
	public ServiceResult<Object> updateUserOpinion(Integer messageState, Integer userId, List<Integer> messageIds) {
		ServiceResult<Object> result = new ServiceResult<>();
		try {
			for (Integer messageId : messageIds) {
				sysMessageWriterMapper.updateUserOpinionByMessageId(userId, messageState, messageId);
			}
			result.setStateCode(StateCodeConstant.SUCCESS_CODE);
			if (4 == messageState) {
				result.setMessage("删除成功!");
			} else {
				result.setMessage("更新成功!");
			}
		} catch (Exception e) {
			result.setStateCode(StateCodeConstant.ERROR_CODE);
			if (4 == messageState) {
				result.setMessage("删除失败!");
			} else {
				result.setMessage("更新失败!");
			}
		}
		return result;
	}

	@Override
	public Integer selectMessageCount(Integer userId, Integer countType) {
		Integer opinionCount = 0;
		Integer errorCount = 0;
		if (countType == 1) {
			// 查询错误信息总数
			return sysMessageReaderMapper.selectErrorCount(userId);
		} else if (countType == 2) {
			// 查询意见反馈总数
			return sysMessageReaderMapper.selectOpinionCount(userId);
		} else {
			// 查询错误信息总数
			errorCount = sysMessageReaderMapper.selectErrorCount(userId);
			// 查询意见反馈总数
			opinionCount = sysMessageReaderMapper.selectOpinionCount(userId);
			// 查询所有总数
			return opinionCount + errorCount;
		}
	}

	@Override
	public ServiceResult<Object> updateAllUserOpinion(Integer messageType, Integer userId) {
		ServiceResult<Object> result = new ServiceResult<>();
		// 查询条件
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("messageType", messageType);
		map.put("userId", userId);
		// 说明该用户有纠错或反馈的信息
		List<SysMessage> messageList = sysMessageReaderMapper.selectAllUnreadMessage(map);
		try {
			for (SysMessage sysMessage : messageList) {
				sysMessageWriterMapper.updateUserOpinionByMessageId(userId, 3, sysMessage.getMessageId());
			}
			result.setStateCode(StateCodeConstant.SUCCESS_CODE);
			result.setMessage("更新成功!");
		} catch (Exception e) {
			result.setStateCode(StateCodeConstant.ERROR_CODE);
			result.setMessage("更新失败!");
		}

		return result;
	}

}
