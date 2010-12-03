package com.ssi.web.services.impl;

import java.util.HashMap;
import java.util.List;

import com.ssi.common.dal.dao.IChartDAO;
import com.ssi.common.dal.domain.Chart;
import com.ssi.web.services.IChartService;

public class ChartServiceImpl implements IChartService {

	private IChartDAO chartDao;
	
	/**
	 * 文章总数统计
	 * @param map
	 * @return
	 */
	public List<Chart> article(HashMap map){
		return chartDao.article(map);
	}
	
	/**
	 * IT文章总数统计
	 * @param map
	 * @return
	 */
	public List<Chart> articleDoc(HashMap map){
		return chartDao.articleDoc(map);
	}

	/**
	 * 图片总数统计
	 * @param map
	 * @return
	 */
	public List<Chart> image(HashMap map){
		return chartDao.image(map);
	}

	/**
	 * 图片文件总数统计
	 * @param map
	 * @return
	 */
	public List<Chart> pictureFile(HashMap map){
		return chartDao.pictureFile(map);
	}
	
	/**
	 * 添加统计对象
	 * @param chart
	 * @return
	 */
	public boolean insert(Chart chart){
		boolean b = false;
		if(chartDao.check(chart) == 0){
			int result =  chartDao.insert(chart);
			if(result > 0)
				b = true;
		}
		return b;
	}
	
	/**
	 * 查找统计数据
	 * @param map
	 * @return
	 */
	public List<Chart> find(HashMap map){
		return chartDao.find(map);
	}
	

	public IChartDAO getChartDao() {
		return chartDao;
	}

	public void setChartDao(IChartDAO chartDao) {
		this.chartDao = chartDao;
	}

	
}
