package org.eredlab.g4.arm.service.impl;

import java.util.List;

import org.eredlab.g4.arm.service.RoleService;
import org.eredlab.g4.arm.util.ArmConstants;
import org.eredlab.g4.arm.util.idgenerator.IDHelper;
import org.eredlab.g4.bmf.base.BaseServiceImpl;
import org.eredlab.g4.ccl.datastructure.Dto;
import org.eredlab.g4.ccl.datastructure.impl.BaseDto;
import org.eredlab.g4.ccl.json.JsonHelper;
import org.eredlab.g4.ccl.util.G4Utils;

/**
 * 角色管理与授权业务实现类
 * @author XiongChun
 * @since 2010-04-13
 */
public class RoleServiceImpl extends BaseServiceImpl implements RoleService{
	
	/**
	 * 角色管理 - 角色列表
	 * @param pDto
	 * @return
	 */
	public Dto queryRolesForManage(Dto pDto){
		Dto outDto = new BaseDto();
		List roleList = g4Dao.queryForPage("queryRolesForManage", pDto);
		String jsonString = JsonHelper.encodeObject2Json(roleList);
		Integer pageCount = (Integer)g4Dao.queryForObject("queryRolesForManageForPageCount", pDto);
		outDto.put("jsonString", JsonHelper.encodeJson2PageJson(jsonString, pageCount));
		return outDto;
	}
	
	/**
	 * 保存角色
	 * @param pDto
	 * @return
	 */
	public Dto saveRoleItem(Dto pDto){
		Dto outDto = new BaseDto();
		pDto.put("roleid", IDHelper.getRoleID());
		g4Dao.insert("saveRoleItem", pDto);
		outDto.put("msg", "角色数据新增成功");
		outDto.put("success", new Boolean(true));
		return outDto;
	}
	
	/**
	 * 删除角色
	 * @param pDto
	 * @return
	 */
	public Dto deleteRoleItems(Dto pDto){
		Dto outDto = new BaseDto();
		Dto dto = new BaseDto();
		String[] arrChecked = pDto.getAsString("strChecked").split(",");
		for(int i = 0; i < arrChecked.length; i++){
			dto.put("roleid", arrChecked[i]);
			g4Dao.delete("deleteEaroleAuthorizeInRoleManage", dto);
			g4Dao.delete("deleteEaroleInRoleManage", dto);
			g4Dao.delete("deleteEauserauthorizeInRoleManage", dto);
		}
		outDto.put("success", new Boolean(true));
		outDto.put("msg", "角色数据删除成功!");
		return outDto;
	}
	
	/**
	 * 修改角色
	 * @param pDto
	 * @return
	 */
	public Dto updateRoleItem(Dto pDto){
		Dto outDto = new BaseDto();
		g4Dao.update("updateRoleItem", pDto);
		if(!pDto.getAsString("deptid").equals(pDto.getAsString("deptid_old"))){
			g4Dao.delete("deleteEaroleAuthorizeInRoleManage", pDto);
		}
		outDto.put("success", new Boolean(true));
		outDto.put("msg", "角色数据修改成功!");
		return outDto;
	}
	
	/**
	 * 保存角色授权信息
	 * @param pDto
	 * @return
	 */
	public Dto saveGrant(Dto pDto){
		g4Dao.delete("deleteERoleGrants", pDto);
		Dto outDto = new BaseDto();
		String[] menuids = pDto.getAsString("menuid").split(",");
		for(int i = 0; i < menuids.length; i++){
			String menuid = menuids[i];
			if(G4Utils.isEmpty(menuid))
				continue;
			pDto.put("menuid", menuid);
			pDto.put("authorizeid", IDHelper.getAuthorizeid4Role());
			g4Dao.insert("saveRoleGrantItem", pDto);
		}
		if(pDto.getAsString("authorizelevel").equals(ArmConstants.AUTHORIZELEVEL_ACCESS))
		   outDto.put("msg", "经办权限授权成功");
		if(pDto.getAsString("authorizelevel").equals(ArmConstants.AUTHORIZELEVEL_ADMIN))
			   outDto.put("msg", "管理权限授权成功");
		outDto.put("success", new Boolean(true));
		return outDto;
	}
	
	/**
	 * 保存角色用户关联信息
	 * @param pDto
	 * @return
	 */
	public Dto saveSelectUser(Dto pDto){
		g4Dao.delete("deleteEaUserAuthorizeByRoleId", pDto);
		Dto outDto = new BaseDto();
		String[] userids = pDto.getAsString("userid").split(",");
		for(int i = 0; i < userids.length; i++){
			String userid = userids[i];
			if(G4Utils.isEmpty(userid))
				continue;
			pDto.put("userid", userid);
			pDto.put("authorizeid", IDHelper.getAuthorizeid4User());
			g4Dao.insert("saveSelectUser", pDto);
		}
		outDto.put("msg", "您选择的角色人员关联数据保存成功");
		outDto.put("success", new Boolean(true));
		return outDto;
	}
}
