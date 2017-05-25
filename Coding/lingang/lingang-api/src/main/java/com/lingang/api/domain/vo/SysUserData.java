package com.lingang.api.domain.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.lingang.api.domain.entity.SysUser;

public class SysUserData implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6959409582606831351L;
	
	List<SysUser> data=new ArrayList<SysUser>();

	public List<SysUser> getData() {
		return data;
	}

	public void setData(List<SysUser> data) {
		this.data = data;
	}

	
}
