package com.lingang.api.domain.para;

import java.io.Serializable;

public class SysUmanagerPara implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4425856509576085149L;

	Integer objId;
	
	String userAccount;

	public Integer getObjId() {
		return objId;
	}

	public void setObjId(Integer objId) {
		this.objId = objId;
	}

	public String getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}
	
}
