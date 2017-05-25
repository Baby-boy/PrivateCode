package com.yd.gcj.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yd.gcj.entity.YdMangerAttention;
import com.yd.gcj.entity.vo.YdMangerUserVo;
import com.yd.gcj.mapper.YdMangerMapperAttention;
import com.yd.gcj.service.YdMangerServiceAttention;
import com.yd.gcj.tool.MapInitFactory;
import com.yd.gcj.tool.Values;

@Service("YdMangerServiceAttention")
public class YdMangerServiceImplAttention implements YdMangerServiceAttention {

	@Autowired
	private YdMangerMapperAttention mapperAttention;

	// @Autowired
	// private YdMangerMapperUser mapperUser;

	@Autowired
	private HttpSession session;// TODO 此session存在线程安全问题,后期进行修改

	@Override
	public List<YdMangerAttention> $queryAll(Integer userId) {
		return mapperAttention.$queryAll(userId);
	}

	@Override
	public Object $queryACountNum(HashMap<String, Object> map) {
		MapInitFactory mapInitFactory = new MapInitFactory();
		return mapInitFactory.getMap();
	}

	@Override
	public Object $queryBCountNum(HashMap<String, Object> map) {
		MapInitFactory mapInitFactory = new MapInitFactory();

		return mapInitFactory.getMap();
	}

	@Override
	public Object $addAtten(YdMangerAttention attention) {
		MapInitFactory mapInitFactory = new MapInitFactory();
		mapInitFactory.setSystemError();
		Integer isExist = mapperAttention.$isExist(attention.getAtt_aid(), attention.getAtt_bid());
		if (isExist > 0) {
			mapInitFactory.setMsg("502", "已经关注过了");
		} else {
			Integer success = mapperAttention.$insert(attention);
			if (success > 0) {
				mapInitFactory.setMsg(Values.INITSUCCESSCODE, "关注成功");
				YdMangerUserVo userVo = (YdMangerUserVo) session.getAttribute("user");
				if (userVo != null) {
					userVo.setAttentionNum(mapperAttention.$queryACountNum(userVo.getUser_id()));
					session.setAttribute("user", userVo);
				}
			} else {
				mapInitFactory.setMsg("501", "关注失败");
			}
		}
		return mapInitFactory.getMap();
	}

	@Override
	public Object $delAtten(Integer userAId, Integer userBId) {
		MapInitFactory mapInitFactory = new MapInitFactory();
		mapInitFactory.setSystemError();
		Integer isExist = mapperAttention.$isExist(userAId, userBId);
		if (isExist != null) {
			Integer success = mapperAttention.$delete(userAId, userBId);
			if (success > 0) {
				mapInitFactory.setMsg(Values.INITSUCCESSCODE, "取消成功");
				YdMangerUserVo userVo = (YdMangerUserVo) session.getAttribute("user");
				if (userVo != null) {
					userVo.setAttentionNum(mapperAttention.$queryACountNum(userVo.getUser_id()));
					session.setAttribute("user", userVo);
				}
			} else {
				mapInitFactory.setMsg("501", "取消失败");
			}
		} else {
			mapInitFactory.setMsg("501", "参数有误");
		}
		return mapInitFactory.getMap();
	}

}
