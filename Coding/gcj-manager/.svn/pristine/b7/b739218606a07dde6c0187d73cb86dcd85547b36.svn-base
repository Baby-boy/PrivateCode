package com.yd.gcj.controller.page;

import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yd.gcj.entity.YdMangerTaskModel;
import com.yd.gcj.entity.vo.YdMangerUserVo;
import com.yd.gcj.service.YdMangerServiceTaskModel;
import com.yd.gcj.tool.MapInitFactory;

@RestController
@RequestMapping(value = "/page/task_model", produces = { "application/json;charset=UTF-8" })
public class YdMangerControllerPageTaskModel {
	@Autowired
	private YdMangerServiceTaskModel ydMangerServiceTaskModel;

	/***
	 * 查询指定任务的任务模块
	 * 
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/queryByTid", method = RequestMethod.POST)
	public Object queryByTid(HashMap<String, Object> map) {
		return ydMangerServiceTaskModel.$queryByTid(0);
	}

	/**
	 * 添加（申请）检查任务模块
	 * 
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public Object add(Integer taskId, String title, Date ptime, Date etime, String desc, float price,
			HttpServletRequest request) {
		try {
			YdMangerUserVo userVo = (YdMangerUserVo) request.getSession().getAttribute("user");
			if (userVo != null) {
				YdMangerTaskModel taskModel = new YdMangerTaskModel();
				taskModel.setTaskmd_tid(taskId);
				taskModel.setTaskmd_title(title);
				taskModel.setTaskmd_ptime(ptime);
				taskModel.setTaskmd_etime(etime);
				taskModel.setTaskmd_desc(desc);
				taskModel.setTaskmd_state(0);
				taskModel.setTaskmd_uid(userVo.getUser_id());
				taskModel.setTaskmd_price(price);
				return ydMangerServiceTaskModel.$insert(taskModel);
			} else {
				return new MapInitFactory("600", "登录超时或者没有登录，请登录后再进行操作！").getMap();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new MapInitFactory().setSystemError().getMap();
		}

	}

	/**
	 * 修改模块基本信息
	 * 
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/updateMsg", method = RequestMethod.POST)
	public Object updateMsg(HashMap<String, Object> map) {
		try {
			return ydMangerServiceTaskModel.$update(map);
		} catch (Exception e) {
			e.printStackTrace();
			return new MapInitFactory().setSystemError().getMap();
		}

	}

	/**
	 * 更新模块检查状态
	 * 
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/updateState", method = RequestMethod.POST)
	public Object updateState(HashMap<String, Object> map) {
		try {
			return ydMangerServiceTaskModel.$updateState(map);
		} catch (Exception e) {
			e.printStackTrace();
			return new MapInitFactory().setSystemError().getMap();
		}

	}

	/**
	 * 删除指定的任务模块
	 * 
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public Object delete(HashMap<String, Object> map) {
		try {
			return ydMangerServiceTaskModel.$delete(map);
		} catch (Exception e) {
			e.printStackTrace();
			return new MapInitFactory().setSystemError().getMap();
		}

	}

}
