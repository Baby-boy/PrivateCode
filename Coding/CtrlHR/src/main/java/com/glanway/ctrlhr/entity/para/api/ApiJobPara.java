package com.glanway.ctrlhr.entity.para.api;

/**
 * 说明 : 接口服务职位列表接收参数
 * 
 * @author fuqihao
 * @version 1.0.0
 * @dateTime 2017年6月13日 下午5:51:46
 */
public class ApiJobPara extends ApiBasePara {

	private String keyword;// 搜索关键字

	private String deptIds;// 部门ID(备注: 多个ID使用","分隔)

	private String[] deptIdList;// 部门ID数组

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getDeptIds() {
		return deptIds;
	}

	public void setDeptIds(String deptIds) {
		this.deptIds = deptIds;
	}

	public String[] getDeptIdList() {
		return deptIdList;
	}

	public void setDeptIdList(String[] deptIdList) {
		this.deptIdList = deptIdList;
	}

}
