package com.takesoon.oms.ssi.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.dom4j.Document;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import com.opensymphony.xwork2.ActionSupport;

@Namespace("/admin")
@Results({
	@Result(name = "success", location = "/index.jsp")
})
public class BaseAction extends ActionSupport implements SessionAware,
		ServletRequestAware, ServletResponseAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	protected Map map;

	protected HttpServletRequest request;

	protected HttpServletResponse response;

	// 数据内容
	protected String data = null;

	// 起始时间
	protected String startdate = null;

	// 结束时间
	protected String enddate = null;

	// 数据是否准备好
	protected String isOk = null;

	// 类型参数
	protected String type = null;

	//开始ID
	protected Integer start = 0;

	//每页显示数量
	protected Integer limit = 15;

	//查询字段名称
	protected String colName = null;

	//查询字段值
	protected String value = null;
	
	public String execute() throws Exception {
		try {
			return SUCCESS;
		} catch (Exception e) {
			return ERROR;
		}
	}

	public void setSession(Map map) {
		this.map = map;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	/**
	 * 根据指定的内容类型构建输出对象
	 * @param contentType
	 * @param response
	 * @return
	 */
	protected PrintWriter getOut(String contentType,
			HttpServletResponse response) {
		response.setHeader("progma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType(contentType);
		try {
			return response.getWriter();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			logger.debug("BaseAction.getOut("+contentType+",response).Exception:"+e1);
		}
		return null;
	}

	/**
	 * 构建输出对象
	 * @param response
	 * @return
	 */
	protected PrintWriter getOut(HttpServletResponse response) {
		response.setHeader("progma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("text/html");
		try {
			return response.getWriter();
		} catch (IOException e1) {
			logger.debug("BaseAction.getOut(HttpServletResponse response).Exception:"+e1);
		}
		return null;
	}

	/**
	 * 格式化XML文档
	 * 
	 * @param document
	 *            xml文档
	 * @param charset
	 *            字符串的编码
	 * @return 格式化后XML字符串
	 */
	protected String formatXML(Document document, String charset) {
		OutputFormat format = OutputFormat.createPrettyPrint();
		format.setEncoding(charset);
		StringWriter sw = new StringWriter();
		XMLWriter xw = new XMLWriter(sw, format);
		try {
			xw.write(document);
			xw.flush();
			xw.close();
		} catch (IOException e) {
			logger.error("格式化XML文档发生异常，请检查！", e);
		}
		return sw.toString().replace("&amp;", "&");
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getEnddate() {
		return enddate;
	}

	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}

	public String getStartdate() {
		return startdate;
	}

	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}

	public String getIsOk() {
		return isOk;
	}

	public void setIsOk(String isOk) {
		this.isOk = isOk;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getColName() {
		return colName;
	}

	public void setColName(String colName) {
		this.colName = colName;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}