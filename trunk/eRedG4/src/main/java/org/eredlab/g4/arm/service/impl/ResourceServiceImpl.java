package org.eredlab.g4.arm.service.impl;

import java.util.List;

import org.eredlab.g4.arm.service.ResourceService;
import org.eredlab.g4.arm.util.ArmConstants;
import org.eredlab.g4.arm.util.idgenerator.IDHelper;
import org.eredlab.g4.arm.util.idgenerator.IdGenerator;
import org.eredlab.g4.bmf.base.BaseServiceImpl;
import org.eredlab.g4.ccl.datastructure.Dto;
import org.eredlab.g4.ccl.datastructure.impl.BaseDto;
import org.eredlab.g4.ccl.json.JsonHelper;
import org.eredlab.g4.ccl.util.G4Utils;

/**
 * 资源模型业务实现类
 * 
 * @author XiongChun
 * @since 2010-01-13
 */
public class ResourceServiceImpl extends BaseServiceImpl implements ResourceService {

	/**
	 * 查询代码表
	 * 
	 * @param pDto
	 * @return
	 */
	public Dto getCodeList(Dto pDto) {
		Dto outDto = new BaseDto();
		List codeList = g4Dao.queryForList("getCodeList", pDto);
		outDto.setDefaultAList(codeList);
		return outDto;
	}

	/**
	 * 查询全局参数表
	 * 
	 * @param pDto
	 * @return
	 */
	public Dto getParamList(Dto pDto) {
		Dto outDto = new BaseDto();
		List paramList = g4Dao.queryForList("getParamList", pDto);
		outDto.setDefaultAList(paramList);
		return outDto;
	}

	/**
	 * 查询代码表[代码表管理]
	 * 
	 * @param pDto
	 * @return
	 */
	public Dto getCodeListForManage(Dto pDto) {
		Dto outDto = new BaseDto();
		List codeList = g4Dao.queryForPage("getCodeListForPage", pDto);
		Integer totalCount = (Integer) g4Dao.queryForObject("getCodeListForPageCount", pDto);
		String jsonStrList = JsonHelper.encodeObject2Json(codeList);
		outDto.put("jsonStrList", JsonHelper.encodeJson2PageJson(jsonStrList, totalCount));
		return outDto;
	}

	/**
	 * 保存代码对照
	 * 
	 * @param pDto
	 * @return
	 */
	public Dto saveCodeItem(Dto pDto) {
		Dto outDto = new BaseDto();
		String codeid = IDHelper.getCodeID();
		pDto.put("codeid", codeid);
		Dto checkDto = (BaseDto) g4Dao.queryForObject("checkEaCodeByIndex", pDto);
		if (G4Utils.isNotEmpty(checkDto)) {
			outDto.put("success", new Boolean(false));
			outDto.put("msg", "违反唯一约束,[对照字段]和[代码]组合不能重复.");
			return outDto;
		} else {
			g4Dao.insert("createEacodeDomain", pDto);
			outDto.put("success", new Boolean(true));
		}
		return outDto;
	}

	/**
	 * 删除代码表
	 * 
	 * @param pDto
	 * @return
	 */
	public Dto deleteCodeItem(Dto pDto) {
		Dto outDto = new BaseDto();
		Dto dto = new BaseDto();
		String[] arrChecked = pDto.getAsString("strChecked").split(",");
		for (int i = 0; i < arrChecked.length; i++) {
			dto.put("codeid", arrChecked[i]);
			Dto chechkDto = (BaseDto) g4Dao.queryForObject("getEaCodeByKey", dto);
			if (chechkDto.getAsString("editmode").equals(ArmConstants.EDITMODE_Y)) {
				g4Dao.delete("deleteCodeItem", dto);
			}
		}
		outDto.put("success", new Boolean(true));
		outDto.put("msg", "代码对照数据删除成功!");
		return outDto;
	}

	/**
	 * 修改代码表
	 * 
	 * @param pDto
	 * @return
	 */
	public Dto updateCodeItem(Dto pDto) {
		Dto outDto = new BaseDto();
		g4Dao.update("updateCodeItem", pDto);
		outDto.put("success", new Boolean(true));
		outDto.put("msg", "代码对照数据修改成功!");
		return outDto;
	}

	/**
	 * 菜单资源管理生成菜单树
	 * 
	 * @param pDto
	 * @return
	 */
	public Dto queryMenuItems(Dto pDto) {
		Dto outDto = new BaseDto();
		List menuList = g4Dao.queryForList("queryMenuItemsByDto", pDto);
		Dto menuDto = new BaseDto();
		for (int i = 0; i < menuList.size(); i++) {
			menuDto = (BaseDto) menuList.get(i);
			if (menuDto.getAsString("leaf").equals(ArmConstants.LEAF_Y))
				menuDto.put("leaf", new Boolean(true));
			else
				menuDto.put("leaf", new Boolean(false));
			if (menuDto.getAsString("id").length() == 4)
				menuDto.put("expanded", new Boolean(true)); // ID长度为4的节点自动展开
		}
		outDto.put("jsonString", JsonHelper.encodeObject2Json(menuList));
		return outDto;
	}

