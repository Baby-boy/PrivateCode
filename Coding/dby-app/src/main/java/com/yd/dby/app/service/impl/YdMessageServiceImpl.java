package com.yd.dby.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yd.dby.app.basic.Page;
import com.yd.dby.app.common.HttpCode;
import com.yd.dby.app.common.ServiceResult;
import com.yd.dby.app.entity.YdCtc;
import com.yd.dby.app.entity.YdMessage;
import com.yd.dby.app.entity.YdMessageCtc;
import com.yd.dby.app.entity.YdUser;
import com.yd.dby.app.entity.para.BasePara;
import com.yd.dby.app.entity.para.CtcMessagePara;
import com.yd.dby.app.entity.vo.CtcMessageListVo;
import com.yd.dby.app.entity.vo.MessageVo;
import com.yd.dby.app.mapper.YdCtcMapper;
import com.yd.dby.app.mapper.YdMessageCtcMapper;
import com.yd.dby.app.mapper.YdMessageMapper;
import com.yd.dby.app.mapper.YdUserMapper;
import com.yd.dby.app.service.YdMessageService;

@Service("ydMessageService")
public class YdMessageServiceImpl implements YdMessageService {

	@Autowired
	private YdMessageMapper ydMessageMapper;

	@Autowired
	private YdUserMapper ydUserMapper;

	@Autowired
	private YdMessageCtcMapper ydMessageCtcMapper;

	@Autowired
	private YdCtcMapper ydCtcMapper;

	@Override
	public ServiceResult<Object> insertUserLeaveMessage(CtcMessagePara para, YdUser user) {
		ServiceResult<Object> result = new ServiceResult<>();

		// 根据当前用户ID查询该用户的信息
		YdUser authorUser = ydUserMapper.selectByPrimaryKey(user.getUserId());
		if (null == authorUser) {
			result.setMsg("用户身份异常!");
			result.setStatusCode(HttpCode.FORBIDDEN);
			return result;
		}

		// 根据商品ID查询商品信息
		YdCtc ydCtc = ydCtcMapper.selectByPrimaryKey(para.getCtcId());
		if (null == ydCtc) {
			result.setMsg("查无此商品!");
			result.setStatusCode(HttpCode.FORBIDDEN);
			return result;
		}

		// 通过商品发布人ID查询发布人的信息
		YdUser receiveUser = ydUserMapper.selectByPrimaryKey(ydCtc.getUserId());
		if (null == receiveUser) {
			result.setMsg("用户身份异常!");
			result.setStatusCode(HttpCode.FORBIDDEN);
			return result;
		}

		// 查询该商品信息是否已经保存了一份
		YdMessageCtc ydMessageCtc = ydMessageCtcMapper.selectMessageCtcByCtcId(para.getCtcId());
		if (null == ydMessageCtc) {
			// 插入商品信息到message_ctc表,创建商品对象
			ydMessageCtc = new YdMessageCtc();
			ydMessageCtc.setMessageCtcId(null);// 防止恶意传参
			ydMessageCtc.setCtcId(ydCtc.getCtcId());
			ydMessageCtc.setCtcImg(ydCtc.getCtcCover());
			ydMessageCtc.setCtcName(ydCtc.getCtcName());
			ydMessageCtc.setCtcPrice(ydCtc.getCtcPrice());
			ydMessageCtc.setCtcSummary(ydCtc.getCtcSummary());
			ydMessageCtcMapper.insertSelective(ydMessageCtc);// 插入成功后需要返回一个messageCtcId作为消息的一个外键关联
		} else {
			ydMessageCtc.setCtcImg(ydCtc.getCtcCover());
			ydMessageCtc.setCtcName(ydCtc.getCtcName());
			ydMessageCtc.setCtcPrice(ydCtc.getCtcPrice());
			ydMessageCtc.setCtcSummary(ydCtc.getCtcSummary());
			ydMessageCtcMapper.updateByPrimaryKeySelective(ydMessageCtc);// 把最新的懒鱼商品信息持久化
		}

		// 插入留言内容到message表,创建message对象
		YdMessage ydMessage = new YdMessage();
		ydMessage.setMessageId(null);
		ydMessage.setMessageObjId(ydMessageCtc.getMessageCtcId());// 生成的商品信息外键
		ydMessage.setMessageState(1);// 未读
		ydMessage.setMessageType(2);// 懒鱼
		ydMessage.setMessageAuthorId(authorUser.getUserId());
		ydMessage.setMessageAuthorImg(authorUser.getUserAvatar());
		ydMessage.setMessageAuthorName(authorUser.getUserNickname());
		ydMessage.setMessageContent(para.getMessageContent());
		ydMessage.setMessageFartherId(0);
		ydMessage.setMessageReceiveId(receiveUser.getUserId());
		ydMessage.setMessageReceiveImg(receiveUser.getUserAvatar());
		ydMessage.setMessageReceiveName(receiveUser.getUserNickname());
		ydMessage.setCreateDate(Long.valueOf(System.currentTimeMillis()).toString());
		int ydMessageInfo = ydMessageMapper.insertSelective(ydMessage);

		// 留言成功后,更新留言总数
		ydCtcMapper.updateCtcTotalMessage(para.getCtcId());

		if (ydMessageInfo == 1) {
			result.setMsg("留言成功!");
			result.setStatusCode(HttpCode.OK);
		} else {
			result.setMsg("留言失败!");
			result.setStatusCode(HttpCode.INTERNAL_SERVER_ERROR);
		}

		return result;
	}

