package com.yd.gcj.controller.page;

import java.util.HashMap;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/page/banktr", produces = { "application/json;charset=UTF-8" })
public class YdMangerControllerPageBankTR {

	// @Autowired
	// private YdMangerServiceBankTR ydMangerServiceBankTR;

	/***
	 * 查询指定用户交易记录
	 * 
	 * @param map
	 * @return
	 */
	Object queryAll(@RequestBody HashMap<String, Object> map) {
		return null;
	}

	/***
	 * 根据时间查询交易记录，主要用于后台操作
	 * 
	 * @param map
	 * @return
	 */
	Object queryByDate(@RequestBody HashMap<String, Object> map) {
		return null;
	}

	/***
	 * 多条件复杂情况使用sql动态查询
	 * 
	 * @param map
	 * @return
	 */
	Object queryBySql(@RequestBody HashMap<String, Object> map) {
		return null;
	}

	/***
	 * 根据id查询指定交易记录信息
	 * 
	 * @param map
	 * @return
	 */
	Object queryById(@RequestBody HashMap<String, Object> map) {
		return null;
	}

	/***
	 * 根据用户id查询该用户的交易记录数量，主要用户用户查询分页
	 * 
	 * @param map
	 * @return
	 */
	Object queryCountNum(@RequestBody HashMap<String, Object> map) {
		return null;
	}

	/***
	 * 查询数据库所有交易记录信息数量，主要用于后台查询分页
	 * 
	 * @return
	 */
	Object $queryCountNum() {
		return null;
	}

	/***
	 * 生成新的交易记录信息
	 * 
	 * @param map
	 * @return
	 */
	Object insert(@RequestBody HashMap<String, Object> map) {
		return null;
	}

}
