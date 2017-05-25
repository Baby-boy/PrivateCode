package com.lingang.api.domain.entity;

import java.io.Serializable;
import java.util.Date;

public class SysBasics implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 5186440807437867827L;

	private Integer basicsId;

    private Integer basicsType;

    private String basicsName;

    private Date createTime;

    private Date updateTime;

    private Integer basicsState;
    
    private String createYear;
    
    private String createMonth;
    
    private Integer countType;

    public Integer getBasicsId() {
        return basicsId;
    }

    public void setBasicsId(Integer basicsId) {
        this.basicsId = basicsId;
    }

    public Integer getBasicsType() {
        return basicsType;
    }

    public void setBasicsType(Integer basicsType) {
        this.basicsType = basicsType;
    }

    public String getBasicsName() {
        return basicsName;
    }

    public void setBasicsName(String basicsName) {
        this.basicsName = basicsName == null ? null : basicsName.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getBasicsState() {
        return basicsState;
    }

    public void setBasicsState(Integer basicsState) {
        this.basicsState = basicsState;
    }

	public Integer getCountType() {
		return countType;
	}

	public void setCountType(Integer countType) {
		this.countType = countType;
	}

	public String getCreateYear() {
		return createYear;
	}

	public void setCreateYear(String createYear) {
		this.createYear = createYear;
	}

	public String getCreateMonth() {
		return createMonth;
	}

	public void setCreateMonth(String createMonth) {
		this.createMonth = createMonth;
	}
    
    
}