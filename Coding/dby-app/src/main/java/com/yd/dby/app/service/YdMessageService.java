package com.yd.dby.app.service;

import org.springframework.transaction.annotation.Transactional;

import com.yd.dby.app.basic.Page;
import com.yd.dby.app.common.ServiceResult;
import com.yd.dby.app.entity.YdMessage;
import com.yd.dby.app.entity.YdUser;
import com.yd.dby.app.entity.para.BasePara;
import com.yd.dby.app.entity.para.CtcMessagePara;
import com.yd.dby.app.entity.vo.CtcMessageListVo;
import com.yd.dby.app.entity.vo.MessageVo;

public interface YdMessageService {

	/**
	 * 说明: 买家留言
	 * 
	 * @param para
	 * @param user
	 * @return
	 * @author fqh(fqh@zwzyd.com)
	 * @date: 2017年2月21日 下午6:57:20
	 */
	@Transactional
	public ServiceResult<Object> insertUserLeaveMessage(CtcMessagePara para, YdUser user);

	/**
	 * 说明: 用户消息中心
	 * 
	 * @param para
	 * @param user
	 * @return
	 * @author fqh(fqh@zwzyd.com)
	 * @date: 2017年2月22日 上午11:17:58
	 */
	public ServiceResult<Page<MessageVo>> selectUserMessageCenter(BasePara para, YdUser user);

	/**
	 * 说明: 更新用户消息状态
	 * 
	 * @param ydMessage
	 * @return
	 * @author fqh(fqh@zwzyd.com)
	 * @date: 2017年2月22日 下午2:49:03
	 */
	@Transactional
	public ServiceResult<Object> updateUserMessageState(YdMessage ydMessage);

	/**
	 * 说明: 回复信息
	 * 
	 * @param para
	 * @param user
	 * @return
	 * @author fqh(fqh@zwzyd.com)
	 * @date: 2017年2月22日 下午3:41:40
	 */
	@Transactional
	public ServiceResult<Object> insertUserReplyMessage(CtcMessagePara para, YdUser user);

	/**
	 * 说明: 懒鱼商品全部留言
	 * 
	 * @param para
	 * @return
	 * @author fqh(fqh@zwzyd.com)
	 * @date: 2017年2月23日 下午1:16:40
	 */
	public ServiceResult<Page<CtcMessageListVo>> selectCtcMessagePageList(CtcMessagePara para);

}
