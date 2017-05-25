package com.yd.dby.app.entity.vo;

import java.util.ArrayList;
import java.util.List;

public class CtcMessageListVo {

	private Integer messageAuthorId;// 消息创建者ID

	private String messageAuthorName;// 消息创建者名称

	private String messageAuthorImg;// 消息创建者头像

	private String messageContent;// 消息内容

	private String createDate;// 创建时间

	private List<CtcMessageListTwoVo> twoMessageList = new ArrayList<>();

	public Integer getMessageAuthorId() {
		return messageAuthorId;
	}

	public void setMessageAuthorId(Integer messageAuthorId) {
		this.messageAuthorId = messageAuthorId;
	}

	public String getMessageAuthorName() {
		return messageAuthorName;
	}

	public void setMessageAuthorName(String messageAuthorName) {
		this.messageAuthorName = messageAuthorName;
	}

	public String getMessageAuthorImg() {
		return messageAuthorImg;
	}

	public void setMessageAuthorImg(String messageAuthorImg) {
		this.messageAuthorImg = messageAuthorImg;
	}

	public String getMessageContent() {
		return messageContent;
	}

	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public List<CtcMessageListTwoVo> getTwoMessageList() {
		return twoMessageList;
	}

	public void setTwoMessageList(List<CtcMessageListTwoVo> twoMessageList) {
		this.twoMessageList = twoMessageList;
	}

}
