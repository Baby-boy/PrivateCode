package com.yd.dby.app.test;

//TestDriver.java是一个驱动测试，sendMsg方法不间断的向ThreadPoolManager发送数据
public class TestDriver {

	ThreadPoolManager tpm = ThreadPoolManager.newInstance();

	public void sendMsg(String msg) {

		tpm.addLogMsg(msg + "记录一条日志 ");

	}

//	public static void main(String[] args) {
//		for (int i = 0; i < 1000; i++) {
//			new TestDriver().sendMsg(Integer.toString(i));
//		}
//
//		// new TestDriver().sendMsg("发起一条对象" );
//	}
}