	/**
	 * 菜单资源管理 - 菜单列表
	 * 
	 * @param pDto
	 * @return
	 */
	public Dto queryMenuItemsForManage(Dto pDto) {
		Dto outDto = new BaseDto();
		List menuList = g4Dao.queryForPage("queryMenuItemsForManage", pDto);
		String jsonString = JsonHelper.encodeObject2Json(menuList);
		Integer pageCount = (Integer) g4Dao.queryForObject("queryMenuItemsForManageForPageCount", pDto);
		outDto.put("jsonString", JsonHelper.encodeJson2PageJson(jsonString, pageCount));
		return outDto;
	}

	/**
	 * 保存菜单
	 * 
	 * @param pDto
	 * @return
	 */
	public synchronized Dto saveMenuItem(Dto pDto) {
		Dto outDto = new BaseDto();
		String menuid = IdGenerator.getMenuIdGenerator(pDto.getAsString("parentid"));
		pDto.put("menuid", menuid);
		pDto.put("leaf", ArmConstants.LEAF_Y);
		g4Dao.insert("saveMenuItem", pDto);
		Dto updateDto = new BaseDto();
		updateDto.put("menuid", pDto.getAsString("parentid"));
		updateDto.put("leaf", ArmConstants.LEAF_N);
		g4Dao.update("updateLeafFieldInEaMenu", updateDto);
		outDto.put("msg", "菜单数据新增成功");
		outDto.put("success", new Boolean(true));
		return outDto;
	}

	/**
	 * 删除菜单项
	 * 
	 * @param pDto
	 * @return
	 */
	public Dto deleteMenuItems(Dto pDto) {
		Dto outDto = new BaseDto();
		Dto dto = new BaseDto();
		Dto changeLeafDto = new BaseDto();
		if (pDto.getAsString("type").equals("1")) {
			// 列表复选删除
			String[] arrChecked = pDto.getAsString("strChecked").split(",");
			for (int i = 0; i < arrChecked.length; i++) {
				dto.put("menuid", arrChecked[i]);
				changeLeafDto.put("parentid", ((BaseDto) g4Dao.queryForObject("queryMenuItemsByDto", dto))
						.getAsString("parentid"));
				g4Dao.delete("deleteEamenuItem", dto);
				g4Dao.delete("deleteEarwauthorizeItem", dto);
				g4Dao.delete("deleteEausermenumapByMenuid", dto);
				changeLeafOfDeletedParent(changeLeafDto);
			}
		} else {
			// 菜单树右键删除
			dto.put("menuid", pDto.getAsString("menuid"));
			changeLeafDto.put("parentid", ((BaseDto) g4Dao.queryForObject("queryMenuItemsByDto", dto))
					.getAsString("parentid"));
			g4Dao.delete("deleteEamenuItem", dto);
			g4Dao.delete("deleteEarwauthorizeItem", dto);
			g4Dao.delete("deleteEausermenumapByMenuid", dto);
			changeLeafOfDeletedParent(changeLeafDto);
		}
		outDto.put("success", new Boolean(true));
		outDto.put("msg", "菜单数据删除成功!");
		return outDto;
	}

	/**
	 * 调整被删除菜单的直系父级菜单的Leaf属性
	 * 
	 * @param pDto
	 */
	private void changeLeafOfDeletedParent(Dto pDto) {
		String parentid = pDto.getAsString("parentid");
		pDto.put("menuid", parentid);
		Integer countInteger = (Integer)g4Dao.queryForObject("prepareChangeLeafOfDeletedParent", pDto);
		if (countInteger.intValue() == 0) {
			pDto.put("leaf", ArmConstants.LEAF_Y);
		} else {
			pDto.put("leaf", ArmConstants.LEAF_N);
		}
		g4Dao.update("updateLeafFieldInEaMenu", pDto);
	}

	/**
	 * 修改菜单
	 * 
	 * @param pDto
	 * @return
	 */
	public Dto updateMenuItem(Dto pDto) {
		Dto outDto = new BaseDto();
		if (pDto.getAsString("parentid").equals(pDto.getAsString("parentid_old"))) {
			pDto.remove("parentid");
			g4Dao.update("updateMenuItem", pDto);
		} else {
			g4Dao.delete("deleteEamenuItem", pDto);
			g4Dao.delete("deleteEarwauthorizeItem", pDto);
			g4Dao.delete("deleteEausermenumapByMenuid", pDto);
			saveMenuItem(pDto);
			pDto.put("parentid", pDto.getAsString("parentid_old"));
			changeLeafOfDeletedParent(pDto);
		}
		outDto.put("success", new Boolean(true));
		outDto.put("msg", "菜单数据修改成功!");
		return outDto;
	}

	/**
	 * 系统图标列表
	 * 
	 * @param pDto
	 * @return
	 */
	public Dto queryIconsForManage(Dto pDto) {
		Dto outDto = new BaseDto();
		List iconList = null;
		if (G4Utils.isOracle()) {
			iconList = g4Dao.queryForPage("queryIconsForManage", pDto);
		}else if (G4Utils.isMysql()) {
			iconList = g4Dao.queryForPage("queryIconsForManageMysql", pDto);
		}
		String jsonString = JsonHelper.encodeObject2Json(iconList);
		Integer pageCount = (Integer)g4Dao.queryForObject("queryIconsForManageForPageCount", pDto);
		outDto.put("jsonString", JsonHelper.encodeJson2PageJson(jsonString, pageCount));
		return outDto;
	}

}
