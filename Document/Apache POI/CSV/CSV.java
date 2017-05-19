package com.glanway.csv;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class CSV {

	private final static byte commonCsvHead[] = { (byte) 0xEF, (byte) 0xBB, (byte) 0xBF };

	public static void writeCsv(List<User> source) throws IOException {
		if (null == source) {
			throw new NullPointerException();
		}
		StringBuilder sbBuilder = new StringBuilder();
		for (User user : source) {
			// sbBuilder.append(user.getId() + user.getName()).append("\r\n");
			sbBuilder.append(user.getId()).append(user.getName()).append("\r");
		}
		OutputStream out = new FileOutputStream(new File("F:/ljq.csv"));
		out.write(concat(commonCsvHead, sbBuilder.toString().getBytes(Charset.forName("UTF-8").toString())));
		out.close();
	}

	public static byte[] concat(byte[]... arrays) {
		int length = 0;
		for (byte[] array : arrays) {
			length += array.length;
		}
		byte[] result = new byte[length];
		int pos = 0;
		for (byte[] array : arrays) {
			System.arraycopy(array, 0, result, pos, array.length);
			pos += array.length;
		}
		return result;
	}

	public static void main(String[] args) throws IOException {
		List<User> list = new ArrayList<>();
		User user = new User();
		user.setId(1);
		user.setName("付其浩");
		list.add(user);
		User user2 = new User();
		user2.setId(2);
		user2.setName("高伟男");
		list.add(user2);
		writeCsv(list);
	}
}
