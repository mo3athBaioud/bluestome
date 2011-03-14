package com.ssi.dal.usgs.dao.impl;

import java.util.HashMap;
import java.util.List;

import com.ssi.common.dal.IbatisEntityDao;
import com.ssi.dal.usgs.dao.IEarthRegionDAO;
import com.ssi.dal.usgs.domain.EarthQuakeInfo;
import com.ssi.dal.usgs.domain.EarthRegion;

public class EarthRegionDAOImpl extends IbatisEntityDao<EarthRegion> implements
		IEarthRegionDAO {

	/**
	 * 获取记录列表
	 * @param map
	 * @return
	 */
	public List<EarthRegion> find(HashMap map){
		List<EarthRegion> list = getQueryDelegate().queryForList("earthregion.selectByMap", map,getRoute());
		return list;
	}

}
