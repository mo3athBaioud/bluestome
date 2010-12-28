package org.eredlab.g4.arm.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.eredlab.g4.arm.service.OrganizationService;
import org.eredlab.g4.arm.service.UserService;
import org.eredlab.g4.ccl.datastructure.Dto;
import org.eredlab.g4.ccl.datastructure.impl.BaseDto;
import org.eredlab.g4.ccl.json.JsonHelper;
import org.eredlab.g4.ccl.util.G4Utils;
import org.eredlab.g4.rif.web.BaseAction;
import org.eredlab.g4.rif.web.CommonActionForm;

/**
 * 用户管理与授权
 * 
 * @author XiongChun
 * @since 2010-04-21
 * @see BaseAction
 */
public class UserAction extends BaseAction {
	
	private UserService userService = (UserService) super.getService("userService");
	
	private OrganizationService organizationService = (OrganizationService) super.getService("organizationService");


	/**
	 * 用户管理与授权页面初始化
	 * 
	 * @param
	 * @return
	 */
	public ActionForward userInit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		super.removeSessionAttribute(request, "deptid");
		Dto inDto = new BaseDto();
		String deptid = super.getSessionContainer(request).getUserInfo().getDeptid();
		inDto.put("deptid", deptid);
		Dto outDto = organizationService.queryDeptinfoByDeptid(inDto);
		request.setAttribute("rootDeptid", outDto.getAsString("deptid"));
		request.setAttribute("rootDeptname", outDto.getAsString("deptname"));
		return mapping.findForward("manageUserView");
	}

	/**
	 * 部门管理树初始化
	 * 
	 * @param
	 * @return
	 */
	public ActionForward departmentTreeInit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Dto dto = new BaseDto();
		String nodeid = request.getParameter("node");
		dto.put("parentid", nodeid);
		Dto outDto = organizationService.queryDeptItems(dto);
		response.getWriter().write(outDto.getAsString("jsonString"));
		return mapping.findForward(null);
	}

	/**
	 * 查询用户列表
	 * 
	 * @param
	 * @return
	 */
	public ActionForward queryUsersForManage(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		CommonActionForm aForm = (CommonActionForm)form;
		Dto dto = aForm.getParamAsDto(request);
		String deptid = request.getParameter("deptid");
		if (G4Utils.isNotEmpty(deptid)) {
			setSessionAttribute(request, "deptid", deptid);
		}
		if (!G4Utils.isEmpty(request.getParameter("firstload"))) {
			dto.put("deptid", super.getSessionContainer(request).getUserInfo().getDeptid());
		} else {
			dto.put("deptid", super.getSessionAttribute(request, "deptid"));
		}
		Dto outDto = userService.queryUsersForManage(dto);
		write(outDto.getAsString("jsonString"), response);
		return mapping.findForward(null);
	}

	/**
	 * 保存用户
	 * 
	 * @param
	 * @return
	 */
	public ActionForward saveUserItem(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		CommonActionForm aForm = (CommonActionForm) form;
		Dto inDto = aForm.getParamAsDto(request);
		Dto outDto = userService.saveUserItem(inDto);
		String jsonString = JsonHelper.encodeObject2Json(outDto);
		response.getWriter().write(jsonString);
		return mapping.findForward(null);
	}

	/**
	 * 删除用户
	 * 
	 * @param
	 * @return
	 */
	public ActionForward deleteUserItems(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String strChecked = request.getParameter("strChecked");
		Dto inDto = new BaseDto();
		inDto.put("strChecked", strChecked);
		Dto outDto = userService.deleteUserItems(inDto);
		String jsonString = JsonHelper.encodeObject2Json(outDto);
		response.getWriter().write(jsonString);
		return mapping.findForward(null);
	}

	/**
	 * 修改用户
	 * 
	 * @param
	 * @return
	 */
	public ActionForward updateUserItem(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		CommonActionForm aForm = (CommonActionForm) form;
		Dto inDto = aForm.getParamAsDto(request);
		Dto outDto = userService.updateUserItem(inDto);
		String jsonString = JsonHelper.encodeObject2Json(outDto);
		response.getWriter().write(jsonString);
		return mapping.findForward(null);
	}

	/**
	 * 用户授权页面初始化:选择角色
	 * 
	 * @param
	 * @return
	 */
	public ActionForward userGrantInit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		super.removeSessionAttribute(request, "USERID_USERACTION");
		String userid = request.getParameter("userid");
		super.setSessionAttribute(request, "USERID_USERACTION", userid);
		return mapping.findForward("selectRoleTreeView");
	}

	/**
	 * 用户授权页面初始化:选择菜单
	 * 
	 * @param
	 * @return
	 */
	public ActionForward selectMenuInit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return mapping.findForward("selectMenuTreeView");
	}

	/**
	 * 保存用户角色关联信息
	 * 
	 * @param
	 * @return
	 */
	public ActionForward saveSelectedRole(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Dto inDto = new BaseDto();
		inDto.put("roleid", request.getParameter("roleid"));
		inDto.put("userid", super.getSessionAttribute(request, "USERID_USERACTION"));
		Dto outDto = userService.saveSelectedRole(inDto);
		String jsonString = JsonHelper.encodeObject2Json(outDto);
		response.getWriter().write(jsonString);
		return mapping.findForward(null);
	}

	/**
	 * 保存用户菜单关联信息
	 * 
	 * @param
	 * @return
	 */
	public ActionForward saveSelectedMenu(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Dto inDto = new BaseDto();
		inDto.put("menuid", request.getParameter("menuid"));
		inDto.put("userid", super.getSessionAttribute(request, "USERID_USERACTION"));
		Dto outDto = userService.saveSelectedMenu(inDto);
		String jsonString = JsonHelper.encodeObject2Json(outDto);
		response.getWriter().write(jsonString);
		return mapping.findForward(null);
	}
}
