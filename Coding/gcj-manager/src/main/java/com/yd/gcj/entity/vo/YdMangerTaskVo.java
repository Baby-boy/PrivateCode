package com.yd.gcj.entity.vo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.yd.gcj.entity.YdMangerContract;
import com.yd.gcj.entity.YdMangerTask;
import com.yd.gcj.entity.YdMangerTaskActive;
import com.yd.gcj.entity.YdMangerTaskLabel;
import com.yd.gcj.entity.YdMangerTaskType;
import com.yd.gcj.entity.YdMangerTaskTypeTR;

public class YdMangerTaskVo extends YdMangerTask {

	/**
	 * 任务状态
	 * 任务状态（0：发布审核中、1：审核通过（投标中）、2：投标结束，开始选标（投标时间截止）、3：编辑合同(并签订合同)、4：工作中、5：工作完成（并支付完尾款，待评价）、6：评价完成（任务完成）、7：退款申请、8：退款完成、9：审核失败.10:商议失败,重新）
	 */
	private String state;
	/** 托管描述 */
	private String hosting;
	/** 剩余天数 */
	private Integer remain = 0;
	/** 剩余小时 */
	private Integer remainH = 0;
	/** 剩余分钟 */
	private Integer remainM = 0;
	/** 剩余秒 */
	private Integer remainS = 0;
	/** 剩余总毫秒 */
	private long remainTime = 0;
	/** 获取剩余时间字符串 */
	private String remainDT = "0 天 0小时 0分 0秒";
	/** 时间是否格式化过 */
	private boolean isFormat = true;
	/** 投标人数 */
	private Integer tender_num = 0;
	// 投标信息
	private YdMangerTenderVo tender;
	/** 任务发布方用户昵称 */
	private String user_name;
	/** 任务发布方用户头像地址 */
	private String user_imgpath;
	/** 任务发布时间格式化描述 */
	private String startDateTime;
	/** 任务结束时间格式化描述 */
	private String endDateTime;
	/** 数据添加时间格式化描述 */
	private String createTime;
	/** 任务最近一次更新时间格式化描述 */
	private String updateTime;
	/** 任务类型 */
	private List<YdMangerTaskTypeTR> types;

	/***/
	private List<YdMangerTaskType> typess;

	/** 标签 */
	private List<YdMangerTaskLabel> labels;
	/** 任务标签 */
	private List<YdMangerLabelVo> labelVos;
	/** 任务评论信息 */
	private List<YdMangerTaskActive> actives;
	/** 参与投标的企业用户 */
	private List<YdMangerTenderVo> tenderVos;
	/** 是否收藏标识 */
	private Integer is_collect = 0;
	/** 是否申请标识 */
	private Integer is_apply = 0;
	/** 合同签订状态描述 */
	private String contract_state;
	/** 雇主合同状态描述 */
	private String contractStateE;
	/** 服务商合同状态描述 */
	private String contractStateS;

	/** 任务的合同信息 */
	private YdMangerContract contract;

	/** 雇主是否选定组长判断跳转链接 */
	private String learE_state;
	/** 服务商是否选定组长判断跳转链接 */
	private String learS_state;

	/** 任务雇主信息 */
	private YdMangerUserVo userVoE;
	/** 任务组长信息 */
	private YdMangerUserVo userVoS;

	public YdMangerContract getContract() {
		return contract;
	}

	public void setContract(YdMangerContract contract) {
		this.contract = contract;
	}

	/**
	 * 获取
	 * 任务状态（0：发布审核中、1：审核通过（投标中）、2：投标结束，开始选标（投标时间截止）、3：编辑合同(并签订合同)、4：工作中、5：工作完成（并支付完尾款，待评价）、6：评价完成（任务完成）、7：申请退款、8：退款完成、9：审核失败）
	 * 
	 * @return state
	 */
	public String getState() {
		switch (super.getTask_state()) {
		case 0:
			state = "审核中";
			break;
		case 1:
			state = "投标中";
			break;
		case 2:
			state = "选标中";
			break;
		case 3:
			state = "合同签订中";
			break;
		case 4:
			state = "工作中";
			break;
		case 5:
			state = "待评价";
			break;
		case 6:
			state = "任务完成";
			break;
		case 7:
			state = "申请退款";
			break;
		case 8:
			state = "退款已完成";
			break;
		case 9:
			state = "审核失败";
			break;
		}
		return state;
	}

