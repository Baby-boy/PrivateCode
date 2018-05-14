package com.glanway.util.sax;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.eventusermodel.EventWorkbookBuilder.SheetRecordCollectingListener;
import org.apache.poi.hssf.eventusermodel.FormatTrackingHSSFListener;
import org.apache.poi.hssf.eventusermodel.HSSFListener;
import org.apache.poi.hssf.eventusermodel.dummyrecord.LastCellOfRowDummyRecord;
import org.apache.poi.hssf.eventusermodel.dummyrecord.MissingCellDummyRecord;
import org.apache.poi.hssf.model.HSSFFormulaParser;
import org.apache.poi.hssf.record.BOFRecord;
import org.apache.poi.hssf.record.BlankRecord;
import org.apache.poi.hssf.record.BoolErrRecord;
import org.apache.poi.hssf.record.BoundSheetRecord;
import org.apache.poi.hssf.record.FormulaRecord;
import org.apache.poi.hssf.record.LabelRecord;
import org.apache.poi.hssf.record.LabelSSTRecord;
import org.apache.poi.hssf.record.NumberRecord;
import org.apache.poi.hssf.record.Record;
import org.apache.poi.hssf.record.SSTRecord;
import org.apache.poi.hssf.record.StringRecord;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.glanway.utils.sax.ExcelRowReader;

/**
 * Excel2003读取监听器.
 * 
 * @author fuqihao
 * @version 1.0
 * @date 2018年1月1日
 */
public class ReadSingleExcelListener implements HSSFListener {

	private boolean outputFormulaValues;

	public ReadSingleExcelListener(boolean outputFormulaValues) {
		super();
		this.outputFormulaValues = outputFormulaValues;
	}

	// 存储行记录的容器
	private final List<String> rowlist = new ArrayList<String>();;

	private int sheetIndex = -1;// 表索引
	private boolean outputNextStringRecord;
	private int nextRow;
	private int nextColumn;
	private int curRow = 0;// 当前行
	private int minColumns = -1;

	private int lastRowNumber;
	private int lastColumnNumber;

	@SuppressWarnings("unused")
	private String sheetName;

	/** 绑定标记录 */
	@SuppressWarnings("rawtypes")
	private ArrayList boundSheetRecords = new ArrayList();

	private HSSFWorkbook stubWorkbook;
	private BoundSheetRecord[] orderedBSRs;
	private SSTRecord sstRecord;

	private ExcelRowReader rowReader;
	private FormatTrackingHSSFListener formatListener;
	private SheetRecordCollectingListener workbookBuildingListener;

	public void setRowReader(ExcelRowReader rowReader) {
		this.rowReader = rowReader;
	}

	public void setFormatListener(FormatTrackingHSSFListener formatListener) {
		this.formatListener = formatListener;
	}