	@Override
	public ServiceResult<Page<MessageVo>> selectUserMessageCenter(BasePara para, YdUser user) {
		ServiceResult<Page<MessageVo>> result = new ServiceResult<>();

		int countRecord = ydMessageMapper.selectUserMessageCenterCount(user.getUserId());
		Page<MessageVo> page = new Page<>(para.getCurrentPage(), countRecord, para.getOnePageCount());
		if (para.getCurrentPage() <= page.getCountPage()) {
			para.setStartIndex(page.getStartIndex());
			List<MessageVo> list = ydMessageMapper.selectUserMessageCenterPageList(para, user.getUserId());
			page.setList(list);
		}

		result.setData(page);
		result.setMsg("查询成功!");
		result.setStatusCode(HttpCode.OK);

		return result;
	}

	@Override
	public ServiceResult<Object> updateUserMessageState(YdMessage ydMessage) {
		ServiceResult<Object> result = new ServiceResult<>();

		int info = ydMessageMapper.updateByPrimaryKeySelective(ydMessage);
		if (1 == info) {
			result.setMsg("更新成功!");
			result.setStatusCode(HttpCode.OK);
		} else {
			result.setMsg("更新失败!");
			result.setStatusCode(HttpCode.INTERNAL_SERVER_ERROR);
		}

		return result;
	}