	/**
	 * 设置 状态
	 * 
	 * @param state
	 */
	public void setState(String state) {
		this.state = state;
	}

	public String getContract_state() {
		switch (getTask_contract_state()) {
		case 0:
			contract_state = "签订合同";
			break;
		case 1:
			contract_state = "待雇主发起合同";
			break;
		case 2:
			contract_state = "待服务商确认";
			break;
		case 3:
			contract_state = "完成签订";
			break;
		case 4:
			contract_state = "合同不符，等待雇主重新提交资料.....！";
			break;
		default:
			contract_state = "合同签订";
			break;
		}
		return contract_state;
	}

	/**
	 * 获取 雇主合同状态描述
	 * 
	 * @return contractStateE
	 */
	public String getContractStateE() {
		switch (getTask_contract_state()) {
		case 0:
			contractStateE = "签订合同";
			break;
		case 1:
			contractStateE = "发起合同";
			break;
		case 2:
			contractStateE = "合同待确认";
			break;
		case 3:
			contractStateE = "合同签订完成";
			break;
		case 4:
			contractStateE = "合同被拒";
			break;
		default:
			contractStateE = "合同签订";
			break;
		}
		return contractStateE;
	}

	/**
	 * 获取 服务商合同状态描述
	 * 
	 * @return contractStateS
	 */
	public String getContractStateS() {
		switch (getTask_contract_state()) {
		case 0:
			contractStateS = "签订合同";
			break;
		case 1:
			contractStateS = "待发起合同";
			break;
		case 2:
			contractStateS = "确认合同";
			break;
		case 3:
			contractStateS = "完成签订完成";
			break;
		case 4:
			contractStateS = "合同已拒";
			break;
		default:
			contractStateS = "合同签订";
			break;
		}
		return contractStateS;
	}

	/**
	 * 设置 雇主合同状态描述
	 * 
	 * @param contractStateE
	 */
	public void setContractStateE(String contractStateE) {
		this.contractStateE = contractStateE;
	}

	/**
	 * 设置 服务商合同状态描述
	 * 
	 * @param contractStateS
	 */
	public void setContractStateS(String contractStateS) {
		this.contractStateS = contractStateS;
	}

	public void setContract_state(String contract_state) {
		this.contract_state = contract_state;
	}


	public String getLearE_state() {
		switch (getTask_lear_state()) {
		case 0:
			learE_state = "";
			break;
		case 1:
			learE_state = "";
			break;
		default:
			learE_state = "";
			break;
		}
		return learE_state;
	}

	public void setLearE_state(String learE_state) {
		this.learE_state = learE_state;
	}

	public String getLearS_state() {
		return learS_state;
	}

	public void setLearS_state(String learS_state) {
		this.learS_state = learS_state;
	}

	/**
	 * 获取 托管描述
	 * 
	 * @return hosting
	 */
	public String getHosting() {

		return hosting;
	}

	/**
	 * 设置 托管描述
	 * 
	 * @param hosting
	 */
	public void setHosting(String hosting) {
		this.hosting = hosting;
	}

	/**
	 * 获取 剩余天数
	 * 
	 * @return remain
	 */
	public Integer getRemain() {
		if (isFormat) {
			remainDateTime();
		}
		return remain;
	}

	/**
	 * 设置 剩余天数
	 * 
	 * @param remain
	 */
	public void setRemain(Integer remain) {
		this.remain = remain;
	}

	/**
	 * 获取 剩余小时
	 * 
	 * @return remainH
	 */
	public Integer getRemainH() {
		if (isFormat) {
			remainDateTime();
		}
		return remainH;
	}

	/**
	 * 设置 剩余小时
	 * 
	 * @param remainH
	 */
	public void setRemainH(Integer remainH) {
		this.remainH = remainH;
	}

	/**
	 * 获取 剩余分钟
	 * 
	 * @return remainM
	 */
	public Integer getRemainM() {
		if (isFormat) {
			remainDateTime();
		}
		return remainM;
	}

	/**
	 * 设置 剩余分钟
	 * 
	 * @param remainM
	 */
	public void setRemainM(Integer remainM) {
		this.remainM = remainM;
	}

