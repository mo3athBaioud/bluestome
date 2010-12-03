package com.ssi.web.services;

import java.util.HashMap;
import java.util.List;

import com.ssi.common.dal.domain.Chart;

public interface IChartService {

	/**
	 * 文章总数统计
	 * @param map
	 * @return
	 */
	List<Chart> article(HashMap map);
	
	/**
	 * IT文章总数统计
	 * @param map
	 * @return
	 */
	List<Chart> articleDoc(HashMap map);

	/**
	 * 图片总数统计
	 * @param map
	 * @return
	 */
	List<Chart> image(HashMap map);

	/**
	 * 图片文件总数统计
	 * @param map
	 * @return
	 */
	List<Chart> pictureFile(HashMap map);

	/**
	 * 添加统计对象
	 * @param chart
	 * @return
	 */
	boolean insert(Chart chart);
	
	/**
	 * 查找统计数据
	 * @param map
	 * @return
	 */
	List<Chart> find(HashMap map);
	
}
