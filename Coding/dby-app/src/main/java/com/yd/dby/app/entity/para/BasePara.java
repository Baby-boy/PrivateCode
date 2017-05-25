package com.yd.dby.app.entity.para;

public class BasePara {

	private int sort = 0;// 排序(0.倒序，1.升序)

	private int currentPage = 1;// 查询页(前端)

	private int startIndex = 0;// 起始页(数据库)

	private int onePageCount = 10;// 查询条数

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}

	public int getOnePageCount() {
		return onePageCount;
	}

	public void setOnePageCount(int onePageCount) {
		this.onePageCount = onePageCount;
	}

}
