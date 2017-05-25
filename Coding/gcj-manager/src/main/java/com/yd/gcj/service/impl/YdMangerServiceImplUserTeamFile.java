package com.yd.gcj.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yd.gcj.entity.YdMangerFiles;
import com.yd.gcj.entity.YdMangerUserTeamFile;
import com.yd.gcj.mapper.YdMangerMapperUserTeamFile;
import com.yd.gcj.service.YdMangerServiceUserTeamFile;

@Service("YdMangerServiceUserTeamFile")
public class YdMangerServiceImplUserTeamFile implements YdMangerServiceUserTeamFile{
	
	@Autowired
	private YdMangerMapperUserTeamFile mapperTeamFile;
	
	@Override
	public List<YdMangerFiles> $queryAllByUserId(Integer userId) {
		return mapperTeamFile.$queryAllByUserId(userId);
	}

	@Override
	public Integer $insert(YdMangerUserTeamFile teamFile) {
		return mapperTeamFile.$insert(teamFile);
	}

	@Override
	public Integer $deleteById(Integer id) {
		return mapperTeamFile.$deleteById(id);
	}

	@Override
	public Integer $deleteByUserId(Integer userId) {
		return mapperTeamFile.$deleteByUserId(userId);
	}

	@Override
	public Integer $queryCount(Integer userId) {
		return mapperTeamFile.$queryCount(userId);
	}

}