	/**
	 * 获取 剩余秒
	 * 
	 * @return remainS
	 */
	public Integer getRemainS() {
		if (isFormat) {
			remainDateTime();
		}
		return remainS;
	}

	/**
	 * 设置 剩余秒
	 * 
	 * @param remainS
	 */
	public void setRemainS(Integer remainS) {
		this.remainS = remainS;
	}

	/**
	 * 获取 剩余总毫秒
	 * 
	 * @return remainTime
	 */
	public long getRemainTime() {
		if (isFormat) {
			remainDateTime();
		}
		return remainTime;
	}

	/**
	 * 设置 剩余总毫秒
	 * 
	 * @param remainTime
	 */
	public void setRemainTime(long remainTime) {
		this.remainTime = remainTime;
	}

	/**
	 * 获取 获取剩余时间字符串
	 * 
	 * @return remainDT
	 */
	public String getRemainDT() {
		if (isFormat) {
			remainDateTime();
		}
		return remainDT;
	}

	/**
	 * 设置 获取剩余时间字符串
	 * 
	 * @param remainDT
	 */
	public void setRemainDT(String remainDT) {
		this.remainDT = remainDT;
	}

	/**
	 * 获取 投标人数
	 * 
	 * @return tender_num
	 */
	public Integer getTender_num() {
		return tender_num;
	}

	/**
	 * 设置 投标人数
	 * 
	 * @param tender_num
	 */
	public void setTender_num(Integer tender_num) {
		this.tender_num = tender_num;
	}

	/**
	 * 获取 标签
	 * 
	 * @return labels
	 */
	public List<YdMangerTaskLabel> getLabels() {
		return labels;
	}

	/**
	 * 设置 标签
	 * 
	 * @param labels
	 */
	public void setLabels(List<YdMangerTaskLabel> labels) {
		this.labels = labels;
	}

	/**
	 * 获取 添加的字段
	 * 
	 * @return user_name
	 */
	public String getUser_name() {
		return user_name;
	}

	/**
	 * 设置 添加的字段
	 * 
	 * @param user_name
	 */
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	/**
	 * 获取 任务发布时间格式化描述
	 * 
	 * @return startDateTime
	 */
	public String getStartDateTime() {
		startDateTime = dateFormat(super.getTask_start_time());
		return startDateTime;
	}

	/**
	 * 设置 任务发布时间格式化描述
	 * 
	 * @param startDateTime
	 */
	public void setStartDateTime(String startDateTime) {
		this.startDateTime = startDateTime;
	}

	/**
	 * 获取 任务结束时间格式化描述
	 * 
	 * @return endDateTime
	 */
	public String getEndDateTime() {
		endDateTime = dateFormat(super.getTask_end_time());
		return endDateTime;
	}

	/**
	 * 设置 任务结束时间格式化描述
	 * 
	 * @param endDateTime
	 */
	public void setEndDateTime(String endDateTime) {
		this.endDateTime = endDateTime;
	}

	/**
	 * 获取 数据添加时间格式化描述
	 * 
	 * @return createTime
	 */
	public String getCreateTime() {
		createTime = dateFormat(super.getTask_create_time());
		return createTime;
	}

	/**
	 * 设置 数据添加时间格式化描述
	 * 
	 * @param createTime
	 */
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	/**
	 * 获取 任务最近一次更新时间格式化描述
	 * 
	 * @return updateTime
	 */
	public String getUpdateTime() {
		updateTime = dateFormat(super.getTask_update_time());
		return updateTime;
	}

	/**
	 * 设置 任务最近一次更新时间格式化描述
	 * 
	 * @param updateTime
	 */
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	private String dateFormat(Date date) {
		String datestr = "";
		SimpleDateFormat scf = new SimpleDateFormat("yyyy年MM月dd日");
		datestr = scf.format(date);
		return datestr;
	}

