package org.eredlab.g4.arm.service;

import org.eredlab.g4.bmf.base.BaseService;
import org.eredlab.g4.ccl.datastructure.Dto;

/*
 * 异常消息管理业务接口
 * @author XiongChun
 * @since 2010-05-13
 */
public interface ExceptionService extends BaseService {
	/**
	 * 查询异常列表
	 * 
	 * @param pDto
	 * @return
	 */
	public Dto queryExceptionsForManage(Dto pDto);

	/**
	 * 保存异常信息表
	 */
	public Dto saveExceptionItem(Dto pDto);

	/**
	 * 删除异常信息
	 * 
	 * @param pDto
	 */
	public Dto deleteExceptionItem(Dto pDto);

	/**
	 * 修改异常信息
	 * 
	 * @param pDto
	 */
	public Dto updateExceptionItem(Dto pDto);
}
