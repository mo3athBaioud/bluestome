package org.eredlab.g4.demo.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.eredlab.g4.arm.util.idgenerator.IDHelper;
import org.eredlab.g4.bmf.base.BaseServiceImpl;
import org.eredlab.g4.ccl.datastructure.Dto;
import org.eredlab.g4.ccl.datastructure.impl.BaseDto;
import org.eredlab.g4.demo.service.DemoService;
import org.eredlab.g4.rif.report.fcf.DataSet;
import org.eredlab.g4.rif.report.fcf.Set;

/**
 * 系统演示实现类
 * 
 * @author XiongChun
 * @since 2010-02-13
 */
public class DemoServiceImpl extends BaseServiceImpl implements DemoService {
	
	/**
	 * 插入一条收费项目
	 * @param pDto
	 * @return
	 */
	public Dto saveSfxmDomain(Dto pDto){
		Dto outDto = new BaseDto();
		String xmid = IDHelper.getXmID();
		pDto.put("xmid", xmid);
		g4Dao.insert("insertEz_sfxmDomain", pDto);
		outDto.put("xmid", xmid);
		return outDto;
	}
	
	/**
	 * 修改一条收费项目
	 * @param pDto
	 * @return
	 */
	public Dto updateSfxmDomain(Dto pDto){
		Dto outDto = new BaseDto();
		g4Dao.update("updatesfxm", pDto);
		return outDto;
	}
	
	/**
	 * 删除一条收费项目
	 * @param pDto
	 * @return
	 */
	public Dto deleteSfxm(Dto pDto){
		Dto outDto = new BaseDto();
		g4Dao.update("deleteSfxm", pDto);
		return outDto;
	}
	
	
	
}
