package org.eredlab.g4.arm.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.eredlab.g4.arm.service.OrganizationService;
import org.eredlab.g4.arm.service.RoleService;
import org.eredlab.g4.ccl.datastructure.Dto;
import org.eredlab.g4.ccl.datastructure.impl.BaseDto;
import org.eredlab.g4.ccl.json.JsonHelper;
import org.eredlab.g4.ccl.util.G4Utils;
import org.eredlab.g4.rif.web.BaseAction;
import org.eredlab.g4.rif.web.CommonActionForm;

/**
 * 角色管理与授权
 * 
 * @author XiongChun
 * @since 2010-04-21
 * @see BaseAction
 */
public class RoleAction extends BaseAction{
	
	private RoleService roleService = (RoleService) super.getService("roleService");
	private OrganizationService organizationService = (OrganizationService) super.getService("organizationService");
	
	/**
	 * 角色管理与授权页面初始化
	 * 
	 * @param
	 * @return
	 */
	public ActionForward roleInit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		super.removeSessionAttribute(request, "deptid");
		Dto inDto = new BaseDto();
		String deptid = super.getSessionContainer(request).getUserInfo().getDeptid();
		inDto.put("deptid", deptid);
		Dto outDto = organizationService.queryDeptinfoByDeptid(inDto);
		request.setAttribute("rootDeptid", outDto.getAsString("deptid"));
		request.setAttribute("rootDeptname", outDto.getAsString("deptname"));
		return mapping.findForward("manageRoleView");
	}
	
	/**
	 * 部门树初始化
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
	 * 查询角色列表
	 * 
	 * @param
	 * @return
	 */
	public ActionForward queryRolesForManage(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		CommonActionForm aForm = (CommonActionForm)form;
		Dto dto = aForm.getParamAsDto(request);
		String deptid = request.getParameter("deptid");
		if (G4Utils.isNotEmpty(deptid)) {
			super.setSessionAttribute(request, "deptid", deptid);
		}
		if(!G4Utils.isEmpty(request.getParameter("firstload"))){
			dto.put("deptid", super.getSessionContainer(request).getUserInfo().getDeptid());
		}else{
			dto.put("deptid", super.getSessionAttribute(request, "deptid"));
		}
		Dto outDto = roleService.queryRolesForManage(dto);
		response.getWriter().write(outDto.getAsString("jsonString"));
		return mapping.findForward(null);
	}
	
	/**
	 * 保存角色
	 * 
	 * @param
	 * @return
	 */
	public ActionForward saveRoleItem(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		CommonActionForm aForm = (CommonActionForm) form;
		Dto inDto = aForm.getParamAsDto(request);
		Dto outDto = roleService.saveRoleItem(inDto);
		String jsonString = JsonHelper.encodeObject2Json(outDto);
		response.getWriter().write(jsonString);
		return mapping.findForward(null);
	}
	
	/**
	 * 删除角色
	 * 
	 * @param
	 * @return
	 */
	public ActionForward deleteRoleItems(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String strChecked = request.getParameter("strChecked");
		Dto inDto = new BaseDto();
		inDto.put("strChecked", strChecked);
		Dto outDto = roleService.deleteRoleItems(inDto);
		String jsonString = JsonHelper.encodeObject2Json(outDto);
		response.getWriter().write(jsonString);
		return mapping.findForward(null);
	}
	
	/**
	 * 修改角色
	 * 
	 * @param
	 * @return
	 */
	public ActionForward updateRoleItem(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		CommonActionForm aForm = (CommonActionForm) form;
		Dto inDto = aForm.getParamAsDto(request);
		Dto outDto = roleService.updateRoleItem(inDto);
		String jsonString = JsonHelper.encodeObject2Json(outDto);
		response.getWriter().write(jsonString);
		return mapping.findForward(null);
	}
		
	/**
	 * 操作权限授权初始化
	 * 
	 * @param
	 * @return
	 */
	public ActionForward operatorTabInit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		super.removeSessionAttribute(request, "ROLEID_ROLEACTION");
		String roleid = request.getParameter("roleid");
		super.setSessionAttribute(request, "ROLEID_ROLEACTION", roleid);
		return mapping.findForward("operatorTabView");
	}
	
	/**
	 * 选择人员初始化
	 * 
	 * @param
	 * @return
	 */
	public ActionForward selectUserTabInit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		return mapping.findForward("selectUserTabView");
	}
	
	/**
	 * 管理权限授权初始化
	 * 
	 * @param
	 * @return
	 */
	public ActionForward managerTabInit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		return mapping.findForward("managerTabView");
	}
	
	/**
	 * 保存角色授权信息
	 * 
	 * @param
	 * @return
	 */
	public ActionForward saveGrant(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Dto inDto = new BaseDto();
		inDto.put("menuid", request.getParameter("menuid"));
		inDto.put("authorizelevel", request.getParameter("key"));
		inDto.put("roleid", super.getSessionAttribute(request, "ROLEID_ROLEACTION"));
		Dto outDto = roleService.saveGrant(inDto);
		String jsonString = JsonHelper.encodeObject2Json(outDto);
		response.getWriter().write(jsonString);
		return mapping.findForward(null);
	}
	
	/**
	 * 保存角色用户关联信息
	 * 
	 * @param
	 * @return
	 */
	public ActionForward saveUser(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Dto inDto = new BaseDto();
		inDto.put("userid", request.getParameter("userid"));
		inDto.put("roleid", super.getSessionAttribute(request, "ROLEID_ROLEACTION"));
		Dto outDto = roleService.saveSelectUser(inDto);
		String jsonString = JsonHelper.encodeObject2Json(outDto);
		response.getWriter().write(jsonString);
		return mapping.findForward(null);
	}
}
