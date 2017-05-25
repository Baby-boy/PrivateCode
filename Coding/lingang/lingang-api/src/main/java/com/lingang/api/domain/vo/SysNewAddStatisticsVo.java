package com.lingang.api.domain.vo;

import java.io.Serializable;
import java.util.List;

public class SysNewAddStatisticsVo implements Serializable{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = -7671460779277618817L;

	private List<SysNewAddMonthStatisticsVo> monthList;
	
    private List<SysNewAddQuarterStatisticsVo> quarterList;

	public List<SysNewAddMonthStatisticsVo> getMonthList() {
		return monthList;
	}

	public void setMonthList(List<SysNewAddMonthStatisticsVo> monthList) {
		this.monthList = monthList;
	}

	public List<SysNewAddQuarterStatisticsVo> getQuarterList() {
		return quarterList;
	}

	public void setQuarterList(List<SysNewAddQuarterStatisticsVo> quarterList) {
		this.quarterList = quarterList;
	}




   
    
    
}