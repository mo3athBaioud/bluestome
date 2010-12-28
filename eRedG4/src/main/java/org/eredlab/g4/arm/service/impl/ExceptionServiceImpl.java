package org.eredlab.g4.arm.service.impl;

import java.util.List;

import org.eredlab.g4.arm.service.ExceptionService;
import org.eredlab.g4.arm.util.idgenerator.IDHelper;
import org.eredlab.g4.bmf.base.BaseServiceImpl;
import org.eredlab.g4.ccl.datastructure.Dto;
import org.eredlab.g4.ccl.datastructure.impl.BaseDto;
import org.eredlab.g4.ccl.json.JsonHelper;

/*
 * 异常消息管理业务实现
 * @author XiongChun
 * @since 2010-05-13
 */
public class ExceptionServiceImpl extends BaseServiceImpl implements ExceptionService {

	/**
	 * 查询异常列表
	 * 
	 * @param pDto
	 * @return
	 */
	public Dto queryExceptionsForManage(Dto pDto) {
		Dto outDto = new BaseDto();
		List ExceptionList = g4Dao.queryForPage("queryExceptionsForManage", pDto);
		String jsonString = JsonHelper.encodeObject2Json(ExceptionList);
		Integer pageCount = (Integer) g4Dao.queryForObject("queryExceptionsForManageForPageCount", pDto);
		outDto.put("jsonString", JsonHelper.encodeJson2PageJson(jsonString, pageCount));
		return outDto;
	}

	/**
	 * 保存异常信息表
	 */
	public Dto saveExceptionItem(Dto pDto) {
		Dto outDto = new BaseDto();
		String exceptionid = IDHelper.getExceptionID();
		pDto.put("exceptionid", exceptionid);
		Integer count = (Integer)g4Dao.queryForObject("checkExceptionCode", pDto);
		if (count.intValue() != 0) {
			outDto.put("msg", "此异常代码[" + pDto.getAsString("exceptioncode") + "]已经存在.");
			outDto.put("success", new Boolean(false));
		} else {
			g4Dao.insert("saveExceptionItem", pDto);
			outDto.put("msg", "异常数据新增成功");
			outDto.put("success", new Boolean(true));
		}
		return outDto;
	}

	/**
	 * 删除异常信息
	 * 
	 * @param pDto
	 */
	public Dto deleteExceptionItem(Dto pDto) {
		Dto outDto = new BaseDto();
		Dto dto = new BaseDto();
		String[] arrChecked = pDto.getAsString("strChecked").split(",");
		for (int i = 0; i < arrChecked.length; i++) {
			dto.put("exceptionid", arrChecked[i]);
			g4Dao.delete("deletExceptionItem", dto);
		}
		outDto.put("success", new Boolean(true));
		outDto.put("msg", "异常数据删除成功!");
		return outDto;
	}

	/**
	 * 修改异常信息
	 * 
	 * @param pDto
	 */
	public Dto updateExceptionItem(Dto pDto) {
		Dto outDto = new BaseDto();
		Integer count = (Integer)g4Dao.queryForObject("checkExceptionCode", pDto);
		if (count.intValue() != 0 && !pDto.getAsString("exceptioncode").equals(pDto.getAsString("exceptioncode_old"))) {
			outDto.put("msg", "此异常代码[" + pDto.getAsString("exceptioncode") + "]已经存在.");
			outDto.put("success", new Boolean(false));
		} else {
			g4Dao.update("updateExceptionItem", pDto);
			outDto.put("success", new Boolean(true));
			outDto.put("msg", "异常数据修改成功!");
		}
		return outDto;
	}

}
