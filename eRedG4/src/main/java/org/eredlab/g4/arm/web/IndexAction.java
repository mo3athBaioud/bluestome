package org.eredlab.g4.arm.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.eredlab.g4.arm.service.OrganizationService;
import org.eredlab.g4.bmf.util.SpringBeanLoader;
import org.eredlab.g4.ccl.datastructure.Dto;
import org.eredlab.g4.ccl.datastructure.impl.BaseDto;
import org.eredlab.g4.ccl.json.JsonHelper;
import org.eredlab.g4.rif.web.BaseAction;

/**
 * 首页Action
 * 
 * @author XiongChun
 * @since 2010-01-13
 * @see BaseAction
 */
public class IndexAction extends BaseAction {
	
	private OrganizationService organizationService = (OrganizationService)SpringBeanLoader.getSpringBean("organizationService");

	/**
	 * 首页初始化
	 * 
	 * @param
	 * @return
	 */
	public ActionForward indexInit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		return mapping.findForward("indexView");
	}

	/**
	 * 欢迎页面初始化
	 * 
	 * @param
	 * @return
	 */
	public ActionForward preferencesInit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		return mapping.findForward("welcomeView");
	}
	
	/**
	 * 保存用户自定义主题
	 * 
	 * @param
	 * @return
	 */
	public ActionForward saveUserTheme(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		Dto dto = new BaseDto();
		String theme = request.getParameter("theme");
		dto.put("userid", super.getSessionContainer(request).getUserInfo().getUserid());
		dto.put("theme", theme);
		Dto outDto = organizationService.saveUserTheme(dto);
		String jsonString = JsonHelper.encodeObject2Json(outDto);
		response.getWriter().write(jsonString);
		response.getWriter().close();
		return mapping.findForward(null);
	}

}
