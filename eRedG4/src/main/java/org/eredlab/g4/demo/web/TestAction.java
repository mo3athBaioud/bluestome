package org.eredlab.g4.demo.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.eredlab.g4.ccl.datastructure.Dto;
import org.eredlab.g4.ccl.datastructure.impl.BaseDto;
import org.eredlab.g4.ccl.json.JsonHelper;
import org.eredlab.g4.ccl.util.GlobalConstants;
import org.eredlab.g4.ccl.util.G4Utils;
import org.eredlab.g4.rif.web.BaseAction;

/**
 * 演示用 Action
 * 
 * @author XiongChun
 * @since 2010-06-13
 * @see BaseAction
 */
public class TestAction extends BaseAction {
	/**
	 * 测试页面1初始化
	 * 
	 * @param
	 * @return
	 */
	public ActionForward test1Init(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		return mapping.findForward("test1View");
	}

	/**
	 * 测试页面2初始化
	 * 
	 * @param
	 * @return
	 */
	public ActionForward test2Init(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		return mapping.findForward("test2View");
	}

	/**
	 * 测试3页面初始化
	 * 
	 * @param
	 * @return
	 */
	public ActionForward test3Init(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		return mapping.findForward("test3View");
	}

	/**
	 * 2D饼图初始化
	 * 
	 * @param
	 * @return
	 */
	public ActionForward test4Init(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		return mapping.findForward("test4View");
	}

	/**
	 * 3D饼图初始化
	 * 
	 * @param
	 * @return
	 */
	public ActionForward test5Init(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		return mapping.findForward("test5View");
	}

	/**
	 * 面积图初始化
	 * 
	 * @param
	 * @return
	 */
	public ActionForward test6Init(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		return mapping.findForward("test6View");
	}

	/**
	 * 环状图初始化
	 * 
	 * @param
	 * @return
	 */
	public ActionForward test7Init(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		return mapping.findForward("test7View");
	}

	/**
	 * 漏斗图初始化
	 * 
	 * @param
	 * @return
	 */
	public ActionForward test8Init(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		return mapping.findForward("test8View");
	}

	/**
	 * 2D柱形组合图初始化
	 * 
	 * @param
	 * @return
	 */
	public ActionForward test9Init(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		return mapping.findForward("test9View");
	}

	/**
	 * 3D柱形组合图初始化
	 * 
	 * @param
	 * @return
	 */
	public ActionForward test10Init(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		return mapping.findForward("test10View");
	}

	/**
	 * 面积组合图初始化
	 * 
	 * @param
	 * @return
	 */
	public ActionForward test11Init(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		return mapping.findForward("test11View");
	}

	/**
	 * 面积组合图初始化
	 * 
	 * @param
	 * @return
	 */
	public ActionForward test12Init(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		return mapping.findForward("test12View");
	}

	/**
	 * 交叉图初始化
	 * 
	 * @param
	 * @return
	 */
	public ActionForward test13Init(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		return mapping.findForward("test13View");
	}
	
	/**
	 * 查询医院收费目录
	 * 
	 * @param
	 * @return
	 */
	public ActionForward queryCatalogs4Print(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		return mapping.findForward(null);
	}
}
