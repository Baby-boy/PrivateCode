package com.yd.gcj.controller.page;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yd.gcj.service.YdMangerServiceBank;
import com.yd.gcj.tool.MapInitFactory;


@RestController
@RequestMapping(value="/page/bank",produces = {"application/json;charset=UTF-8"})
public class YdMangerControllerPageBank<HttpSerlvetRequest> {
	@Autowired
	private YdMangerServiceBank ydMangerServiceBank;
	
	/***
	 * 添加银行卡信息（后期需要移植到银行卡信息接口控制器）
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/addBankMsg", method = RequestMethod.POST)
	public Object addBankMsg(@RequestBody HashMap<String, Object> map) {
		try {
			return ydMangerServiceBank.$addBankMsg(map);
		} catch (Exception e) {
			e.printStackTrace();
			return new MapInitFactory().setSystemError().getMap();
		}
		
	}
	
	/***
	 * 删除银行卡信息（后期需要移植到银行卡信息接口控制器）
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/delBankMsg", method = RequestMethod.POST)
	public Object delBankMsg(@RequestBody HashMap<String, Object> map) {
		try {
			return ydMangerServiceBank.$delBankMsg(map);
		} catch (Exception e) {
			e.printStackTrace();
			return new MapInitFactory().setSystemError().getMap();
		}
		
	}
	
	/***
	 * 查询指定用户银行卡信息
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/queryAll", method = RequestMethod.POST)
	public Object queryAll(@RequestBody HashMap<String, Object> map){
		try {
			return ydMangerServiceBank.$queryAll(map);
		} catch (Exception e) {
			e.printStackTrace();
			return new MapInitFactory().setSystemError().getMap();
		}
		
	}
}
