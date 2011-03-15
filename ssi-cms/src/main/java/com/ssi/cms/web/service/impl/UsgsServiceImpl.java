package com.ssi.cms.web.service.impl;

import java.util.HashMap;
import java.util.List;

import com.ssi.cms.web.service.IUsgsService;
import com.ssi.common.dal.domain.Website;
import com.ssi.dal.usgs.dao.IEarthQuakeDetailDAO;
import com.ssi.dal.usgs.dao.IEarthQuakeInfoDAO;
import com.ssi.dal.usgs.domain.EarthQuakeInfo;

public class UsgsServiceImpl implements IUsgsService {

	private IEarthQuakeDetailDAO earthQuakeDetailDAO;
	private IEarthQuakeInfoDAO earthQuakeInfoDAO;
	
	/**
	 * 根据参数MAP查找地震信息
	 * @param map
	 * @return
	 */
	public List<EarthQuakeInfo> find(HashMap map) {
		return earthQuakeInfoDAO.find(map);
	}
	
	/**
	 * 获取总数
	 * @param map
	 * @return
	 */
	public int getCount(HashMap map){
		return earthQuakeInfoDAO.getCount(map);
	}


	public IEarthQuakeDetailDAO getEarthQuakeDetailDAO() {
		return earthQuakeDetailDAO;
	}

	public void setEarthQuakeDetailDAO(IEarthQuakeDetailDAO earthQuakeDetailDAO) {
		this.earthQuakeDetailDAO = earthQuakeDetailDAO;
	}

	public IEarthQuakeInfoDAO getEarthQuakeInfoDAO() {
		return earthQuakeInfoDAO;
	}

	public void setEarthQuakeInfoDAO(IEarthQuakeInfoDAO earthQuakeInfoDAO) {
		this.earthQuakeInfoDAO = earthQuakeInfoDAO;
	}

	public List<EarthQuakeInfo> getPageList(String colName, String value, Integer startIndex, Integer pageSize, boolean asc) throws Exception {
		HashMap map = new HashMap();
		if(null == colName || colName.equalsIgnoreCase("")){
			map.put("magnitude", value);
		}
		if(null == startIndex){
			startIndex = 0;
		}
		if(null == pageSize){
			pageSize = 20;
		}
		map.put(colName, value);
		map.put("limit", pageSize);
		map.put("offset", startIndex);
		if(asc){
			map.put("asc", "asc");
		}else{
			map.put("asc", "desc");
		}
		List<EarthQuakeInfo>  list = earthQuakeInfoDAO.find(map);
		return list;
	}

	public int getCount(String colName, String value) {
		HashMap map = new HashMap();
		if(null != colName && !"".equals(colName)){
			map.put(colName, value);
		}else{
			map.put("magnitude", value);
		}
		return earthQuakeInfoDAO.getCount(map);
	}
	
}
