package com.xcms.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class FormServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected final Log logger = LogFactory.getLog(this.getClass());
	final String REQUEST_URL = "http://180.168.68.82:6012/nutzd/website/root.cgi";


	/**
	 * Constructor of the object.
	 */
	public FormServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		logger.info(" >> request:"+request.getQueryString());
		logger.info(" >> hsbrand:"+request.getParameter("hsbrand"));
		logger.info(" >> hsmodel:"+request.getParameter("hsmodel"));
		logger.info(" >> camere:"+request.getParameter("camere"));
		response.setCharacterEncoding("UTF-8");
		PrintWriter pw = response.getWriter();
		String content = null;
		if(null != content){
			pw.append(content);
		}else{
			pw.append("{\"success\":true,\"msg\":'成功'}");
		}
		pw.flush();
		pw.close();
	}
	
	private String getContent(){
		String  content = null;
		HttpClient client = null;
		GetMethod get = null;
		try{
			client = new HttpClient();
			get = new GetMethod(REQUEST_URL);
			
			int statusCode = client.executeMethod(get);
			if(statusCode == HttpStatus.SC_OK){
				content = new String(get.getResponseBody(),"UTF-8");
				logger.info(">> remote content is :" + content);
			}
		}catch(Exception e){
			logger.error(e);
		}finally{
			if(null != client)
				client = null;
			if(null != get)
				get = null;
		}
		return content;
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occure
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
