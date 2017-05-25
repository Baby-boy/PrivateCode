package com.yd.gcj.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yd.gcj.entity.YdMangerUserLabel;
import com.yd.gcj.entity.vo.YdMangerUserVo;
import com.yd.gcj.mapper.YdMangerMapperUser;
import com.yd.gcj.mapper.YdMangerMapperUserLabel;
import com.yd.gcj.service.YdMangerServiceUserLabel;
import com.yd.gcj.tool.MapInitFactory;

@Service("ydMangerServiceUserLabel")
public class YdMangerServiceImplUserLabel implements YdMangerServiceUserLabel {
	
	@Autowired
	private YdMangerMapperUser mapperUser;
	
	@Autowired
	private YdMangerMapperUserLabel ydMangerMapperUserLabel;

	@Override
	public List<YdMangerUserLabel> $queryAll(Integer userl_uid) {
		return ydMangerMapperUserLabel.$queryAll(userl_uid);
	}

	@Override
	public YdMangerUserLabel $queryById(Integer userl_id) {
		return ydMangerMapperUserLabel.$queryById(userl_id);
	}

	@Override
	public Integer $delete(Integer userl_id) {
		return ydMangerMapperUserLabel.$delete(userl_id);
	}

	@Override
	public Object $insert(Integer userId, String labelIdsStr,final YdMangerUserVo userVo) {
		MapInitFactory mapInitFactory = new MapInitFactory();
		mapInitFactory.setSystemError();
		if (labelIdsStr.length() > 0) {
			String[] labelIds = labelIdsStr.split(",");
			List<Integer> ids = new ArrayList<Integer>();
			for (String labelId : labelIds) {
				ids.add(Integer.parseInt(labelId));
			}
			ydMangerMapperUserLabel.$delete(userId);
			Integer success = ydMangerMapperUserLabel
					.$insert(new MapInitFactory().setDatas("userId", userId).setDatas("ids", ids).getMap());
			if (success > 0) {
				mapperUser.$updateSkillstate(userId, 3);
				userVo.setUser_skillstate(3);
				mapInitFactory.setMsg("200", "提交成功，后台审核中！");
			} else {
				mapInitFactory.setMsg("501", "提交失败！");
			}
			if (userVo != null) {
				List<YdMangerUserLabel> labels = ydMangerMapperUserLabel.$queryAll(userVo.getUser_id());
				userVo.setUserLabels(labels);
			}
		}else{
			mapInitFactory.setMsg("502", "请选择技能!");
		}
		
		return mapInitFactory.getMap();
	}

	/***
	 * 添加个人技能
	 * 
	 * @param userId
	 * @param reLabel
	 * @return
	 */
	@Override
	public Object $insertRe(Integer userId, String reLabel) {
		MapInitFactory mapInitFactory = new MapInitFactory();
		try {

		} catch (Exception e) {
			e.printStackTrace();
			mapInitFactory.setSystemError();
		}
		return mapInitFactory.getMap();
	}

	@Override
	public Integer $queryCountNumById(Integer userl_id) {
		return ydMangerMapperUserLabel.$queryCountNumById(userl_id);
	}

}
