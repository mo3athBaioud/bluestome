package com.ssi.dal.usgs.dao;

import java.util.HashMap;
import java.util.List;

import com.ssi.common.dal.EntityDAO;
import com.ssi.dal.usgs.domain.EarthRegion;

public interface IEarthRegionDAO extends EntityDAO<EarthRegion> {

	/**
	 * 获取记录列表
	 * @param map
	 * @return
	 */
	List<EarthRegion> find(HashMap map);

}
