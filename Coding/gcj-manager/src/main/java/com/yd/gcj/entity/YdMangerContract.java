package com.yd.gcj.entity;

import java.util.Date;

public class YdMangerContract {

	/**编号（主键）*/
	private Integer contract_id;
	/**雇主id*/
	private Integer contract_eid;
	/**任务id*/
	private Integer contract_tid;
	/**甲方（雇主）地址*/
	private String contract_eaddr;
	/**甲方（雇主）姓名*/
	private String contract_ename;
	/**甲方（雇主）手机号*/
	private String contract_ephone;
	/**乙方（服务方）id*/
	private Integer contract_sid;
	/**乙方（服务方）姓名*/
	private String contract_sname;
	/**乙方（服务方）地址*/
	private String contract_saddr;
	/**乙方（服务方）手机号	*/
	private String contract_sphone;
	/**项目名称/代码*/
	private String contract_pname;
	/**项目佣金*/
	private float contract_price;
	/**工程描述*/
	private String contract_pdesc;
	/**工期开始日期*/
	private Date contract_pdesc_start;
	/**工期结束日期*/
	private Date contract_pdesc_end;
	/**保密协议*/
	private String contract_cp;
	/**服务协议*/
	private String contract_sp;
	/**服务内容及深度要求*/
	private String contract_scsd;
	/**工期要求*/
	private String contract_durat;
	/**甲方需要提交的资料*/
	private String contract_pad;
	/**乙方需要提交的成果*/
	private String contract_pbr;
	/**甲方权利与责任*/
	private String contract_paresp;
	/**乙方权利与责任*/
	private String contract_pbresp;
	/**合同款及支付*/
	private String contract_pay;
	/**违约责任*/
	private String contract_dal;
	/**合同生效及其他*/
	private String contract_ce;
	/**合同下载状态*/
	private Integer contract_ds;
	/**合同雇主签字*/
	private String contract_esig;
	/**合同雇主代签名*/
	private String contract_esiga;
	/**雇主签名时间*/
	private String contract_esigd;
	/**雇主是否签订*/
	private Integer contract_eissign;
	/**合同服务商签名*/
	private String contract_ssig;
	/**合同服务商代签名*/
	private String contract_ssiga;
	/**服务商签名时间*/
	private String contract_ssigd;
	/**服务商是否签订*/
	private Integer contract_sissign;
	/**合同补充*/
	private String contract_supp;
	/**信息创建时间*/
	private Date contract_create_time;
	/**信息更新时间*/
	private Date contract_update_time;
	
	
	/**
	 * 获取 编号（主键）
	 * @return contract_id
	 */
	public Integer getContract_id() {
		return contract_id;
	}
	
	/**
	 * 设置 编号（主键）
	 * @param contract_id
	 */
	public void setContract_id(Integer contract_id) {
		this.contract_id = contract_id;
	}
	
	/**
	 * 获取 雇主id
	 * @return contract_eid
	 */
	public Integer getContract_eid() {
		return contract_eid;
	}
	
	/**
	 * 设置 雇主id
	 * @param contract_eid
	 */
	public void setContract_eid(Integer contract_eid) {
		this.contract_eid = contract_eid;
	}
	
	/**
	 * 获取 任务id
	 * @return contract_tid
	 */
	public Integer getContract_tid() {
		return contract_tid;
	}
	
	/**
	 * 设置 任务id
	 * @param contract_tid
	 */
	public void setContract_tid(Integer contract_tid) {
		this.contract_tid = contract_tid;
	}
	
	/**
	 * 获取 甲方（雇主）地址
	 * @return contract_eaddr
	 */
	public String getContract_eaddr() {
		return contract_eaddr;
	}
	
	/**
	 * 设置 甲方（雇主）地址
	 * @param contract_eaddr
	 */
	public void setContract_eaddr(String contract_eaddr) {
		this.contract_eaddr = contract_eaddr;
	}
	
	/**
	 * 获取 甲方（雇主）姓名
	 * @return contract_ename
	 */
	public String getContract_ename() {
		return contract_ename;
	}
	
