package org.eredlab.g4.arm.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.eredlab.g4.arm.service.ExceptionService;
import org.eredlab.g4.ccl.datastructure.Dto;
import org.eredlab.g4.ccl.datastructure.impl.BaseDto;
import org.eredlab.g4.ccl.json.JsonHelper;
import org.eredlab.g4.rif.web.BaseAction;
import org.eredlab.g4.rif.web.CommonActionForm;

/**
 * 异常信息管理Action
 * 
 * @author XiongChun
 * @since 2010-05-05
 * @see BaseAction
 */
public class ExceptionAction extends BaseAction{
	
	private ExceptionService exceptionService = (ExceptionService)super.getService("exceptionService");
	
	/**
	 * 页面初始化
	 * 
	 * @param
	 * @return
	 */
	public ActionForward init(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return mapping.findForward("manageExceptionView");
	}
	
	/**
	 * 查询异常消息列表
	 * 
	 * @param
	 * @return
	 */
	public ActionForward queryExceptionsForManage(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		CommonActionForm aForm = (CommonActionForm)form;
		Dto dto = aForm.getParamAsDto(request);
		Dto outDto = exceptionService.queryExceptionsForManage(dto);
		response.getWriter().write(outDto.getAsString("jsonString"));
		return mapping.findForward(null);
	}
	
	/**
	 * 保存异常信息
	 * 
	 * @param
	 * @return
	 */
	public ActionForward saveExceptionItem(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		CommonActionForm aForm = (CommonActionForm) form;
		Dto inDto = aForm.getParamAsDto(request);
		Dto outDto = exceptionService.saveExceptionItem(inDto);
		String jsonString = JsonHelper.encodeObject2Json(outDto);
		response.getWriter().write(jsonString);
		return mapping.findForward(null);
	}
	
	/**
	 * 删除异常信息
	 * 
	 * @param
	 * @return
	 */
	public ActionForward deleteExceptionItems(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String strChecked = request.getParameter("strChecked");
		Dto inDto = new BaseDto();
		inDto.put("strChecked", strChecked);
		Dto outDto = exceptionService.deleteExceptionItem(inDto);
		String jsonString = JsonHelper.encodeObject2Json(outDto);
		response.getWriter().write(jsonString);
		return mapping.findForward(null);
	}
	
	/**
	 * 修改异常信息
	 * 
	 * @param
	 * @return
	 */
	public ActionForward updateExceptionItem(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		CommonActionForm aForm = (CommonActionForm) form;
		Dto inDto = aForm.getParamAsDto(request);
		Dto outDto = exceptionService.updateExceptionItem(inDto);
		String jsonString = JsonHelper.encodeObject2Json(outDto);
		response.getWriter().write(jsonString);
		return mapping.findForward(null);
	}
}
