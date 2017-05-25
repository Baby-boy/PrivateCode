package com.lingang.api.domain.para;
import java.io.Serializable;

/**
 *@Description: 基础条件信息
 *@Author: 卢光磊  lgl1012dream@foxmail.com
 *@Since:2016年4月27日 下午6:08:21
 *@Version:1.0
 */
public class BasePara implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4458619322975439700L;

	private int sort=0;//排序(0.倒序，1.升序)
    
	private int currentPage=1;//当前页(前端)
	 
	private int startIndex=0;//起始页(数据库)
	
	private int onePageCount=10;//查询条数
	
	private int isPage=1;//是否分页查询(1.是,0.否)

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public int getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getOnePageCount() {
		return onePageCount;
	}

	public void setOnePageCount(int onePageCount) {
		this.onePageCount = onePageCount;
	}

	public int getIsPage() {
		return isPage;
	}

	public void setIsPage(int isPage) {
		this.isPage = isPage;
	}
}
