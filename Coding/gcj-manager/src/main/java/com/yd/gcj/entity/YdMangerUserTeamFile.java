package com.yd.gcj.entity;

public class YdMangerUserTeamFile {
	/**唯一标识*/
	private Integer file_id;
	/**团队信息id*/
	private Integer file_uid;
	/**文件信息id*/
	private Integer file_fid;
	
	/**
	 * 获取 唯一标识
	 * @return file_id
	 */
	public Integer getFile_id() {
		return file_id;
	}
	
	/**
	 * 设置 唯一标识
	 * @param file_id
	 */
	public void setFile_id(Integer file_id) {
		this.file_id = file_id;
	}
	
	/**
	 * 获取 团队信息id
	 * @return file_uid
	 */
	public Integer getFile_uid() {
		return file_uid;
	}
	
	/**
	 * 设置 团队信息id
	 * @param file_uid
	 */
	public void setFile_uid(Integer file_uid) {
		this.file_uid = file_uid;
	}
	
	/**
	 * 获取 文件信息id
	 * @return file_fid
	 */
	public Integer getFile_fid() {
		return file_fid;
	}
	
	/**
	 * 设置 文件信息id
	 * @param file_fid
	 */
	public void setFile_fid(Integer file_fid) {
		this.file_fid = file_fid;
	}
}
