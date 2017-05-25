package com.yd.dby.b.shop.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yd.dby.b.shop.service.YdShopServiceMaintenance;
import com.yd.dby.d.seller.entity.YdSellerGoodsVo;

/**
 * @author Administrator
 * 说明(养护)
 */
@RequestMapping(value="maintenance")
@Controller
public class YdShopControllerMaintenance {
	
	@Autowired
	private YdShopServiceMaintenance YdShopServiceMaintenance;
	
	/**
	 * @param model
	 * @return
	 * 方法作用(查询所有的养护套餐)
	 */
	@RequestMapping(value="queryAllMaintenance")
	public Object queryAllMaintenance(Model model,Integer p){
		
		Map<String,Object> map = new HashMap<String, Object>();
		
		if (p == null || p == 0) {
			p = 1;
		}
		Integer to = (p - 1) * 40;
		
		//查询 所有的养护套餐
		List<YdSellerGoodsVo> goodsList = YdShopServiceMaintenance.queryAllMaintenance(to);
		
		//查询养护套餐的总数
		Integer sum =  YdShopServiceMaintenance.queryAllNum();
		
		map.put("p",p);
		map.put("to", to);
		if (sum % 40 == 0) {
			map.put("totalPage", Math.ceil(sum / 40));
		} else {
			map.put("totalPage", Math.ceil(sum / 40) + 1);
		}
		
		model.addAttribute("num",sum);
		model.addAttribute("data",goodsList);
		model.addAttribute("map",map);
		return "shop/index/maintenanceGoods";
	}
}
