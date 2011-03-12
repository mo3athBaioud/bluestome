package com.ssi.common.dal.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.ssi.common.dal.BaseDAOImpl;
import com.ssi.common.dal.IbatisEntityDao;
import com.ssi.common.dal.dao.IChartDAO;
import com.ssi.common.dal.domain.Chart;

public class ChartDAOImpl extends IbatisEntityDao<Chart> implements IChartDAO {

	public List<Chart> article(HashMap map) {
		List<Chart> list = new ArrayList<Chart>();
		list = getQueryDelegate().queryForList("QUERY_ARTICLE_CHART", map, getRoute());
		return list;
	}

	public List<Chart> articleDoc(HashMap map) {
		List<Chart> list = new ArrayList<Chart>();
		list = getQueryDelegate().queryForList("QUERY_ARTICLEDOC_CHART", map, getRoute());
		return list;
	}

	public int check(Chart chart) {
		int result = (Integer)getQueryDelegate().queryForObject("QUERY_UNIQUE_CHART",chart,getRoute());
		return result;
	}

	public List<Chart> find(HashMap map) {
		List<Chart> list = new ArrayList<Chart>();
		list = getQueryDelegate().queryForList("QUERY_CHART", map, getRoute());
		return list;
	}

	public List<Chart> image(HashMap map) {
		List<Chart> list = new ArrayList<Chart>();
		list = getQueryDelegate().queryForList("QUERY_IMAGE_CHART", map, getRoute());
		return list;
	}

	public List<Chart> pictureFile(HashMap map) {
		List<Chart> list = new ArrayList<Chart>();
		list = getQueryDelegate().queryForList("QUERY_PICTUREFILE_CHART", map, getRoute());
		return list;
	}

}
