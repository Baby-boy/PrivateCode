package com.yd.gcj.entity;

import java.util.Date;
import java.util.List;

public class YdMangerUser {

	private Integer user_id;//编号(主键)
	private Integer user_level = 0;//等级
	private Integer user_sex = 0;//性别
	private String user_account;//用户账号
	private String user_pwd;//密码
	private String user_ppwd;//支付密码
	private String user_name;//姓名
	private String user_nickname;//昵称
	private String user_avatar;//头像
	private String user_phone;//手机号码
	private String user_email;//邮箱
	private String user_qqtoken;//QQtoken
	private String user_wxtoken;//微信token
	private Integer user_provid;//省码
	private Integer user_cityid;//市码
	private Integer user_areaid;//区码
	private String user_pca;//省-市-县（区）
	private String user_address;//详细地址
	private float user_cprice;//账户金额
	private Integer user_pcn;//好评数
	private Integer user_cstate;//服务商团队认证状态（0未认证 1认证成功 2认证失败 3认证审核中）
	private Integer user_skillstate;//技能认证状态(0未认证 1认证成功 2认证失败 3审核中)
	private Integer user_jobtitle;//服务商职称认证(0未认证 1认证成功 2认证失败 3审核中)
	private Integer user_type;//用户类型 0雇主 1设计师(个人) 2设计师（公司）
	private Integer user_verified;//实名认证标识（0未认证，1已认证，2失败，3提交审核中）
	private String user_ename;//公司名称
	private Integer user_otype;//接单类型：0全职 1较少 2较多
	private Date user_birthday;//生日
	private Integer user_taskcount = 0;//服务商用户完成任务数量
	private String user_resume;//个人简历
	private Integer user_isdel;//是否冻结
	private Date user_create_time;
	private Date user_update_time;
	
	/**标签关联关系信息*/
	private List<YdMangerUserLabel> userLabels;
	
	public Integer getUser_isdel() {
		return user_isdel;
	}

	public void setUser_isdel(Integer user_isdel) {
		this.user_isdel = user_isdel;
	}

	/**
	 * 获取 标签关联关系信息
	 * @return userLabels
	 */
	public List<YdMangerUserLabel> getUserLabels() {
		return userLabels;
	}
	
	/**
	 * 设置 标签关联关系信息
	 * @param userLabels
	 */
	public void setUserLabels(List<YdMangerUserLabel> userLabels) {
		this.userLabels = userLabels;
	}
	