	/**
	 * 设置 甲方（雇主）姓名
	 * @param contract_ename
	 */
	public void setContract_ename(String contract_ename) {
		this.contract_ename = contract_ename;
	}
	
	/**
	 * 获取 甲方（雇主）手机号
	 * @return contract_ephone
	 */
	public String getContract_ephone() {
		return contract_ephone;
	}
	
	/**
	 * 设置 甲方（雇主）手机号
	 * @param contract_ephone
	 */
	public void setContract_ephone(String contract_ephone) {
		this.contract_ephone = contract_ephone;
	}
	
	/**
	 * 获取 乙方（服务方）id
	 * @return contract_sid
	 */
	public Integer getContract_sid() {
		return contract_sid;
	}
	
	/**
	 * 设置 乙方（服务方）id
	 * @param contract_sid
	 */
	public void setContract_sid(Integer contract_sid) {
		this.contract_sid = contract_sid;
	}
	
	/**
	 * 获取 乙方（服务方）姓名
	 * @return contract_sname
	 */
	public String getContract_sname() {
		return contract_sname;
	}
	
	/**
	 * 设置 乙方（服务方）姓名
	 * @param contract_sname
	 */
	public void setContract_sname(String contract_sname) {
		this.contract_sname = contract_sname;
	}
	
	/**
	 * 获取 乙方（服务方）地址
	 * @return contract_saddr
	 */
	public String getContract_saddr() {
		return contract_saddr;
	}
	
	/**
	 * 设置 乙方（服务方）地址
	 * @param contract_saddr
	 */
	public void setContract_saddr(String contract_saddr) {
		this.contract_saddr = contract_saddr;
	}
	
	/**
	 * 获取 乙方（服务方）手机号
	 * @return contract_sphone
	 */
	public String getContract_sphone() {
		return contract_sphone;
	}
	
	/**
	 * 设置 乙方（服务方）手机号
	 * @param contract_sphone
	 */
	public void setContract_sphone(String contract_sphone) {
		this.contract_sphone = contract_sphone;
	}
	
	/**
	 * 获取 项目名称代码
	 * @return contract_pname
	 */
	public String getContract_pname() {
		return contract_pname;
	}
	
	/**
	 * 设置 项目名称代码
	 * @param contract_pname
	 */
	public void setContract_pname(String contract_pname) {
		this.contract_pname = contract_pname;
	}
	
	/**
	 * 获取 项目佣金
	 * @return contract_price
	 */
	public float getContract_price() {
		return contract_price;
	}
	

	/**
	 * 设置 项目佣金
	 * @param contract_price
	 */
	public void setContract_price(float contract_price) {
		this.contract_price = contract_price;
	}
	

	/**
	 * 获取 工程描述
	 * @return contract_pdesc
	 */
	public String getContract_pdesc() {
		return contract_pdesc;
	}
	
	/**
	 * 设置 工程描述
	 * @param contract_pdesc
	 */
	public void setContract_pdesc(String contract_pdesc) {
		this.contract_pdesc = contract_pdesc;
	}
	
	/**
	 * 获取 工期开始日期
	 * @return contract_pdesc_start
	 */
	public Date getContract_pdesc_start() {
		return contract_pdesc_start;
	}
	

	/**
	 * 设置 工期开始日期
	 * @param contract_pdesc_start
	 */
	public void setContract_pdesc_start(Date contract_pdesc_start) {
		this.contract_pdesc_start = contract_pdesc_start;
	}
	

	/**
	 * 获取 工期结束日期
	 * @return contract_pdesc_end
	 */
	public Date getContract_pdesc_end() {
		return contract_pdesc_end;
	}
	

	/**
	 * 设置 工期结束日期
	 * @param contract_pdesc_end
	 */
	public void setContract_pdesc_end(Date contract_pdesc_end) {
		this.contract_pdesc_end = contract_pdesc_end;
	}
	

	/**
	 * 获取 保密协议
	 * @return contract_cp
	 */
	public String getContract_cp() {
		return contract_cp;
	}
	
