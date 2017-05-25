package com.yd.gcj.controller.page;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yd.gcj.entity.vo.YdMangerUserVo;
import com.yd.gcj.service.YdMangerServiceCollection;
import com.yd.gcj.tool.MapInitFactory;

@RestController
@RequestMapping(value = "/page/collection", produces = { "application/json;charset=UTF-8" })
public class YdMangerControllerPageCollection {

	@Autowired
	private YdMangerServiceCollection ydMangerServiceCollection;

	/***
	 * 查询该用户所有收藏任务信息
	 * 
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/queryAll", method = RequestMethod.POST)
	public Object queryAll(@RequestBody HashMap<String, Object> map) {
		return ydMangerServiceCollection.$queryAll(map);
	}

	/***
	 * 查询该用户根据分页的收藏任务信息
	 * 
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/queryAllByPageNum", method = RequestMethod.POST)
	public Object queryAllByPageNum(@RequestBody HashMap<String, Object> map) {
		return ydMangerServiceCollection.$queryAllByPageNum(0, 0, 0);
	}

	/***
	 * 查询该用户收藏任务信息数量
	 * 
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/queryCountNum", method = RequestMethod.POST)
	public Object queryCountNum(@RequestBody HashMap<String, Object> map) {
		return ydMangerServiceCollection.$queryCountNum(map);
	}

	/***
	 * 添加收藏
	 * 
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public Object insert(Integer taskId, HttpServletRequest request) {
		try {
			YdMangerUserVo userVo = (YdMangerUserVo) request.getSession().getAttribute("user");
			if (userVo != null) {
				return ydMangerServiceCollection.$insert(userVo.getUser_id(), taskId);
			} else {
				return new MapInitFactory().setMsg("600", "登陆超时或者没有登录，请登录后再操作！").getMap();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new MapInitFactory().setSystemError().getMap();
		}

	}

	/***
	 * 删除收藏任务信息（取消收藏）
	 * 
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public Object delete(Integer taskId, HttpServletRequest request) {
		try {
			YdMangerUserVo userVo = (YdMangerUserVo) request.getSession().getAttribute("user");
			if (userVo != null) {
				return ydMangerServiceCollection.$delete(userVo.getUser_id(), taskId);
			} else {
				return new MapInitFactory().setMsg("600", "登陆超时或者没有登录，请登录后再操作！").getMap();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new MapInitFactory().setSystemError().getMap();
		}

	}

}
