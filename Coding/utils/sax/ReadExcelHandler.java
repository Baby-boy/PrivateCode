package com.glanway.util.sax;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.poi.xssf.model.SharedStringsTable;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.glanway.utils.sax.ExcelRowReader;

/**
 * 自定义解析处理器.
 *
 * @author fuqihao
 * @version 1.0
 * @date 2018年5月14日
 */
public class ReadExcelHandler extends DefaultHandler {

	private Integer startRow;// 开始行数
	private Integer endRow;// 结束行数
	private SharedStringsTable sst;// 工作簿中所有工作表共享的字符串表

	public ReadExcelHandler(SharedStringsTable sst, Integer startRow, Integer endRow) {
		this.sst = sst;
		this.startRow = startRow;
		this.endRow = endRow;
	}

	private String key;// key值
	private String value;// 内容
	private int curRow = 0;// 当前行数
	private boolean nextIsString;// 是否是String
	private List<MappingEntity> rowData;// 数据

	private Integer sheetIndex;
	private ExcelRowReader rowReader;

	public void setSheetIndex(Integer sheetIndex) {
		this.sheetIndex = sheetIndex;
	}

	public void setRowReader(ExcelRowReader rowReader) {
		this.rowReader = rowReader;
	}

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
				rowData = new ArrayList<MappingEntity>();// 新行要先清除上一行的数据
				curRow++;// 当前行+1
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
			if (qName.equals("row")) {// 行结束
				if (rowData != null && isAccess() && !rowData.isEmpty()) {
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
					rowReader.getRows(sheetIndex, curRow, rowlist);// 读每行
				}
			}

		}
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		if (isAccess()) {
			value += new String(ch, start, length);
		}
	}

	private boolean isAccess() {
		if (null == endRow) {
			endRow = curRow;
		}
		if (curRow >= startRow && startRow <= endRow) {
			return true;
		}
		return false;
	}
}
