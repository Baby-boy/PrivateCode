package com.yd.gcj.controller.page;

import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yd.gcj.entity.YdMangerTaskActive;
import com.yd.gcj.entity.vo.YdMangerUserVo;
import com.yd.gcj.service.YdMangerServiceTaskActive;
import com.yd.gcj.tool.MapInitFactory;

@RestController
@RequestMapping(value = "/page/task_active", produces = { "application/json;charset=UTF-8" })
public class YdMangerControllerPageTaskActive {
	@Autowired
	private YdMangerServiceTaskActive ydMangerServiceTaskActive;

	/**
	 * 获取指定任务的所有评论信息
	 * 
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/queryAll", method = RequestMethod.POST)
	public Object queryAll(@RequestBody HashMap<String, Object> map) {
		return ydMangerServiceTaskActive.$queryByTid(map);
	}

	/**
	 * 对任务进行评论or对评论任务的用户回复评论
	 * 
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/active", method = RequestMethod.POST)
	public Object active(Integer taskId, String contents, Integer userBId, Integer groupNum,
			HttpServletRequest request) {
		try {
			YdMangerUserVo userVo = (YdMangerUserVo) request.getSession().getAttribute("user");
			if (userVo != null) {
				YdMangerTaskActive active = new YdMangerTaskActive();
				active.setTaska_auid(userVo.getUser_id());
				active.setTaska_buid(userBId);
				active.setTaska_contents(contents);
				active.setTaska_create_time(new Date());
				active.setTaska_tid(taskId);
				active.setTaska_group(groupNum);
				return ydMangerServiceTaskActive.$insert(active);
			} else {
				return new MapInitFactory().setMsg("501", "请登录后再评论！").getMap();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new MapInitFactory().setSystemError().getMap();
		}

	}

	/**
	 * 删除评论信息
	 * 
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/delActive", method = RequestMethod.POST)
	public Object delActive(@RequestBody HashMap<String, Object> map) {
		try {
			return ydMangerServiceTaskActive.$delete(map);
		} catch (Exception e) {
			e.printStackTrace();
			return new MapInitFactory().setSystemError().getMap();
		}

	}

}
