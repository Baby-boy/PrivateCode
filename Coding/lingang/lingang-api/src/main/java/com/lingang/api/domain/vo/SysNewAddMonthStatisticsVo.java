package com.lingang.api.domain.vo;

import java.io.Serializable;

public class SysNewAddMonthStatisticsVo implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 5186440807437867827L;
	
    private String createYear;
	
    private String createMonth;
    
    private Integer countAdd;
   
    private Integer type;    //类型     1月              2季度

	public Integer getCountAdd() {
		return countAdd;
	}

	public void setCountAdd(Integer countAdd) {
		this.countAdd = countAdd;
	}

	public String getCreateYear() {
		return createYear;
	}

	public void setCreateYear(String createYear) {
		this.createYear = createYear;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getCreateMonth() {
		return createMonth;
	}

	public void setCreateMonth(String createMonth) {
		this.createMonth = createMonth;
	}

	
	
	
    
    
}