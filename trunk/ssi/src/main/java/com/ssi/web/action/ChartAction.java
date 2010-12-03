package com.ssi.web.action;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;

import com.ssi.common.dal.domain.Chart;
import com.ssi.common.utils.DateUtils;
import com.ssi.web.services.IChartService;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class ChartAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private IChartService chartService;

	private String isOk = "notOk";
	
	final static String TEMPLATE = "<chart><series>{1}</series><graphs><graph gid='0' title='{3}'>{2}</graph></graphs></chart>";
	
	private String data;
	
	private String data2;
	
	private String data3;
	
	private String data4;
	
	private String startdate;
	
	private String enddate;
	
	/**
	 * 文章统计表
	 * @return
	 * @throws Exception
	 */
	public String article() throws Exception{
		HashMap map = new HashMap();
		map.put("tableName", "TBL_ARTICLE");
		map.put("chartType", "DAY");
		if(null != startdate && !"".equalsIgnoreCase(startdate))
			map.put("startdate", startdate);
		if(null != enddate && !"".equalsIgnoreCase(enddate))
			map.put("enddate", enddate);
		else
			map.put("enddate", DateUtils.getShortNow());
		logger.debug("startdate:"+map.get("startdate"));
		logger.debug("enddate:"+map.get("enddate"));
		List<Chart> list = chartService.find(map);
		if(null != list && list.size() > 0){
			StringBuffer total = new StringBuffer();
			StringBuffer series = new StringBuffer();
			StringBuffer graph = new StringBuffer();
			int i=0;
			for(Chart chart:list){
				series.append("<value xid='"+i+"'>"+chart.getName()+"</value>");
				graph.append("<value xid='"+i+"'>"+chart.getTotal()+"</value>");
				logger.debug("TableName:"+chart.getTableName());
				logger.debug("Name:"+chart.getName());
				logger.debug("Total:"+chart.getTotal());
				i++;
			}
			if(i > 0)
				isOk = "ok";
			total.append(TEMPLATE);
			data = total.toString().replace("{1}", series).replace("{2}", graph).replace("{3}", "文章");
		}
		logger.debug(">> data:"+data);
		request.setAttribute("isOk", isOk);
		return "article";
	}
	
	public String articleDoc() throws Exception{
		HashMap map = new HashMap();
		map.put("tableName", "TBL_ARTICLE_DOC");
		map.put("chartType", "DAY");
		if(null != startdate && !"".equalsIgnoreCase(startdate))
			map.put("startdate", startdate);
		if(null != enddate && !"".equalsIgnoreCase(enddate))
			map.put("enddate", enddate);
		else
			map.put("enddate", DateUtils.getShortNow());
		List<Chart> list = chartService.find(map);
		if(null != list && list.size() > 0){
			StringBuffer total = new StringBuffer();
			StringBuffer series = new StringBuffer();
			StringBuffer graph = new StringBuffer();
			int i=0;
			for(Chart chart:list){
				series.append("<value xid='"+i+"'>"+chart.getName()+"</value>");
				graph.append("<value xid='"+i+"'>"+chart.getTotal()+"</value>");
				logger.debug("TableName:"+chart.getTableName());
				logger.debug("Name:"+chart.getName());
				logger.debug("Total:"+chart.getTotal());
				i++;
			}
			if(i > 0){
				isOk = "ok";
				request.setAttribute("isOk", "ok");
			}
			total.append(TEMPLATE);
			data = total.toString().replace("{1}", series).replace("{2}", graph).replace("{3}", "IT文章");;
		}
		return "articledoc";
	}
	
	public String image() throws Exception{
		HashMap map = new HashMap();
		map.put("tableName", "TBL_IMAGE");
		map.put("chartType", "DAY");
		if(null != startdate && !"".equalsIgnoreCase(startdate))
			map.put("startdate", startdate);
		if(null != enddate && !"".equalsIgnoreCase(enddate))
			map.put("enddate", enddate);
		else
			map.put("enddate", DateUtils.getShortNow());
		List<Chart> list = chartService.find(map);
		if(null != list && list.size() > 0){
			StringBuffer total = new StringBuffer();
			StringBuffer series = new StringBuffer();
			StringBuffer graph = new StringBuffer();
			int i=0;
			for(Chart chart:list){
				series.append("<value xid='"+i+"'>"+chart.getName()+"</value>");
				graph.append("<value xid='"+i+"'>"+chart.getTotal()+"</value>");
				logger.debug("TableName:"+chart.getTableName());
				logger.debug("Name:"+chart.getName());
				logger.debug("Total:"+chart.getTotal());
				i++;
			}
			if(i > 0){
				isOk = "ok";
				request.setAttribute("isOk", "ok");
			}
			total.append(TEMPLATE);
			data = total.toString().replace("{1}", series).replace("{2}", graph).replace("{3}", "图片");;
		}
		return "image";
	}
	
	public String picturefile() throws Exception{
		HashMap map = new HashMap();
		map.put("tableName", "TBL_PIC_FILE");
		map.put("chartType", "DAY");
		if(null != startdate && !"".equalsIgnoreCase(startdate))
			map.put("startdate", startdate);
		if(null != enddate && !"".equalsIgnoreCase(enddate))
			map.put("enddate", enddate);
		else
			map.put("enddate", DateUtils.getShortNow());
		List<Chart> list = chartService.find(map);
		if(null != list && list.size() > 0){
			StringBuffer total = new StringBuffer();
			StringBuffer series = new StringBuffer();
			StringBuffer graph = new StringBuffer();
			int i=0;
			for(Chart chart:list){
				series.append("<value xid='"+i+"'>"+chart.getName()+"</value>");
				graph.append("<value xid='"+i+"'>"+chart.getTotal()+"</value>");
				i++;
			}
			if(i > 0){
				isOk = "ok";
				request.setAttribute("isOk", "ok");
			}
			total.append(TEMPLATE);
			data = total.toString().replace("{1}", series).replace("{2}", graph).replace("{3}", "图片文件");;
		}
		return "picturefile";
	}
	
	/**
	 * 导出图片
	 * @throws Exception
	 */
	public void export2Image() throws Exception{
		// 页面flash的宽度和高度
		int width = Integer.parseInt(request.getParameter("width"));
		int height = Integer.parseInt(request.getParameter("height"));
		logger.debug(">> exportImg.width:"+width);
		logger.debug(">> exportImg.height:"+height);
		BufferedImage result = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		// 页面是将一个个像素作为参数传递进来的,所以如果图表越大,处理时间越长
		for (int y = 0; y < height; y++) {
			int x = 0;
			String[] row = request.getParameter("r" + y).split(",");
			for (int c = 0; c < row.length; c++) {
				String[] pixel = row[c].split(":"); // 十六进制颜色数组
				int repeat = pixel.length > 1 ? Integer.parseInt(pixel[1]) : 1;
				for (int l = 0; l < repeat; l++) {
					result.setRGB(x, y, Integer.parseInt(pixel[0], 16));
					x++;
				}
			}
		}
		response.setContentType("image/jpeg");
		response.addHeader("Content-Disposition",
				"attachment; filename=\"amchart.jpeg\"");
		Graphics2D g = result.createGraphics();
		// 处理图形平滑度
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g.drawImage(result, 0, 0, width, height, null);
		g.dispose();

		ServletOutputStream f = response.getOutputStream();
		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(f);
		JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(result);
		param.setQuality((float) (100 / 100.0), true);// 设置图片质量,100最大,默认70
		encoder.encode(result, param);
		ImageIO.write(result, "JPEG", response.getOutputStream());// 输出图片
		f.close();
	}
	
	/**
	 * 4个图片一个集合
	 * @throws Exception
	 */
	public String total() throws Exception{

		List<Chart> list = chartService.article(new HashMap());
		if(null != list && list.size() > 0){
			StringBuffer total = new StringBuffer();
			StringBuffer series = new StringBuffer();
			StringBuffer graph = new StringBuffer();
			int i=0;
			for(Chart chart:list){
				series.append("<value xid='"+i+"'>"+chart.getName()+"</value>");
				graph.append("<value xid='"+i+"'>"+chart.getTotal()+"</value>");
				logger.debug("Name:"+chart.getName());
				logger.debug("Total:"+chart.getTotal());
				i++;
			}
			total.append(TEMPLATE);
			data = total.toString().replace("{1}", series).replace("{2}", graph);
		}
		
		
		List<Chart> list2 = chartService.articleDoc(new HashMap());
		if(null != list2 && list2.size() > 0){
			StringBuffer total = new StringBuffer();
			StringBuffer series = new StringBuffer();
			StringBuffer graph = new StringBuffer();
			int i=0;
			for(Chart chart:list2){
				series.append("<value xid='"+i+"'>"+chart.getName()+"</value>");
				graph.append("<value xid='"+i+"'>"+chart.getTotal()+"</value>");
				logger.debug("Name:"+chart.getName());
				logger.debug("Total:"+chart.getTotal());
				i++;
			}
			total.append(TEMPLATE);
			data2 = total.toString().replace("{1}", series).replace("{2}", graph);
		}
		
		List<Chart> list3 = chartService.image(new HashMap());
		if(null != list3 && list3.size() > 0){
			StringBuffer total = new StringBuffer();
			StringBuffer series = new StringBuffer();
			StringBuffer graph = new StringBuffer();
			int i=0;
			for(Chart chart:list3){
				series.append("<value xid='"+i+"'>"+chart.getName()+"</value>");
				graph.append("<value xid='"+i+"'>"+chart.getTotal()+"</value>");
				logger.debug("Name:"+chart.getName());
				logger.debug("Total:"+chart.getTotal());
				i++;
			}
			total.append(TEMPLATE);
			data3 = total.toString().replace("{1}", series).replace("{2}", graph);
		}
		
		List<Chart> list4 = chartService.pictureFile(new HashMap());
		if(null != list4 && list4.size() > 0){
			StringBuffer total = new StringBuffer();
			StringBuffer series = new StringBuffer();
			StringBuffer graph = new StringBuffer();
			int i=0;
			for(Chart chart:list4){
				series.append("<value xid='"+i+"'>"+chart.getName()+"</value>");
				graph.append("<value xid='"+i+"'>"+chart.getTotal()+"</value>");
				logger.debug("Name:"+chart.getName());
				logger.debug("Total:"+chart.getTotal());
				i++;
			}
			total.append(TEMPLATE);
			data4 = total.toString().replace("{1}", series).replace("{2}", graph);
		}
		
		return "total";
	}

	public IChartService getChartService() {
		return chartService;
	}

	public void setChartService(IChartService chartService) {
		this.chartService = chartService;
	}

	public String isOk() {
		return isOk;
	}

	public void setOk(String isOk) {
		this.isOk = isOk;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getData2() {
		return data2;
	}

	public void setData2(String data2) {
		this.data2 = data2;
	}

	public String getData3() {
		return data3;
	}

	public void setData3(String data3) {
		this.data3 = data3;
	}

	public String getData4() {
		return data4;
	}

	public void setData4(String data4) {
		this.data4 = data4;
	}

	public String getEnddate() {
		return enddate;
	}

	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}

	public String getStartdate() {
		return startdate;
	}

	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}
	
	
	
}
