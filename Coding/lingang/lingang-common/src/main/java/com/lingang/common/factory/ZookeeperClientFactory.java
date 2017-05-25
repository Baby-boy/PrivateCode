package com.lingang.common.factory;

import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.ZkConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * zookeeperclient 工厂类
 * @author Tommy
 *
 */
public class ZookeeperClientFactory {
	
	private String appName;

	private String appNameMark = "\\$\\{appName\\}";
	
	private String appPath ="/apps_node/${appName}";

	private String zkServers;
	
	private int connectionTimeout = Integer.MAX_VALUE;
	
	private int sessionTimeOut = 15000 ;
	
	private ZkConnection zkConnection;
	
	private ZkClient zkClient;
	
	private static final Logger logger = LoggerFactory.getLogger(ZookeeperClientFactory.class);

	public String getAppPath() {
		return appPath;
	}
	
	public String getAppName() {
		return appName;
	}

	public void setAppPath(String appPath) {
		this.appPath = appPath;
	}
	
	public void setZkServers(String zkServers) {
		this.zkServers = zkServers;
	}

	public void setConnectionTimeout(int connectionTimeout) {
		this.connectionTimeout = connectionTimeout;
	}

	public void setSessionTimeOut(int sessionTimeOut) {
		this.sessionTimeOut = sessionTimeOut;
	}
	
	public void setAppName(String appName) {
		this.appName = appName;
	}

	public void init(){
		zkConnection = new ZkConnection( zkServers,  sessionTimeOut);
		zkClient = new ZkClient( zkConnection,  connectionTimeout);
		appPath = appPath.replaceAll(appNameMark, appName);
		if(!zkClient.exists(appPath)){
			try{
				zkClient.createPersistent(appPath, true);
			}catch(Exception e){
				logger.error("create persistent path : "+appPath+"error!",e);
			}
		}
	}
	
	public ZkClient getZkClient(){
		return zkClient;
	}

}
