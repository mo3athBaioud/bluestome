package com.ssi.common.excel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 定义excel解析规则
 * 
 * @author wangqi
 * 
 */
public class ExcelSheetConfig {
	public static final int MAX_ROW_SIZE = 65536;

	public static final int MAX_COLUMN_SIZE = 675;

	// 以顺序保存的属性名
	private List<Property> props = new ArrayList<Property>();

	// 数据从哪一行开始
	private int beginRow;

	// 数据从哪一行截止。
	private int endRow;

	// 数据从哪一个工作表导入，或者导出的工作表名
	private String sheetName;

	// 需要到处的数据源
	private List<?> exportDataSource;

	/**
	 * @return the exportDataSource
	 */
	public List<?> getExportDataSource() {
		return exportDataSource;
	}

	/**
	 * @param exportDataSource
	 *            the exportDataSource to set
	 */
	public void setExportDataSource(List<?> exportDataSource) {
		this.exportDataSource = exportDataSource;
	}

	public int getBeginRow() {
		return beginRow;
	}

	public void setBeginRow(int beginRow) {
		this.beginRow = beginRow;
	}

	public int getEndRow() {
		return endRow;
	}

	public void setEndRow(int endRow) {
		this.endRow = endRow;
	}

	/**
	 * 取得工作表的名称
	 * 
	 * @return
	 */
	public String getSheetName() {
		return sheetName;
	}

	/**
	 * 设置工作表的名称
	 * 
	 * @param sheetName
	 */
	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}

	/**
	 * 取得所有定义的属性集合
	 * 
	 * @return
	 */
	public List<Property> getProps() {
		return props;
	}

	/**
	 * 增加一个属性定义，会自动根据属性中的索引对所有属性重新排序
	 * 
	 * @param prop
	 * @return
	 */
	public ExcelSheetConfig addProp(Property prop) {
		this.props.add(prop);
		Collections.sort(this.props);
		return this;
	}

	/**
	 * 根据索引获取定义的属性
	 * 
	 * @param index
	 * @return
	 */
	public Property getProperty(int index) {
		return this.props.get(index);
	}

	/**
	 * 获取定义的属性数量
	 * 
	 * @return
	 */
	public int getPropertySize() {
		return this.props.size();
	}
}
