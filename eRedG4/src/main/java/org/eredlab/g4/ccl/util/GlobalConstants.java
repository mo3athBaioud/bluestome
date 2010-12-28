package org.eredlab.g4.ccl.util;

/**
 * 常量表
 * 
 * @author XiongChun
 * @since 2009-07-13
 */
public interface GlobalConstants {
	/**
	 * XML文档风格<br>
	 * 0:节点属性值方式
	 */
	public static final String XML_Attribute = "0";

	/**
	 * XML文档风格<br>
	 * 1:节点元素值方式
	 */
	public static final String XML_Node = "1";

	/**
	 * 字符串组成类型<br>
	 * number:数字字符串
	 */
	public static final String S_STYLE_N = "number";

	/**
	 * 字符串组成类型<br>
	 * letter:字母字符串
	 */
	public static final String S_STYLE_L = "letter";

	/**
	 * 字符串组成类型<br>
	 * numberletter:数字字母混合字符串
	 */
	public static final String S_STYLE_NL = "numberletter";

	/**
	 * 格式化<br>
	 * FORMAT_DateTime: 日期时间
	 */
	public static final String FORMAT_DateTime = "yyyy-MM-dd hh:mm:ss";

	/**
	 * 格式化<br>
	 * FORMAT_DateTime: 日期
	 */
	public static final String FORMAT_Date = "yyyy-MM-dd";

	/**
	 * 格式化<br>
	 * FORMAT_DateTime: 时间
	 */
	public static final String FORMAT_Time = "hh:mm:ss";

	/**
	 * 换行符<br>
	 * \n:换行
	 */
	public static final String ENTER = "\n";

	/**
	 * 异常信息统一头信息<br>
	 * 非常遗憾的通知您,程序发生了异常
	 */
	public static final String Exception_Head = "\n非常遗憾的通知您,程序发生了异常.\n" + "异常信息如下:\n";

	/**
	 * Ext表格加载模式<br>
	 * \n:非翻页排序加载模式
	 */
	public static final String EXT_GRID_FIRSTLOAD = "first";

	/**
	 * Excel模板数据类型<br>
	 * number:数字类型
	 */
	public static final String ExcelTPL_DataType_Number = "number";

	/**
	 * Excel模板数据类型<br>
	 * number:文本类型
	 */
	public static final String ExcelTPL_DataType_Label = "label";
}