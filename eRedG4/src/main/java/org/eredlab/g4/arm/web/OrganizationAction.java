package org.eredlab.g4.arm.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.eredlab.g4.arm.service.OrganizationService;
import org.eredlab.g4.ccl.datastructure.Dto;
import org.eredlab.g4.ccl.datastructure.impl.BaseDto;
import org.eredlab.g4.ccl.json.JsonHelper;
import org.eredlab.g4.ccl.util.G4Utils;
import org.eredlab.g4.rif.web.BaseAction;
import org.eredlab.g4.rif.web.CommonActionForm;

/**
 * 组织机构模型
 * 
 * @author XiongChun
 * @since 2010-04-10
 * @see BaseAction
 */
public class OrganizationAction extends BaseAction {
	
	private OrganizationService organizationService = (OrganizationService) super.getService("organizationService");
	
	/**
	 * 部门管理页面初始化
	 * 
	 * @param
	 * @return
	 */
	public ActionForward departmentInit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		super.removeSessionAttribute(request, "deptid");
		Dto inDto = new BaseDto();
		String deptid = super.getSessionContainer(request).getUserInfo().getDeptid();
		inDto.put("deptid", deptid);
		Dto outDto = organizationService.queryDeptinfoByDeptid(inDto);
		request.setAttribute("rootDeptid", outDto.getAsString("deptid"));
		request.setAttribute("rootDeptname", outDto.getAsString("deptname"));
		return mapping.findForward("manageDepartmentView");
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
	 * 查询部门列表信息
	 * 
	 * @param
	 * @return
	 */
	public ActionForward queryDeptsForManage(ActionMapping mapping, ActionForm form, HttpServletRequest request,
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
		Dto outDto = organizationService.queryDeptsForManage(dto);
		response.getWriter().write(outDto.getAsString("jsonString"));
		return mapping.findForward(null);
	}
	
	/**
	 * 保存部门
	 * 
	 * @param
	 * @return
	 */
	public ActionForward saveDeptItem(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		CommonActionForm aForm = (CommonActionForm) form;
		Dto inDto = aForm.getParamAsDto(request);
		Dto outDto = organizationService.saveDeptItem(inDto);
		String jsonString = JsonHelper.encodeObject2Json(outDto);
		response.getWriter().write(jsonString);
		return mapping.findForward(null);
	}
	
	/**
	 * 修改部门
	 * 
	 * @param
	 * @return
	 */
	public ActionForward updateDeptItem(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		CommonActionForm aForm = (CommonActionForm) form;
		Dto inDto = aForm.getParamAsDto(request);
		Dto outDto = organizationService.updateDeptItem(inDto);
		String jsonString = JsonHelper.encodeObject2Json(outDto);
		response.getWriter().write(jsonString);
		return mapping.findForward(null);
	}
	
	/**
	 * 删除部门项
	 * 
	 * @param
	 * @return
	 */
	public ActionForward deleteDeptItems(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String strChecked = request.getParameter("strChecked");
		String type = request.getParameter("type");
		String deptid = request.getParameter("deptid");
		Dto inDto = new BaseDto();
		inDto.put("strChecked", strChecked);
		inDto.put("type", type);
		inDto.put("deptid", deptid);
		Dto outDto = organizationService.deleteDeptItems(inDto);
		String jsonString = JsonHelper.encodeObject2Json(outDto);
		response.getWriter().write(jsonString);
		return mapping.findForward(null);
	}
	
	/**
	 * 根据用户所属部门编号查询部门对象<br>
	 * 用于构造组织机构树的根节点
	 * @param
	 * @return
	 */
	public ActionForward queryDeptinfoByDeptid(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		CommonActionForm aForm = (CommonActionForm) form;
		Dto inDto = new BaseDto();
		String deptid = super.getSessionContainer(request).getUserInfo().getDeptid();
		inDto.put("deptid", deptid);
		Dto outDto = organizationService.queryDeptinfoByDeptid(inDto);
		String jsonString = JsonHelper.encodeObject2Json(outDto);
		response.getWriter().write(jsonString);
		return mapping.findForward(null);
	}
}
