package com.glanway.util.sax;

import java.util.List;

/**
 * 读取每行接口.
 *
 * @author fuqihao
 * @version 1.0
 * @date 2018年1月1日
 */
public interface ExcelRowReader {

	/**
	 * 业务逻辑实现方法.
	 * 
	 * @param sheetIndex(sheet索引)
	 * @param curRow(当前行)
	 * @param rowlist(当前行数据)
	 * @author fuqihao
	 * @date 2018年1月1日
	 */
	void getRows(int sheetIndex, int curRow, List<String> rowlist);
}
