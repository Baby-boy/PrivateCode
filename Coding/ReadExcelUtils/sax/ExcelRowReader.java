package com.glanway.utils.sax;

import java.util.List;

public interface ExcelRowReader {

	/**
	 * 业务逻辑实现方法
	 * 
	 * @param sheetIndex
	 * @param curRow
	 * @param rowlist
	 */
	void getRows(int sheetIndex, int curRow, List<String> rowlist);
}
