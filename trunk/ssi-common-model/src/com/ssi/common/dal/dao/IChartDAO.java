package com.ssi.common.dal.dao;

import java.util.HashMap;
import java.util.List;

import com.ssi.common.dal.domain.Chart;

public interface IChartDAO {
	
	/**
	 * 添加图片对象
	 * @param chart
	 * @return
	 */
	int insert(Chart chart);
	
	/**
	 * 更新记录
	 * @param chart
	 * @return
	 */
	int update(Chart chart);
	
	/**
	 * 检查唯一性
	 * @param chart
	 * @return
	 */
	int check(Chart chart);
	
	/**
	 * 查找统计数据
	 * @param map
	 * @return
	 */
	List<Chart> find(HashMap map);

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

}