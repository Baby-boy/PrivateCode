package com.yd.dby.app.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yd.dby.app.common.HttpCode;
import com.yd.dby.app.common.ServiceResult;
import com.yd.dby.app.entity.YdCoupon;
import com.yd.dby.app.entity.YdUser;
import com.yd.dby.app.entity.para.CouponPara;
import com.yd.dby.app.entity.vo.UserCoupon;
import com.yd.dby.app.mapper.YdCouponMapper;
import com.yd.dby.app.mapper.YdUserMapper;
import com.yd.dby.app.service.YdCouponService;

@Service("ydCouponService")
public class YdCouponServiceImpl implements YdCouponService {

	@Autowired
	private YdCouponMapper ydCouponMapper;

	@Autowired
	private YdUserMapper ydUserMapper;

	@Override
	public ServiceResult<List<UserCoupon>> selectCouponList(CouponPara para) {
		ServiceResult<List<UserCoupon>> result = new ServiceResult<List<UserCoupon>>();

		// 数据转换
		String storeIdStr = para.getStoreIdStr();
		String moneyStr = para.getMoneyStr();
		String[] ids = storeIdStr.split(",");
		String[] moneys = moneyStr.split(",");

		// 信息过滤
		if (ids.length != moneys.length) {
			result.setStatusCode(HttpCode.BAD_REQUEST);
			result.setMsg("参数信息异常");
			return result;
		}

		// 根据商铺查询我的优惠券信息
		List<Object> paraList = new ArrayList<Object>();
		for (String string : ids) {
			paraList.add(Integer.parseInt(string));
		}
		para.setIds(paraList);

		// 店铺可用的优惠券
		List<UserCoupon> list = ydCouponMapper.selectCouponList(para);
		Date nowDate = new Date();
		for (int i = 0; i < list.size(); i++) {
			UserCoupon uc = list.get(i);
			List<YdCoupon> nweCoupons = new ArrayList<YdCoupon>();
			for (YdCoupon yc : uc.getCoupons()) {
				// 下单时间必须早于优惠券结束时间
				Date endDate = yc.getCouponEndTime();
				if (yc != null && endDate != null && nowDate.before(endDate)) {
					// 满金额数
					Integer fullMoney = yc.getCouponFullMoney();
					// 支付(对比)的金额
					Double payMoney = Double.valueOf(moneys[i]);
					// 下单金额必须符合优惠券 满金额
					if (fullMoney < payMoney) {
						nweCoupons.add(yc);
					}
				}
			}
			// 插入可用的优惠券
			uc.setCoupons(nweCoupons);
		}

		// 全场通用的优惠券
		List<UserCoupon> canCoupons = ydCouponMapper.selectCouponCanList(para);
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("canCoupons", canCoupons);

		// 积分信息
		YdUser user = ydUserMapper.selectUserIntegrationByUserId(para.getUserId());
		dataMap.put("userIntegration", user.getUserIntegration());

		// 返回可用优惠券信息
		result.setStatusCode(HttpCode.OK);
		result.setData(list);
		result.setDataMap(dataMap);

		return result;
	}

}
