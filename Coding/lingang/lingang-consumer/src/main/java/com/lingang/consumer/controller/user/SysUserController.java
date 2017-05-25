package com.lingang.consumer.controller.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lingang.api.domain.basic.JsonResult;
import com.lingang.api.domain.basic.Page;
import com.lingang.api.domain.basic.ServiceResult;
import com.lingang.api.domain.basic.StateCodeConstant;
import com.lingang.api.domain.entity.SysCollect;
import com.lingang.api.domain.entity.SysHome;
import com.lingang.api.domain.entity.SysMessage;
import com.lingang.api.domain.entity.SysUser;
import com.lingang.api.domain.vo.SysDownloadVo;
import com.lingang.api.domain.vo.SysFileParkVo;
import com.lingang.api.domain.vo.SysReplyVo;
import com.lingang.api.domain.vo.SysUserVo;
import com.lingang.api.service.SysHomeService;
import com.lingang.api.service.SysMessageService;
import com.lingang.api.service.SysUserService;
import com.lingang.consumer.controller.BaseController;

/**
 * @Description: (APP用户信息控制)
 * @Author: lgl(lgl1012dream@foxmail.com)
 * @date:2016年12月1日 上午11:40:12
 * @Version:1.0
 */
@Controller
@RequestMapping("/user")
public class SysUserController extends BaseController {

	@Resource
	private SysUserService sysUserService;

	@Resource
	private SysMessageService sysMessageService;

	@Resource
	private SysHomeService sysHomeService;


	/**
	 * @Description: (登录)
	 * @param userAccount
	 * @param userPassword
	 * @return
	 * @author lgl(lgl1012dream@foxmail.com)
	 * @date: 2016年12月1日 上午11:47:55
	 */
	@ResponseBody
	@RequestMapping(value = "/userLogin", method = { RequestMethod.GET, RequestMethod.POST })
	public JsonResult userLogin(
			@RequestParam(value = "loginType", required = false, defaultValue = "1") Integer loginType,
			@RequestParam(value = "account", required = true) String userAccount,
			@RequestParam(value = "pwd", required = true) String userPassword,HttpServletRequest request) {
		JsonResult jsonResult = new JsonResult();
		// HttpSession session=request.getSession();
		ValueOperations<String, Object> valueops = redisTemplate.opsForValue();
		String redisPwd="";
		if(valueops.get("PWD_"+userAccount) !=null){
			redisPwd=(String) valueops.get("PWD_"+userAccount);
		}
		String gesturePwd="";
		if (loginType == 2) {
			gesturePwd=userPassword;
			userPassword=redisPwd;
		}
		// 登录
		ServiceResult<SysUserVo> result = sysUserService.selectSysUserLoginByUserAccount(loginType, userAccount,
				userPassword,gesturePwd);
		jsonResult.setStateCode(result.getStateCode());
		jsonResult.setMessage(result.getMessage());
		jsonResult.setData(result.getData());
		Map<String, Object> dataMap = new HashMap<String, Object>();
		if (jsonResult.getStateCode().equals(StateCodeConstant.SUCCESS_CODE)) {
			// 首页链接
			SysUserVo sysUserVo = (SysUserVo) jsonResult.getData();
			ServiceResult<SysHome> result2 = sysHomeService.selectSysHomeByUserId(sysUserVo.getUserId());
			dataMap.put("homeLink", result2.getData());
			// 缓存信息
			String token = getToken(jsonResult.getData(),request);
			dataMap.put("token", token);
			if(loginType==1 && (redisPwd==null || !redisPwd.equals(userPassword))){
				//密码本地存储
				valueops.set("PWD_"+userAccount, userPassword);
			}
			jsonResult.setDataMap(dataMap);
		}

		return jsonResult;
	}

