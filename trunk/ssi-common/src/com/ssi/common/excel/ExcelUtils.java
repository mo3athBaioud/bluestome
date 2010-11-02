package com.ssi.common.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.Converter;
import org.apache.commons.beanutils.converters.StringConverter;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.NumberUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.ssi.common.exception.ExcelException;
import com.ssi.common.utils.RegexpUtils;


/**
 * 该类为处理excel文件的工具类。
 */
public class ExcelUtils {
	private static final Log logger = LogFactory.getLog(ExcelUtils.class);

	private static BeanUtilsBean beanUtilsBean = new BeanUtilsBean();
	private static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

	private static class DateConverter implements Converter {
		private Converter stringConverter = new StringConverter();

		public Object convert(Class type, Object value) {
			if (value instanceof java.util.Date) {
				DateFormat format = new SimpleDateFormat(DATE_TIME_FORMAT);

				return format.format(value);
			} else {
				return stringConverter.convert(type, value);
			}
		}
	}

	static {
		// 设置日期转为字符串的转换器。
		beanUtilsBean.getConvertUtils().register(new DateConverter(),
				String.class);
	}

	/**
	 * 方法根据多个sheetConfig设置的内容及数据源，将数据以excel的形式输出到output中。
	 * 
	 * @param sheetConfigs
	 * @param output
	 * @throws IOException
	 */
	public static void exportToExcel(ExcelSheetConfig[] sheetConfigs,
			OutputStream output) throws IOException {
		if (logger.isDebugEnabled()) {
			logger.debug("开始导出Excel文件。");
		}

		for (ExcelSheetConfig config : sheetConfigs) {
			checkExportArgument(config, output);
		}

		HSSFWorkbook wb = new HSSFWorkbook();

		exportToExcel(sheetConfigs, wb);

		try {
			wb.write(output);
		} catch (IOException e) {
			String msg = "excel文件在输出的时发生错误。";

			logger.error(msg, e);
			throw e;
		}

		if (logger.isDebugEnabled()) {
			logger.debug("成功导出" + sheetConfigs.length + "个sheet。");
		}
	}

	/**
	 * 方法根据sheetConfig设置的内容及数据源，将数据以excel的形式输出到output中。
	 * 
	 * @param sheetConfig
	 * @param output
	 */
	public static void exportToExcel(ExcelSheetConfig sheetConfig,
			OutputStream output) throws IOException {
		if (logger.isDebugEnabled()) {
			logger.debug("开始导出Excel文件。");
		}

		checkExportArgument(sheetConfig, output);

		HSSFWorkbook wb = new HSSFWorkbook();

		exportToExcel(sheetConfig, wb);

		try {
			wb.write(output);
		} catch (IOException e) {
			String msg = "excel文件在输出的时发生错误。";

			logger.error(msg, e);
			throw e;
		}

		if (logger.isDebugEnabled()) {
			logger.debug("成功导出" + sheetConfig.getExportDataSource().size()
					+ "条记录。");
		}
	}

	/**
	 * 根据多个sheetConfig设置的内容及数据源，将数据以excel的形式输出到同一个workbook中。
	 * 
	 * @param configs
	 * @param wb
	 */
	private static void exportToExcel(ExcelSheetConfig[] configs,
			HSSFWorkbook wb) {
		for (ExcelSheetConfig config : configs) {
			exportToExcel(config, wb);
		}
	}

	/**
	 * 方法根据sheetConfig设置的内容及数据源，将数据以excel的形式输出到workbook中。
	 * 
	 * @param sheetConfig
	 * @param output
	 * @param exportData
	 * @param wb
	 */
	private static void exportToExcel(ExcelSheetConfig sheetConfig,
			HSSFWorkbook wb) {
		HSSFSheet sheet = wb.createSheet();
		// 设置work sheet的名称
		if (!StringUtils.isEmpty(sheetConfig.getSheetName())) {
			String sheetName = sheetConfig.getSheetName();

			if (wb.getSheet(sheetName) != null) {
				sheetName += wb.getNumberOfSheets();
			}

			wb.setSheetName(wb.getNumberOfSheets() - 1, sheetConfig
					.getSheetName());
		}

		// 设置excel的表示显示风格
		HSSFCellStyle labelStyle = wb.createCellStyle();

		labelStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		labelStyle.setFillBackgroundColor((short) 0);

		HSSFFont labelFont = wb.createFont();

		labelFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		labelFont.setColor(HSSFFont.COLOR_NORMAL);
		labelStyle.setFont(labelFont);

		verticalExport(sheetConfig, sheetConfig.getExportDataSource(), sheet,
				labelStyle);

	}

