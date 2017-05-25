package com.lingang.api.domain.para;

public class SysMessagePara extends BasePara {

	private static final long serialVersionUID = -8330675148623629523L;

	private Integer messageType;
	private Integer linkType;
	private Integer messageState;
	private Integer managerId;
	private String replyContent;
	private Integer messageId;
	private Integer replyId;

	public Integer getMessageType() {
		return messageType;
	}

	public void setMessageType(Integer messageType) {
		this.messageType = messageType;
	}

	public Integer getLinkType() {
		return linkType;
	}

	public void setLinkType(Integer linkType) {
		this.linkType = linkType;
	}

	public Integer getMessageState() {
		return messageState;
	}

	public void setMessageState(Integer messageState) {
		this.messageState = messageState;
	}

	public Integer getManagerId() {
		return managerId;
	}

	public void setManagerId(Integer managerId) {
		this.managerId = managerId;
	}

	public String getReplyContent() {
		return replyContent;
	}

	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}

	public Integer getMessageId() {
		return messageId;
	}

	public void setMessageId(Integer messageId) {
		this.messageId = messageId;
	}

	public Integer getReplyId() {
		return replyId;
	}

	public void setReplyId(Integer replyId) {
		this.replyId = replyId;
	}

}
