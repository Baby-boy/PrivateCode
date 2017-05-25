package com.yd.dby.utils.random;

import java.util.Random;

public class YdUtilRandom {

	public static enum YdUtilsRandomEnum {
		NUMBER, LOWERTEXT, UPPERTEXT, LOWERGROUP, UPPERGROUP;
	}

	private static final Random RANDOM = new Random();
	private static final String CHARSNUMBER = "0123456789";
	private static final String CHARSLOWERTEXT = "qwertyupasdfghjkzxcvbnm";
	private static final String CHARSUPPERTEXT = "QWERTYUPASDFGHJKZXCVBNM";
	private static final String CHARSLOWERGROUP = "23456789qwertyupasdfghjkzxcvbnm";
	private static final String CHARSUPPERGROUP = "23456789QWERTYUPASDFGHJKZXCVBNM";

	private static String generate(YdUtilsRandomEnum type, Integer num) {
		char[] outs = new char[num];

		switch (type) {
		case NUMBER:
			for (int i = 0; i < num; i++) {
				outs[i] = CHARSNUMBER.charAt(RANDOM.nextInt(CHARSNUMBER.length()));
			}
			break;
		case LOWERTEXT:
			for (int i = 0; i < num; i++) {
				outs[i] = CHARSLOWERTEXT.charAt(RANDOM.nextInt(CHARSLOWERTEXT.length()));
			}
			break;
		case UPPERTEXT:
			for (int i = 0; i < num; i++) {
				outs[i] = CHARSUPPERTEXT.charAt(RANDOM.nextInt(CHARSUPPERTEXT.length()));
			}
			break;
		case LOWERGROUP:
			for (int i = 0; i < num; i++) {
				outs[i] = CHARSLOWERGROUP.charAt(RANDOM.nextInt(CHARSLOWERGROUP.length()));
			}
			break;
		case UPPERGROUP:
			for (int i = 0; i < num; i++) {
				outs[i] = CHARSUPPERGROUP.charAt(RANDOM.nextInt(CHARSUPPERGROUP.length()));
			}
			break;
		default:
			for (int i = 0; i < num; i++) {
				outs[i] = CHARSNUMBER.charAt(RANDOM.nextInt(CHARSNUMBER.length()));
			}
			break;
		}
		return new String(outs);
	}

	public static String number(Integer num) {
		return generate(YdUtilsRandomEnum.NUMBER, num);
	}

	public static String string(Integer num) {
		return generate(YdUtilsRandomEnum.LOWERTEXT, num);
	}

	public static String group(Integer num) {
		return generate(YdUtilsRandomEnum.LOWERGROUP, num);
	}

}