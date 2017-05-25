package com.yd.gcj.service.impl.page;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yd.gcj.entity.YdMangerUserLabel;
import com.yd.gcj.entity.vo.YdMangerUserVo;
import com.yd.gcj.mapper.YdMangerMapperUserLabel;

@Service("userLabel")
public class YdMangerServiceImplPageUserLabel {

	@Autowired
	private YdMangerMapperUserLabel ydMangerMapperUserLabel;
	
	@Autowired
	HttpServletRequest request;
	
	public List<YdMangerUserLabel> $queryAll() {
		List<YdMangerUserLabel> userLabels = new ArrayList<YdMangerUserLabel>();
		YdMangerUserVo userVo = (YdMangerUserVo) request.getSession().getAttribute("user");
		if (null == userVo) {
			return null;
		}
		Integer userId = userVo.getUser_id();
		userLabels = ydMangerMapperUserLabel.$queryAll(userId);
		userVo.setUserLabels(userLabels);
		return userLabels;
	}

	public Integer $delete(Integer userl_id) {
		return ydMangerMapperUserLabel.$delete(userl_id);
	}

}
