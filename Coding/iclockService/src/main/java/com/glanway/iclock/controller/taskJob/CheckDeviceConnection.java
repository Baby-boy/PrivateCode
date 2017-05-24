/**
 * @author zhangshuang
 * 2017年4月20日 下午5:28:40
 */
package com.glanway.iclock.controller.taskJob;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.glanway.iclock.entity.sign.Device;
import com.glanway.iclock.service.sign.DeviceService;


/**
 * 说明 : 定时器
 * 检查设备连接状态
 * 每次执行定时器时,将超过5分钟没有连接的设备状态修改成异常
 * {取数据库中根据已连通设备最后一次连接时间超过5分钟的设备(list)}
 * @author zhangshaung
 * @version 1.0.0
 * @dateTime 2017年4月20日 下午5:28:40
 */
@Service
public class CheckDeviceConnection {
	
	private static final Logger logger=LoggerFactory.getLogger(CheckDeviceConnection.class);
	@Autowired
	private DeviceService deviceService;
	
	/**
	 * 
	 * 说明 : 定时任务,每30秒执行一次
	 * 将超过5分钟没有连接的设备状态修改成异常
	 * @author zhangshaung
	 * @dateTime 2017年4月20日 下午6:07:40
	 */
	@Scheduled(cron="0/30 * *  * * ? ")
	public void checkDeviceConnctionState(){
		Date date=new Date();
		List<Device> devices=deviceService.findLastConnectionTimeExcendFiveMinute();
		
		for(Device device : devices){
			//将查到的设备状态修改成异常:state=3
			deviceService.updateStateById(device.getId(), 3);
			
			logger.info(new Date()+" 将设备名称:{},设备代码:{} 设置为异常",device.getDeleted(),device.getSn());
		}
		
	}
	
}
