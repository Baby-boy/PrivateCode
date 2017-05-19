package com.glanway.csv;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.google.common.base.Charsets;
import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.io.Files;
import com.google.common.primitives.Bytes;

public class FooUtilsCsvHelper {

	// csv's default delemiter is ','
	private final static String DEFAULT_DELIMITER = ",";
	// Mark a new line
	private final static String DEFAULT_END = "\r\n";
	// If you do not want a UTF-8 ,just replace the byte array.
	private final static byte commonCsvHead[] = { (byte) 0xEF, (byte) 0xBB, (byte) 0xBF };

	/**
	 * Write source to a csv file
	 * 
	 * @param source
	 * @throws IOException
	 */
	public static void writeCsv(List<List<String>> source) throws IOException {
		// Aoid java.lang.NullPointerException
		Preconditions.checkNotNull(source);
		StringBuilder sbBuilder = new StringBuilder();
		for (List<String> list : source) {
			sbBuilder.append(Joiner.on(DEFAULT_DELIMITER).join(list)).append(DEFAULT_END);
		}
		Files.write(Bytes.concat(commonCsvHead, sbBuilder.toString().getBytes(Charsets.UTF_8.toString())), new File("F:\\/123.csv"));
	}

	/**
	 * Simple read a csv file
	 * 
	 * @param file
	 * @throws IOException
	 */
	public static void readCsv(File file) throws IOException {
		System.out.println(Files.readFirstLine(file, Charsets.UTF_8));
	}

	// Run a small test yourself.
	public static void main(String[] args) throws IOException {
		List<List<String>> source = Lists.newArrayList();
		List<String> tmpL = Lists.newArrayList();
		tmpL.add("测试titile1");
		tmpL.add("测试titile2");
		source.add(tmpL);
		writeCsv(source);
		readCsv(new File("F:\\/123.csv"));
	}
}