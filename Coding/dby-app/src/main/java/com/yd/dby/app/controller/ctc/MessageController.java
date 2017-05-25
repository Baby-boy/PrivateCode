package com.yd.dby.app.controller.ctc;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yd.dby.app.basic.Page;
import com.yd.dby.app.common.HttpCode;
import com.yd.dby.app.common.JsonResult;
import com.yd.dby.app.common.ServiceResult;
import com.yd.dby.app.controller.BaseController;
import com.yd.dby.app.entity.YdMessage;
import com.yd.dby.app.entity.YdUser;
import com.yd.dby.app.entity.para.BasePara;
import com.yd.dby.app.entity.para.CtcMessagePara;
import com.yd.dby.app.entity.vo.CtcMessageListVo;
import com.yd.dby.app.entity.vo.MessageVo;
import com.yd.dby.app.service.YdMessageService;

@RestController
@RequestMapping("message")
public class MessageController extends BaseController {

	@Autowired
	private YdMessageService ydMessageService;

	/**
	 * 说明: 懒鱼商品买家留言
	 * 
	 * @param para(请求参数)
	 * @param request
	 * @return
	 * @throws Exception
	 * @author fqh(fqh@zwzyd.com)
	 * @date: 2017年2月21日 下午6:34:48
	 */
	@RequestMapping(value = "leaveMessage", method = RequestMethod.POST)
	public JsonResult addUserLeaveMessage(@RequestBody CtcMessagePara para, HttpServletRequest request) throws Exception {
		JsonResult jsonResult = new JsonResult();

		// 获取当前用户
		YdUser user = (YdUser) redisGet(request.getHeader("token") + "User", YdUser.class);

		// 判断是否是该用户给自己评论
		if (para.getMessageReceiveId() == user.getUserId()) {
			jsonResult.setMsg("禁止本人留言!");
			jsonResult.setStatusCode(HttpCode.FORBIDDEN);
			return jsonResult;
		}

		ServiceResult<Object> result = ydMessageService.insertUserLeaveMessage(para, user);
		jsonResult.setMsg(result.getMsg());
		jsonResult.setStatusCode(result.getStatusCode());
		return jsonResult;
	}

	/**
	 * 说明: 消息中心
	 * 
	 * @param para(请求参数)
	 * @param request
	 * @return
	 * @throws Exception
	 * @author fqh(fqh@zwzyd.com)
	 * @date: 2017年2月22日 上午10:29:53
	 */
	@RequestMapping(value = "messageCenter", method = RequestMethod.POST)
	public JsonResult queryUserMessageCenter(@RequestBody BasePara para, HttpServletRequest request) throws Exception {
		JsonResult jsonResult = new JsonResult();

		// 获取当前用户
		YdUser user = (YdUser) redisGet(request.getHeader("token") + "User", YdUser.class);

		ServiceResult<Page<MessageVo>> result = ydMessageService.selectUserMessageCenter(para, user);
		jsonResult.setMsg(result.getMsg());
		jsonResult.setData(result.getData());
		jsonResult.setStatusCode(result.getStatusCode());
		return jsonResult;
	}

	/**
	 * 说明: 更新用户消息状态
	 * 
	 * @param ydMessage(请求参数)
	 * @return
	 * @author fqh(fqh@zwzyd.com)
	 * @date: 2017年2月22日 下午2:48:43
	 */
	@RequestMapping(value = "updateMessageState", method = RequestMethod.POST)
	public JsonResult updateUserMessageState(@RequestBody YdMessage ydMessage) {
		JsonResult jsonResult = new JsonResult();

		ServiceResult<Object> result = ydMessageService.updateUserMessageState(ydMessage);
		jsonResult.setMsg(result.getMsg());
		jsonResult.setStatusCode(result.getStatusCode());
		return jsonResult;
	}

	/**
	 * 说明: 懒鱼商品回复信息
	 * 
	 * @param para(请求参数)
	 * @param request
	 * @return
	 * @throws Exception
	 * @author fqh(fqh@zwzyd.com)
	 * @date: 2017年2月22日 下午3:42:05
	 */
	@RequestMapping(value = "replyMessage", method = RequestMethod.POST)
	public JsonResult addUserReplyMessage(@RequestBody CtcMessagePara para, HttpServletRequest request) throws Exception {
		JsonResult jsonResult = new JsonResult();

		// 获取当前用户
		YdUser user = (YdUser) redisGet(request.getHeader("token") + "User", YdUser.class);

		ServiceResult<Object> result = ydMessageService.insertUserReplyMessage(para, user);
		jsonResult.setMsg(result.getMsg());
		jsonResult.setStatusCode(result.getStatusCode());
		return jsonResult;
	}

	/**
	 * 说明: 懒鱼商品全部留言
	 * 
	 * @param para
	 * @return
	 * @author fqh(fqh@zwzyd.com)
	 * @date: 2017年2月23日 上午10:53:38
	 */
	@RequestMapping(value = "ctcMessagePageList", method = RequestMethod.POST)
	public JsonResult queryCtcMessagePageList(@RequestBody CtcMessagePara para) {
		JsonResult jsonResult = new JsonResult();

		ServiceResult<Page<CtcMessageListVo>> result = ydMessageService.selectCtcMessagePageList(para);
		jsonResult.setMsg(result.getMsg());
		jsonResult.setData(result.getData());
		jsonResult.setStatusCode(result.getStatusCode());
		return jsonResult;
	}
}
