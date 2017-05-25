package com.yd.gcj.entity.vo;

import com.yd.gcj.entity.YdMangerBankTR;

public class YdMangerBankTRVo extends YdMangerBankTR {
	
	//充值用户的id
	private Integer user_id;
	
	//充值用户名称
	private String user_account;
	
	//充值用户姓名
	private String user_name;
	
	//充值用户昵称
	private String nickname;
	
	//充值用户手机号码
	private String user_phone;
	
	//充值用户邮箱
	private String user_email;
	
	//充值用户的总金额
	private float user_cprice;
	
	//本次充值金额
	private float user_recharge_price;
	
	//充值次数
	private Integer user_recharge_num;
	
	//类型
	private String btrType;
	
	public String getBtrType() {
		
		if(btrType==null||btrType.isEmpty()){
			setBtrType("未设置");
		}else{
			switch (super.getBtr_type()) {
			case 0:
				setBtrType("充值");
				break;
			case 1:
				setBtrType("转账 ");
				break;
			case 2:
				setBtrType("退款");
				break;
			default:
				setBtrType("未设置");
				break;
			}
		}
		return btrType;
	}

	public void setBtrType(String btrType) {
		this.btrType = btrType;
	}

	//银行名称
	private String bank_name;

	public String getBank_name() {
		return bank_name;
	}

	public void setBank_name(String bank_name) {
		this.bank_name = bank_name;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public String getUser_account() {
		return user_account;
	}

	public void setUser_account(String user_account) {
		this.user_account = user_account;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getUser_phone() {
		return user_phone;
	}

	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}

	public String getUser_email() {
		return user_email;
	}

	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}

	public float getUser_cprice() {
		return user_cprice;
	}

	public void setUser_cprice(float user_cprice) {
		this.user_cprice = user_cprice;
	}

	public float getUser_recharge_price() {
		return user_recharge_price;
	}

	public void setUser_recharge_price(float user_recharge_price) {
		this.user_recharge_price = user_recharge_price;
	}

	public Integer getUser_recharge_num() {
		return user_recharge_num;
	}

	public void setUser_recharge_num(Integer user_recharge_num) {
		this.user_recharge_num = user_recharge_num;
	}
	
	
}
