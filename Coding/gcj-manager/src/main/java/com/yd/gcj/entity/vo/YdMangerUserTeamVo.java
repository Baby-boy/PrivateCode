package com.yd.gcj.entity.vo;

import java.util.List;

import com.yd.gcj.entity.YdMangerUserTeam;
import com.yd.gcj.entity.YdMangerUserTeamEmpr;

public class YdMangerUserTeamVo extends YdMangerUserTeam {
	/**团队人员信息*/
	private List<YdMangerUserTeamEmpr> emprs;

	/**
	 * 获取 团队人员信息
	 * @return emprs
	 */
	public List<YdMangerUserTeamEmpr> getEmprs() {
		return emprs;
	}
	/**
	 * 设置 团队人员信息
	 * @param emprs
	 */
	public void setEmprs(List<YdMangerUserTeamEmpr> emprs) {
		this.emprs = emprs;
	}
}
