package com.glanway.ctrlhr.entity.dept;

import java.util.List;

/**
 * 
 * @author 王晨
 * 说明 公司
 *
 */
public class ComVO  {

	private Long id; 
	private String name;
	private List<ComVO> ComVO;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<ComVO> getComVO() {
		return ComVO;
	}
	public void setComVO(List<ComVO> comVO) {
		ComVO = comVO;
	} 
	
	
	
}