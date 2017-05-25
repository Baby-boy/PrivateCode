package com.rabbit.entity;

public class RabbitMqMessage {

	private String objId;// 预留ID

	private String routingKey;// 路由key

	private String createtime;// 时间戳(十三位)

	public String getObjId() {
		return objId;
	}

	public void setObjId(String objId) {
		this.objId = objId;
	}

	public String getRoutingKey() {
		return routingKey;
	}

	public void setRoutingKey(String routingKey) {
		this.routingKey = routingKey;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	@Override
	public String toString() {
		return "RabbitMqMessage [objId=" + objId + ", routingKey=" + routingKey + ", createtime=" + createtime + "]";
	}

}