	/**
	 * 获取 编号(主键)
	 * @return user_id
	 */
	public Integer getUser_id() {
		return user_id;
	}
	/**
	 * 设置 编号(主键)
	 * @param user_id
	 */
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	/**
	 * 获取 等级
	 * @return user_level
	 */
	public Integer getUser_level() {
		return user_level;
	}
	/**
	 * 设置 等级
	 * @param user_level
	 */
	public void setUser_level(Integer user_level) {
		this.user_level = user_level;
	}
	/**
	 * 获取 性别
	 * @return user_sex
	 */
	public Integer getUser_sex() {
		return user_sex;
	}
	/**
	 * 设置 性别
	 * @param user_sex
	 */
	public void setUser_sex(Integer user_sex) {
		this.user_sex = user_sex;
	}
	/**
	 * 获取 账号
	 * @return user_account
	 */
	public String getUser_account() {
		return user_account;
	}
	/**
	 * 设置 账号
	 * @param user_account
	 */
	public void setUser_account(String user_account) {
		this.user_account = user_account;
	}
	/**
	 * 获取 密码
	 * @return user_pwd
	 */
	public String getUser_pwd() {
		return user_pwd;
	}
	/**
	 * 设置 密码
	 * @param user_pwd
	 */
	public void setUser_pwd(String user_pwd) {
		this.user_pwd = user_pwd;
	}
	/**
	 * 获取 支付密码
	 * @return user_ppwd
	 */
	public String getUser_ppwd() {
		return user_ppwd;
	}
	/**
	 * 设置 支付密码
	 * @param user_ppwd
	 */
	public void setUser_ppwd(String user_ppwd) {
		this.user_ppwd = user_ppwd;
	}
	/**
	 * 获取 姓名
	 * @return user_name
	 */
	public String getUser_name() {
		if(user_name==null){
			user_name = "未实名认证";
		}
		return user_name;
	}
	/**
	 * 设置 姓名
	 * @param user_name
	 */
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	/**
	 * 获取 昵称
	 * @return user_nickname
	 */
	public String getUser_nickname() {
		if(user_nickname==null){
			user_nickname = "未设置";
		}
		return user_nickname;
	}
	/**
	 * 设置 昵称
	 * @param user_nickname
	 */
	public void setUser_nickname(String user_nickname) {
		this.user_nickname = user_nickname;
	}
	/**
	 * 获取 头像图片地址
	 * @return user_avatar
	 */
	public String getUser_avatar() {
		return user_avatar;
	}
	/**
	 * 设置 头像图片地址
	 * @param user_avatar
	 */
	public void setUser_avatar(String user_avatar) {
		this.user_avatar = user_avatar;
	}
	/**
	 * 获取 手机号
	 * @return user_phone
	 */
	public String getUser_phone() {
		return user_phone;
	}
	/**
	 * 设置 手机号
	 * @param user_phone
	 */
	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}
	/**
	 * 获取 邮箱
	 * @return user_email
	 */
	public String getUser_email() {
		if(user_email==null){
			user_email = "未绑定";
		}
		return user_email;
	}
	/**
	 * 设置 邮箱
	 * @param user_email
	 */
	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}
	/**
	 * 获取 QQtoken
	 * @return user_qqtoken
	 */
	public String getUser_qqtoken() {
		if(user_qqtoken==null){
			user_qqtoken = "未绑定";
		}
		return user_qqtoken;
	}
	/**
	 * 设置 QQtoken
	 * @param user_qqtoken
	 */
	public void setUser_qqtoken(String user_qqtoken) {
		this.user_qqtoken = user_qqtoken;
	}
	/**
	 * 获取 微信token
	 * @return user_wxtoken
	 */
	public String getUser_wxtoken() {
		return user_wxtoken;
	}
	/**
	 * 设置 微信token
	 * @param user_wxtoken
	 */
	public void setUser_wxtoken(String user_wxtoken) {
		this.user_wxtoken = user_wxtoken;
	}
	/**
	 * 获取 省码
	 * @return user_provid
	 */
	public Integer getUser_provid() {
		return user_provid;
	}
	/**
	 * 设置 省码
	 * @param user_provid
	 */
	public void setUser_provid(Integer user_provid) {
		this.user_provid = user_provid;
	}
	/**
	 * 获取 市码
	 * @return user_cityid
	 */
	public Integer getUser_cityid() {
		return user_cityid;
	}
	/**
	 * 设置 市码
	 * @param user_cityid
	 */
	public void setUser_cityid(Integer user_cityid) {
		this.user_cityid = user_cityid;
	}
	/**
	 * 获取 县（区）码
	 * @return user_areaid
	 */
	public Integer getUser_areaid() {
		return user_areaid;
	}
	/**
	 * 设置 县（区）码
	 * @param user_areaid
	 */
	public void setUser_areaid(Integer user_areaid) {
		this.user_areaid = user_areaid;
	}
	/**
	 * 获取 省-市-县（区）
	 * @return user_pca
	 */
	public String getUser_pca() {
		if(user_pca==null){
			user_pca = "未设置";
		}
		return user_pca;
	}
	/**
	 * 设置  省-市-县（区）
	 * @param user_pca
	 */
	public void setUser_pca(String user_pca) {
		this.user_pca = user_pca;
	}
	/**
	 * 获取 详细地址
	 * @return user_address
	 */
	public String getUser_address() {
		return user_address;
	}
	/**
	 * 设置 详细地址
	 * @param user_address
	 */
	public void setUser_address(String user_address) {
		this.user_address = user_address;
	}
	
	/**
	 * 获取 user_cprice
	 * @return user_cprice
	 */
	public float getUser_cprice() {
		return user_cprice;
	}
	
	/**
	 * 设置 user_cprice
	 * @param user_cprice
	 */
	public void setUser_cprice(float user_cprice) {
		this.user_cprice = user_cprice;
	}
	/**
	 * 获取 好评数
	 * @return user_pcn
	 */
	public Integer getUser_pcn() {
		return user_pcn;
	}
	/**
	 * 设置 好评数
	 * @param user_pcn
	 */
	public void setUser_pcn(Integer user_pcn) {
		this.user_pcn = user_pcn;
	}
	/**
	 * 获取 服务商认证状态（0未认证 1认证成功 2认证失败 3认证审核中）
	 * @return user_cstate
	 */
	public Integer getUser_cstate() {
		return user_cstate;
	}
	/**
	 * 设置 服务商认证状态（0未认证 1认证成功 2认证失败 3认证审核中）
	 * @param user_cstate
	 */
	public void setUser_cstate(Integer user_cstate) {
		this.user_cstate = user_cstate;
	}
	/**
	 * 获取 技能认证状态(0未认证 1认证成功 2认证失败 3审核中)
	 * @return user_skillstate
	 */
	public Integer getUser_skillstate() {
		return user_skillstate;
	}
	/**
	 * 设置 技能认证状态(0未认证 1认证成功 2认证失败 3审核中)
	 * @param user_skillstate
	 */
	public void setUser_skillstate(Integer user_skillstate) {
		this.user_skillstate = user_skillstate;
	}
	
	/**
	 * 获取 user_jobtitle
	 * @return user_jobtitle
	 */
	public Integer getUser_jobtitle() {
		return user_jobtitle;
	}
	
	/**
	 * 设置 user_jobtitle
	 * @param user_jobtitle
	 */
	public void setUser_jobtitle(Integer user_jobtitle) {
		this.user_jobtitle = user_jobtitle;
	}
	
	/**
	 * 获取 用户类型 0雇主 1设计师(个人) 2设计师（公司）
	 * @return user_type
	 */
	public Integer getUser_type() {
		return user_type;
	}
	/**
	 * 设置 用户类型 0雇主 1设计师(个人) 2设计师（公司）
	 * @param user_type
	 */
	public void setUser_type(Integer user_type) {
		this.user_type = user_type;
	}
	
	/**
	 * 获取 用户实名认证标识
	 * @return user_verified
	 */
	public Integer getUser_verified() {
		return user_verified;
	}
	

	/**
	 * 设置 用户实名认证标识
	 * @param user_verified
	 */
	public void setUser_verified(Integer user_verified) {
		this.user_verified = user_verified;
	}
	

	/**
	 * 获取 公司名称
	 * @return user_ename
	 */
	public String getUser_ename() {
		return user_ename;
	}
	/**
	 * 设置 公司名称
	 * @param user_ename
	 */
	public void setUser_ename(String user_ename) {
		this.user_ename = user_ename;
	}
	/**
	 * 获取 接单类型：0全职 1较少 2较多
	 * @return user_otype
	 */
	public Integer getUser_otype() {
		return user_otype;
	}
	/**
	 * 设置 接单类型：0全职 1较少 2较多
	 * @param user_otype
	 */
	public void setUser_otype(Integer user_otype) {
		this.user_otype = user_otype;
	}
	/**
	 * 获取 user_birthday
	 * @return user_birthday
	 */
	public Date getUser_birthday() {
		return user_birthday;
	}
	/**
	 * 设置 user_birthday
	 * @param user_birthday
	 */
	public void setUser_birthday(Date user_birthday) {
		this.user_birthday = user_birthday;
	}
	/**
	 * 获取 user_taskcount
	 * @return user_taskcount
	 */
	public Integer getUser_taskcount() {
		return user_taskcount;
	}
	/**
	 * 设置 user_taskcount
	 * @param user_taskcount
	 */
	public void setUser_taskcount(Integer user_taskcount) {
		this.user_taskcount = user_taskcount;
	}
	/**
	 * 获取 个人简历
	 * @return user_resume
	 */
	public String getUser_resume() {
		return user_resume;
	}
	/**
	 * 设置 个人简历
	 * @param user_resume
	 */
	public void setUser_resume(String user_resume) {
		this.user_resume = user_resume;
	}
	
	/**
	 * 获取 user_create_time
	 * @return user_create_time
	 */
	public Date getUser_create_time() {
		return user_create_time;
	}
	

	/**
	 * 设置 user_create_time
	 * @param user_create_time
	 */
	public void setUser_create_time(Date user_create_time) {
		this.user_create_time = user_create_time;
	}
	

	/**
	 * 获取 user_update_time
	 * @return user_update_time
	 */
	public Date getUser_update_time() {
		return user_update_time;
	}
	

	/**
	 * 设置 user_update_time
	 * @param user_update_time
	 */
	public void setUser_update_time(Date user_update_time) {
		this.user_update_time = user_update_time;
	}
	
}