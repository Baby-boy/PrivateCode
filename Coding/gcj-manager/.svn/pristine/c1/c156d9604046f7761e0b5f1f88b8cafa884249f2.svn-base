package com.yd.gcj.controller.page;

import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yd.gcj.entity.YdMangerMessage;
import com.yd.gcj.entity.vo.YdMangerUserVo;
import com.yd.gcj.service.YdMangerServiceMessage;
import com.yd.gcj.tool.MapInitFactory;

@RestController
@RequestMapping(value = "/page/message", produces = { "application/json;charset=UTF-8" })
public class YdMangerControllerPageMessage {

	@Autowired
	private YdMangerServiceMessage ydMangerServiceMessage;

	/***
	 * 按条件查询相应消息信息数量
	 * 
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/queryCountNumByUserId", method = RequestMethod.POST)
	Object queryCountNumByUserId(HashMap<String, Object> map) {
		return ydMangerServiceMessage.$queryCountNumByUserId(map);
	}

	/***
	 * 添加新消息
	 * 
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	Object insert(Integer type, Integer userBId, String contents, Integer taskId, HttpServletRequest request) {
		try {
			YdMangerMessage message = new YdMangerMessage();
			YdMangerUserVo userVo = (YdMangerUserVo) request.getSession().getAttribute("user");
			if (userVo != null) {
				message.setMsg_said(userVo.getUser_id());
			}
			message.setMsg_type(type);
			message.setMsg_sbid(userBId);
			message.setMsg_tid(taskId);
			message.setMsg_contents(contents);
			message.setMsg_isdel(0);
			message.setMsg_create_time(new Date());

			return ydMangerServiceMessage.$insert(message);
		} catch (Exception e) {
			e.printStackTrace();
			return new MapInitFactory().setSystemError().getMap();
		}
	}

	@RequestMapping(value = "/invite", method = RequestMethod.POST)
	Object invite(Integer userBId, Integer taskId, String contents, HttpServletRequest request) {
		try {
			YdMangerUserVo userVo = (YdMangerUserVo) request.getSession().getAttribute("user");
			if (userVo != null) {
				YdMangerMessage message = new YdMangerMessage();
				message.setMsg_said(userVo.getUser_id());
				message.setMsg_type(1);
				message.setMsg_sbid(userBId);
				message.setMsg_tid(taskId);
				message.setMsg_contents(contents);
				return ydMangerServiceMessage.$insert(message);
			} else {
				return new MapInitFactory("600", "登录超时或者没有登录，请登录后再进行操作！").getMap();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new MapInitFactory().setSystemError().getMap();
		}
	}

	/***
	 * 删除指定消息
	 * 
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	Object delete(HashMap<String, Object> map) {
		try {
			return ydMangerServiceMessage.$delete(map);
		} catch (Exception e) {
			e.printStackTrace();
			return new MapInitFactory().setSystemError().getMap();
		}
	}

}