	/**
	 * 根据sheetConfig设置的内容，从指定的excel文件中解析数据。
	 * 
	 * @param sheetConfig
	 * @param filename
	 * @return Map as List，一个Map对应于一行数据，Key为属性名，Value为实际值
	 * @throws IOException
	 *             从输入流生成POI workbook对象出错时
	 */
	public static List<Map<String, Object>> importFromExcel(
			ExcelSheetConfig sheetConfig, String filename) throws ExcelException,IOException {

		File datasetFile = new File(filename);
		InputStream is = null;

		// First look for data file in file system
		if (datasetFile.exists()) {
			is = new FileInputStream(datasetFile);
			logger.debug("Loading file: " + filename + " from file system");
		} else {
			// Not in file system, check classpath.
			is = ExcelUtils.class.getClassLoader()
					.getResourceAsStream(filename);
			logger.debug("Loading file: " + filename + " from classpath");
		}

		return importFromExcel(sheetConfig, is);
	}

	/**
	 * 根据指定sheetConfig校验excel文件内容
	 * 
	 * @param sheetConfig
	 * @param filename
	 * @return
	 * @throws IOException
	 */
	public static boolean validateExcel(ExcelSheetConfig sheetConfig,
			String filename) throws IOException, ExcelException {

		File datasetFile = new File(filename);
		InputStream is = null;

		// First look for data file in file system
		if (datasetFile.exists()) {
			is = new FileInputStream(datasetFile);
			logger.debug("Loading file: " + filename + " from file system");
		} else {
			// Not in file system, check classpath.
			is = ExcelUtils.class.getClassLoader()
					.getResourceAsStream(filename);
			logger.debug("Loading file: " + filename + " from classpath");
		}

		if (logger.isDebugEnabled()) {
			logger.debug("开始解析Excel文件。");
		}

		checkImportArgument(sheetConfig, is);

		HSSFWorkbook wb = new HSSFWorkbook(is);

		return validateExcel(sheetConfig, wb);
	}

	/**
	 * 根据指定sheetConfig校验excel文件内容
	 * 
	 * @param sheetConfig
	 * @param wb
	 * @return
	 */
	private static boolean validateExcel(ExcelSheetConfig sheetConfig,
			HSSFWorkbook wb) throws ExcelException {
		HSSFSheet sheet;
		if (StringUtils.isEmpty(sheetConfig.getSheetName())) {
			logger.info("未设置需要读取的工作表名称，默认读取第一张工作表。");
			sheet = wb.getSheetAt(0);// 默认读取第一张工作表
		} else {
			logger.info("读取工作表[" + sheetConfig.getSheetName() + "]。");
			sheet = wb.getSheet(sheetConfig.getSheetName());
		}
		if (sheet == null) {
			if (logger.isWarnEnabled()) {
				logger.warn("No sheet [" + sheetConfig.getSheetName()
						+ "] found in the file.");
			}
			throw new ExcelException("No sheet [" + sheetConfig.getSheetName()
					+ "] found in the file.");
		}

		int beginRow = sheetConfig.getBeginRow();
		int endRow = (sheetConfig.getEndRow() == 0) ? getLastRowNum(sheet,
				sheetConfig) : sheetConfig.getEndRow();
		int propSize = sheetConfig.getPropertySize();

		if (logger.isInfoEnabled()) {
			logger.info("Read row start from index [" + beginRow
					+ "], end with index [" + endRow + "], [" + propSize
					+ "] properties defined.");
		}

		for (int rowIndex = beginRow; rowIndex <= endRow; rowIndex++) {

			HSSFRow row = sheet.getRow(rowIndex);
			for (Property prop : sheetConfig.getProps()) {
				HSSFCell cell = row.getCell((short) prop.getIndex());
				if (cell == null) {
					continue;
				}

				String value = getValue(cell, prop.getType()).trim();
				if (!prop.isNullable()) {
					if (StringUtils.isEmpty(value)) {
						logger.warn("column [" + prop.getName()
								+ "] is not nullable, but found cell ["
								+ rowIndex + "," + prop.getIndex() + "] empty");
						throw new ExcelException("column [" + prop.getName()
								+ "] is not nullable, but found cell ["
								+ rowIndex + "," + prop.getIndex() + "] empty");
					}
				}
				if (Type.LONG == prop.getType()) {
					if (StringUtils.isNotEmpty(value) && !NumberUtils.isNumber(value)) {
						throw new ExcelException("the data type of column ["
								+ prop.getName()
								+ "] is Long, but found cell [" + rowIndex
								+ "," + prop.getIndex() + "] is [" + value
								+ "]");
					}
				}
				if (prop.getRegex() != null) {
					if (!RegexpUtils.matches(prop.getRegex().pattern(), value)) {
						throw new ExcelException("the data of column ["
								+ prop.getName() + "] need to be in format [ "
								+ prop.getRegex().pattern()
								+ "], but found cell [" + rowIndex + ","
								+ prop.getIndex() + "] is [" + value + "]");
					}
				}
			}
		}

		return true;
	}

