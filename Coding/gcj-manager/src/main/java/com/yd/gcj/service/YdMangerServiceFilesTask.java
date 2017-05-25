package com.yd.gcj.service;

import org.springframework.transaction.annotation.Transactional;

import com.yd.gcj.entity.YdMangerFilesTask;

public interface YdMangerServiceFilesTask {
	@Transactional
	Integer $insert(YdMangerFilesTask filesTask);
	
}
