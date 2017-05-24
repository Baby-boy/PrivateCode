package com.glanway.ctrlhr.entity.para;

/**
 * 说明 : 临时导出考勤记录数据 TODO
 * 
 * @author 付其浩
 * @version 1.0.0
 * @dateTime 2017年4月24日 上午11:58:30
 */
public class SignPara {

	private String keyword;// 搜索关键字

	private String dateStart;// 开始考勤时间

	private String dateEnd;// 结束考勤时间

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getDateStart() {
		return dateStart;
	}

	public void setDateStart(String dateStart) {
		this.dateStart = dateStart;
	}

	public String getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(String dateEnd) {
		this.dateEnd = dateEnd;
	}

}
