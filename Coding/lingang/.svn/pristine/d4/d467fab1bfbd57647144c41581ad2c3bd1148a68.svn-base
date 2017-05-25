package com.lingang.common.util;


import javax.annotation.Resource;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.dubbo.common.utils.NetUtils;
import com.lingang.common.factory.ZookeeperClientFactory;

/**
 * 任务调度管理器
 *
 */
public class TaskManager {
	
	@Resource
	private ZookeeperClientFactory zookeeperClientFactory;
	
	private boolean masterNode = false;
	
	private String timerNodeName = "timer";
	
	private String masterNodeName = "master";
	
	private static final Logger logger = LoggerFactory.getLogger(TaskManager.class);
	
	/**
	 * 是否为主结点
	 * @return
	 */
	public boolean isMasterNode(){
		if(masterNode){
			logger.debug("************************************************");
			logger.debug("******Is master，Is running！****************");
			logger.debug("************************************************");
		}else{
			logger.debug("**********************************************");
			logger.debug("******Not master，Not running！****************");
			logger.debug("***********************************************");
		}
		return masterNode;
	}
	
	public void init(){
		String timerNodePath = zookeeperClientFactory.getAppPath() + "/" + timerNodeName ;
		ZkClient zkClient = zookeeperClientFactory.getZkClient();
		if(!zkClient.exists(timerNodePath)){
			try{
				zkClient.createPersistent(timerNodePath);
			}catch(Exception e){
				logger.error("create Persistent node error ,path:"+timerNodePath, e);
			}
		}
		getMasterNode();
	}
	
	public boolean getMasterNode(){
		StringBuilder masterNodePathSB = new StringBuilder();
		masterNodePathSB.append(zookeeperClientFactory.getAppPath()).append("/").append(timerNodeName).append("/").append(masterNodeName);
		String masterNodePath = masterNodePathSB.toString();
		String ip = NetUtils.getLocalHost();
		ZkClient zkClient = zookeeperClientFactory.getZkClient();
		if(zkClient.exists(masterNodePath)){
			Object data = zkClient.readData(masterNodePath, true);
			if(data != null && data.equals(ip)){
				masterNode = true;
			}else{
				//添加watch 监控master节点
				zkClient.subscribeDataChanges(masterNodePath, new IZkDataListener(){

					@Override
					public void handleDataChange(String dataPath, Object data)
							throws Exception {
						getMasterNode();
					}

					@Override
					public void handleDataDeleted(String dataPath)
							throws Exception {
						getMasterNode();
					}
					
				});
			}
		}else{
			try{
				zkClient.createEphemeral(masterNodePath, ip);
				masterNode = true;
			}catch(Exception e){
				logger.error("create Ephemeral node error ,path:"+masterNodePath, e);
				if(zkClient.exists(masterNodePath)){
					//添加watch 监控master节点
					zkClient.subscribeDataChanges(masterNodePath, new IZkDataListener(){

						@Override
						public void handleDataChange(String dataPath, Object data)
								throws Exception {
							getMasterNode();
						}

						@Override
						public void handleDataDeleted(String dataPath)
								throws Exception {
							getMasterNode();
						}
						
					});
				}
			}
		}
		return masterNode;
	}
	
}