	@Override
	public ServiceResult<Object> insertUserReplyMessage(CtcMessagePara para, YdUser user) {
		ServiceResult<Object> result = new ServiceResult<>();

		// 根据当前用户ID查询该用户的信息
		YdUser authorUser = ydUserMapper.selectByPrimaryKey(user.getUserId());
		if (null == authorUser) {
			result.setMsg("用户身份异常!");
			result.setStatusCode(HttpCode.FORBIDDEN);
			return result;
		}

		YdUser receiveUser = ydUserMapper.selectByPrimaryKey(para.getMessageReceiveId());
		if (null == receiveUser) {
			result.setMsg("用户身份异常!");
			result.setStatusCode(HttpCode.FORBIDDEN);
			return result;
		}

		YdMessage ydMessage = ydMessageMapper.selectByPrimaryKey(para.getMessageId());

		// 需要提前对消息的状态进行更新,更新为已回复状态
		ydMessage.setMessageState(3);
		ydMessageMapper.updateByPrimaryKeySelective(ydMessage);

		if (0 != ydMessage.getMessageFartherId()) {
			// 查询这条信息的发起者是哪位用户
			ydMessage = ydMessageMapper.selectByPrimaryKey(ydMessage.getMessageFartherId());
			if (ydMessage.getMessageAuthorId() == user.getUserId()) {
				// 说明是买家回复
				ydMessage.setMessageFartherId(ydMessage.getMessageId());// 设置消息的父ID
				ydMessage.setMessageId(null);
				ydMessage.setMessageState(1);// 未读
				ydMessage.setMessageAuthorId(authorUser.getUserId());
				ydMessage.setMessageAuthorImg(authorUser.getUserAvatar());
				ydMessage.setMessageAuthorName(authorUser.getUserNickname());
				ydMessage.setMessageContent(authorUser.getUserNickname() + ": " + para.getMessageContent());
				ydMessage.setMessageReceiveId(receiveUser.getUserId());
				ydMessage.setMessageReceiveName(receiveUser.getUserNickname());
				ydMessage.setMessageReceiveImg(receiveUser.getUserAvatar());
				ydMessage.setCreateDate(Long.valueOf(System.currentTimeMillis()).toString());
				int info = ydMessageMapper.insertSelective(ydMessage);
				if (1 == info) {
					result.setMsg("回复成功!");
					result.setStatusCode(HttpCode.OK);
				} else {
					result.setMsg("回复失败!");
					result.setStatusCode(HttpCode.INTERNAL_SERVER_ERROR);
				}
			} else {
				// 说明是卖家回复
				ydMessage.setMessageFartherId(ydMessage.getMessageId());// 设置消息的父ID
				ydMessage.setMessageId(null);
				ydMessage.setMessageState(1);// 未读
				ydMessage.setMessageAuthorId(authorUser.getUserId());
				ydMessage.setMessageAuthorImg(authorUser.getUserAvatar());
				ydMessage.setMessageAuthorName(authorUser.getUserNickname());
				ydMessage.setMessageContent("卖家回复: " + para.getMessageContent());
				ydMessage.setMessageReceiveId(receiveUser.getUserId());
				ydMessage.setMessageReceiveName(receiveUser.getUserNickname());
				ydMessage.setMessageReceiveImg(receiveUser.getUserAvatar());
				ydMessage.setCreateDate(Long.valueOf(System.currentTimeMillis()).toString());
				int info = ydMessageMapper.insertSelective(ydMessage);
				if (1 == info) {
					result.setMsg("回复成功!");
					result.setStatusCode(HttpCode.OK);
				} else {
					result.setMsg("回复失败!");
					result.setStatusCode(HttpCode.INTERNAL_SERVER_ERROR);
				}
			}
		} else {
			// 说明是卖家回复(这里只会执行一次)
			ydMessage.setMessageId(null);
			ydMessage.setMessageState(1);// 未读
			ydMessage.setMessageFartherId(para.getMessageId());
			ydMessage.setMessageAuthorId(authorUser.getUserId());
			ydMessage.setMessageAuthorImg(authorUser.getUserAvatar());
			ydMessage.setMessageAuthorName(authorUser.getUserNickname());
			ydMessage.setMessageContent("卖家回复: " + para.getMessageContent());
			ydMessage.setMessageReceiveId(receiveUser.getUserId());
			ydMessage.setMessageReceiveName(receiveUser.getUserNickname());
			ydMessage.setMessageReceiveImg(receiveUser.getUserAvatar());
			ydMessage.setCreateDate(Long.valueOf(System.currentTimeMillis()).toString());
			int info = ydMessageMapper.insertSelective(ydMessage);
			if (1 == info) {
				result.setMsg("回复成功!");
				result.setStatusCode(HttpCode.OK);
			} else {
				result.setMsg("回复失败!");
				result.setStatusCode(HttpCode.INTERNAL_SERVER_ERROR);
			}
		}

		return result;
	}

	@Override
	public ServiceResult<Page<CtcMessageListVo>> selectCtcMessagePageList(CtcMessagePara para) {
		ServiceResult<Page<CtcMessageListVo>> result = new ServiceResult<>();

		int countRecord = ydMessageMapper.selectCtcMessagePageListCount(para);
		Page<CtcMessageListVo> page = new Page<>(para.getCurrentPage(), countRecord, para.getOnePageCount());
		if (para.getCurrentPage() <= page.getCountPage()) {
			para.setStartIndex(page.getStartIndex());
			List<CtcMessageListVo> list = ydMessageMapper.selectCtcMessagePageList(para);
			page.setList(list);
		}
		result.setMsg("查询成功!");
		result.setData(page);
		result.setStatusCode(HttpCode.OK);

		return result;
	}

}