	private void remainDateTime() {
		long nowt = new Date().getTime();
		long endt = super.getTask_end_time().getTime();
		Integer s = 1000;
		Integer m = 1000 * 60;
		Integer h = 1000 * 60 * 60;
		Integer d = 1000 * 60 * 60 * 24;
		remainTime = endt - nowt;
		if (remainTime > 0) {
			Integer dNum = (int) (remainTime / d);
			if (dNum >= 1) {
				remain = dNum;
			} else {
				remain = 0;
			}
			Integer hNum = (int) ((remainTime % d) / h);
			if (hNum >= 1) {
				remainH = hNum;
			} else {
				remainH = 0;
			}
			Integer mNum = (int) ((remainTime % h) / m);
			if (mNum >= 1) {
				remainM = mNum;
			} else {
				remainM = 0;
			}
			Integer sNum = (int) ((remainTime % m) / s);
			if (sNum >= 1) {
				remainS = sNum;
			} else {
				remainS = 0;
			}
			remainDT = remain + " 天" + remainH + " 小时" + remainM + " 分" + remainS + " 秒";
		} else {
			remainDT = "0天0小时0分0秒";
			remainTime = 0;
		}
		isFormat = false;
	}

	/**
	 * 获取 任务类型
	 * 
	 * @return types
	 */
	public List<YdMangerTaskTypeTR> getTypes() {
		return types;
	}

	/**
	 * 设置 任务类型
	 * 
	 * @param types
	 */
	public void setTypes(List<YdMangerTaskTypeTR> types) {
		this.types = types;
	}

	/**
	 * 获取
	 * 
	 * @return typess
	 */
	public List<YdMangerTaskType> getTypess() {
		return typess;
	}

	/**
	 * 设置
	 * 
	 * @param typess
	 */
	public void setTypess(List<YdMangerTaskType> typess) {
		this.typess = typess;
	}

	/**
	 * 获取 tender
	 * 
	 * @return tender
	 */
	public YdMangerTenderVo getTender() {
		return tender;
	}

	/**
	 * 设置 tender
	 * 
	 * @param tender
	 */
	public void setTender(YdMangerTenderVo tender) {
		this.tender = tender;
	}

	/**
	 * 获取 labelVos
	 * 
	 * @return labelVos
	 */
	public List<YdMangerLabelVo> getLabelVos() {
		return labelVos;
	}

	/**
	 * 设置 labelVos
	 * 
	 * @param labelVos
	 */
	public void setLabelVos(List<YdMangerLabelVo> labelVos) {
		this.labelVos = labelVos;
	}

	/**
	 * 获取 is_collect
	 * 
	 * @return is_collect
	 */
	public Integer getIs_collect() {
		return is_collect;
	}

	/**
	 * 设置 is_collect
	 * 
	 * @param is_collect
	 */
	public void setIs_collect(Integer is_collect) {
		this.is_collect = is_collect;
	}

	/**
	 * 获取 是否申请标识
	 * 
	 * @return is_apply
	 */
	public Integer getIs_apply() {
		return is_apply;
	}

	/**
	 * 设置 是否申请标识
	 * 
	 * @param is_apply
	 */
	public void setIs_apply(Integer is_apply) {
		this.is_apply = is_apply;
	}

	/**
	 * 获取 任务评论信息
	 * 
	 * @return actives
	 */
	public List<YdMangerTaskActive> getActives() {
		return actives;
	}

	/**
	 * 设置 任务评论信息
	 * 
	 * @param actives
	 */
	public void setActives(List<YdMangerTaskActive> actives) {
		this.actives = actives;
	}

	/**
	 * 获取 tenderVos
	 * 
	 * @return tenderVos
	 */
	public List<YdMangerTenderVo> getTenderVos() {
		return tenderVos;
	}

	/**
	 * 设置 tenderVos
	 * 
	 * @param tenderVos
	 */
	public void setTenderVos(List<YdMangerTenderVo> tenderVos) {
		this.tenderVos = tenderVos;
	}

	/**
	 * 获取 任务发布方用户头像地址
	 * 
	 * @return user_imgpath
	 */
	public String getUser_imgpath() {
		return user_imgpath;
	}

	/**
	 * 设置 任务发布方用户头像地址
	 * 
	 * @param user_imgpath
	 */
	public void setUser_imgpath(String user_imgpath) {
		this.user_imgpath = user_imgpath;
	}

	public YdMangerUserVo getUserVoE() {
		return userVoE;
	}

	public void setUserVoE(YdMangerUserVo userVoE) {
		this.userVoE = userVoE;
	}

	public YdMangerUserVo getUserVoS() {
		return userVoS;
	}

	public void setUserVoS(YdMangerUserVo userVoS) {
		this.userVoS = userVoS;
	}

}