	/**
	 * 设置 保密协议
	 * @param contract_cp
	 */
	public void setContract_cp(String contract_cp) {
		this.contract_cp = contract_cp;
	}
	
	/**
	 * 获取 服务协议
	 * @return contract_sp
	 */
	public String getContract_sp() {
		return contract_sp;
	}
	
	/**
	 * 设置 服务协议
	 * @param contract_sp
	 */
	public void setContract_sp(String contract_sp) {
		this.contract_sp = contract_sp;
	}
	
	/**
	 * 获取 服务内容及深度要求
	 * @return contract_scsd
	 */
	public String getContract_scsd() {
		return contract_scsd;
	}
	
	/**
	 * 设置 服务内容及深度要求
	 * @param contract_scsd
	 */
	public void setContract_scsd(String contract_scsd) {
		this.contract_scsd = contract_scsd;
	}
	
	/**
	 * 获取 工期要求
	 * @return contract_durat
	 */
	public String getContract_durat() {
		return contract_durat;
	}
	
	/**
	 * 设置 工期要求
	 * @param contract_durat
	 */
	public void setContract_durat(String contract_durat) {
		this.contract_durat = contract_durat;
	}
	
	/**
	 * 获取 甲方需要提交的资料
	 * @return contract_pad
	 */
	public String getContract_pad() {
		return contract_pad;
	}
	
	/**
	 * 设置 甲方需要提交的资料
	 * @param contract_pad
	 */
	public void setContract_pad(String contract_pad) {
		this.contract_pad = contract_pad;
	}
	
	/**
	 * 获取 乙方需要提交的成果
	 * @return contract_pbr
	 */
	public String getContract_pbr() {
		return contract_pbr;
	}
	
	/**
	 * 设置 乙方需要提交的成果
	 * @param contract_pbr
	 */
	public void setContract_pbr(String contract_pbr) {
		this.contract_pbr = contract_pbr;
	}
	
	/**
	 * 获取 甲方权利与责任
	 * @return contract_paresp
	 */
	public String getContract_paresp() {
		return contract_paresp;
	}
	
	/**
	 * 设置 甲方权利与责任
	 * @param contract_paresp
	 */
	public void setContract_paresp(String contract_paresp) {
		this.contract_paresp = contract_paresp;
	}
	
	/**
	 * 获取 乙方权利与责任
	 * @return contract_pbresp
	 */
	public String getContract_pbresp() {
		return contract_pbresp;
	}
	
	/**
	 * 设置 乙方权利与责任
	 * @param contract_pbresp
	 */
	public void setContract_pbresp(String contract_pbresp) {
		this.contract_pbresp = contract_pbresp;
	}
	
	/**
	 * 获取 合同款及支付
	 * @return contract_pay
	 */
	public String getContract_pay() {
		return contract_pay;
	}
	
	/**
	 * 设置 合同款及支付
	 * @param contract_pay
	 */
	public void setContract_pay(String contract_pay) {
		this.contract_pay = contract_pay;
	}
	
	/**
	 * 获取 违约责任
	 * @return contract_dal
	 */
	public String getContract_dal() {
		return contract_dal;
	}
	
	/**
	 * 设置 违约责任
	 * @param contract_dal
	 */
	public void setContract_dal(String contract_dal) {
		this.contract_dal = contract_dal;
	}
	
	/**
	 * 获取 合同生效及其他
	 * @return contract_ce
	 */
	public String getContract_ce() {
		return contract_ce;
	}
	
	/**
	 * 设置 合同生效及其他
	 * @param contract_ce
	 */
	public void setContract_ce(String contract_ce) {
		this.contract_ce = contract_ce;
	}
	
	/**
	 * 获取 合同下载状态
	 * @return contract_ds
	 */
	public Integer getContract_ds() {
		return contract_ds;
	}
	
	/**
	 * 设置 合同下载状态
	 * @param contract_ds
	 */
	public void setContract_ds(Integer contract_ds) {
		this.contract_ds = contract_ds;
	}
	
