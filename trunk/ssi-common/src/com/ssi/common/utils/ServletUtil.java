package com.ssi.common.utils;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

public class ServletUtil {
	public static int getInt(HttpServletRequest request, String key) {
		return StringUtils.isBlank(request.getParameter(key)) ? 0 : Integer
				.parseInt(request.getParameter(key));
	}

	public static int getInt(HttpServletRequest request, String key,
			int defaultValue) {
		return StringUtils.isBlank(request.getParameter(key)) ? defaultValue
				: Integer.parseInt(request.getParameter(key));
	}

	public static Long getLong(HttpServletRequest request, String key) {
		return StringUtils.isBlank(request.getParameter(key)) ? 0l : Long
				.parseLong(request.getParameter(key));
	}

	public static Long getLong(HttpServletRequest request, String key,
			Long defaultValue) {
		return StringUtils.isBlank(request.getParameter(key)) ? defaultValue
				: Long.parseLong(request.getParameter(key));
	}

	public static Short getShort(HttpServletRequest request, String key) {
		return StringUtils.isBlank(request.getParameter(key)) ? 0 : Short
				.parseShort(request.getParameter(key));
	}

	public static Short getShort(HttpServletRequest request, String key,
			Short defaultValue) {
		return StringUtils.isBlank(request.getParameter(key)) ? defaultValue
				: Short.parseShort(request.getParameter(key));
	}

	public static String getString(HttpServletRequest request, String key) {
		return StringUtils.isBlank(request.getParameter(key)) ? "" : request
				.getParameter(key);
	}

	public static String getString(ServletRequest request, String key) {
		return StringUtils.isBlank(request.getParameter(key)) ? "" : request
				.getParameter(key);
	}
	
	public static String getString(HttpServletRequest request, String key,
			String defaultValue) {
		return StringUtils.isBlank(request.getParameter(key)) ? defaultValue
				: request.getParameter(key);
	}

	/**
	 * 页面跳转
	 * 
	 * @param out
	 * @param info
	 *            提示信息
	 * @param url
	 *            目标URL
	 */
	public static void forword(PrintWriter out, String info, String url) {
		if (info != null) {
			out.println("<script language='javascript'>alert(\"" + info
					+ "\");</script>");
		}
		out.println("<script language='javascript'>");
		out.println("window.location.href='" + url + "'");
		out.println("</script>");
	}

	public static void forwordClose(PrintWriter out, String info) {
		if (info != null) {
			out.println("<script language='javascript'>alert(\"" + info
					+ "\");</script>");
		}
		out.println("<script language='javascript'>");
		out.println("window.close();");
		out.println("</script>");
	}

	/**
	 * 页面跳转
	 * 
	 * @param out
	 * @param info
	 *            提示信息
	 * @param url
	 *            目标URL
	 */
	public static void forwordTop(PrintWriter out, String info, String url) {
		if (info != null) {
			out.println("<script language='javascript'>alert(\"" + info
					+ "\");</script>");
		}
		out.println("<script language='javascript'>");
		out.println("parent.window.location.href='" + url + "'");
		out.println("</script>");
	}

	/**
	 * 页面跳转
	 * 
	 * @param out
	 * @param info
	 *            提示信息
	 * @param url
	 *            目标URL
	 */
	public static void forword(PrintWriter out, String url) {

		out.println("<script language='javascript'>");
		out.println("window.location.href='" + url + "'");
		out.println("</script>");
	}

	public static PrintWriter getOut(HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		try {
			return response.getWriter();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return null;
	}

}
