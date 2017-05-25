package com.yd.gcj.service.impl.page;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yd.gcj.entity.YdMangerTaskTemp;
import com.yd.gcj.mapper.YdMangerMapperTaskTemp;

@Service("taskTemp")
public class YdMangerServiceImplPageTaskTemp{
	
	@Autowired
	private YdMangerMapperTaskTemp ydMangerMapperTaskTemp;
	
	public List<YdMangerTaskTemp> $queryAll() {
		Integer startPageNum = 0;
		Integer queryPageNum = 10;
		return ydMangerMapperTaskTemp.$queryAll(startPageNum,queryPageNum);
	}

}
