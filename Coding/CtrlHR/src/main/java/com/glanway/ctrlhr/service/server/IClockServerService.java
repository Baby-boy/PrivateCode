package com.glanway.ctrlhr.service.server;

/**
 * 说明 : 专门调用IClockServer系统的服务
 * 
 * @author fuqihao
 * @version 1.0.0
 * @dateTime 2017年6月7日 上午10:46:16
 */
public interface IClockServerService {

	/**
	 * 说明 : 根据设备ID同步设备上的用户信息
	 * 
	 * @param ids
	 * @author fuqihao
	 * @dateTime 2017年6月7日 上午10:59:25
	 */
	public void syncUserInfoByDeviceId(String ids) throws Exception;

	/**
	 * 说明 : 根据设备序列号同步设备上的用户信息
	 * 
	 * @param sns
	 * @throws Exception
	 * @author fuqihao
	 * @dateTime 2017年6月7日 上午11:22:49
	 */
	public void syncUserInfoByDeviceSn(String sns) throws Exception;

	/**
	 * 说明 : 同步所有在使用设备上的用户信息
	 * 
	 * @throws Exception
	 * @author fuqihao
	 * @dateTime 2017年6月7日 上午11:37:18
	 */
	public void syncUserInfoByAllDevice() throws Exception;

	/**
	 * 说明 : 清除所有在使用设备上的信息(包括考勤信息)
	 * 
	 * @throws Exception
	 * @author fuqihao
	 * @dateTime 2017年6月7日 上午11:41:44
	 */
	public void cleanAll(String sns) throws Exception;

	/**
	 * 说明 : 删除设备上的用户信息
	 * 
	 * @param sns
	 * @throws Exception
	 * @author fuqihao
	 * @dateTime 2017年6月7日 上午11:53:51
	 */
	public void cleanUserInfoBySn(String sns) throws Exception;

	/**
	 * 说明 : 删除设备上的考勤记录
	 * 
	 * @param sns
	 * @throws Exception
	 * @author fuqihao
	 * @dateTime 2017年6月7日 下午1:08:56
	 */
	public void cleanLog(String sns) throws Exception;
}
