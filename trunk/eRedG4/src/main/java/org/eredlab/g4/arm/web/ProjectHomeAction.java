package org.eredlab.g4.arm.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.eredlab.g4.rif.web.BaseAction;

/**
 * 项目主页Action
 * 在线演示系统里面的项目主页相关功能
 * 
 * @author XiongChun
 * @since 2010-10-30
 * @see BaseAction
 */
public class ProjectHomeAction extends BaseAction {

	/**
	 * 面板页面初始化
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward forumInit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		return mapping.findForward("forumView");
	}
}
