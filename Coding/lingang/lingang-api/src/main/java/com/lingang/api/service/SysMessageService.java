package com.lingang.api.service;

import java.util.List;

import com.lingang.api.domain.basic.Page;
import com.lingang.api.domain.basic.ServiceResult;
import com.lingang.api.domain.entity.SysMessage;
import com.lingang.api.domain.entity.SysReply;
import com.lingang.api.domain.para.SysMessagePara;
import com.lingang.api.domain.pfvo.SysMessagePfvo;
import com.lingang.api.domain.vo.SysReplyVo;

public interface SysMessageService {

	/**
	 * @Description: (信息纠错/意见反馈列表)
	 * @param pageIndex
	 * @param pageSize
	 * @param messageType
	 * @return
	 * @author lgl(lgl1012dream@foxmail.com)
	 * @param userId
	 * @date: 2016年12月5日 下午11:02:20
	 */
	public ServiceResult<Page<SysMessage>> selectMessagePageList(Integer pageIndex, Integer pageSize,
			Integer messageType, Integer userId);

	/**
	 * @Description: (信息纠错/意见反馈 回复详情)
	 * @param messageId
	 * @return
	 * @author lgl(lgl1012dream@foxmail.com)
	 * @date: 2016年12月6日 上午12:13:38
	 */
	public ServiceResult<SysReplyVo> selectSysReply(Integer messageId);

	/**
	 * @Description: (信息纠错/意见反馈列表(后台))
	 * @param para
	 * @return
	 * @author gsh(15136390655@163.com)
	 * @date: 2016年12月8日 下午3:31:44
	 */
	public ServiceResult<Page<SysMessagePfvo>> selectMessageAll(SysMessagePara para);

	/**
	 * @Description: (修改恢复状态)
	 * @param messageType
	 * @return
	 * @author gsh(15136390655@163.com)
	 * @date: 2016年12月9日 上午1:39:10
	 */
	public ServiceResult<SysReply> updateMessageType(SysMessagePara para);

	/**
	 * @Description: (删除)
	 * @param para
	 * @return
	 * @author gsh(15136390655@163.com)
	 * @date: 2016年12月9日 下午5:34:35
	 */
	public ServiceResult<Integer> delMessage(SysMessagePara para);

	/**
	 * @Description: (根据ID修改意见反馈和错误信息)
	 * @param messageState
	 * @param userId
	 * @param messageIds
	 * @return 参数注释
	 * @author fqh (qihao.fu@outlook.com)
	 * @date: 2016年12月20日 下午5:52:32
	 */
	public ServiceResult<Object> updateUserOpinion(Integer messageState, Integer userId, List<Integer> messageIds);

	/**
	 * @Description: ((根据当前用户的id,查询当前用户的消息信息))
	 * @param userId
	 * @param countType
	 * @return 参数注释
	 * @author fqh (qihao.fu@outlook.com)
	 * @date: 2016年12月20日 下午7:38:54
	 */
	public Integer selectMessageCount(Integer userId, Integer countType);

	/**
	 * @Description: (所有消息修改成已读)
	 * @param messageType
	 * @param userId
	 * @return 参数注释
	 * @author fqh (qihao.fu@outlook.com)
	 * @date: 2016年12月27日 下午3:34:57
	 */
	public ServiceResult<Object> updateAllUserOpinion(Integer messageType, Integer userId);

}
