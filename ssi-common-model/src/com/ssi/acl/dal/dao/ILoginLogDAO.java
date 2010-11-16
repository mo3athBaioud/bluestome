package com.ssi.acl.dal.dao;

import java.util.HashMap;
import java.util.List;

import com.ssi.acl.dal.domain.LoginLog;

public interface ILoginLogDAO {

	/**
	 * 根据条件查询记录
	 * @param map
	 * @return
	 */
	List<LoginLog> find(HashMap map);
	
	/**
	 * 添加日志
	 * @param loginLog
	 * @return
	 */
	int insert(LoginLog loginLog);
	
	/**
	 * 修改日志
	 * @param loginLog
	 * @return
	 */
	int update(LoginLog loginLog);
	
	/**
	 * 删除日志
	 * @param id
	 * @return
	 */
	int delete(Integer id);
	
	/**
	 * 根据HASHMAP中的条件获取记录总数
	 * @param map
	 * @return
	 */
	int getCount(HashMap map);
}
