package com.yd.gcj.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yd.gcj.entity.YdMangerFiles;
import com.yd.gcj.entity.YdMangerFilesTask;
import com.yd.gcj.entity.YdMangerTaskFolder;
import com.yd.gcj.entity.vo.YdMangerTaskFolderVo;
import com.yd.gcj.mapper.YdMangerMapperFiles;
import com.yd.gcj.mapper.YdMangerMapperFilesTask;
import com.yd.gcj.mapper.YdMangerMapperTaskFolder;
import com.yd.gcj.service.YdMangerServiceTaskFolder;
import com.yd.gcj.tool.MapInitFactory;

@Service("YdMangerServiceTaskFolder")
public class YdMangerServiceImplTaskFolder implements YdMangerServiceTaskFolder{
	
	@Autowired
	private YdMangerMapperFiles mapperFiles;
	
	@Autowired
	private YdMangerMapperFilesTask mapperFilesTask;
	
	@Autowired
	private YdMangerMapperTaskFolder mapperFolder;
	
	@Override
	public List<YdMangerTaskFolderVo> queryFolderByTaskId(Integer taskId) {
		
		List<YdMangerTaskFolderVo> folders = mapperFolder.queryByTaskId(taskId);
		List<YdMangerFilesTask> filesTasks = mapperFilesTask.$queryFiles(taskId);
		List<YdMangerFiles> files = mapperFiles.$queryAllByTask(taskId);
		
		for(YdMangerTaskFolderVo folder : folders){
			List<YdMangerFiles> fs = new ArrayList<YdMangerFiles>();
			
			for(YdMangerFilesTask filesTask : filesTasks){
				if(folder.getF_id() == filesTask.getFiletr_fid()){
					for(YdMangerFiles file : files){
						if(filesTask.getFiletr_id() == file.getFiles_id()){
							fs.add(file);
						}
					}
					
				}
			}
			
			folder.setFiles(fs);
		}
		return folders;
	}

	@Override
	public Object addFolder(Integer taskId, String name) {
		MapInitFactory mf = new MapInitFactory();
		YdMangerTaskFolder folder = new YdMangerTaskFolder();
		folder.setF_name(name);
		folder.setF_tid(taskId);
		folder.setF_create_time(new Date());
		mapperFolder.insert(folder);
		mf.setMsg("200", "添加成功！");
		return mf.getMap();
	}

}
