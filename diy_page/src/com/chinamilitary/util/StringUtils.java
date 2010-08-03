package com.chinamilitary.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {
	/**
	 * 判断连接中是否存在创建文件夹时的非法字符
	 * 
	 * @param str
	 *            输入字符串
	 * @return
	 */
	public static String illageString(String str) {
		
		if(str.indexOf("'") > -1){
			str = str.replaceAll("'", "’");
		}
		
		if (str.indexOf("\"") != -1) {
			str = str.replace("\"", "");
		}

		if (str.indexOf("“") != -1) {
			str = str.replace("“", "");
		}

		if (str.indexOf("”") != -1) {
			str = str.replace("“", "");
		}

//		if (str.indexOf("?") != -1) {
//			str = construct(str);
//		}

		if (str.indexOf("\\") != -1) {
			str = str.replaceAll("\\", "");
		}

		if (str.indexOf("//") != -1) {
			str = str.replaceAll("//", "");
		}

		if (str.indexOf(":") != -1) {
			str = str.replaceAll(":", "：");
		}
		
		if (str.indexOf("*") != -1) {
			str = str.replace("*", "×");
		}
		if(str.indexOf("!") != -1){
			str = str.replace("!", "！");
		}

		if (str.indexOf(">") != -1) {
			str = str.replace(">", "");
		}

		if (str.indexOf("<") != -1) {
			str = str.replace("<", "");
		}

		if (str.indexOf("||") != -1) {
			str = str.replace("||", "");
		}

		if (str.indexOf("?") != -1) {
			str = str.replace("?", "？");
		}
		
		return str;
	}

	public static String construct(String sql) {
		String key = "\\?";
		Pattern p = Pattern.compile(key);
		Matcher m = p.matcher(sql);
		StringBuffer stringBuffer = new StringBuffer();
		int i = 0;
		boolean result = m.find();
		while (result) {
			m.appendReplacement(stringBuffer, "");
			result = m.find();
			i++;
		}
		return String.valueOf(m.appendTail(stringBuffer));
	}
	
	public static void main(String args[]){
		String str = "?1?2?3?4*5**";
		System.out.println(illageString(str));
	}

}
