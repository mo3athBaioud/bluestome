package org.eredlab.g4.arm.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.eredlab.g4.arm.service.ParamService;
import org.eredlab.g4.ccl.datastructure.Dto;
import org.eredlab.g4.ccl.datastructure.impl.BaseDto;
import org.eredlab.g4.ccl.json.JsonHelper;
import org.eredlab.g4.rif.web.BaseAction;
import org.eredlab.g4.rif.web.CommonActionForm;

/**
 * 全局参数表管理Action
 * 
 * @author XiongChun
 * @since 2010-05-05
 * @see BaseAction
 */
public class ParamAction extends BaseAction{
	
	private ParamService paramService = (ParamService)super.getService("paramService");
	
	/**
	 * 页面初始化
	 * 
	 * @param
	 * @return
	 */
	public ActionForward init(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return mapping.findForward("manageParamView");
	}
	
	/**
	 * 查询参数列表
	 * 
	 * @param
	 * @return
	 */
	public ActionForward queryParamsForManage(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		CommonActionForm aForm = (CommonActionForm) form;
		Dto dto = aForm.getParamAsDto(request);
		Dto outDto = paramService.queryParamsForManage(dto);
		response.getWriter().write(outDto.getAsString("jsonString"));
		return mapping.findForward(null);
	}
	
	/**
	 * 保存参数信息
	 * 
	 * @param
	 * @return
	 */
	public ActionForward saveParamItem(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		CommonActionForm aForm = (CommonActionForm) form;
		Dto inDto = aForm.getParamAsDto(request);
		Dto outDto = paramService.saveParamItem(inDto);
		String jsonString = JsonHelper.encodeObject2Json(outDto);
		response.getWriter().write(jsonString);
		return mapping.findForward(null);
	}
	
	/**
	 * 删除参数信息
	 * 
	 * @param
	 * @return
	 */
	public ActionForward deleteParamItems(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String strChecked = request.getParameter("strChecked");
		Dto inDto = new BaseDto();
		inDto.put("strChecked", strChecked);
		Dto outDto = paramService.deleteParamItem(inDto);
		String jsonString = JsonHelper.encodeObject2Json(outDto);
		response.getWriter().write(jsonString);
		return mapping.findForward(null);
	}
	
	/**
	 * 修改参数信息
	 * 
	 * @param
	 * @return
	 */
	public ActionForward updateParamItem(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		CommonActionForm aForm = (CommonActionForm) form;
		Dto inDto = aForm.getParamAsDto(request);
		Dto outDto = paramService.updateParamItem(inDto);
		String jsonString = JsonHelper.encodeObject2Json(outDto);
		response.getWriter().write(jsonString);
		return mapping.findForward(null);
	}
}
