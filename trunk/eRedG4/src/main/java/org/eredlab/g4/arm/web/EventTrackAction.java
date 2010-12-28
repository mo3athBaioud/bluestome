package org.eredlab.g4.arm.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.eredlab.g4.arm.service.MonitorService;
import org.eredlab.g4.ccl.datastructure.Dto;
import org.eredlab.g4.rif.web.BaseAction;
import org.eredlab.g4.rif.web.CommonActionForm;

/**
 * 事件跟踪Action
 * 
 * @author XiongChun
 * @since 2010-09-12
 * @see BaseAction
 */
public class EventTrackAction extends BaseAction {
	
	private MonitorService monitorService = (MonitorService)super.getService("monitorService");
	
	/**
	 * 事件跟踪页面初始化
	 * 
	 * @param
	 * @return
	 */
	public ActionForward init(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return mapping.findForward("eventTrackView");
	}
	
	/**
	 * 查询事件列表
	 * 
	 * @param
	 * @return
	 */
	public ActionForward queryEvents(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		CommonActionForm aForm = (CommonActionForm)form;
		Dto dto = aForm.getParamAsDto(request);
		Dto outDto = monitorService.queryEventsByDto(dto);
		super.write(outDto.getDefaultJson(), response);
		return mapping.findForward(null);
	}
	
	/**
	 * 删除事件
	 * 
	 * @param
	 * @return
	 */
	public ActionForward deleteEvents(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		CommonActionForm aForm = (CommonActionForm)form;
		Dto dto = aForm.getParamAsDto(request);
		Dto outDto = monitorService.deleteEvent(dto);
		super.write(outDto.toJson(), response);
		return mapping.findForward(null);
	}
}