	/**
	 * 根据sheetConfig设置的内容，从excel输入流中解析数据。
	 * 
	 * @param sheetConfig
	 * @param input
	 * @return Map as List，一个Map对应于一行数据，Key为属性名，Value为实际值
	 * @throws IOException
	 *             从输入流生成POI workbook对象出错时
	 */
	public static List<Map<String, Object>> importFromExcel(
			ExcelSheetConfig sheetConfig, InputStream input) throws ExcelException,IOException {
		if (logger.isDebugEnabled()) {
			logger.debug("开始解析Excel文件。");
		}

		checkImportArgument(sheetConfig, input);

		HSSFWorkbook wb = new HSSFWorkbook(input);

		List<Map<String, Object>> retVal = importFromExcel(sheetConfig, wb);

		if (logger.isDebugEnabled()) {
			logger.debug("成功解析" + retVal.size() + "条记录。");
		}

		return retVal;
	}

	/**
	 * 从excel文件中解析工作表张数
	 * 
	 * @param filename
	 * @return
	 * @throws IOException
	 *             从输入流生成POI workbook对象出错时
	 */
	public static HSSFSheet[] getSheets(String filename) {
		File datasetFile = null;
		InputStream is = null;

		try {
			datasetFile = new File(filename);

			// First look for data file in file system
			if (datasetFile.exists()) {
				is = new FileInputStream(datasetFile);
				logger.debug("Loading file: " + filename + " from file system");
			} else {
				// Not in file system, check classpath.
				is = ExcelUtils.class.getClassLoader().getResourceAsStream(
						filename);
				logger.debug("Loading file: " + filename + " from classpath");
			}

			HSSFWorkbook wb = new HSSFWorkbook(is);

			List<HSSFSheet> ret = new ArrayList<HSSFSheet>();
			for (int index = 0; index < wb.getNumberOfSheets(); index++) {
				if (!wb.isSheetHidden(index)) {
					ret.add(wb.getSheetAt(index));
				}
			}
			return ret.toArray(new HSSFSheet[] {});

		} catch (IOException e) {
			logger.error("", e);
			return new HSSFSheet[0];
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException ignore) {
				}
				is = null;
			}
			if (datasetFile != null) {
				datasetFile = null;
			}
		}
	}

	/**
	 * 
	 * @param sheet
	 * @return
	 * @throws IOException
	 */
	public static String[] readHeadLine(HSSFSheet sheet) throws IOException {
		return readHeadLine(sheet, 0);
	}

	/**
	 * 
	 * @param sheet
	 * @param rowIndex
	 * @return
	 * @throws IOException
	 */
	public static String[] readHeadLine(HSSFSheet sheet, int rowIndex)
			throws IOException {
		HSSFRow row = sheet.getRow(rowIndex);

		List<String> ret = new ArrayList<String>();
		for (int cellIndex = row.getFirstCellNum(); cellIndex < row
				.getLastCellNum(); cellIndex++) {
			ret.add(getValue(row.getCell(cellIndex), Type.STRING));
		}

		return ret.toArray(new String[] {});
	}

	/**
	 * 根据sheetConfig设置的内容，从<code>HSSFWorkbook</code>中解析数据。
	 * 
	 * @param sheetConfig
	 * @param wb
	 * @return Map as List，一个Map对应于一行数据，Key为属性名，Value为实际值
	 */
	private static List<Map<String, Object>> importFromExcel(
			ExcelSheetConfig sheetConfig, HSSFWorkbook wb) throws ExcelException {
		List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();

		HSSFSheet sheet;
		if (StringUtils.isEmpty(sheetConfig.getSheetName())) {
			logger.info("未设置需要读取的工作表名称，默认读取第一张工作表。");
			sheet = wb.getSheetAt(0);// 默认读取第一张工作表
		} else {
			logger.info("读取工作表[" + sheetConfig.getSheetName() + "]。");
			sheet = wb.getSheet(sheetConfig.getSheetName());
		}
		if (sheet == null) {
			if (logger.isWarnEnabled()) {
				logger.warn("No sheet [" + sheetConfig.getSheetName()
						+ "] found in the file.");
			}
			return data;
		}

		int beginRow = sheetConfig.getBeginRow();
		int endRow = (sheetConfig.getEndRow() == 0) ? getLastRowNum(sheet,
				sheetConfig) : sheetConfig.getEndRow();
		int propSize = sheetConfig.getPropertySize();

		if (logger.isInfoEnabled()) {
			logger.info("Read row start from index [" + beginRow
					+ "], end with index [" + endRow + "], [" + propSize
					+ "] properties defined.");
		}

		for (int rowIndex = beginRow; rowIndex <= endRow; rowIndex++) {

			try {
				HSSFRow row = sheet.getRow(rowIndex);
				Map<String, Object> map = readOneRow(sheetConfig, row);
				if (map != null && !map.isEmpty()) {
					data.add(map);
				}
			} catch (ExcelException e) {
				logger.warn("failed to read row index [" + rowIndex
						+ "]. caused by ", e);
				throw new ExcelException("failed to read row index [" + rowIndex
						+ "]. caused by ");
			}
		}

		return data;
	}

	private static int getLastRowNum(HSSFSheet sheet,
			ExcelSheetConfig sheetConfig) {
		int lastRowNum = 0;
		for (int rowIndex = sheetConfig.getBeginRow(); rowIndex <= sheet
				.getLastRowNum(); rowIndex++) {
			HSSFRow row = sheet.getRow(rowIndex);

			if (row == null)
				break;

			HSSFCell cell = row.getCell(sheetConfig.getProps().get(0)
					.getIndex());

			// 如果config中指定的第一列无值则为最末行
			if (cell == null
					|| StringUtils.isEmpty(getValue(cell, Type.STRING))) {
				break;
			} else {
				lastRowNum = rowIndex;
			}
		}
		return lastRowNum;
	}

	/**
	 * 根据sheetConfig设置的内容，从<code>HSSFRow</code>中解析数据。
	 * 
	 * @param sheetConfig
	 * @param row
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private static Map<String, Object> readOneRow(ExcelSheetConfig sheetConfig,
			HSSFRow row) throws ExcelException {
		Map<String, Object> anObj = new HashMap<String, Object>();

		// read one row by given properties.
		for (Property prop : sheetConfig.getProps()) {
			String name = prop.getName();

			HSSFCell cell = row.getCell((short) prop.getIndex());
			if (cell == null) {
				continue;
			}

			if (Type.DATE == prop.getType()) {
				anObj.put(name, cell.getDateCellValue());
			} else {
				String value = getValue(cell, prop.getType());
				if( !prop.isNullable() ){
					if(null == value || "".equalsIgnoreCase(value)){
						throw new ExcelException("列名为["+name+"]不能为空");
					}
				}
				if (Type.STRING == prop.getType()) {
					anObj.put(name, String.valueOf(value));
				} else if (Type.DOUBLE == prop.getType()) {
					if (StringUtils.isNotEmpty(value)) {
						anObj.put(name, Double.valueOf(value));
					}else{
						anObj.put(name, Double.valueOf("0"));
					}
				} else if (Type.BOOLEAN == prop.getType()) {
					if (StringUtils.isNotEmpty(value)) {
						anObj.put(name, Boolean.valueOf(value));
					}else{
						anObj.put(name, false);
					}
				} else if (Type.BYTE == prop.getType()) {
					if (StringUtils.isNotEmpty(value)) {
						anObj.put(name, Byte.valueOf(value));
					}else{
						anObj.put(name, Byte.valueOf("0"));
					}
				} else if (Type.LONG == prop.getType()) {
					if (StringUtils.isNotEmpty(value)) {
						anObj.put(name, Long.valueOf(value));
					}else{
						anObj.put(name, Long.valueOf(0l));
					}
				}
			}

		}

		return anObj;
	}

	/**
	 * 
	 * @param cell
	 * @return
	 */
	private static String getValue(HSSFCell cell, Type expectedType) {
		if (cell == null) {
			return "";
		}
		
		String cellValue = null;
		if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
			if (expectedType == Type.DOUBLE) {
				cellValue = String.valueOf(cell.getNumericCellValue());
			} else {
				cellValue = String.valueOf((long) cell.getNumericCellValue());
			}
		} else if (cell.getCellType() == HSSFCell.CELL_TYPE_BOOLEAN) {
			cellValue = String.valueOf(cell.getBooleanCellValue());
		} else if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
			cellValue = cell.getRichStringCellValue().getString();
		} else if (cell.getCellType() == HSSFCell.CELL_TYPE_BLANK) {
			cellValue = "";
		}
		return cellValue;
	}

	/**
	 * 纵向导出数据
	 * 
	 * @param sheetConfig
	 * @param exportData
	 * @param sheet
	 * @param labelStyle
	 */
	private static void verticalExport(ExcelSheetConfig sheetConfig,
			List<?> exportData, HSSFSheet sheet, HSSFCellStyle labelStyle) {

		writeLabelRow(sheet, sheetConfig.getBeginRow(), sheetConfig.getProps(),
				labelStyle);

		// 输出各行值
		int rowIndex = sheetConfig.getBeginRow() + 1;
		for (Object elem : exportData) {
			if (elem == null)
				continue;

			HSSFRow row = sheet.createRow(rowIndex);
			writeDataRow(row, elem, sheetConfig.getProps());
			rowIndex++;
		}
	}

	/**
	 * 输出数据行
	 * 
	 * @param row
	 * @param elem
	 * @param props
	 */
	private static void writeDataRow(HSSFRow row, Object elem,
			List<Property> props) {
		for (Property prop : props) {
			if (prop == null)
				continue;

			HSSFCell cell = row.createCell((short) prop.getIndex(),
					HSSFCell.CELL_TYPE_BLANK);

			try {
				String propertyValue = beanUtilsBean.getProperty(elem, prop
						.getName());

				// 不同的参数类型自动改变单元格的格式
				if (Type.DATE == prop.getType()) {
					cell.setCellValue(propertyValue);
				} else if (Type.STRING == prop.getType()) {
					cell.setCellValue(propertyValue);
				} else if (Type.DOUBLE == prop.getType()) {
					cell.setCellValue(Double.parseDouble(propertyValue));
				} else if (Type.BOOLEAN == prop.getType()) {
					cell.setCellValue(Boolean.parseBoolean(propertyValue));
				} else if (Type.BYTE == prop.getType()) {
					cell.setCellValue(Byte.parseByte(propertyValue));
				} else if (Type.LONG == prop.getType()) {
					cell.setCellValue(Long.parseLong(propertyValue));
				}
			} catch (Exception e) {
				String msg = "在取得导出对象的java bean属性时出错。";
				logger.error(msg, e);
			}
		}
	}

	/**
	 * 输出标题行
	 * 
	 * @param sheet
	 * @param beginRow
	 * @param props
	 * @param labelStyle
	 */
	private static void writeLabelRow(HSSFSheet sheet, int beginRow,
			List<Property> props, HSSFCellStyle labelStyle) {

		HSSFRow row = sheet.createRow(beginRow);
		for (Property prop : props) {
			if (prop == null)
				continue;

			HSSFCell cell = row.createCell((short) prop.getIndex());

			cell.setCellValue(prop.getTitle());
			cell.setCellStyle(labelStyle);
		}
	}

	/**
	 * 检查数据参数是否正确
	 * 
	 * @param label
	 * @param data
	 * @param output
	 * @param beginRow
	 * @param beginCol
	 */
	private static void checkExportArgument(ExcelSheetConfig sheetConfig,
			OutputStream output) {
		if (sheetConfig == null) {
			String msg = "导出Excel文件的sheetConfig不能为null。";

			logger.error(msg);
			throw new IllegalArgumentException(msg);
		}

		if (sheetConfig.getPropertySize() == 0) {
			String msg = "没有设置要导出的属性及显示名。";

			logger.error(msg);
			throw new IllegalArgumentException(msg);
		}

		if (sheetConfig.getExportDataSource() == null) {
			String msg = "没有设置要导出的数据，必须要指定要导出的数据列表。";

			logger.error(msg);
			throw new IllegalArgumentException(msg);
		}

		if (output == null) {
			String msg = "参数output不能为空，必须指出输出流。";

			logger.error(msg);
			throw new IllegalArgumentException(msg);
		}

		if (sheetConfig.getBeginRow() < 0) {
			String msg = "数据导出的起始行不能小于0。";

			logger.error(msg);
			throw new IllegalArgumentException(msg);
		}

		if (sheetConfig.getPropertySize() > ExcelSheetConfig.MAX_COLUMN_SIZE) {
			String msg = "要导出属性列太多，根据目前的工作表配置，最多只能导出"
					+ ExcelSheetConfig.MAX_COLUMN_SIZE + "列属性";

			logger.error(msg);
			throw new IllegalArgumentException(msg);
		}

		if ((sheetConfig.getBeginRow() + sheetConfig.getExportDataSource()
				.size()) > ExcelSheetConfig.MAX_ROW_SIZE) {
			String msg = "要导出的数据量太大，根据目前的工作表配置，最多只能导出"
					+ (ExcelSheetConfig.MAX_ROW_SIZE - sheetConfig
							.getBeginRow()) + "条记录";

			logger.error(msg);
			throw new IllegalArgumentException(msg);
		}
	}

	/**
	 * 检查数据参数是否正确
	 * 
	 * @param sheetConfig
	 * @param input
	 */
	private static void checkImportArgument(ExcelSheetConfig sheetConfig,
			InputStream input) {
		if (input == null) {
			String msg = "参数input不能为空，必须指出输入流。";

			logger.error(msg);
			throw new IllegalArgumentException(msg);
		}

		if (sheetConfig == null) {
			String msg = "导入Excel文件的sheetConfig不能为null。";

			logger.error(msg);
			throw new IllegalArgumentException(msg);
		}

		if (sheetConfig.getPropertySize() == 0) {
			String msg = "没有设置要解析的属性。它需要指出要解析的属性、类型以及在excel文件中的位置。";

			logger.error(msg);
			throw new IllegalArgumentException(msg);
		}

		if (sheetConfig.getBeginRow() < 0) {
			String msg = "起始行不能小于0。";

			logger.error(msg);
			throw new IllegalArgumentException(msg);
		}

		for (Property prop : sheetConfig.getProps()) {
			if (!ArrayUtils.contains(Type.values(), prop.getType())) {
				String msg = "指定的类型[" + prop.getType() + "]无效。";

				logger.error(msg);
				throw new IllegalArgumentException(msg);
			}
		}
	}
}
