package org.eredlab.g4.arm.service.impl;

import java.util.List;

import org.eredlab.g4.arm.service.UserService;
import org.eredlab.g4.arm.util.ArmConstants;
import org.eredlab.g4.arm.util.idgenerator.IDHelper;
import org.eredlab.g4.bmf.base.BaseServiceImpl;
import org.eredlab.g4.ccl.datastructure.Dto;
import org.eredlab.g4.ccl.datastructure.impl.BaseDto;
import org.eredlab.g4.ccl.json.JsonHelper;
import org.eredlab.g4.ccl.util.G4Utils;

/**
 * 用户管理与授权业务实现类
 * 
 * @author XiongChun
 * @since 2010-04-13
 */
public class UserServiceImpl extends BaseServiceImpl implements UserService {
	
	/**
	 * 用户管理 - 用户列表
	 * 
	 * @param pDto
	 * @return
	 */
	public Dto queryUsersForManage(Dto pDto) {
		Dto outDto = new BaseDto();
		List userList = g4Dao.queryForPage("queryUsersForManage", pDto);
		String jsonString = JsonHelper.encodeObject2Json(userList);
		Integer pageCount = (Integer)g4Dao.queryForObject("queryUsersForManageForPageCount", pDto);
		outDto.put("jsonString", JsonHelper.encodeJson2PageJson(jsonString, pageCount));
		return outDto;
	}

	/**
	 * 保存用户
	 * 
	 * @param pDto
	 * @return
	 */
	public Dto saveUserItem(Dto pDto) {
		Dto outDto = new BaseDto();
		Integer temp = (Integer)g4Dao.queryForObject("checkAccount", pDto);
		if (temp.intValue() != 0) {
			outDto.put("msg", "登录账户" + outDto.getAsString("account") + "已被占用,请尝试其它帐户!");
			outDto.put("success", new Boolean(false));
			return outDto;
		}
		pDto.put("userid", IDHelper.getUserID());
		String password = pDto.getAsString("password");
		String mPasswor = G4Utils.encryptBasedDes(password);
		pDto.put("password", mPasswor);
		g4Dao.insert("saveUserItem", pDto);
		g4Dao.insert("saveEausersubinfoItem", pDto);
		outDto.put("msg", "用户数据新增成功");
		outDto.put("success", new Boolean(true));
		return outDto;
	}

	/**
	 * 删除用户
	 * 
	 * @param pDto
	 * @return
	 */
	public Dto deleteUserItems(Dto pDto) {
		Dto outDto = new BaseDto();
		Dto dto = new BaseDto();
		String[] arrChecked = pDto.getAsString("strChecked").split(",");
		for (int i = 0; i < arrChecked.length; i++) {
			dto.put("userid", arrChecked[i]);
			g4Dao.delete("deleteEauserInUserManage", dto);
			g4Dao.delete("deleteEauserauthorizeInUserManage", dto);
			g4Dao.delete("deleteEausermenumapByUserid", dto);
			g4Dao.delete("deleteEausersubinfoByUserid", dto);
		}
		outDto.put("success", new Boolean(true));
		outDto.put("msg", "用户数据删除成功!");
		return outDto;
	}

	/**
	 * 修改用户
	 * 
	 * @param pDto
	 * @return
	 */
	public Dto updateUserItem(Dto pDto) {
		Dto outDto = new BaseDto();
		String password = pDto.getAsString("password");
		String mPasswor = G4Utils.encryptBasedDes(password);
		pDto.put("password", mPasswor);
		g4Dao.update("updateUserItem", pDto);
		if (!pDto.getAsString("deptid").equals(pDto.getAsString("deptid_old"))) {
			g4Dao.delete("deleteEauserauthorizeInUserManage", pDto);
			g4Dao.delete("deleteEausermenumapByUserId", pDto);
		}
		outDto.put("success", new Boolean(true));
		outDto.put("msg", "用户数据修改成功!");
		return outDto;
	}

	/**
	 * 保存人员角色关联信息
	 * 
	 * @param pDto
	 * @return
	 */
	public Dto saveSelectedRole(Dto pDto) {
		g4Dao.delete("deleteEaUserAuthorizeByUserId", pDto);
		Dto outDto = new BaseDto();
		String[] roleids = pDto.getAsString("roleid").split(",");
		for (int i = 0; i < roleids.length; i++) {
			String roleid = roleids[i];
			if (G4Utils.isEmpty(roleid))
				continue;
			pDto.put("roleid", roleid);
			pDto.put("authorizeid", IDHelper.getAuthorizeid4User());
			g4Dao.insert("saveSelectedRole", pDto);
		}
		outDto.put("msg", "您选择的人员角色关联数据保存成功");
		outDto.put("success", new Boolean(true));
		return outDto;
	}

	/**
	 * 保存人员菜单关联信息
	 * 
	 * @param pDto
	 * @return
	 */
	public Dto saveSelectedMenu(Dto pDto) {
		g4Dao.delete("deleteEausermenumapByUserId", pDto);
		Dto outDto = new BaseDto();
		String[] menuids = pDto.getAsString("menuid").split(",");
		for (int i = 0; i < menuids.length; i++) {
			String menuid = menuids[i];
			if (G4Utils.isEmpty(menuid))
				continue;
			pDto.put("menuid", menuid);
			pDto.put("authorizeid", IDHelper.getAuthorizeid4Usermenumap());
			pDto.put("authorizelevel", ArmConstants.AUTHORIZELEVEL_ACCESS);
			g4Dao.insert("saveSelectedMenu", pDto);
		}
		outDto.put("msg", "您选择的人员菜单关联数据保存成功");
		outDto.put("success", new Boolean(true));
		return outDto;
	}
	
}
