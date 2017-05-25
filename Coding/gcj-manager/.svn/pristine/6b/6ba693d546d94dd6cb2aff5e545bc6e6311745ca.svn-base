package com.yd.gcj.controller.page;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yd.gcj.entity.YdMangerTaskMsg;
import com.yd.gcj.entity.vo.YdMangerUserVo;
import com.yd.gcj.service.YdMangerServiceTaskMsg;
import com.yd.gcj.tool.MapInitFactory;

@RestController
@RequestMapping(value = "/page/taskmsg", produces = { "application/json;charset=UTF-8" })
public class YdMangerControllerPageTaskMsg {

	@Autowired
	private YdMangerServiceTaskMsg serviceTaskMsg;

	/**
	 * 发布公告
	 * 
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public Object add(Integer taskId, String contents, HttpServletRequest request) {
		try {
			YdMangerUserVo userVo = (YdMangerUserVo) request.getSession().getAttribute("user");
			if (userVo != null) {
				YdMangerTaskMsg taskMsg = new YdMangerTaskMsg();
				taskMsg.setTaskmsg_contents(contents);
				taskMsg.setTaskmsg_tid(taskId);
				taskMsg.setTaskmsg_uid(userVo.getUser_id());
				return serviceTaskMsg.$insert(taskMsg);
			} else {
				return new MapInitFactory("600", "登录超时或者没有登录，请登录后再进行操作！").getMap();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new MapInitFactory().setSystemError().getMap();
		}
	}

	/**
	 * 修改公告内容
	 * 
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/updateMsg", method = RequestMethod.POST)
	public Object updateMsg(HashMap<String, Object> map) {

		return null;
	}

	/**
	 * 删除指定的公告信息
	 * 
	 * @param map
	 * @return
	 */
	public Object delete(HashMap<String, Object> map) {

		return null;
	}

}
