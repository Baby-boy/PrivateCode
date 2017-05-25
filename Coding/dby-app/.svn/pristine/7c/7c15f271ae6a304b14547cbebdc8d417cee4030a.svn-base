package com.yd.dby.app.util;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

public class YdUtilBase64 {
	private static final String CHARSET = "utf-8";

	public static String encode(byte[] input) throws UnsupportedEncodingException {
		return new String(Base64.getEncoder().encode(input), CHARSET);
	}

	public static String encode(byte[] input, String charset) throws UnsupportedEncodingException {
		return new String(Base64.getEncoder().encode(input), charset);
	}

	public static String encode(String input) throws UnsupportedEncodingException {
		return YdUtilBase64.encode(input.getBytes(CHARSET));
	}

	public static String encode(String input, String charset) throws UnsupportedEncodingException {
		return YdUtilBase64.encode(input.getBytes(charset));
	}

	public static String encode(String input, String charsetA, String charsetB) throws UnsupportedEncodingException {
		return YdUtilBase64.encode(input.getBytes(charsetA), charsetB);
	}

	public static String decode(byte[] input) throws UnsupportedEncodingException {
		return new String(Base64.getDecoder().decode(input));
	}

	public static String decode(byte[] input, String charset) throws UnsupportedEncodingException {
		return new String(Base64.getDecoder().decode(input), charset);
	}

	public static String decode(String input) throws UnsupportedEncodingException {
		return YdUtilBase64.decode(input.getBytes(CHARSET));
	}

	public static String decode(String input, String charset) throws UnsupportedEncodingException {
		return YdUtilBase64.decode(input.getBytes(charset));
	}

	public static String decode(String input, String charsetA, String charsetB) throws UnsupportedEncodingException {
		return YdUtilBase64.decode(input.getBytes(charsetA), charsetB);
	}
}