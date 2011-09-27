package com.takesoon.oms.ssi.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.takesoon.oms.ssi.json.DateJsonValueProcessor;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import net.sf.json.JSONObject;

/**
 * 数据转换工具类，用于将单个对象、List转换为json、xml格式的字符串
 */
public class ExtUtil {
	
	public static String TOTAL = "total";
	
	public static String RECORDS = "records";

	/**
	 * 将list对象转换为json格式的数据
	 * 
	 * @param totalNum，记录总数
	 * @param inList，需要转换的list
	 * @return
	 */
	public static String getJsonFromList(long totalNum, List inList) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(TOTAL, totalNum);
		map.put(RECORDS, inList);
		return DateJsonValueProcessor.map2JSonStr(map);
	}

	/**
	 * 将单个对象转换为json格式,此对象不能为集合类型
	 * 
	 * @param inObject
	 * @return
	 */
	public static String getJsonFromObject(Object inObject) {
		JSONObject jsonString = DateJsonValueProcessor.obj2JsonObj(inObject);
		return jsonString.toString();
	}

	/**
	 * 将List转化为xml格式的数据
	 * 
	 * @param totalNum
	 * @param inList，需要转换的list
	 * @return String
	 */
	public static String getXmlFromList(long totalNum, List inList) {
		Total total = new Total();
		total.setTotalNum(totalNum);
		// 创建临时的List对象
		List results = inList;
		results.add(total);
		// 创建XStream对象
		XStream xs = new XStream(new DomDriver());
		// 为所有的类创建别名，别名为不包含包名的类名
		for (int i = 0; i < results.size(); i++) {
			Class clzz = results.get(i).getClass();
			// 得到全限定类名
			String fullName = clzz.getName();
			// 以"."分割字符串
			String[] temp = fullName.split("\\.");
			xs.alias(temp[temp.length - 1], clzz);
		}
		String xmlString = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
				+ xs.toXML(results);
		return xmlString;
	}

	/**
	 * 将一个Object对象转化为xml格式输出
	 * 
	 * @param object
	 * @return
	 */
	public static String getXmlFromObject(Object object) {
		XStream xs = new XStream(new DomDriver());
		Class clazz = object.getClass();
		String[] temp = clazz.getName().split("\\.");
		xs.alias(temp[temp.length - 1], clazz);
		String xmlString = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
				+ xs.toXML(object);
		return xmlString;
	}

	/**
	 * 将对象转换为表单数据加载需要的格式
	 * 
	 * @param object
	 * @return
	 */
	public static String getLoadFormData(Object object) {

		return "";
	}

	public static void main(String[] args) {
	}
}

/**
 * 将List转化为xml时getXmlFromList()，用来存放记录总数
 * 
 * @author Administrator
 * 
 */
class Total {

	private long totalNum;// 记录总数

	private List resultList;// 要转换的List,也是转换后的结果集名称

	public long getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(long totalNum) {
		this.totalNum = totalNum;
	}

	public List getResultList() {
		return resultList;
	}

	public void setResultList(List resultsList) {
		this.resultList = resultsList;
	}

}