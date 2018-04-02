package com.glanway.utils.sax2003;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.eventusermodel.EventWorkbookBuilder.SheetRecordCollectingListener;
import org.apache.poi.hssf.eventusermodel.FormatTrackingHSSFListener;
import org.apache.poi.hssf.eventusermodel.HSSFEventFactory;
import org.apache.poi.hssf.eventusermodel.HSSFRequest;
import org.apache.poi.hssf.eventusermodel.MissingRecordAwareHSSFListener;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import com.glanway.utils.sax.ExcelRowReader;

/**
 * POI以SAX方式解析Excel2003.
 *
 * @author fuqihao
 * @version 1.0
 * @date 2018年3月30日 下午12:01:40
 */
public class ExcelSAX2003 {

	private POIFSFileSystem fileSystem;// POI系统文件
	private ExcelRowReader rowReader;

	public ExcelSAX2003(POIFSFileSystem fileSystem, ExcelRowReader rowReader) {
		super();
		this.fileSystem = fileSystem;
		this.rowReader = rowReader;
	}

	public ExcelSAX2003(POIFSFileSystem fileSystem) {
		super();
		this.fileSystem = fileSystem;
	}

	private boolean outputFormulaValues = true;
	private Map<Integer, List<List<String>>> map = new HashMap<>();

	/**
	 * 单行读取Excel表格.
	 *
	 * @author fuqihao
	 * @throws IOException
	 * @date 2018年3月30日 下午12:02:49
	 */
	public void readSingleExcel() throws IOException {
		final ReadSingleExcelListener hssfListener = new ReadSingleExcelListener(outputFormulaValues);
		hssfListener.setRowReader(rowReader);// 设置处理读每行
		final MissingRecordAwareHSSFListener listener = new MissingRecordAwareHSSFListener(hssfListener);
		final FormatTrackingHSSFListener formatListener = new FormatTrackingHSSFListener(listener);
		hssfListener.setFormatListener(formatListener);// 设置格式化监听器
		final HSSFEventFactory factory = new HSSFEventFactory();
		final HSSFRequest request = new HSSFRequest();
		if (outputFormulaValues) {
			request.addListenerForAllRecords(formatListener);
		} else {
			final SheetRecordCollectingListener workbookBuildingListener = new SheetRecordCollectingListener(
					formatListener);
			hssfListener.setWorkbookBuildingListener(workbookBuildingListener);// 设置工作簿创建监听器
			request.addListenerForAllRecords(workbookBuildingListener);
		}
		factory.processWorkbookEvents(request, fileSystem);
	}

	/**
	 * 单sheet读取Excel表格.
	 *
	 * @author fuqihao
	 * @return
	 * @throws IOException
	 * @date 2018年4月2日 下午5:14:06
	 */
	public Map<Integer, List<List<String>>> readExcel() throws IOException {
		final ReadExcelListener hssfListener = new ReadExcelListener(map, outputFormulaValues);
		final MissingRecordAwareHSSFListener listener = new MissingRecordAwareHSSFListener(hssfListener);
		final FormatTrackingHSSFListener formatListener = new FormatTrackingHSSFListener(listener);
		hssfListener.setFormatListener(formatListener);// 设置格式化监听器
		final HSSFEventFactory factory = new HSSFEventFactory();
		final HSSFRequest request = new HSSFRequest();
		if (outputFormulaValues) {
			request.addListenerForAllRecords(formatListener);
		} else {
			final SheetRecordCollectingListener workbookBuildingListener = new SheetRecordCollectingListener(
					formatListener);
			hssfListener.setWorkbookBuildingListener(workbookBuildingListener);// 设置工作簿创建监听器
			request.addListenerForAllRecords(workbookBuildingListener);
		}
		factory.processWorkbookEvents(request, fileSystem);

		return map;
	}

}
