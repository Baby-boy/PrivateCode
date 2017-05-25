package com.yd.gcj.controller.page;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yd.gcj.entity.YdMangerEpcs;
import com.yd.gcj.entity.vo.YdMangerUserVo;
import com.yd.gcj.service.YdMangerServiceEpcs;
import com.yd.gcj.tool.MapInitFactory;

@RestController
@RequestMapping(value = "/page/epcs", produces = { "application/json;charset=UTF-8" })
public class YdMangerControllerPageEpcs {
	@Autowired
	private YdMangerServiceEpcs ydMangerServiceEpcs;

	/**
	 * 查询指定用户的评论信息
	 * 
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/queryByUid", method = RequestMethod.POST)
	public Object queryByUid(HashMap<String, Object> map) {
		return ydMangerServiceEpcs.$queryBySql(map);
	}

	/**
	 * 服务商对雇主评价
	 * 
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/pcs", method = RequestMethod.POST)
	public Object pcs(Integer taskId, Integer evaluation, String epcsReason, String eContent,
			HttpServletRequest request) {
		try {
			YdMangerUserVo userVo = (YdMangerUserVo) request.getSession().getAttribute("user");
			if (userVo != null) {
				YdMangerEpcs epcs = new YdMangerEpcs();
				epcs.setEpcs_tid(taskId);
				epcs.setEpcs_evaluation(evaluation);
				epcs.setEpcs_reason(epcsReason);
				epcs.setEpcs_contents(eContent);
				epcs.setEpcs_eid(userVo.getUser_id());
				epcs.setEpcs_ename(userVo.getUser_nickname());
				return ydMangerServiceEpcs.$insert(epcs);
			} else {
				return new MapInitFactory("600", "登录超时或者没有登录，请登录后再进行操作！").getMap();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new MapInitFactory().setSystemError().getMap();
		}
	}

	/**
	 * 删除指定评论
	 * 
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public Object delete(HashMap<String, Object> map) {
		try {

		} catch (Exception e) {
			e.printStackTrace();
			return new MapInitFactory().setSystemError().getMap();
		}
		return ydMangerServiceEpcs.$delete(map);
	}

	/**
	 * 根据指定的任务查询评价信息
	 * 
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/queryByTid", method = RequestMethod.POST)
	public Object queryByTid(HashMap<String, Object> map) {
		return ydMangerServiceEpcs.$queryAllByTid(map);
	}

	/**
	 * 根据指定的任务查询评价信息的数量（主要用于分页）
	 * 
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/queryCountNumByTid", method = RequestMethod.POST)
	public Object queryCountNumByTid(HashMap<String, Object> map) {
		return ydMangerServiceEpcs.$queryCountNum(map);
	}

}