	/**
	 * @Description: (设置解锁)
	 * @param newPwd
	 * @return
	 * @author lgl(lgl1012dream@foxmail.com)
	 * @date: 2016年12月3日 上午9:48:17
	 */
	@RequestMapping(value = "userUpdatePWD", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public JsonResult userUpdatePWD(@RequestParam(value = "newPwd", required = true) String newPwd,
			HttpServletRequest request) {
		JsonResult jsonResult = new JsonResult();
		SysUserVo sysUserVo = (SysUserVo) getTokenData(request);
		SysUser sysUser = new SysUser();
		sysUser.setUserId(sysUserVo.getUserId());
		sysUser.setGesturePwd(newPwd);
		ServiceResult<SysUserVo> result = sysUserService.updateSysUser(sysUser);
		jsonResult.setStateCode(result.getStateCode());
		jsonResult.setMessage(result.getMessage());
		jsonResult.setData(result.getData());
		// // 缓存信息
		// if (jsonResult.getStateCode().equals(StateCodeConstant.SUCCESS_CODE))
		// {
		// String token = getToken(jsonResult.getData(), request);
		// Map<String, Object> dataMap = new HashMap<String, Object>();
		// dataMap.put("token", token);
		// jsonResult.setDataMap(dataMap);
		// }
		return jsonResult;
	}

	/**
	 * @Description: (手势密码开关)
	 * @param gestureState
	 * @return
	 * @author lgl(lgl1012dream@foxmail.com)
	 * @date: 2016年12月3日 上午9:48:17
	 */
	@RequestMapping(value = "userUpdatePWDState", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public JsonResult userUpdatePWDState(@RequestParam(value = "gestureState", required = true) Integer gestureState,
			HttpServletRequest request) {
		JsonResult jsonResult = new JsonResult();
		SysUserVo sysUserVo = (SysUserVo) getTokenData(request);
		SysUser sysUser = new SysUser();
		sysUser.setUserId(sysUserVo.getUserId());
		sysUser.setGestureState(gestureState);
		ServiceResult<SysUserVo> result = sysUserService.updateSysUser(sysUser);
		jsonResult.setStateCode(result.getStateCode());
		jsonResult.setMessage(result.getMessage());
		jsonResult.setData(result.getData());
		return jsonResult;
	}

	/**
	 * @Description: (信息纠错/意见反馈列表)
	 * @param pageIndex
	 * @param pageSize
	 * @param messageType
	 * @return
	 * @author lgl(lgl1012dream@foxmail.com)
	 * @date: 2016年12月5日 下午10:56:44
	 */
	@ResponseBody
	@RequestMapping(value = "/messagePageList.do", method = { RequestMethod.GET, RequestMethod.POST })
	public JsonResult messagePageList(
			@RequestParam(value = "pageIndex", required = false, defaultValue = "1") Integer pageIndex,
			@RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
			@RequestParam(value = "messageType", required = true) Integer messageType, HttpServletRequest request) {
		JsonResult jsonResult = new JsonResult();
		// 当前登录的用户
		SysUserVo sysUserVo = (SysUserVo) getTokenData(request);

		ServiceResult<Page<SysMessage>> result = sysMessageService.selectMessagePageList(pageIndex, pageSize,
				messageType, sysUserVo.getUserId());

		jsonResult.setStateCode(result.getStateCode());
		jsonResult.setData(result.getData());
		return jsonResult;
	}

	/**
	 * @Description: (信息纠错/意见反馈消息总条数)
	 * @param request
	 * @return 参数注释
	 * @author fqh (qihao.fu@outlook.com)
	 * @date: 2016年12月21日 下午5:27:25
	 */
	@ResponseBody
	@RequestMapping(value = "/messageCount.do", method = { RequestMethod.GET, RequestMethod.POST })
	public JsonResult messageCount(HttpServletRequest request) {
		JsonResult jsonResult = new JsonResult();
		// 当前登录的用户
		SysUserVo sysUserVo = (SysUserVo) getTokenData(request);
		// 获取纠错总数
		Integer errorCount = sysMessageService.selectMessageCount(sysUserVo.getUserId(), 1);
		// 获取反馈总数
		Integer opinionCount = sysMessageService.selectMessageCount(sysUserVo.getUserId(), 2);
		// 获取总数
		Integer count = errorCount + opinionCount;
		Map<String, Object> map = new HashMap<>();
		map.put("count", count);
		map.put("errorCount", errorCount);
		map.put("opinionCount", opinionCount);
		jsonResult.setDataMap(map);
		jsonResult.setMessage("查询成功!");
		return jsonResult;
	}

	/**
	 * @Description: (信息纠错/意见反馈 回复详情)
	 * @param messageId
	 * @return
	 * @author lgl(lgl1012dream@foxmail.com)
	 * @date: 2016年12月6日 上午12:09:09
	 */
	@RequestMapping(value = "/messageDetails.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public JsonResult messageDetails(@RequestParam("messageId") Integer messageId) {
		JsonResult jsonResult = new JsonResult();
		ServiceResult<SysReplyVo> result = sysMessageService.selectSysReply(messageId);
		jsonResult.setStateCode(result.getStateCode());
		jsonResult.setData(result.getData());
		return jsonResult;
	}

	/**
	 * @Description: (我的收藏)
	 * @param pageIndex
	 * @param pageSize
	 * @param collectType
	 * @return
	 * @author lgl(lgl1012dream@foxmail.com)
	 * @date: 2016年12月6日 上午12:49:49
	 */
	@RequestMapping(value = "/userCollect.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public JsonResult userCollect(
			@RequestParam(value = "pageIndex", required = false, defaultValue = "1") Integer pageIndex,
			@RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
			@RequestParam(value = "collectType", required = false, defaultValue = "1") Integer collectType,
			HttpServletRequest request) {
		JsonResult jsonResult = new JsonResult();
		SysUserVo sysUserVo = (SysUserVo) getTokenData(request);
		ServiceResult<Object> result = sysUserService.selectUserCollect(pageIndex, pageSize, collectType,
				sysUserVo.getUserId());
		jsonResult.setStateCode(result.getStateCode());
		jsonResult.setData(result.getData());
		return jsonResult;
	}

	/**
	 * @Description: (我的下载)
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 * @author lgl(lgl1012dream@foxmail.com)
	 * @date: 2016年12月6日 上午2:20:25
	 */
	@RequestMapping(value = "/userDownload.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public JsonResult userDownload(
			@RequestParam(value = "pageIndex", required = false, defaultValue = "1") Integer pageIndex,
			@RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
			HttpServletRequest request) {
		JsonResult jsonResult = new JsonResult();
		SysUserVo sysUserVo = (SysUserVo) getTokenData(request);
		ServiceResult<Page<SysDownloadVo>> result = sysUserService.selectUserDownload(pageIndex, pageSize,
				sysUserVo.getUserId());
		jsonResult.setStateCode(result.getStateCode());
		jsonResult.setData(result.getData());
		return jsonResult;
	}

	/**
	 * @Description: (全部下载)
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 * @author lgl(lgl1012dream@foxmail.com)
	 * @date: 2016年12月6日 上午2:51:07
	 */
	@RequestMapping(value = "/allFile.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public JsonResult allFile(
			@RequestParam(value = "pageIndex", required = false, defaultValue = "1") Integer pageIndex,
			@RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
			HttpServletRequest request) {
		JsonResult jsonResult = new JsonResult();
		SysUserVo sysUserVo = (SysUserVo) getTokenData(request);
		ServiceResult<Page<SysFileParkVo>> result = sysUserService.selectAllFile(pageIndex, pageSize,
				sysUserVo.getUserId());
		jsonResult.setStateCode(result.getStateCode());
		jsonResult.setData(result.getData());
		return jsonResult;
	}

	/**
	 * @Description: (信息纠错/信息反馈)
	 * @param messageType
	 * @param messageContent
	 * @param linkType
	 * @return
	 * @author lgl(lgl1012dream@foxmail.com)
	 * @date: 2016年12月6日 上午4:56:57
	 */
	@RequestMapping(value = "/userOpinion.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public JsonResult userOpinion(@RequestParam(value = "messageType", required = true) Integer messageType,
			@RequestParam(value = "messageContent", required = true) String messageContent,
			@RequestParam(value = "linkType", required = false) Integer linkType,
			@RequestParam(value = "linkId", required = false) Integer linkId, HttpServletRequest request) {
		JsonResult jsonResult = new JsonResult();
		SysUserVo sysUserVo = (SysUserVo) getTokenData(request);
		if (linkType != null && linkType == 9) {
			linkId = sysUserVo.getUserId();
		}
		SysMessage message = new SysMessage();
		message.setUserId(sysUserVo.getUserId());
		message.setMessageType(messageType);
		message.setMessageContent(messageContent);
		message.setCreateTime(new Date());
		message.setMessageState(1);
		message.setLinkType(linkType);
		message.setLinkId(linkId);
		// 添加信息
		ServiceResult<Object> result = sysUserService.insertUserOpinion(message);
		jsonResult.setStateCode(result.getStateCode());
		jsonResult.setData(result.getData());
		return jsonResult;
	}

	/**
	 * @Description: (修改状态 信息纠错/信息反馈)
	 * @param messageIds
	 * @param request
	 * @return 参数注释
	 * @author fqh (qihao.fu@outlook.com)
	 * @date: 2016年12月20日 下午3:48:41
	 */
	@ResponseBody
	@RequestMapping(value = "/updateUserOpinion.do", method = { RequestMethod.GET, RequestMethod.POST })
	public JsonResult updateUserOpinion(@RequestParam("messageState") Integer messageState,
			@RequestParam("messageIds") String messageIdStr, HttpServletRequest request) {
		JsonResult jsonResult = new JsonResult();

		if (messageState > 4 || messageState < 1) {
			jsonResult.setStateCode(StateCodeConstant.ERROR_CODE);
			jsonResult.setMessage("参数有误!");
			return jsonResult;
		}

		SysUserVo sysUserVo = (SysUserVo) getTokenData(request);

		List<Integer> messageIds = null;
		if (!"".equals(messageIdStr) && null != messageIdStr) {
			String[] str = org.apache.commons.lang3.StringUtils.split(messageIdStr, ",");
			messageIds = new ArrayList<>();
			for (String industryId : str) {
				messageIds.add(Integer.parseInt(industryId));
			}
		}
		ServiceResult<Object> result = sysMessageService.updateUserOpinion(messageState, sysUserVo.getUserId(),
				messageIds);
		jsonResult.setStateCode(result.getStateCode());
		jsonResult.setMessage(result.getMessage());
		return jsonResult;
	}

	/**
	 * @Description: (所有消息修改成已读)
	 * @param messageType
	 * @param request
	 * @return 参数注释
	 * @author fqh (qihao.fu@outlook.com)
	 * @date: 2016年12月27日 下午3:39:44
	 */
	@ResponseBody
	@RequestMapping(value = "/updateAllUserOpinion.do", method = { RequestMethod.GET, RequestMethod.POST })
	public JsonResult updateAllUserOpinion(@RequestParam(value = "messageType", required = false) Integer messageType,
			HttpServletRequest request) {
		JsonResult jsonResult = new JsonResult();
		// 当前登录的用户
		SysUserVo sysUserVo = (SysUserVo) getTokenData(request);

		ServiceResult<Object> result = sysMessageService.updateAllUserOpinion(messageType, sysUserVo.getUserId());
		jsonResult.setStateCode(result.getStateCode());
		jsonResult.setMessage(result.getMessage());
		return jsonResult;
	}

	/**
	 * @Description: (首页链接表)
	 * @return
	 * @author lgl(lgl1012dream@foxmail.com)
	 * @date: 2016年12月7日 下午11:22:30
	 */
	@ResponseBody
	@RequestMapping(value = "/homeLinkList", method = { RequestMethod.GET, RequestMethod.POST })
	public JsonResult homeLinkList(HttpServletRequest request) {
		JsonResult jsonResult = new JsonResult();
		SysUserVo sysUserVo = (SysUserVo) getTokenData(request);
		ServiceResult<SysHome> result = sysHomeService.selectSysHomeByUserId(sysUserVo.getUserId());
		jsonResult.setStateCode(result.getStateCode());
		jsonResult.setData(result.getData());
		return jsonResult;
	}

	/**
	 * @Description: (首页链接调整顺序)
	 * @return
	 * @author lgl(lgl1012dream@foxmail.com)
	 * @date: 2016年12月7日 下午11:50:42
	 */
	@RequestMapping(value = "/homeLinkUpdate.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public JsonResult homeLinkUpdate(@RequestParam(value = "homeId", required = true) Integer homeId,
			@RequestParam(value = "homeName", required = true) String homeName) {
		JsonResult jsonResult = new JsonResult();
		SysHome home = new SysHome();
		home.setHomeId(homeId);
		home.setHomeName(homeName);
		ServiceResult<SysHome> result = sysHomeService.updateSysHome(home);
		jsonResult.setStateCode(result.getStateCode());
		jsonResult.setMessage(result.getMessage());
		jsonResult.setData(result.getData());
		return jsonResult;
	}

	/**
	 * @Description: (添加收藏)
	 * @return
	 * @author lgl(lgl1012dream@foxmail.com)
	 * @date: 2016年12月10日 上午11:53:24
	 */
	@RequestMapping(value = "/addUserCollect.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public JsonResult addUserCollect(@RequestParam(value = "collectType", required = true) Integer collectType,
			@RequestParam(value = "objId", required = true) Integer objId, HttpServletRequest request) {
		JsonResult jsonResult = new JsonResult();
		SysUserVo sysUserVo = (SysUserVo) getTokenData(request);
		SysCollect collect = new SysCollect();
		collect.setUserId(sysUserVo.getUserId());
		collect.setCollectType(collectType);
		collect.setObjId(objId);
		collect.setCreateTime(new Date());
		ServiceResult<Object> result = sysUserService.insertSysCollect(collect);
		jsonResult.setStateCode(result.getStateCode());
		jsonResult.setMessage(result.getMessage());
		jsonResult.setData(result.getData());
		return jsonResult;
	}

	/**
	 * @Description: (取消收藏)
	 * @return
	 * @author lgl(lgl1012dream@foxmail.com)
	 * @date: 2016年12月10日 上午11:53:24
	 */
	@RequestMapping(value = "/delUserCollect.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public JsonResult delUserCollect(@RequestParam(value = "collectId", required = true) Integer collectId) {
		JsonResult jsonResult = new JsonResult();
		ServiceResult<Object> result = sysUserService.delSysCollect(collectId);
		jsonResult.setStateCode(result.getStateCode());
		jsonResult.setMessage(result.getMessage());
		jsonResult.setData(result.getData());
		return jsonResult;
	}

	// /**
	// * @Description: (首页链接表,“已废弃”)
	// * @param pageIndex
	// * @param pageSize
	// * @param request
	// * @return
	// * @author gsh(15136390655@163.com)
	// * @date: 2016年12月6日 上午3:37:56
	// */
	// @RequestMapping(value="/homePageList",method={RequestMethod.GET,RequestMethod.POST})
	// @ResponseBody
	// public JsonResult homePageList(@RequestParam(value = "pageIndex",
	// required = false,defaultValue="1") Integer pageIndex,
	// @RequestParam(value = "pageSize", required =
	// false,defaultValue="10")Integer pageSize,
	// HttpServletRequest request){
	// JsonResult jsonResult = new JsonResult();
	// ServiceResult<Page<SysHomeVo>> result =
	// sysHomeService.selectSysNewsAll(pageIndex, pageSize);
	// jsonResult.setStateCode(result.getStateCode());
	// jsonResult.setData(result.getData());
	// return jsonResult;
	// };

}
