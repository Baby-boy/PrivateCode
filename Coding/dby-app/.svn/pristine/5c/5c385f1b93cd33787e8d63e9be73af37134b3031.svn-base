package com.yd.dby.app.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yd.dby.app.entity.vo.OrderListGoodsVo;
import com.yd.dby.app.entity.vo.OrderListVo;
import com.yd.dby.app.mapper.YdOrderMapper;
import com.yd.dby.app.mapper.YdUserMapper;
import com.yd.dby.app.service.TestService;

@Service("testService")
public class TestServiceImpl implements TestService {

	@Autowired
	YdUserMapper ydUserMapper;

	@Autowired
	YdOrderMapper ydOrderMapper;

	@Override
	public PageInfo<OrderListVo> queryByPage(Integer pageNo, Integer pageSize) {
		pageNo = pageNo == null ? 1 : pageNo;
		pageSize = pageSize == null ? 10 : pageSize;
		PageHelper.startPage(pageNo, pageSize);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ogId", 1);

		List<OrderListVo> list = ydOrderMapper.selectTestVo(map);

		System.out.println("++++++++++++++++++++++++++++++++++++++++");

		for (OrderListVo vo : list) {
			System.out.println("++orderId:" + vo.getOrderId());
			for (OrderListGoodsVo g : vo.getGoods()) {
				System.out.println("--ogId:" + g.getOgId());
			}
		}

		System.out.println("----------------------------------------");

		PageInfo<OrderListVo> page = new PageInfo<OrderListVo>(list);
		System.out.println("pageNum:" + page.getPageNum());
		System.out.println("pageSize:" + page.getPageSize());
		System.out.println("startRow:" + page.getStartRow());
		System.out.println("endRow:" + page.getEndRow());
		System.out.println("toTal:" + page.getTotal());
		System.out.println("pages:" + page.getPages());
		// System.out.println("firstPage:" + page.getFirstPage());
		// System.out.println("lastPage:" + page.getLastPage());
		// System.out.println("previousPage:" + page.isHasPreviousPage());
		// System.out.println("isHasNextPage:" + page.isHasNextPage());

		return page;
	}

}
