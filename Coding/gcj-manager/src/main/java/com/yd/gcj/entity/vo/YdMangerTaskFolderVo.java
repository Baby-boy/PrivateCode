package com.yd.gcj.entity.vo;

import java.util.List;

import com.yd.gcj.entity.YdMangerFiles;
import com.yd.gcj.entity.YdMangerTaskFolder;

public class YdMangerTaskFolderVo extends YdMangerTaskFolder{
	
	private List<YdMangerFiles> files;

	/**
	 * 获取 files
	 * @return files
	 */
	public List<YdMangerFiles> getFiles() {
		return files;
	}
	
	/**
	 * 设置 files
	 * @param files
	 */
	public void setFiles(List<YdMangerFiles> files) {
		this.files = files;
	}
	
}
