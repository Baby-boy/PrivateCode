package com.yd.gcj.controller.page;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yd.gcj.service.YdMangerServiceBanner;
import com.yd.gcj.tool.MapInitFactory;

@RestController
@RequestMapping(value="/page/img",produces = {"application/json;charset=UTF-8"})
public class YdMangerControllerPageBanner<HttpServletRequest> {
	@Autowired
	private YdMangerServiceBanner ydMangerServiceBanner;
	
	/***
	 * 获取所有图片信息
	 */
	@RequestMapping(value = "/queryAll", method = RequestMethod.POST)
	public Object queryAll() {
		return ydMangerServiceBanner.$queryAll();
	}
	
	
	/***
	 * 根据分页信息查询图片所有信息
	 */
	@RequestMapping(value = "/queryAllByPage", method = RequestMethod.POST)
	public Object queryAllByPage(@RequestBody HashMap<String, Object> map){
		return ydMangerServiceBanner.$queryAllByPage(map);
	}
	
	
	/***
	 * 根据图片信息id集合查询相应的数据
	 */
	@RequestMapping(value = "/queryByIds", method = RequestMethod.POST)
	public Object queryByIds(@RequestBody HashMap<String, Object> map){
		return ydMangerServiceBanner.$queryByIds(map);
	}
	
	
	/***
	 * 根据id查询单个图片信息
	 */
	@RequestMapping(value = "/queryById", method = RequestMethod.POST)
	public Object queryById(@RequestBody HashMap<String, Object> map){
		return ydMangerServiceBanner.$queryById(map);
	}
	
	
	/***
	 * 添加新图片信息 
	 */
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public Object insert(@RequestBody HashMap<String, Object> map){
		try {
			return ydMangerServiceBanner.$insert(map);
		} catch (Exception e) {
			e.printStackTrace();
			return new MapInitFactory().setSystemError().getMap();
		}
	}
	
	
	/***
	 * 跟新图片信息
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public Object update(@RequestBody HashMap<String, Object> map){
		try {
			return ydMangerServiceBanner.$update(map);
		} catch (Exception e) {
			e.printStackTrace();
			return new MapInitFactory().setSystemError().getMap();
		}
		
	}
	
	
	/***
	 * 删除指定id图片信息
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public Object delete(@RequestBody HashMap<String, Object> map){
		try {
			return ydMangerServiceBanner.$delete(map);
		} catch (Exception e) {
			e.printStackTrace();
			return new MapInitFactory().setSystemError().getMap();
		}
		
	}
	
}
