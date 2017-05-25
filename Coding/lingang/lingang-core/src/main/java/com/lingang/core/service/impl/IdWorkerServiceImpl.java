package com.lingang.core.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lingang.api.component.IdWorkerServiceApi;
import com.lingang.core.component.IdWorker;

@Service("idWorkerService")
public class IdWorkerServiceImpl implements IdWorkerServiceApi{

	@Resource
	private IdWorker idWorker;
	
	@Override
	public long nextId() {
		return idWorker.nextId();
	}

}
