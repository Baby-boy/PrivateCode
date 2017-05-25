package com.yd.gcj.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yd.gcj.entity.YdMangerFiles;
import com.yd.gcj.mapper.YdMangerMapperFiles;
import com.yd.gcj.service.YdMangerServiceFiles;

@Service("YdMangerServiceFiles")
public class YdMangerServiceImplFiles implements YdMangerServiceFiles{
	
	@Autowired
	private YdMangerMapperFiles ydMangerMapperFiles;
	
	@Override
	public List<YdMangerFiles> $queryAllBySql(String sql) {
		return ydMangerMapperFiles.$queryAllBySql(sql);
	}

	@Override
	public YdMangerFiles $queryById(Integer files_id) {
		return ydMangerMapperFiles.$queryById(files_id);
	}

	@Override
	public Integer $queryCountNum(String sql) {
		return ydMangerMapperFiles.$queryCountNum(sql);
	}

	@Override
	public Integer $insert(YdMangerFiles files) {
		return ydMangerMapperFiles.$insert(files);
	}

	@Override
	public Integer $update(YdMangerFiles files) {
		return ydMangerMapperFiles.$update(files);
	}

	@Override
	public Integer $delete(Integer files_id) {
		return ydMangerMapperFiles.$delete(files_id);
	}

}
