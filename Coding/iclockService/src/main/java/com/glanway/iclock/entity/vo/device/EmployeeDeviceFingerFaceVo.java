/**
 * @author zhangshuang
 * 2017年4月21日 下午6:10:46
 */
package com.glanway.iclock.entity.vo.device;

/**
 * 说明 : 应该在设备上打卡员工的指纹模板或人脸模板
 * 
 * @author zhangshaung
 * @version 1.0.0
 * @dateTime 2017年4月21日 下午6:10:46
 */
public class EmployeeDeviceFingerFaceVo {
	/**
	 * 员工代码
	 */
	private String code;
	/**
	 * 手指编号/脸纹编号
	 */
	private String fid;
	/**
	 * 图片字节码长度
	 */
	private Integer size;

	/**
	 * 描述模板的有效性及胁迫标志 0.无效模板 1.正常模板 3.胁迫模板
	 */
	private String valid;
	/**
	 * 模板图片 原始二进制指纹或面部模板的base64编码
	 */
	private String tmp;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getFid() {
		return fid;
	}

	public void setFid(String fid) {
		this.fid = fid;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public String getValid() {
		return valid;
	}

	public void setValid(String valid) {
		this.valid = valid;
	}

	public String getTmp() {
		return tmp;
	}

	public void setTmp(String tmp) {
		this.tmp = tmp;
	}

}
