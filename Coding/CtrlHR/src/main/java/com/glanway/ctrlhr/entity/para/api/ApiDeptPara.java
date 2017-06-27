package com.glanway.ctrlhr.entity.para.api;

/**
 * 说明 : 接口服务部门接收参数
 * 
 * @author fuqihao
 * @version 1.0.0
 * @dateTime 2017年6月13日 下午5:32:26
 */
public class ApiDeptPara extends ApiBasePara {

	private String keyword;// 搜索关键字

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

}
