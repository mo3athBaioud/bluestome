package com.ssi.common.utils;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

public class RequestUtils {

	public static String getRequestHeader(HttpServletRequest request) {
		Enumeration e = request.getHeaderNames();

		String ret = "";
		while (e.hasMoreElements()) {
			String a = (String) e.nextElement();
			ret += a + ": " + request.getHeader(a) + "\n";
		}

		return ret;
	}
}
