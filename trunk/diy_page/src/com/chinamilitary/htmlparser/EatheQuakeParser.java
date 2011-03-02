package com.chinamilitary.htmlparser;

import java.io.*;
import java.util.*;

import org.htmlparser.filters.*;
import org.htmlparser.*;
import org.htmlparser.util.*;
import org.htmlparser.tags.*;

import com.chinamilitary.util.IOUtil;

public class EatheQuakeParser {
	final static String USGS_LATEST_LIST = "http://neic.usgs.gov/neis/qed/";
	final static HashMap<String, Integer> SYS_HASH = new HashMap<String, Integer>();

	static {
		SYS_HASH.put("Magnitude", 1);
		SYS_HASH.put("Date-Time", 2);
		SYS_HASH.put("Location", 3);
		SYS_HASH.put("Depth", 4);
		SYS_HASH.put("Region", 5);
		SYS_HASH.put("Distances", 6);
		SYS_HASH.put("Location Uncertainty", 7);
		SYS_HASH.put("Parameters", 8);
		SYS_HASH.put("Source", 9);
		SYS_HASH.put("Remarks", 10);
		SYS_HASH.put("Event ID", 11);
	}

	public static void main(String[] args) {
		List<EarthQuakeInfo> list = getLatestEarthQuake();
		StringBuffer buffer = new StringBuffer();
		for (EarthQuakeInfo info : list) {
			if (null == info.getUrl() || "null".equals(info.getUrl())) {
				continue;
			}
			System.out.println(" >> url:" + info.getUrl());
			getEarthQuakeDetail(info.getUrl());
			// buffer.append(info.getDate()).append("\t").append(info.getLatitude()).append("\t").append(info.getLongitude()).append("\t");
			// buffer.append(info.getDepth()).append("\t").append(info.getMagnitude()).append("\t").append(info.getUrl()).append("\r\n");
			// buffer.append("\r\n");
			break;
		}
		// IOUtil.createFileWithExt(buffer.toString(),"html");
	}

