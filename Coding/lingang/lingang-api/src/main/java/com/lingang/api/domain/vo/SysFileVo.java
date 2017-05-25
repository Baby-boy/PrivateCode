package com.lingang.api.domain.vo;

import java.io.Serializable;
import java.util.Date;

public class SysFileVo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5469074734209512604L;

	private Integer fileId;
	
	private String fileTitle;
	
	private Integer imgId;
	
	private String imgPath;
	
	private String fileAddress;
	
	private Integer fileType;
	
	private Date fileUpdateTime;
	
	private Date updateTime;
	
	private Double fileSize;
	
	private Double downloadSpeed;
	
	private Integer downloadState;

	public Integer getFileId() {
		return fileId;
	}

	public void setFileId(Integer fileId) {
		this.fileId = fileId;
	}

	public String getFileTitle() {
		return fileTitle;
	}

	public void setFileTitle(String fileTitle) {
		this.fileTitle = fileTitle;
	}

	public Integer getImgId() {
		return imgId;
	}

	public void setImgId(Integer imgId) {
		this.imgId = imgId;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	public String getFileAddress() {
		return fileAddress;
	}

	public void setFileAddress(String fileAddress) {
		this.fileAddress = fileAddress;
	}

	public Integer getFileType() {
		return fileType;
	}

	public void setFileType(Integer fileType) {
		this.fileType = fileType;
	}

	public Date getFileUpdateTime() {
		return fileUpdateTime;
	}

	public void setFileUpdateTime(Date fileUpdateTime) {
		this.fileUpdateTime = fileUpdateTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Double getFileSize() {
		return fileSize;
	}

	public void setFileSize(Double fileSize) {
		this.fileSize = fileSize;
	}

	public Double getDownloadSpeed() {
		return downloadSpeed;
	}

	public void setDownloadSpeed(Double downloadSpeed) {
		this.downloadSpeed = downloadSpeed;
	}

	public Integer getDownloadState() {
		return downloadState;
	}

	public void setDownloadState(Integer downloadState) {
		this.downloadState = downloadState;
	}
	
}
