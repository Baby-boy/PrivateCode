package com.glanway.hr.app.entity.para;

public class BasePara {

	private int sort = 0;// 排序(0.倒序，1.升序)

	private int page = 1;// 查询页(前端)

	private int startIndex = 0;// 起始页(数据库)

	private int pageSize = 10;// 查询条数

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

}
