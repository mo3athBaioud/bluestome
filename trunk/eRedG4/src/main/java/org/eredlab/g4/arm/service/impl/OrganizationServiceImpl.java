package org.eredlab.g4.arm.service.impl;

import java.util.List;

import org.eredlab.g4.arm.service.OrganizationService;
import org.eredlab.g4.arm.util.ArmConstants;
import org.eredlab.g4.arm.util.idgenerator.IdGenerator;
import org.eredlab.g4.arm.vo.UserInfoVo;
import org.eredlab.g4.bmf.base.BaseServiceImpl;
import org.eredlab.g4.ccl.datastructure.Dto;
import org.eredlab.g4.ccl.datastructure.impl.BaseDto;
import org.eredlab.g4.ccl.json.JsonHelper;

/**
 * 组织机构模型业务实现类
 * 
 * @author XiongChun
 * @since 2010-01-13
 */
public class OrganizationServiceImpl extends BaseServiceImpl implements OrganizationService {

	/**
	 * 获取用户信息
	 * 
	 * @param pDto
	 * @return
	 */
	public Dto getUserInfo(Dto pDto) {
		Dto outDto = new BaseDto();
		pDto.put("lock", ArmConstants.LOCK_N);
		UserInfoVo userInfo = (UserInfoVo) g4Dao.queryForObject("getUserInfo", pDto);
		outDto.put("userInfo", userInfo);
		return outDto;
	}

	/**
	 * 查询部门信息生成部门树
	 * 
	 * @param pDto
	 * @return
	 */
	public Dto queryDeptItems(Dto pDto) {
		Dto outDto = new BaseDto();
		List deptList = g4Dao.queryForList("queryDeptItemsByDto", pDto);
		Dto deptDto = new BaseDto();
		for (int i = 0; i < deptList.size(); i++) {
			deptDto = (BaseDto) deptList.get(i);
			if (deptDto.getAsString("leaf").equals(ArmConstants.LEAF_Y))
				deptDto.put("leaf", new Boolean(true));
			else
				deptDto.put("leaf", new Boolean(false));
			if (deptDto.getAsString("id").length() == 6)
				deptDto.put("expanded", new Boolean(true));
		}
		outDto.put("jsonString", JsonHelper.encodeObject2Json(deptList));
		return outDto;
	}

	/**
	 * 部门管理 - 部门列表
	 * 
	 * @param pDto
	 * @return
	 */
	public Dto queryDeptsForManage(Dto pDto) {
		Dto outDto = new BaseDto();
		List menuList = g4Dao.queryForPage("queryDeptsForManage", pDto);
		String jsonString = JsonHelper.encodeObject2Json(menuList);
		Integer pageCount = (Integer) g4Dao.queryForObject("queryDeptsForManageForPageCount", pDto);
		outDto.put("jsonString", JsonHelper.encodeJson2PageJson(jsonString, pageCount));
		return outDto;
	}

	/**
	 * 保存部门
	 * 
	 * @param pDto
	 * @return
	 */
	public synchronized Dto saveDeptItem(Dto pDto) {
		Dto outDto = new BaseDto();
		String deptid = IdGenerator.getDeptIdGenerator(pDto.getAsString("parentid"));
		pDto.put("deptid", deptid);
		pDto.put("leaf", ArmConstants.LEAF_Y);
		g4Dao.insert("saveDeptItem", pDto);
		Dto updateDto = new BaseDto();
		updateDto.put("deptid", pDto.getAsString("parentid"));
		updateDto.put("leaf", ArmConstants.LEAF_N);
		g4Dao.update("updateLeafFieldInEaDept", updateDto);
		outDto.put("msg", "部门数据新增成功");
		outDto.put("success", new Boolean(true));
		return outDto;
	}

	/**
	 * 修改部门
	 * 
	 * @param pDto
	 * @return
	 */
	public Dto updateDeptItem(Dto pDto) {
		Dto outDto = new BaseDto();
		if (pDto.getAsString("parentid").equals(pDto.getAsString("parentid_old"))) {
			pDto.remove("parentid");
			g4Dao.update("updateDeptItem", pDto);
		} else {
			g4Dao.delete("deleteEadeptItem", pDto);
			saveDeptItem(pDto);
			pDto.put("parentid", pDto.getAsString("parentid_old"));
			changeLeafOfDeletedParent(pDto);
		}
		outDto.put("success", new Boolean(true));
		outDto.put("msg", "部门数据修改成功!");
		return outDto;
	}

	/**
	 * 调整被删除部门的直系父级部门的Leaf属性
	 * 
	 * @param pDto
	 */
	private void changeLeafOfDeletedParent(Dto pDto) {
		String parentid = pDto.getAsString("parentid");
		pDto.put("deptid", parentid);
		Integer countInteger = (Integer) g4Dao.queryForObject("prepareChangeLeafOfDeletedParentForEadept", pDto);
		if (countInteger.intValue() == 0) {
			pDto.put("leaf", ArmConstants.LEAF_Y);
		} else {
			pDto.put("leaf", ArmConstants.LEAF_N);
		}
		g4Dao.update("updateLeafFieldInEaDept", pDto);
	}

	/**
	 * 删除部门项
	 * 
	 * @param pDto
	 * @return
	 */
	public Dto deleteDeptItems(Dto pDto) {
		Dto outDto = new BaseDto();
		Dto dto = new BaseDto();
		if (pDto.getAsString("type").equals("1")) {
			// 列表复选删除
			String[] arrChecked = pDto.getAsString("strChecked").split(",");
			for (int i = 0; i < arrChecked.length; i++) {
				dto.put("deptid", arrChecked[i]);
				deleteDept(dto);
			}
		} else {
			// 部门树右键删除
			dto.put("deptid", pDto.getAsString("deptid"));
			deleteDept(dto);
		}
		outDto.put("success", new Boolean(true));
		outDto.put("msg", "部门数据删除成功!");
		return outDto;
	}

	/**
	 * 删除部门 类内部调用
	 * 
	 * @param pDto
	 */
	private void deleteDept(Dto pDto) {
		Dto changeLeafDto = new BaseDto();
		changeLeafDto.put("parentid", ((BaseDto) g4Dao.queryForObject("queryDeptItemsByDto", pDto))
				.getAsString("parentid"));
		g4Dao.delete("deleteEaroleAuthorizeInDeptManage", pDto);
		g4Dao.delete("deleteEaroleInDeptManage", pDto);
		g4Dao.delete("deleteEauserauthorizeInDeptManage", pDto);
		g4Dao.delete("deleteEauserauthorizeInDeptManage2", pDto);
		g4Dao.delete("deleteEauserInDeptManage", pDto);
		g4Dao.delete("deleteEausermenumapInDeptManage", pDto);
		g4Dao.delete("deleteEadeptItem", pDto);
		changeLeafOfDeletedParent(changeLeafDto);
	}

	/**
	 * 根据用户所属部门编号查询部门对象<br>
	 * 用于构造组织机构树的根节点
	 * 
	 * @param
	 * @return
	 */
	public Dto queryDeptinfoByDeptid(Dto pDto) {
		Dto outDto = new BaseDto();
		outDto.putAll((BaseDto) g4Dao.queryForObject("queryDeptinfoByDeptid", pDto));
		outDto.put("success", new Boolean(true));
		return outDto;
	}

	/**
	 * 保存用户主题信息
	 * 
	 * @param pDto
	 */
	public Dto saveUserTheme(Dto pDto) {
		Dto outDto = new BaseDto();
		g4Dao.update("saveUserTheme", pDto);
		outDto.put("success", new Boolean(true));
		return outDto;
	}

}
