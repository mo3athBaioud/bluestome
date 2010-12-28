package org.eredlab.g4.arm.service;

import org.eredlab.g4.arm.vo.UserInfoVo;
import org.eredlab.g4.bmf.base.BaseService;
import org.eredlab.g4.ccl.datastructure.Dto;

/**
 * 系统监控业务接口
 * @author XiongChun
 * @since 2010-05-13
 */
public interface MonitorService extends BaseService{
	
	/**
	 * 查询HTTP会话
	 * @param inDto
	 * @return
	 */
	public Dto queryHttpSessions(Dto inDto);
	
	/**
	 * 保存一个HTTP会话
	 * @param userInfo
	 */
	public void saveHttpSession(UserInfoVo userInfo);
	
	/**
	 * 删除一个托管的会话连接
	 * @param pSessionID
	 */
	public void deleteHttpSession(Dto dto);
	
	/**
	 * 查询HTTP会话
	 * @param inDto
	 * @return
	 */
	public UserInfoVo queryHttpSessionsByID(String pSessionID);
	
	/**
	 * 创建一个事件
	 * @param inDto
	 */
	public void saveEvent(Dto dto);
	
	/**
	 * 查询事件列表
	 * @param inDto
	 * @return
	 */
	public Dto queryEventsByDto(Dto inDto);
	
	/**
	 * 删除事件
	 * @param inDto
	 */
	public Dto deleteEvent(Dto inDto);
	
	/**
	 * 查询SpringBean监控记录
	 * 
	 * @param inDto
	 * @return
	 */
	public Dto queryMonitorDatasByDto(Dto inDto);

	/**
	 * 删除SpringBean监控记录
	 * 
	 * @param inDto
	 */
	public Dto deleteMonitorData(Dto inDto);
	
	/**
	 * 查询监控信息
	 * 
	 * @param inDto
	 */
	public Dto queryMonitorData(Dto inDto);
	
	/**
	 * 重置监控信息
	 * 
	 * @param inDto
	 */
	public Dto resetMonitorData();
}
