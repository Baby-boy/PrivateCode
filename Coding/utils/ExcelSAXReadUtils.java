package com.glanway.util;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import java.util.Map;

import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.springframework.web.multipart.MultipartFile;

import com.glanway.utils.sax.ExcelRowReader;
import com.glanway.utils.sax2003.ExcelSAX2003;
import com.glanway.utils.sax2007.ExcelSAX2007;

/**
 * Excel读取工具类(SAX模式).
 * 
 * @author fuqihao
 * @version 1.0
 * @date 2018年1月1日
 */
public abstract class ExcelSAXReadUtils {

	/** excel2003扩展名 */
	public static final String EXCEL03_EXTENSION = ".xls";
	/** excel2007扩展名 */
	public static final String EXCEL07_EXTENSION = ".xlsx";

	/**
	 * 读取每一行数据.
	 *
	 * @param fileName
	 * @param rowReader
	 * @throws Exception
	 * @author fuqihao
	 * @date 2018年1月1日
	 */
	public static void readSingleExcel(final String fileName, final ExcelRowReader rowReader) throws Exception {
		if (fileName.endsWith(EXCEL03_EXTENSION)) {// 处理excel2003文件
			final POIFSFileSystem fileSystem = new POIFSFileSystem(new FileInputStream(fileName));
			final ExcelSAX2003 exceXls = new ExcelSAX2003(fileSystem, rowReader);
			exceXls.readSingleExcel();
		} else if (fileName.endsWith(EXCEL07_EXTENSION)) {// 处理excel2007文件
			final OPCPackage opcPackage = OPCPackage.open(new File(fileName));
			final ExcelSAX2007 exceXlsx = new ExcelSAX2007(opcPackage, rowReader);
			exceXlsx.readSingleExcel();
		} else {
			throw new Exception("文件格式错误，fileName的扩展名只能是xls或xlsx。");
		}
	}

	/**
	 * 读取每一行数据.
	 *
	 * @param multipartFile
	 * @param rowReader
	 * @throws Exception
	 * @author fuqihao
	 * @date 2018年1月1日
	 */
	public static void readSingleExcel(final MultipartFile multipartFile, final ExcelRowReader rowReader)
			throws Exception {
		if (multipartFile.getOriginalFilename().endsWith(EXCEL03_EXTENSION)) {// 处理excel2003文件
			final POIFSFileSystem fileSystem = new POIFSFileSystem(multipartFile.getInputStream());
			final ExcelSAX2003 exceXls = new ExcelSAX2003(fileSystem, rowReader);
			exceXls.readSingleExcel();
		} else if (multipartFile.getOriginalFilename().endsWith(EXCEL07_EXTENSION)) {// 处理excel2007文件
			final OPCPackage opcPackage = OPCPackage.open(multipartFile.getInputStream());
			final ExcelSAX2007 exceXlsx = new ExcelSAX2007(opcPackage, rowReader);
			exceXlsx.readSingleExcel();
		} else {
			throw new Exception("文件格式错误，fileName的扩展名只能是xls或xlsx。");
		}
	}

	/**
	 * 根据sheet索引返回数据.
	 *
	 * @param fileName
	 * @return
	 * @throws Exception
	 * @author fuqihao
	 * @date 2018年1月1日
	 */
	public static Map<Integer, List<List<String>>> readExcel(final String fileName) throws Exception {
		Map<Integer, List<List<String>>> map = null;
		if (fileName.endsWith(EXCEL03_EXTENSION)) {// 处理excel2003文件
			final POIFSFileSystem fileSystem = new POIFSFileSystem(new FileInputStream(fileName));
			final ExcelSAX2003 exceXls = new ExcelSAX2003(fileSystem);
			map = exceXls.readExcel();
		} else if (fileName.endsWith(EXCEL07_EXTENSION)) {// 处理excel2007文件
			final OPCPackage opcPackage = OPCPackage.open(new File(fileName));
			final ExcelSAX2007 exceXlsx = new ExcelSAX2007(opcPackage);
			map = exceXlsx.readExcel();
		} else {
			throw new Exception("文件格式错误，fileName的扩展名只能是xls或xlsx。");
		}

		return map;
	}

	/**
	 * 根据sheet索引返回数据.
	 *
	 * @param fileName
	 * @return
	 * @throws Exception
	 * @author fuqihao
	 * @date 2018年1月1日
	 */
	public static Map<Integer, List<List<String>>> readExcel(final MultipartFile multipartFile) throws Exception {
		Map<Integer, List<List<String>>> map = null;
		if (multipartFile.getOriginalFilename().endsWith(EXCEL03_EXTENSION)) {// 处理excel2003文件
			final POIFSFileSystem fileSystem = new POIFSFileSystem(multipartFile.getInputStream());
			final ExcelSAX2003 exceXls = new ExcelSAX2003(fileSystem);
			map = exceXls.readExcel();
		} else if (multipartFile.getOriginalFilename().endsWith(EXCEL07_EXTENSION)) {// 处理excel2007文件
			final OPCPackage opcPackage = OPCPackage.open(multipartFile.getInputStream());
			final ExcelSAX2007 exceXlsx = new ExcelSAX2007(opcPackage);
			map = exceXlsx.readExcel();
		} else {
			throw new Exception("文件格式错误，fileName的扩展名只能是xls或xlsx。");
		}

		return map;
	}

}
