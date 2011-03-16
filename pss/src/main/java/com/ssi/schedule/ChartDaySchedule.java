package com.ssi.schedule;

import java.util.HashMap;
import java.util.List;

import com.ssi.common.dal.domain.Chart;
import com.ssi.common.utils.DateUtils;

public class ChartDaySchedule extends BaseSchedule {
	
	final static String TABLE_ARTICLE = "TBL_ARTICLE";
	final static String TABLE_ARTICLE_DOC = "TBL_ARTICLE_DOC";
	final static String TABLE_IMAGE = "TBL_IMAGE";
	final static String TABLE_PIC_FILE = "TBL_PIC_FILE";

	@Override
	public void process() {
		long start = System.currentTimeMillis();
		article();
		articleDoc();
		image();
		pictureFile();
		//TODO ADD LOG TO LOGDB
		long end = System.currentTimeMillis();
		logger.info(">> ChartDaySchedule.process ["+(end-start)+"]ms ");
	}

	private void article(){
		HashMap map = new HashMap();
//		map.put("date", DateUtils.getShortNow());
		map.put("chart", "ok");
		List<Chart> list = chartDao.article(map);
		if(null != list && list.size() > 0){
			long start = System.currentTimeMillis();
			for(Chart chart:list){
				chart.setChartType("DAY");
				chart.setTableName(TABLE_ARTICLE);
				if(chartDao.check(chart) == 0){
					int result = chartDao.insert(chart);
					if(result > 0){
						System.out.println(">> 添加 "+TABLE_ARTICLE+" 数据记录成功！"+result);
					}else{
						int r = chartDao.update(chart);
						if(r > 0){
							System.out.println(">> 更新记录成功:"+chart.getName()+"\t"+chart.getTableName()+"\t"+chart.getChartType());
						}
					}
				}
			}
			long end = System.currentTimeMillis();
			logger.info(" >> ChartDaySchedule.article ["+(end-start)+"ms]");
		}
	}
	
	private void articleDoc(){
		HashMap map = new HashMap();
//		map.put("date", DateUtils.getShortNow());
		map.put("chart", "ok");
		List<Chart> list = chartDao.articleDoc(map);
		if(null != list && list.size() > 0){
			long start = System.currentTimeMillis();
			for(Chart chart:list){
				chart.setChartType("DAY");
				chart.setTableName(TABLE_ARTICLE_DOC);
				if(chartDao.check(chart) == 0){
					int result = chartDao.insert(chart);
					if(result > 0){
						System.out.println(">> 添加 "+TABLE_ARTICLE_DOC+" 数据记录成功！"+result);
					}else{
						int r = chartDao.update(chart);
						if(r > 0){
							System.out.println(">> 更新记录成功:"+chart.getName()+"\t"+chart.getTableName()+"\t"+chart.getChartType());
						}
					}
				}
			}
			long end = System.currentTimeMillis();
			logger.info(" >> ChartDaySchedule.articleDoc ["+(end-start)+"ms]");
		}
	}
	
	private void image(){
		HashMap map = new HashMap();
//		map.put("date", DateUtils.getShortNow());
		map.put("chart", "ok");
		List<Chart> list = chartDao.image(map);
		if(null != list && list.size() > 0){
			long start = System.currentTimeMillis();
			for(Chart chart:list){
				chart.setChartType("DAY");
				chart.setTableName(TABLE_IMAGE);
				if(chartDao.check(chart) == 0){
					int result = chartDao.insert(chart);
					if(result > 0){
						System.out.println(">> 添加 "+TABLE_IMAGE+" 数据记录成功！"+result);
					}else{
						int r = chartDao.update(chart);
						if(r > 0){
							System.out.println(">> 更新记录成功:"+chart.getName()+"\t"+chart.getTableName()+"\t"+chart.getChartType());
						}
					}
				}
			}
			long end = System.currentTimeMillis();
			logger.info(" >> ChartDaySchedule.image ["+(end-start)+"ms]");
		}
	}
	
	private void pictureFile(){
		HashMap map = new HashMap();
//		map.put("date", DateUtils.getShortNow());
		map.put("chart", "ok");
		List<Chart> list = chartDao.pictureFile(map);
		if(null != list && list.size() > 0){
			long start = System.currentTimeMillis();
			for(Chart chart:list){
				chart.setChartType("DAY");
				chart.setTableName(TABLE_PIC_FILE);
				if(chartDao.check(chart) == 0){
					int result = chartDao.insert(chart);
					if(result > 0){
						System.out.println(">> 添加 "+TABLE_PIC_FILE+" 数据记录成功！"+result);
					}else{
						int r = chartDao.update(chart);
						if(r > 0){
							System.out.println(">> 更新记录成功:"+chart.getName()+"\t"+chart.getTableName()+"\t"+chart.getChartType());
						}
					}
				}
			}
			long end = System.currentTimeMillis();
			logger.info(" >> ChartDaySchedule.pictureFile ["+(end-start)+"ms]");
		}
	}
}
