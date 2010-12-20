package com.sky.spirit.basic.struts;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.sky.spirit.basic.database.util.DBUtil;
import com.sky.spirit.common.Constants;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/**
 * 
 * 注释
 * 
 * @author <a href="jonny_quan@hotmail.com">jonny</a>
 * @date 2008 2008-9-6 上午02:39:10
 * @version 1.0.0<br>
 *          更新记录备注 更新人，更新时间，更新内容，及版本号
 * 
 */
public abstract class BaseDispatchAction extends DispatchAction {
	protected static Log log = LogFactory.getLog(BaseDispatchAction.class);
	protected ExecutorService responseExecutor = Executors.newFixedThreadPool(10);
	protected String peerIp;

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ActionForward forward = null;

		try {
			forward = super.execute(mapping, form, request, response);
		} catch (RuntimeException e) {
			e.printStackTrace();
		} finally {
			DBUtil.warn();
		}

		return forward;
	}

	protected void write(StringBuffer content, HttpServletResponse response,
			boolean isFinal) {

		setTextHeader(response);
		OutputStream out = null;
		try {
			response.setContentLength((content.toString()
					.getBytes(Constants.SKY_SPIRIT_HTTP_ENCODEING)).length);
			out = response.getOutputStream();
//			execute(content.toString().getBytes(Constants.SKY_SPIRIT_HTTP_ENCODEING),out);
			out.write(content.toString().getBytes(
					Constants.SKY_SPIRIT_HTTP_ENCODEING));
			out.flush();
		} catch (IOException e) {
			log.error(e);
		} finally {
			if (isFinal) {
				IOUtils.closeQuietly(out);
			}
		}
	}

	protected void write(String content, HttpServletResponse response,
			boolean isFinal) {
		setTextHeader(response);
		OutputStream out = null;
		try {
			response.setContentLength(content
					.getBytes(Constants.SKY_SPIRIT_HTTP_ENCODEING).length);
			out = response.getOutputStream();
//			execute(content.getBytes(Constants.SKY_SPIRIT_HTTP_ENCODEING),out);
			out.write(content.getBytes(Constants.SKY_SPIRIT_HTTP_ENCODEING));
			out.flush();
		} catch (IOException e) {
			log.error(e);
		} finally {
			if (isFinal) {
				IOUtils.closeQuietly(out);
			}
		}
	}

	public abstract byte[] skyResponseContentPack(byte[] content,
			String messageType);

	protected void write(byte[] content, String messageType,
			HttpServletResponse response, boolean isFinal) {
		log.debug(">> content length is ["+content.length + "]");
		Long start = System.currentTimeMillis();
		if (isFinal) {
			content = skyResponseContentPack(content, messageType);
		}
		Long start1 = System.currentTimeMillis();
		setAppHeader(response);
		if (content == null) {
			response.setContentLength(0);
		} else {
			response.setContentLength(content.length);
			response.setHeader("Keep-alive", "true");
			response.setHeader("Proxy-Connection", "Keep-Alive");
		}
		Long end1 = System.currentTimeMillis();
		log.debug(">> setAppHeader time["+(end1 - start1)+"] ms");
		OutputStream out = null;
		try {
			Long start2 = System.currentTimeMillis();
			out = response.getOutputStream();
//			execute(content,out);
			if (content != null) {
				Long start3 = System.currentTimeMillis();
				out.write(content);
				Long end3 = System.currentTimeMillis();
				log.debug(">> write time["+(end3 - start3)+"] ms");
			}
			out.flush();
			Long end2 = System.currentTimeMillis();
			log.debug(">> output time["+(end2 - start2)+"] ms");
			
			Long end = System.currentTimeMillis();
			log.debug(">> write content speend time["+(end - start)+"] ms");
		} catch (IOException e) {
			log.error(e);
		} finally {
			if (isFinal) {
				IOUtils.closeQuietly(out);
			}
		}
	}

	protected void write(byte[] content, HttpServletResponse response,
			boolean isFinal) {

		setAppHeader(response);
		if (content == null) {
			response.setContentLength(0);
		} else {
			response.setContentLength(content.length);
			response.setHeader("Keep-alive", "true");
			response.setHeader("Proxy-Connection", "Keep-Alive");
		}
		OutputStream out = null;
		try {
			out = response.getOutputStream();
//			execute(content,out);
			if (content != null) {
				out.write(content);
			}
			out.flush();
		} catch (IOException e) {
			log.error(e);
		} finally {
			if (isFinal) {
				IOUtils.closeQuietly(out);
			}
		}
	}
	
	/**
	 * 
	 * @param content
	 * @param out
	 */
	protected synchronized void execute(final byte[] content,final OutputStream out){
		log.debug(">> 执行对外写数据任务");
		Runnable handler =  new Runnable(){
			public synchronized void run(){
				try {
					if (content != null) {
						out.write(content);
					}
					out.flush();
				} catch (IOException e) {
					log.error(e);
				} 
			}
		};
		responseExecutor.execute(handler);
	}
	
	protected void setTextHeader(HttpServletResponse response) {
		response.setContentType(Constants.SKY_SPIRIT_HTTP_CONTENT_TYPE_XML);
		response.setCharacterEncoding(Constants.SKY_SPIRIT_HTTP_ENCODEING);
	}

	protected void setBinaryHeader(HttpServletResponse response) {
		response
				.setContentType(Constants.SKY_SPIRIT_HTTP_CONTENT_TYPE_OC_STREAM);
		response.setCharacterEncoding(Constants.SKY_SPIRIT_HTTP_ENCODEING);
	}

	protected void setAppHeader(HttpServletResponse response) {
		response.setContentType(Constants.SKY_SPIRIT_HTTP_CONTENT_TYPE_APP_MRP);
		response.setCharacterEncoding(Constants.SKY_SPIRIT_HTTP_ENCODEING_GZIP);
	}

	protected String decode(String s) {
		try {
			s = new String(s.getBytes("iso-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return s;
	}

	protected String decodeFromRequest(String key, HttpServletRequest request) {
		String value = (String) request.getParameter(key);
		if (value == null)
			return null;
		try {
			value = new String(value.getBytes("iso-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {

			e.printStackTrace();
		}
		return value;
	}

	protected String decodeAndSave2Request(String key,
			HttpServletRequest request) {

		String value = request.getParameter(key);
		if (value == null)
			return null;
		try {
			value = new String(value.getBytes("iso-8859-1"), "UTF-8");
			request.setAttribute(key, value);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return value;
	}

}
