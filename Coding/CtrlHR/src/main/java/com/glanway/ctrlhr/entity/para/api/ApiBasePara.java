package com.glanway.ctrlhr.entity.para.api;

public class ApiBasePara {

	private int page = 1;// 查询页(前端)

	private int startIndex = 0;// 起始页(数据库)

	private int pageSize = 3000;// 查询条数(默认3000条)

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
