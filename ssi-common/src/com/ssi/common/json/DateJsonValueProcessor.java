package com.ssi.common.json;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Properties;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

public class DateJsonValueProcessor implements JsonValueProcessor{

	//定义Date默认的JSON格式
	private static String DATEFORMAT = "yyyy-MM-dd HH:mm:ss";

	private final static String DATEFORMAT_KEY = "dateformat";
	
	private DateFormat dateFormat;

	/**
	 * 是否格式化Date<br>
	 * 方便自定义需求
	 */
	private static boolean IFFORMATDATE = false;

	private final static String IFFORMATDATE_KEY = "ifFormatDate";
	// 静态方法加载配置文件
	static {
		Properties properties = new Properties();
		try {
			InputStream is = JSONObject.class.getClassLoader()
					.getResourceAsStream("dateformat.properties");
			properties.load(is);
			DATEFORMAT = properties.getProperty(DATEFORMAT_KEY);
			if (properties.containsKey(IFFORMATDATE_KEY)) {
				IFFORMATDATE = Boolean.parseBoolean(properties
						.getProperty(IFFORMATDATE_KEY));
			}
		} catch (FileNotFoundException e) {
			System.out
					.println("系统找不到dateformat.properties配置文件,Date类型对象转换成JSON使用默认的格式:"
							+ DATEFORMAT);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 构造方法.
	 * 
	 * @param datePattern
	 *            日期格式
	 */
	public DateJsonValueProcessor(String datePattern) {
		try {
			dateFormat = new SimpleDateFormat(datePattern);
		} catch (Exception ex) {
			dateFormat = new SimpleDateFormat(DATEFORMAT);
		}
	}

	public Object processArrayValue(Object value, JsonConfig jsonConfig) {
		return process(value);
	}

	public Object processObjectValue(String key, Object value,
			JsonConfig jsonConfig) {
		return process(value);
	}

	private Object process(Object value) {
		if (value == null) {
			value = new Date(); // 为null时返回当前日期，也可以返回"",看需要
		}
		return dateFormat.format((Date) value);
	}

	public static String map2JSonStr(Map map) {
		JsonConfig jsonConfig = new JsonConfig();
		if(IFFORMATDATE){
			DateJsonValueProcessor beanProcessor = new DateJsonValueProcessor(
					DATEFORMAT);
			jsonConfig.registerJsonValueProcessor(Date.class, beanProcessor);
		}
		JSONObject jsonObject = JSONObject.fromObject(map, jsonConfig);

		return jsonObject.toString();
	}

	// 将对象转换为json string，使用上面定义的的日期格式
	public static JSONObject obj2JsonObj(Object obj) {
		JsonConfig jsonConfig = new JsonConfig();
		if(IFFORMATDATE){
			DateJsonValueProcessor beanProcessor = new DateJsonValueProcessor(
					DATEFORMAT);
			jsonConfig.registerJsonValueProcessor(Date.class, beanProcessor);
		}

		JSONObject jsonObject = JSONObject.fromObject(obj, jsonConfig);

		return jsonObject;
	}

}