	/**
	 * 获取 合同雇主签字
	 * @return contract_esig
	 */
	public String getContract_esig() {
		return contract_esig;
	}
	
	/**
	 * 设置 合同雇主签字
	 * @param contract_esig
	 */
	public void setContract_esig(String contract_esig) {
		this.contract_esig = contract_esig;
	}
	
	/**
	 * 获取 合同雇主代签名
	 * @return contract_esiga
	 */
	public String getContract_esiga() {
		return contract_esiga;
	}
	
	/**
	 * 设置 合同雇主代签名
	 * @param contract_esiga
	 */
	public void setContract_esiga(String contract_esiga) {
		this.contract_esiga = contract_esiga;
	}
	
	/**
	 * 获取 雇主签名时间
	 * @return contract_esigd
	 */
	public String getContract_esigd() {
		return contract_esigd;
	}
	

	/**
	 * 获取 雇主是否签订
	 * @return contract_eissign
	 */
	public Integer getContract_eissign() {
		return contract_eissign;
	}
	
	/**
	 * 设置 雇主是否签订
	 * @param contract_eissign
	 */
	public void setContract_eissign(Integer contract_eissign) {
		this.contract_eissign = contract_eissign;
	}
	
	/**
	 * 获取 合同服务商签名
	 * @return contract_ssig
	 */
	public String getContract_ssig() {
		return contract_ssig;
	}
	
	/**
	 * 设置 合同服务商签名
	 * @param contract_ssig
	 */
	public void setContract_ssig(String contract_ssig) {
		this.contract_ssig = contract_ssig;
	}
	
	/**
	 * 获取 合同服务商代签名
	 * @return contract_ssiga
	 */
	public String getContract_ssiga() {
		return contract_ssiga;
	}
	
	/**
	 * 设置 合同服务商代签名
	 * @param contract_ssiga
	 */
	public void setContract_ssiga(String contract_ssiga) {
		this.contract_ssiga = contract_ssiga;
	}
	
	/**
	 * 获取 服务商签名时间
	 * @return contract_ssigd
	 */
	public String getContract_ssigd() {
		return contract_ssigd;
	}
	
	/**
	 * 设置 服务商签名时间
	 * @param contract_ssigd
	 */
	public void setContract_ssigd(String contract_ssigd) {
		this.contract_ssigd = contract_ssigd;
	}
	
	/**
	 * 设置 雇主签名时间
	 * @param contract_esigd
	 */
	public void setContract_esigd(String contract_esigd) {
		this.contract_esigd = contract_esigd;
	}
	

	/**
	 * 获取 服务商是否签订
	 * @return contract_sissign
	 */
	public Integer getContract_sissign() {
		return contract_sissign;
	}
	
	/**
	 * 设置 服务商是否签订
	 * @param contract_sissign
	 */
	public void setContract_sissign(Integer contract_sissign) {
		this.contract_sissign = contract_sissign;
	}
	
	/**
	 * 获取 合同补充
	 * @return contract_supp
	 */
	public String getContract_supp() {
		return contract_supp;
	}
	
	/**
	 * 设置 合同补充
	 * @param contract_supp
	 */
	public void setContract_supp(String contract_supp) {
		this.contract_supp = contract_supp;
	}
	
	/**
	 * 获取 信息创建时间
	 * @return contract_create_time
	 */
	public Date getContract_create_time() {
		return contract_create_time;
	}
	
	/**
	 * 设置 信息创建时间
	 * @param contract_create_time
	 */
	public void setContract_create_time(Date contract_create_time) {
		this.contract_create_time = contract_create_time;
	}
	
	/**
	 * 获取 信息更新时间
	 * @return contract_update_time
	 */
	public Date getContract_update_time() {
		return contract_update_time;
	}
	
	/**
	 * 设置 信息更新时间
	 * @param contract_update_time
	 */
	public void setContract_update_time(Date contract_update_time) {
		this.contract_update_time = contract_update_time;
	}
	
}
