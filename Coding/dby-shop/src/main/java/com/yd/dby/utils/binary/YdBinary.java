package com.yd.dby.utils.binary;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class YdBinary {

	public static byte[] fromBase64String(String base64String) {
		return Base64.getDecoder().decode(base64String.getBytes(StandardCharsets.UTF_8));
	}
}