package com.xcms.web.action;

import java.util.StringTokenizer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.xcms.common.json.JsonObject;

public class BaseAction {

	protected JsonObject json;
	
	protected final Log logger = LogFactory.getLog(this.getClass());

	/**
	 * 获取文件扩展名
	 * @param fileName
	 * @return
	 */
	protected String getFileSuffix(String fileName) {
		String filesuffix = null;
		StringTokenizer fx = new StringTokenizer(fileName, ". ");
		int n = fx.countTokens();
		while (fx.hasMoreTokens()) {
			filesuffix = fx.nextToken();
		}
		return filesuffix;

	}

}
