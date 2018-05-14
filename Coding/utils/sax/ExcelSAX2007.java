package com.glanway.util.sax;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xssf.eventusermodel.XSSFReader;
import org.apache.poi.xssf.model.SharedStringsTable;
import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import com.glanway.utils.sax.ExcelRowReader;

/**
 * POI以SAX方式解析Excel2007.
 *
 * @author fuqihao
 * @version 1.0
 * @date 2018年5月14日
 */
public class ExcelSAX2007 {

	private final OPCPackage opcPackage;
	private Integer startRow;
	private Integer endRow;
	private ExcelRowReader rowReader;

	private List<List<MappingEntity>> dataList = new ArrayList<>();// 原始数据

	public ExcelSAX2007(OPCPackage opcPackage, Integer startRow, Integer endRow, ExcelRowReader rowReader) {
		super();
		this.opcPackage = opcPackage;
		this.startRow = startRow;
		this.endRow = endRow;
		this.rowReader = rowReader;
	}

	public ExcelSAX2007(OPCPackage opcPackage, Integer startRow, Integer endRow) {
		super();
		this.opcPackage = opcPackage;
		this.startRow = startRow;
		this.endRow = endRow;
	}

	public ExcelSAX2007(OPCPackage opcPackage, Integer startRow, ExcelRowReader rowReader) {
		super();
		this.opcPackage = opcPackage;
		this.startRow = startRow;
		this.rowReader = rowReader;
	}

	public ExcelSAX2007(OPCPackage opcPackage, Integer startRow) {
		super();
		this.opcPackage = opcPackage;
		this.startRow = startRow;
	}

	public ExcelSAX2007(OPCPackage opcPackage, ExcelRowReader rowReader) {
		super();
		this.opcPackage = opcPackage;
		this.rowReader = rowReader;
		this.startRow = 0;
	}

	public ExcelSAX2007(OPCPackage opcPackage) {
		super();
		this.opcPackage = opcPackage;
		this.startRow = 0;
	}

	private int sheetIndex = -1;// 表索引

	/**
	 * 单行读取Excel表格.
	 *
	 * @throws SAXException
	 * @throws IOException
	 * @throws OpenXML4JException
	 * @author fuqihao
	 * @date 2018年5月14日
	 */
	public void readSingleExcel() throws SAXException, IOException, OpenXML4JException {
		final XSSFReader reader = new XSSFReader(opcPackage);
		final SharedStringsTable sst = reader.getSharedStringsTable();
		Iterator<InputStream> sheets = reader.getSheetsData();
		while (sheets.hasNext()) {
			sheetIndex++;// 表索引

			InputStream sheet = sheets.next();
			InputSource sheetSource = new InputSource(sheet);

			final ReadExcelHandler handler = new ReadExcelHandler(sst, startRow, endRow);
			handler.setSheetIndex(sheetIndex);
			handler.setRowReader(rowReader);
			XMLReader parser = fetchSheetParser(sst, handler);
			parser.parse(sheetSource);
			sheet.close();
		}
	}

	/**
	 * 单sheet读取Excel表格.
	 *
	 * @return
	 * @throws SAXException
	 * @throws IOException
	 * @throws OpenXML4JException
	 * @author fuqihao
	 * @date 2018年5月14日
	 */
	public Map<Integer, List<List<String>>> readExcel() throws SAXException, IOException, OpenXML4JException {
		final Map<Integer, List<List<String>>> map = new HashMap<>();

		final XSSFReader reader = new XSSFReader(opcPackage);
		final SharedStringsTable sst = reader.getSharedStringsTable();
		Iterator<InputStream> sheets = reader.getSheetsData();
		while (sheets.hasNext()) {
			sheetIndex++;// 表索引

			InputStream sheet = sheets.next();
			InputSource sheetSource = new InputSource(sheet);

			final ReadSingleExcelHandler handler = new ReadSingleExcelHandler(sst, dataList, startRow, endRow);
			XMLReader parser = fetchSheetParser(sst, handler);
			parser.parse(sheetSource);
			sheet.close();
			List<List<String>> realDataList = getRealDataList();
			map.put(sheetIndex, realDataList);
		}

		return map;
	}

	private List<List<String>> getRealDataList() {
		if (null == dataList || 0 >= dataList.size()) {
			return null;
		}

		final List<List<String>> realDataList = new ArrayList<List<String>>();
		for (List<MappingEntity> rowData : dataList) {
			List<String> rowlist = new ArrayList<String>();
			int j = 0;
			for (; j < rowData.size() - 1; j++) {
				// 获取当前的值并存储
				MappingEntity current = rowData.get(j);
				rowlist.add(current.getValue());
				// 获取下一个值并存储
				MappingEntity next = rowData.get(j + 1);
				// 获取差值, 计算空格数量
				int level = next.getLevel(current);
				if (level > 0) {
					for (int k = 0; k < level - 1; k++) {
						rowlist.add(null);
					}
				}
			}
			rowlist.add(rowData.get(j).getValue());
			realDataList.add(rowlist);
		}

		dataList = new ArrayList<>();// 重置容器
		return realDataList;
	}

	/**
	 * 获取解析器.
	 *
	 * @param sst
	 * @param handler
	 * @return
	 * @throws SAXException
	 * @author fuqihao
	 * @date 2018年1月1日
	 */
	private XMLReader fetchSheetParser(SharedStringsTable sst, ContentHandler handler) throws SAXException {
		XMLReader parser = XMLReaderFactory.createXMLReader("org.apache.xerces.parsers.SAXParser");
		parser.setContentHandler(handler);
		return parser;
	}

}
