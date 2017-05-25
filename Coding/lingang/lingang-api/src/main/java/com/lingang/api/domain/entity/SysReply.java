package com.lingang.api.domain.entity;

import java.io.Serializable;
import java.util.Date;

public class SysReply implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 7538644418252349632L;

	private Integer replyId;

    private Integer managerId;

    private Date createTime;

    private String replyContent;

    public Integer getReplyId() {
        return replyId;
    }

    public void setReplyId(Integer replyId) {
        this.replyId = replyId;
    }

    public Integer getManagerId() {
        return managerId;
    }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getReplyContent() {
        return replyContent;
    }

    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent == null ? null : replyContent.trim();
    }
}