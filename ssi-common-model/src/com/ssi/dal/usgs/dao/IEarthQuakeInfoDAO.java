package com.ssi.dal.usgs.dao;

import java.util.HashMap;
import java.util.List;

import com.ssi.common.dal.EntityDAO;
import com.ssi.dal.usgs.domain.EarthQuakeInfo;

public interface IEarthQuakeInfoDAO extends EntityDAO<EarthQuakeInfo> {

	/**
	 * 获取记录列表
	 * @param map
	 * @return
	 */
	List<EarthQuakeInfo> find(HashMap map);
}
