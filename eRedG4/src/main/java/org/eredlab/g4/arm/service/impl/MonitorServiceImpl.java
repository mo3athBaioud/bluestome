package org.eredlab.g4.arm.service.impl;

import java.util.List;

import org.eredlab.g4.arm.service.MonitorService;
import org.eredlab.g4.arm.util.idgenerator.IDHelper;
import org.eredlab.g4.arm.vo.UserInfoVo;
import org.eredlab.g4.bmf.base.BaseServiceImpl;
import org.eredlab.g4.ccl.datastructure.Dto;
import org.eredlab.g4.ccl.datastructure.impl.BaseDto;
import org.eredlab.g4.ccl.json.JsonHelper;
import org.eredlab.g4.ccl.util.G4Utils;

/**
 * 系统监控业务接口
 * 
 * @author XiongChun
 * @since 2010-09-13
 */
public class MonitorServiceImpl extends BaseServiceImpl implements MonitorService {

	/**
	 * 查询HTTP会话
	 * 
	 * @param inDto
	 * @return
	 */
	public Dto queryHttpSessions(Dto inDto) {
		Dto outDto = new BaseDto();
		List sessionList = g4Dao.queryForList("queryHttpSessions", inDto);
		for (int i = 0; i < sessionList.size(); i++) {
			UserInfoVo userInfo = (UserInfoVo) sessionList.get(i);
			if (inDto.getAsString("sessionid").equalsIgnoreCase(userInfo.getSessionID())) {
				userInfo.setUsername("不能自杀哦!");
			}
		}
		String jsonString = JsonHelper.encodeObject2Json(sessionList);
		jsonString = JsonHelper.encodeJson2PageJson(jsonString, new Integer(sessionList.size()));
		outDto.put("jsonString", jsonString);
		return outDto;
	}

	/**
	 * 保存一个HTTP会话
	 * 
	 * @param userInfo
	 */
	public void saveHttpSession(UserInfoVo userInfo) {
		g4Dao.insert("saveHttpSession", userInfo);
	}

	/**
	 * 删除一个托管的会话连接
	 * 
	 * @param pSessionID
	 */
	public void deleteHttpSession(Dto dto) {
		g4Dao.delete("deleteHttpSession", dto);
	}

	/**
	 * 查询HTTP会话
	 * 
	 * @param inDto
	 * @return
	 */
	public UserInfoVo queryHttpSessionsByID(String pSessionID) {
		return (UserInfoVo) g4Dao.queryForObject("queryHttpSessionsByID", pSessionID);
	}

	/**
	 * 创建一个事件
	 * 
	 * @param inDto
	 */
	public void saveEvent(Dto dto) {
		String eventid = IDHelper.getEventID();
		dto.put("eventid", eventid);
		g4Dao.insert("saveEvent", dto);
	}

	/**
	 * 查询事件列表
	 * 
	 * @param inDto
	 * @return
	 */
	public Dto queryEventsByDto(Dto inDto) {
		Dto outDto = new BaseDto();
		List eventList = null;
		Integer totalCount = null;
		if (G4Utils.isOracle()) {
			eventList = g4Dao.queryForPage("queryEventsByDto", inDto);
			totalCount = (Integer) g4Dao.queryForObject("queryEventsByDtoForPageCount", inDto);
		} else {
			eventList = g4Dao.queryForPage("queryEventsByDtoMysql", inDto);
			totalCount = (Integer) g4Dao.queryForObject("queryEventsByDtoForPageCountMysql", inDto);
		}
		outDto.setDefaultJson(JsonHelper.encodeJson2PageJson(JsonHelper.encodeObject2Json(eventList), totalCount));
		return outDto;
	}

	/**
	 * 删除事件
	 * 
	 * @param inDto
	 */
	public Dto deleteEvent(Dto inDto) {
		if (inDto.getAsString("type").equalsIgnoreCase("reset")) {
			g4Dao.delete("resetEvent");
		} else {
			String[] checked = inDto.getAsString("strChecked").split(",");
			for (int i = 0; i < checked.length; i++) {
				g4Dao.delete("deleteEvent", checked[i]);
			}
		}
		Dto outDto = new BaseDto();
		outDto.put("success", new Boolean(true));
		outDto.put("msg", "数据删除成功!");
		if (inDto.getAsString("type").equalsIgnoreCase("reset"))
			outDto.put("msg", "重置成功,所有事件已被清除!");
		return outDto;
	}

	/**
	 * 查询SpringBean监控记录
	 * 
	 * @param inDto
	 * @return
	 */
	public Dto queryMonitorDatasByDto(Dto inDto) {
		Dto outDto = new BaseDto();
		List eventList = null;
		Integer totalCount = null;
		if (G4Utils.isOracle()) {
			eventList = g4Dao.queryForPage("queryBeanMonitorRecordsByDto", inDto);
			totalCount = (Integer) g4Dao.queryForObject("queryBeanMonitorRecordsByDtoForPageCount", inDto);
		} else if (G4Utils.isMysql()) {
			eventList = g4Dao.queryForPage("queryBeanMonitorRecordsByDtoMysql", inDto);
			totalCount = (Integer) g4Dao.queryForObject("queryBeanMonitorRecordsByDtoForPageCountMysql", inDto);
		}
		outDto.setDefaultJson(JsonHelper.encodeJson2PageJson(JsonHelper.encodeObject2Json(eventList), totalCount));
		return outDto;
	}

	/**
	 * 删除SpringBean监控记录
	 * 
	 * @param inDto
	 */
	public Dto deleteMonitorData(Dto inDto) {
		if (inDto.getAsString("type").equalsIgnoreCase("reset")) {
			g4Dao.delete("resetBeanMonitorRecords");
		} else {
			String[] checked = inDto.getAsString("strChecked").split(",");
			for (int i = 0; i < checked.length; i++) {
				g4Dao.delete("deleteBeanMonitorRecord", checked[i]);
			}
		}
		Dto outDto = new BaseDto();
		outDto.put("success", new Boolean(true));
		outDto.put("msg", "数据删除成功!");
		if (inDto.getAsString("type").equalsIgnoreCase("reset"))
			outDto.put("msg", "重置成功,所有监控记录已被清除!");
		return outDto;
	}

	/**
	 * 查询监控信息
	 * 
	 * @param inDto
	 */
	public Dto queryMonitorData(Dto inDto) {
		Dto outDto = new BaseDto();
		List eventList = null;
		Integer totalCount = null;
		if (G4Utils.isOracle()) {
			eventList = g4Dao.queryForPage("queryJdbcMonitorRecordsByDto", inDto);
			totalCount = (Integer)g4Dao.queryForObject("queryJdbcMonitorRecordsByDtoForPageCount", inDto);
		} else if (G4Utils.isMysql()) {
			eventList = g4Dao.queryForPage("queryJdbcMonitorRecordsByDtoMysql", inDto);
			totalCount = (Integer)g4Dao.queryForObject("queryJdbcMonitorRecordsByDtoForPageCountMysql", inDto);
		}
		outDto.setDefaultJson(JsonHelper.encodeJson2PageJson(JsonHelper.encodeObject2Json(eventList), totalCount));
		return outDto;
	}

	/**
	 * 重置监控信息
	 * 
	 * @param inDto
	 */
	public Dto resetMonitorData() {
		Dto outDto = new BaseDto();
		g4Dao.delete("resetJdbcMonitorData");
		outDto.put("success", new Boolean(true));
		outDto.put("msg", "JDBC监控记录重置成功");
		return outDto;
	}

}
