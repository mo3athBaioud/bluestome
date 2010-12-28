package org.eredlab.g4.rif.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;
import org.eredlab.g4.ccl.datastructure.Dto;
import org.eredlab.g4.ccl.json.JsonHelper;
import org.eredlab.g4.ccl.util.G4Utils;
import org.eredlab.g4.rif.util.WebUtils;

/**
 * ActionForm公共类
 * @author XiongChun
 * @since 2009-09-03
 * @see ActionForm
 */
public class CommonActionForm extends BaseActionForm{
	
	private FormFile theFile;
	
	public void CommonActionForm() {
	}
	
	/**
	 * 将请求参数封装为Dto
	 * @param request
	 * @return
	 */
	public Dto getParamAsDto(HttpServletRequest request){
		return WebUtils.getPraramsAsDto(request);
	}
	
	/**
	 * 获取树形节点单击事件传到后台的节点唯一标识号
	 * 对应JS节点对象的ID属性,其Key值为:"node"
	 * @param request
	 * @return
	 */
	public String getTreeNodeUID4Clicked(HttpServletRequest request){
		return request.getParameter("node");
	}
	
	/**
	 * 获取Grid新增和编辑过的脏数据
	 * @return
	 */
	public List getGridDirtyData(HttpServletRequest request){
		List list = new ArrayList();
		String dirtyData = request.getParameter("dirtydata");
		if (G4Utils.isEmpty(dirtyData)) {
			return list;
		}
		dirtyData = dirtyData.substring(1, dirtyData.length()-1);
		String[] dirtyDatas = dirtyData.split("},");
		for (int i = 0; i < dirtyDatas.length; i++) {
			if (i != dirtyDatas.length - 1) {
				dirtyDatas[i] += "}";
			}
			Dto dto = JsonHelper.parseSingleJson2Dto(dirtyDatas[i]);
			list.add(dto);
		}
		return list;
	}
	
    public void reset (ActionMapping mapping, HttpServletRequest request)
    {
        super.reset(mapping, request);
    }

	public FormFile getTheFile() {
		return theFile;
	}

	public void setTheFile(FormFile theFile) {
		this.theFile = theFile;
	}
}