	public void setWorkbookBuildingListener(SheetRecordCollectingListener workbookBuildingListener) {
		this.workbookBuildingListener = workbookBuildingListener;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void processRecord(final Record record) {
		int thisRow = -1;
		int thisColumn = -1;
		String thisStr = null;
		String value = null;
		switch (record.getSid()) {
		case BoundSheetRecord.sid:
			boundSheetRecords.add(record);
			break;
		case BOFRecord.sid:
			BOFRecord br = (BOFRecord) record;
			if (br.getType() == BOFRecord.TYPE_WORKSHEET) {
				// 如果有需要，则建立子工作薄
				if (workbookBuildingListener != null && stubWorkbook == null) {
					stubWorkbook = workbookBuildingListener.getStubHSSFWorkbook();
				}

				sheetIndex++;
				if (orderedBSRs == null) {
					orderedBSRs = BoundSheetRecord.orderByBofPosition(boundSheetRecords);
				}
				sheetName = orderedBSRs[sheetIndex].getSheetname();
			}
			break;

		case SSTRecord.sid:
			sstRecord = (SSTRecord) record;
			break;

		case BlankRecord.sid:
			BlankRecord brec = (BlankRecord) record;
			thisRow = brec.getRow();
			thisColumn = brec.getColumn();
			thisStr = "";
			rowlist.add(thisColumn, thisStr);
			break;
		case BoolErrRecord.sid: // 单元格为布尔类型
			BoolErrRecord berec = (BoolErrRecord) record;
			thisRow = berec.getRow();
			thisColumn = berec.getColumn();
			thisStr = berec.getBooleanValue() + "";
			rowlist.add(thisColumn, thisStr);
			break;

		case FormulaRecord.sid: // 单元格为公式类型
			FormulaRecord frec = (FormulaRecord) record;
			thisRow = frec.getRow();
			thisColumn = frec.getColumn();
			if (outputFormulaValues) {
				if (Double.isNaN(frec.getValue())) {
					// Formula result is a string
					// This is stored in the next record
					outputNextStringRecord = true;
					nextRow = frec.getRow();
					nextColumn = frec.getColumn();
				} else {
					thisStr = formatListener.formatNumberDateCell(frec);
				}
			} else {
				thisStr = '"' + HSSFFormulaParser.toFormulaString(stubWorkbook, frec.getParsedExpression()) + '"';
			}
			rowlist.add(thisColumn, thisStr);
			break;
		case StringRecord.sid:// 单元格中公式的字符串
			if (outputNextStringRecord) {
				// String for formula
				StringRecord srec = (StringRecord) record;
				thisStr = srec.getString();
				thisRow = nextRow;
				thisColumn = nextColumn;
				outputNextStringRecord = false;
			}
			break;
		case LabelRecord.sid:
			LabelRecord lrec = (LabelRecord) record;
			// curRow = thisRow = lrec.getRow();
			thisColumn = lrec.getColumn();
			value = lrec.getValue().trim();
			value = value.equals("") ? " " : value;
			this.rowlist.add(thisColumn, value);
			break;
		case LabelSSTRecord.sid: // 单元格为字符串类型
			LabelSSTRecord lsrec = (LabelSSTRecord) record;
			// curRow = thisRow = lsrec.getRow();
			thisColumn = lsrec.getColumn();
			if (sstRecord == null) {
				rowlist.add(thisColumn, " ");
			} else {
				value = sstRecord.getString(lsrec.getSSTIndex()).toString().trim();
				value = value.equals("") ? " " : value;
				rowlist.add(thisColumn, value);
			}
			break;
		case NumberRecord.sid: // 单元格为数字类型
			NumberRecord numrec = (NumberRecord) record;
			// curRow = thisRow = numrec.getRow();
			thisColumn = numrec.getColumn();
			value = formatListener.formatNumberDateCell(numrec).trim();
			value = value.equals("") ? " " : value;
			// 向容器加入列值
			rowlist.add(thisColumn, value);
			break;
		default:
			break;
		}

		// 遇到新行的操作
		if (thisRow != -1 && thisRow != lastRowNumber) {
			lastColumnNumber = -1;
		}

		// 空值的操作
		if (record instanceof MissingCellDummyRecord) {
			MissingCellDummyRecord mc = (MissingCellDummyRecord) record;
			// curRow = thisRow = mc.getRow();
			thisColumn = mc.getColumn();
			rowlist.add(thisColumn, " ");
		}

		// 更新行和列的值
		if (thisRow > -1)
			lastRowNumber = thisRow;
		if (thisColumn > -1)
			lastColumnNumber = thisColumn;

		// 行结束时的操作
		if (record instanceof LastCellOfRowDummyRecord) {
			if (minColumns > 0) {
				// 列值重新置空
				if (lastColumnNumber == -1) {
					lastColumnNumber = 0;
				}
			}
			lastColumnNumber = -1;

			// 每行结束时， 调用getRows() 方法
			rowReader.getRows(sheetIndex, curRow, rowlist);
			curRow++;

			// 清空容器
			rowlist.clear();
		}
	}

}
