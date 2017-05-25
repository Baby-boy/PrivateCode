package com.yd.dby.utils.string;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

public class YdString {

	private static final String CHARSET = "utf-8";

	public static String fromBase64String(String input) throws UnsupportedEncodingException {
		return new String(Base64.getDecoder().decode(input), CHARSET);
	}

	public static String toBase64String(String input) throws UnsupportedEncodingException {
		return new String(Base64.getEncoder().encode(input.getBytes(CHARSET)), CHARSET);
	}

}