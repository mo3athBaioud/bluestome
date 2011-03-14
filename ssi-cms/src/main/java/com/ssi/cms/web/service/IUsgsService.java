package com.ssi.cms.web.service;

import java.util.HashMap;
import java.util.List;

import com.ssi.dal.usgs.domain.EarthQuakeInfo;

public interface IUsgsService {

	
	/**
	 * 根据参数MAP查找地震信息
	 * @param map
	 * @return
	 */
	List<EarthQuakeInfo> find(HashMap map);
	
	/**
	 * 获取总数
	 * @param map
	 * @return
	 */
	int getCount(HashMap map);
}
