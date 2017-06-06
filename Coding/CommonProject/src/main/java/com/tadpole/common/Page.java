package com.tadpole.common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Page<T> implements Serializable {

	private static final long serialVersionUID = 4325740561053873883L;

	/** 总页数，通过总记录数和每页显示记录条数计算获得 */
	private int countPage;

	/** 总记录数 */
	private int totalCount;

	/** 当前页，默认是第一页 */
	private int page = 1;

	/** 每页显示记录条数 ，默认是每页显示10条记录 */
	private int pageSize = 10;

	/** 开始索引，通过当前页和每页显示记录条数计算获得 */
	private int startIndex;

	private List<T> list = new ArrayList<>();

	public Page() {
	}

	/**
	 * 两个参数的构造方法，调用该构造方法需要另行设置结果list
	 * 
	 * @param page(当前页)
	 * @param totalCount(总记录)
	 */
	public Page(int page, int totalCount) {
		setPage(page);
		setTotalCount(totalCount);
		calculate();
	}

	/**
	 * 说明 : 能够设置一页显示多少条记录的构造方法
	 * 
	 * @param page(当前页)
	 * @param totalCount(总记录数)
	 * @param pageSize(每页最多显示的记录条数)
	 */
	public Page(int page, int totalCount, int pageSize) {
		super();
		setPage(page);
		setTotalCount(totalCount);
		setPageSize(pageSize);
		calculate();
	}

	/** 计算开始索引和总页数 参数设置完后调用该方法 */
	public void calculate() {
		// 计算总页数
		this.countPage = (totalCount % pageSize == 0) ? (totalCount / pageSize) : (totalCount / pageSize + 1);
		// 校正当前页
		if (page < 1 || countPage == 0) {
			page = 1;
		}
		if (countPage != 0 && page > countPage) {
			page = countPage;
		}
		// 计算开始索引(Oracle起始索引是 1,备注: 此次DB是Oracle)
		this.startIndex = (page - 1) * pageSize + 1;

	}

	public int getCountPage() {
		return countPage;
	}

	public void setCountPage(int countPage) {
		this.countPage = countPage;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		if (page > 0) {
			this.page = page;
		}
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		if (pageSize > 0) {
			this.pageSize = pageSize;
		}
	}

	public int getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public List<T> getList() {
		return list == null ? new ArrayList<T>() : list;
	}
}
