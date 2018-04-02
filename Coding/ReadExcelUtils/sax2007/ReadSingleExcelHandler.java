package com.glanway.utils.sax2007;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.poi.xssf.model.SharedStringsTable;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * 自定义解析处理器.
 *
 * @author fuqihao
 * @version 1.0
 * @date 2018年3月28日 下午4:04:43
 */
public class ReadSingleExcelHandler extends DefaultHandler {

	private Integer startRow;// 开始行数
	private Integer endRow;// 结束行数
	private SharedStringsTable sst;// 工作簿中所有工作表共享的字符串表
	private List<List<MappingEntity>> dataList;

	public ReadSingleExcelHandler(SharedStringsTable sst, List<List<MappingEntity>> dataList, Integer startRow, Integer endRow) {
		this.sst = sst;
		this.startRow = startRow;
		this.endRow = endRow;
		this.dataList = dataList;
	}

	private String key;// key值
	private String value;// 内容
	private int currentRow = 0;// 当前行数
	private boolean nextIsString;// 是否是String
	private List<MappingEntity> rowData;// 数据

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		// c => cell
		if (qName.equals("c")) {
			key = attributes.getValue("r");
			if (key.contains("N")) {
				System.out.println("##" + attributes + "##");
			}
			// 这是一个新行
			if (Pattern.compile("^A[0-9]+$").matcher(key).find()) {
				// 存储上一行数据
				if (rowData != null && isAccess() && !rowData.isEmpty()) {
					dataList.add(rowData);
				}
				rowData = new ArrayList<MappingEntity>();// 新行要先清除上一行的数据
				currentRow++;// 当前行+1
			}
			if (isAccess()) {
				// Figure out if the value is an index in the SST
				String cellType = attributes.getValue("t");
				if (cellType != null && cellType.equals("s")) {
					nextIsString = true;
				} else {
					nextIsString = false;
				}
			}

		}

		value = "";
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if (isAccess()) {
			if (nextIsString) {
				int idx = Integer.parseInt(value);
				value = new XSSFRichTextString(sst.getEntryAt(idx)).toString();
				nextIsString = false;
			}
			// v => contents of a cell
			if (qName.equals("v")) {
				rowData.add(new MappingEntity(key, value));
			}
		}
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		if (isAccess()) {
			value += new String(ch, start, length);
		}
	}

	@Override
	public void endDocument() throws SAXException {
		if (rowData != null && isAccess() && !rowData.isEmpty()) {
			dataList.add(rowData);
		}
	}

	private boolean isAccess() {
		if (null == endRow) {
			endRow = currentRow;
		}
		if (currentRow >= startRow && startRow <= endRow) {
			return true;
		}
		return false;
	}
}
