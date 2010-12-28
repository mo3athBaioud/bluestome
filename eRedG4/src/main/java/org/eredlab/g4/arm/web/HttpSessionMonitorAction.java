package org.eredlab.g4.arm.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.eredlab.g4.arm.service.MonitorService;
import org.eredlab.g4.ccl.datastructure.Dto;
import org.eredlab.g4.ccl.datastructure.impl.BaseDto;
import org.eredlab.g4.ccl.json.JsonHelper;
import org.eredlab.g4.ccl.util.G4Utils;
import org.eredlab.g4.rif.util.SessionContainer;
import org.eredlab.g4.rif.util.SessionListener;
import org.eredlab.g4.rif.web.BaseAction;

/**
 * HTTP会话监控
 * @author XiongChun
 * @since 2010-09-03
 * @see BaseAction
 */
public class HttpSessionMonitorAction extends BaseAction {

	private MonitorService monitorService = (MonitorService)super.getService("monitorService");

	/**
	 * 会话监控页面初始化
	 * 
	 * @param
	 * @return
	 */
	public ActionForward init(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		return mapping.findForward("sessionMonitorView");
	}
	
	/**
	 * 获取当前活动的用户列表
	 * 
	 * @param
	 * @return
	 */
	public ActionForward getSessionList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		Dto dto = new BaseDto();
		dto.put("sessionid", request.getSession().getId());
		String jsonString = monitorService.queryHttpSessions(dto).getAsString("jsonString");
		super.write(jsonString, response);
		return mapping.findForward(null);
	}
	
	/**
	 * 杀死会话
	 * 
	 * @param
	 * @return
	 */
	public ActionForward killSession(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String strChecked = request.getParameter("strChecked");
		String[] sessionid = strChecked.split(",");
		Dto delDto = new BaseDto();
		String msg = "选中的会话已杀死!";
		for (int i = 0; i < sessionid.length; i++) {
			String seid = sessionid[i];
			delDto.put("sessionid", seid);
			if(!seid.equalsIgnoreCase(request.getSession().getId())){
				monitorService.deleteHttpSession(delDto);
				HttpSession session = SessionListener.getSessionByID(seid);
				if(G4Utils.isNotEmpty(seid)){
					SessionContainer sessionContainer =  (SessionContainer)session.getAttribute("SessionContainer");
					sessionContainer.setUserInfo(null); //配合RequestFilter进行拦截
					sessionContainer.cleanUp();
				}
			}else {
				msg += " 提示：不能自杀哦!";
			}
		}
		Dto dto = new BaseDto();
		dto.put("success", new Boolean(true));
		dto.put("msg", msg);
	    super.write(JsonHelper.encodeObject2Json(dto), response);
		return mapping.findForward(null);
	}
}
