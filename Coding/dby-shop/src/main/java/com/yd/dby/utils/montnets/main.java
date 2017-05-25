package com.yd.dby.utils.montnets;

public class main {

	public main(final String[] args) {

		try {
			YdMontnets.SendMultixSms("13127987897", "短信测试12");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

}
