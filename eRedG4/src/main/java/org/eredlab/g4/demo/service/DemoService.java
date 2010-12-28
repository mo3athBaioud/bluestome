package org.eredlab.g4.demo.service;

import org.eredlab.g4.bmf.base.BaseService;
import org.eredlab.g4.ccl.datastructure.Dto;

/**
 * 系统演示接口
 * @author XiongChun
 * @since 2010-02-13
 */
public interface DemoService extends BaseService {
	
	/**
	 * 插入一条收费项目
	 * @param pDto
	 * @return
	 */
	public Dto saveSfxmDomain(Dto pDto);
	
	/**
	 * 修改一条收费项目
	 * @param pDto
	 * @return
	 */
	public Dto updateSfxmDomain(Dto pDto);
	
	/**
	 * 删除一条收费项目
	 * @param pDto
	 * @return
	 */
	public Dto deleteSfxm(Dto pDto);
}
