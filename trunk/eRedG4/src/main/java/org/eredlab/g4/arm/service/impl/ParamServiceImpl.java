package org.eredlab.g4.arm.service.impl;

import java.util.List;

import org.eredlab.g4.arm.service.ParamService;
import org.eredlab.g4.arm.util.idgenerator.IDHelper;
import org.eredlab.g4.bmf.base.BaseServiceImpl;
import org.eredlab.g4.ccl.datastructure.Dto;
import org.eredlab.g4.ccl.datastructure.impl.BaseDto;
import org.eredlab.g4.ccl.json.JsonHelper;

/**
 * 全局参数数据访问实现
 * 
 * @author XiongChun
 * @since 2010-05-13
 * @see BaseServiceImpl
 */
public class ParamServiceImpl extends BaseServiceImpl implements ParamService{
	
	/**
	 * 查询参数列表
	 * 
	 * @param pDto
	 * @return
	 */
	public Dto queryParamsForManage(Dto pDto){
		Dto outDto = new BaseDto();
		List paramList = g4Dao.queryForPage("queryParamsForManage", pDto);
		String jsonString = JsonHelper.encodeObject2Json(paramList);
		Integer pageCount = (Integer)g4Dao.queryForObject("queryParamsForManageForPageCount", pDto);
		outDto.put("jsonString", JsonHelper.encodeJson2PageJson(jsonString, pageCount));
		return outDto;
	}

	/**
	 * 保存参数信息表
	 */
	public Dto saveParamItem(Dto pDto){
		Dto outDto = new BaseDto();
		pDto.put("paramid", IDHelper.getParamID());
		g4Dao.insert("saveParamItem", pDto);
		outDto.put("msg", "参数数据新增成功");
		outDto.put("success", new Boolean(true));
		return outDto;
	}

	/**
	 * 删除参数信息
	 * 
	 * @param pDto
	 */
	public Dto deleteParamItem(Dto pDto){
		Dto outDto = new BaseDto();
		Dto dto = new BaseDto();
		String[] arrChecked = pDto.getAsString("strChecked").split(",");
		for(int i = 0; i < arrChecked.length; i++){
			dto.put("paramid", arrChecked[i]);
			g4Dao.delete("deletParamItem", dto);
		}
		outDto.put("success", new Boolean(true));
		outDto.put("msg", "参数数据删除成功!");
		return outDto;
	}

	/**
	 * 修改参数信息
	 * 
	 * @param pDto
	 */
	public Dto updateParamItem(Dto pDto){
		Dto outDto = new BaseDto();
		g4Dao.update("updateParamItem", pDto);
		outDto.put("success", new Boolean(true));
		outDto.put("msg", "参数数据修改成功!");
		return outDto;
	}

}
