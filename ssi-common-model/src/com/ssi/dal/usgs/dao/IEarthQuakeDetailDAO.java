package com.ssi.dal.usgs.dao;

import java.util.HashMap;
import java.util.List;

import com.ssi.common.dal.EntityDAO;
import com.ssi.dal.usgs.domain.EarthQuakeDetail;

public interface IEarthQuakeDetailDAO extends EntityDAO<EarthQuakeDetail> {

	/**
	 * 查询地震详情列表
	 * @param map
	 * @return
	 */
	List<EarthQuakeDetail> find(HashMap map);
}
