package com.sky.spirit.basic.struts;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ExceptionHandler;
import org.apache.struts.config.ExceptionConfig;

/**
 * @author wangtao
 * 
 *         Description modified by Daniel Cheung to solve the problem of process
 *         already finished exception
 * 
 */
public class BaseExceptionHandler extends ExceptionHandler {
	private static Log log = LogFactory.getLog(BaseExceptionHandler.class);
	public static final long serialVersionUID = 10000100001l;

	/**
	 * @see org.apache.struts.action.ExceptionHandler#execute(Exception,
	 *      ExceptionConfig, ActionMapping, ActionForm, HttpServletRequest,
	 *      HttpServletResponse)
	 */
	public ActionForward execute(Exception ex, ExceptionConfig ae,
			ActionMapping mapping, ActionForm formInstance,
			HttpServletRequest request, HttpServletResponse response)
			throws ServletException {
		ActionMessage error = null;
		error = new ActionMessage(ae.getKey(), ex.getMessage());
		storeException(request, ActionErrors.GLOBAL_MESSAGE, error, null, ae
				.getScope());
		request.setAttribute("GlobalError", ex);

		return mapping.findForward("ERROR");
	}
}