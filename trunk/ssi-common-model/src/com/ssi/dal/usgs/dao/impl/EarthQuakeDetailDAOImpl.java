package com.ssi.dal.usgs.dao.impl;

import java.util.HashMap;
import java.util.List;

import com.ssi.common.dal.IbatisEntityDao;
import com.ssi.dal.usgs.dao.IEarthQuakeDetailDAO;
import com.ssi.dal.usgs.domain.EarthQuakeDetail;

public class EarthQuakeDetailDAOImpl extends IbatisEntityDao<EarthQuakeDetail>
		implements IEarthQuakeDetailDAO {

	/**
	 * 查询地震详情列表
	 * @param map
	 * @return
	 */
	public List<EarthQuakeDetail> find(HashMap map) {
		List<EarthQuakeDetail> list = getQueryDelegate().queryForList("earthquakedetail.selectByMap", map,getRoute());
		return list;
	}
}
