package com.yd.dby.app.test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TimerTest02 {
	Timer timer;

	ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

	public TimerTest02() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		// try {
		// Date time = dateFormat.parse("2017-03-10 09:39:00");
		// System.out.println("指定时间time=" + time);
		// timer = new Timer();
		// timer.schedule(new TimerTaskTest02(), time);
		// } catch (ParseException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

		try {
			Date time = dateFormat.parse("2017-03-10 10:05:00");
			
			executor.schedule(new TimerTaskTest02(), 1, TimeUnit.SECONDS);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public Date getTime() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 00);
		Date time = calendar.getTime();

		return time;
	}

//	public static void main(String[] args) {
//		new TimerTest02();
//	}
}