	/**
	 * 获取最新的地震列表数据
	 */
	public static List<EarthQuakeInfo> getLatestEarthQuake() {
		Parser p1 = null;
		String content = null;
		List<EarthQuakeInfo> elist = new ArrayList<EarthQuakeInfo>();
		try {
			p1 = new Parser();
			p1.setURL(USGS_LATEST_LIST);
			p1.setEncoding("UTF-8");
			NodeFilter fileter = new NodeClassFilter(TableTag.class);
			NodeList list = p1.extractAllNodesThatMatch(fileter)
					.extractAllNodesThatMatch(
							new HasAttributeFilter("class", "qed"));
			if (null != list && list.size() > 0) {
				TableTag table = (TableTag) list.elementAt(0);
				if (null != table) {
					int rows = table.getRowCount();
					for (int i = 0; i < rows; i++) {
						if (i == 0 || (i % 17 == 0)) {
							continue;
						}
						TableRow row = table.getRow(i);
						if (null != row) {
							TableColumn[] cols = row.getColumns();
							EarthQuakeInfo info = new EarthQuakeInfo();
							if (cols.length == 6) {
								int k = 0;
								for (TableColumn col : cols) {
									switch (k) {
									case 0:
										if (col.getChild(0) instanceof LinkTag) {
											LinkTag link = (LinkTag) col
													.getChild(0);
											if (null != link.getLink()) {
												info.setUrl(link.getLink());
											}
											info.setDate(link.getLinkText());
										}
										break;
									case 1:
										// 纬度
										String latitude = col.getStringText();
										info.setLatitude(latitude);
										break;
									case 2:
										// 经度
										String longitude = col.getStringText();
										info.setLongitude(longitude);
										break;
									case 3:
										// 震源深度
										String depth = col.getStringText();
										info.setDepth(Integer.valueOf(depth));
										break;
									case 4:
										// 地震级别
										String magnitude = col.getStringText();
										info.setMagnitude(Float
												.valueOf(magnitude));
										break;
									case 5:
										// 备注
										String comments = col.getStringText();
										info.setComments(comments);
										break;

									}
									k++;
								}
							}
							elist.add(info);
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != p1) {
				p1 = null;
			}
		}
		return elist;
	}

	/**
	 * 获取地震详情
	 */
	public static void getEarthQuakeDetail(String url) {
		Parser p1 = null;
		StringBuffer buffer = new StringBuffer();
		try {
			p1 = new Parser();
			p1.setURL(url);
			NodeFilter fileter = new NodeClassFilter(TableTag.class);
			NodeList list = p1.extractAllNodesThatMatch(fileter)
					.extractAllNodesThatMatch(
							new HasAttributeFilter("id", "parameters"));
			if (null != list && list.size() > 0) {
				TableTag table = (TableTag) list.elementAt(0);
				if (null != table) {
					int rows = table.getRowCount();
					for (int i = 0; i < rows; i++) {
						TableRow row = table.getRow(i);
						if (null != row) {
							TableColumn[] cols = row.getColumns();
							Node node = row.getChild(1);
							if (null != node) {
								String key = null;
								Integer id = -1;
								String value = null;
								try {
									NodeList nl = node.getChildren();
									LinkTag link = (LinkTag) nl.elementAt(1);
									if (null != link) {
										key = link.getLinkText().trim();
									} else {
										key = node.toPlainTextString().trim();
									}
									id = SYS_HASH.get(key);
									for (TableColumn col : cols) {
										value = col.toPlainTextString();
									}
									switch (id) {
									case 1:
										buffer.append(" >> " + id + "\t" + key
												+ "\t" + value);
										break;
									case 2:
										buffer.append(" >> " + id + "\t" + key
												+ "\t" + value);
										break;
									case 3:
										buffer.append(" >> " + id + "\t" + key
												+ "\t" + value);
										break;
									case 4:
										buffer.append(" >> " + id + "\t" + key
												+ "\t" + value);
										break;
									case 5:
										buffer.append(" >> " + id + "\t" + key
												+ "\t" + value);
										break;
									case 6:
										buffer.append(" >> " + id + "\t" + key
												+ "\t" + value);
										break;
									case 7:
										buffer.append(" >> " + id + "\t" + key
												+ "\t" + value);
										break;
									case 8:
										buffer.append(" >> " + id + "\t" + key
												+ "\t" + value);
										break;
									case 9:
										buffer.append(" >> " + id + "\t" + key
												+ "\t" + value);
										break;
									case 10:
										buffer.append(" >> " + id + "\t" + key
												+ "\t" + value);
										break;
									case 11:
										buffer.append(" >> " + id + "\t" + key
												+ "\t" + value);
										break;
									default:
										buffer.append(" >> " + id
												+ " no action");
										break;
									}
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
						}
					}
					buffer.append("\r\n");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != p1) {
				p1 = null;
			}
			IOUtil.createFileWithExt(buffer.toString(), "txt");
		}
	}
}

class EarthQuakeInfo {
	// 序列号
	private long id;
	// 发布时间
	private String date;
	// 纬度
	private String latitude;
	// 经度
	private String longitude;
	// 震源深度
	private Integer depth;
	// 震级
	private Float magnitude;
	// 备注
	private String comments;
	// 连接的URL
	private String url;

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Integer getDepth() {
		return depth;
	}

	public void setDepth(Integer depth) {
		this.depth = depth;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public Float getMagnitude() {
		return magnitude;
	}

	public void setMagnitude(Float magnitude) {
		this.magnitude = magnitude;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUrl() {
		return url;
	}
}

class EarthQuakeDetail extends EarthQuakeInfo {
	// 位置 纬度+经度
	private String location;
	// 区域
	private String region;
	// 距离
	private String distinces;
	// 不确定位置Location Uncertainty
	private String lu;
	// 参数
	private String parameters;
	// 源
	private String source;
	// 标记
	private String remarks;
	// 事件编号
	private String eventId;

	public String getDistinces() {
		return distinces;
	}

	public void setDistinces(String distinces) {
		this.distinces = distinces;
	}

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getLu() {
		return lu;
	}

	public void setLu(String lu) {
		this.lu = lu;
	}

	public String getParameters() {
		return parameters;
	}

	public void setParameters(String parameters) {
		this.parameters = parameters;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

}
