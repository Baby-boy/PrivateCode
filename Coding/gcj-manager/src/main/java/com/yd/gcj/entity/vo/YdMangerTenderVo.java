package com.yd.gcj.entity.vo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.yd.gcj.entity.YdMangerCases;
import com.yd.gcj.entity.YdMangerSpcs;
import com.yd.gcj.entity.YdMangerTender;
import com.yd.gcj.entity.YdMangerUserLabel;

public class YdMangerTenderVo extends YdMangerTender{
	/**服务商等级描述*/
	private String level;
	/**等级图标地址*/
	private String levelIcoPath;
	/**投标状态描述*/
	private String state;
	/**服务商投标角色描述*/
	private String type;
	/**投标时间格式化*/
	private String create_time;
	/**更新时间格式化*/
	private String update_time;
	
	/**投标用户信息*/
	private YdMangerUserVo user;
	
	/**投标服务商技能标签*/
	private List<YdMangerUserLabel> labelVos;
	/**投标服务商案例*/
	private List<YdMangerCases> cases;
	/**投标服务商好评信息*/
	private List<YdMangerSpcs> spcs;
	
	/**投标任务名称*/
	private String task_pname;
	
	/**服务商姓名*/
	private String user_name;
	
	
	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getTask_pname() {
		return task_pname;
	}

	public void setTask_pname(String task_pname) {
		this.task_pname = task_pname;
	}

	/**
	 * 获取 服务商等级描述
	 * @return level
	 */
	public String getLevel() {
		switch (super.getTender_ulevel()) {
				case 1:	
					setLevel("中级设计师");
					break;
				case 2:	
					setLevel("高级设计师");
					break;
				case 3:	
					setLevel("资深设计师");
					break;
				case 4:	
					setLevel("专家设计师");
					break;
				case 5:	
					setLevel("设计大师");
					break;
				case 0:
				default:
					setLevel("初级设计师");
					break;
			}
		return level;
	}
	
	/**
	 * 设置 服务商等级描述
	 * @param level
	 */
	public void setLevel(String level) {
		this.level = level;
	}
	
	/**
	 * 获取 等级图标地址
	 * @return levelIcoPath
	 */
	public String getLevelIcoPath() {
		if(levelIcoPath==null || levelIcoPath.isEmpty()){
			switch (super.getTender_ulevel()) {
				case 1:	
					setLevelIcoPath("/images/work/v1.png");
					break;
				case 2:	
					setLevelIcoPath("/images/work/v2.png");
					break;
				case 3:	
					setLevelIcoPath("/images/work/v3.png");
					break;
				case 4:	
					setLevelIcoPath("/images/work/v4.png");
					break;
				case 5:	
					setLevelIcoPath("/images/work/v5.png");
					break;
				case 0:
				default:
					setLevelIcoPath("/images/work/v.png");
					break;
				}
		}
		return levelIcoPath;
	}
	
	/**
	 * 设置 等级图标地址
	 * @param levelIcoPath
	 */
	public void setLevelIcoPath(String levelIcoPath) {
		this.levelIcoPath = levelIcoPath;
	}
	

	/**
	 * 获取 投标状态描述
	 * @return state
	 */
	public String getState() {
		switch (super.getTender_state()) {
		case 0:
			state = "修改投标资料";
			break;
		case 1:
			state = "已中标";
			break;
		case 2:
			state = "未中标";
			break;
		default:
			state = "修改投标资料";
			break;
		}
		return state;
	}
	
	/**
	 * 设置 投标状态描述
	 * @param state
	 */
	public void setState(String state) {
		this.state = state;
	}
	
	/**
	 * 获取 服务商投标角色描述
	 * @return type
	 */
	public String getType() {
		switch (super.getTender_type()) {
		case 0:
			type = "组员";
			break;
		case 1:
			type = "组长";
			break;
		default:
			type = "组员";
			break;
		}
		return type;
	}
	
	/**
	 * 设置 服务商投标角色描述
	 * @param type
	 */
	public void setType(String type) {
		this.type = type;
	}
	
	/**
	 * 获取 投标时间格式化
	 * @return create_time
	 */
	public String getCreate_time() {
		if(super.getTender_create_time() != null){
			create_time = dateFormat(super.getTender_create_time());
		}
		return create_time;
	}
	
	/**
	 * 设置 投标时间格式化
	 * @param create_time
	 */
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	
	/**
	 * 获取 更新时间格式化
	 * @return update_time
	 */
	public String getUpdate_time() {
		if(super.getTender_update_time()!=null){
			update_time = dateFormat(super.getTender_create_time());
		}
		return update_time;
	}
	
	/**
	 * 设置 更新时间格式化
	 * @param update_time
	 */
	public void setUpdate_time(String update_time) {
		this.update_time = update_time;
	}
	
	private String dateFormat(Date date){
		String datestr = "";
		SimpleDateFormat scf = new SimpleDateFormat("yyyy年MM月dd日");
		datestr = scf.format(date);
		return datestr;
	}

	/**
	 * 获取 投标用户信息
	 * @return user
	 */
	public YdMangerUserVo getUser() {
		return user;
	}

	/**
	 * 设置 投标用户信息
	 * @param user
	 */
	public void setUser(YdMangerUserVo user) {
		this.user = user;
	}

	/**
	 * 获取 投标服务商技能标签
	 * @return labelVos
	 */
	public List<YdMangerUserLabel> getLabelVos() {
		return labelVos;
	}
	
	/**
	 * 设置 投标服务商技能标签
	 * @param labelVos
	 */
	public void setLabelVos(List<YdMangerUserLabel> labelVos) {
		this.labelVos = labelVos;
	}
	
	/**
	 * 获取 投标服务商案例
	 * @return cases
	 */
	public List<YdMangerCases> getCases() {
		return cases;
	}
	
	/**
	 * 设置 投标服务商案例
	 * @param cases
	 */
	public void setCases(List<YdMangerCases> cases) {
		this.cases = cases;
	}
	
	/**
	 * 获取 投标服务商好评信息
	 * @return spcs
	 */
	public List<YdMangerSpcs> getSpcs() {
		return spcs;
	}
	
	/**
	 * 设置 投标服务商好评信息
	 * @param spcs
	 */
	public void setSpcs(List<YdMangerSpcs> spcs) {
		this.spcs = spcs;
	}
	
	
}
