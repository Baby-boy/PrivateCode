package com.yd.gcj.controller.page;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yd.gcj.service.YdMangerServiceCases;
import com.yd.gcj.tool.MapInitFactory;

@RestController
@RequestMapping(value = "/page/cases", produces = { "application/json;charset=UTF-8" })
public class YdMangerControllerPageCases {
	@Autowired
	private YdMangerServiceCases ydMangerServiceCases;

	/**
	 * 查询指定用户的所有案例名称（主要用于提供下拉选择）
	 * 
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/queryAllNames", method = RequestMethod.POST)
	public Object queryAllNames(HashMap<String, Object> map) {
		return null;
	}

	/**
	 * 查询指定案例
	 * 
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/queryById", method = RequestMethod.POST)
	public Object queryById(HashMap<String, Object> map) {
		return ydMangerServiceCases.$queryById(map);
	}

	/**
	 * 查询指定用户的所有案例
	 * 
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/queryByUid", method = RequestMethod.POST)
	public Object queryByUid(HashMap<String, Object> map) {
		return null;
	}

	/**
	 * 根据指定用户指定类型查询用户案例
	 * 
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/queryByType", method = RequestMethod.POST)
	public Object queryByType(HashMap<String, Object> map) {
		return null;
	}

	/**
	 * 添加新案例
	 * 
	 * @param map
	 * @return
	 */
	@Transactional
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public Object add(HashMap<String, Object> map) {
		try {
			return ydMangerServiceCases.$insert(map);
		} catch (Exception e) {
			e.printStackTrace();
			return new MapInitFactory().setSystemError().getMap();
		}
	}

	/**
	 * 修改用户案例信息
	 * 
	 * @param map
	 * @return
	 */
	@Transactional
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public Object update(HashMap<String, Object> map) {
		try {
			return ydMangerServiceCases.$update(map);
		} catch (Exception e) {
			e.printStackTrace();
			return new MapInitFactory().setSystemError().getMap();
		}
	}

	/**
	 * 删除用户指定的案例信息
	 * 
	 * @param map
	 * @return
	 */
	@Transactional
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public Object delete(HashMap<String, Object> map) {
		try {
			return ydMangerServiceCases.$delete(map);
		} catch (Exception e) {
			e.printStackTrace();
			return new MapInitFactory().setSystemError().getMap();
		}

	}

}
