package com.ssi.dal.usgs.dao.impl;


import java.util.HashMap;
import java.util.List;

import com.ssi.common.dal.IbatisEntityDao;
import com.ssi.dal.usgs.dao.IEarthQuakeInfoDAO;
import com.ssi.dal.usgs.domain.EarthQuakeInfo;

public class EarthQuakeInfoDAOImpl extends IbatisEntityDao<EarthQuakeInfo> implements
		IEarthQuakeInfoDAO {

	/**
	 * 获取记录列表
	 * @param map
	 * @return
	 */
	public List<EarthQuakeInfo> find(HashMap map){
		List<EarthQuakeInfo> list = getQueryDelegate().queryForList("earthquakeinfo.selectByMap", map,getRoute());
		return list;
	}

}
