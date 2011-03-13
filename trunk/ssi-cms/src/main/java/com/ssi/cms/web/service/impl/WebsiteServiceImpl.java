package com.ssi.cms.web.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ssi.cms.web.service.IWebsiteService;
import com.ssi.common.dal.dao.IWebsiteDAO;
import com.ssi.common.dal.domain.Website;

public class WebsiteServiceImpl implements IWebsiteService {

	private static Log logger = LogFactory.getLog(WebsiteServiceImpl.class);
	private IWebsiteDAO websiteDao;
	
	public List<Website> find(HashMap map) {
		return websiteDao.find(map);
	}

	public List<Website> findByFatherId(Integer fatherId) {
		return websiteDao.findByFatherId(fatherId);
	}

	public Website findById(Integer id) {
		return websiteDao.findById(id);
	}

	public int getCount(HashMap map) {
		return websiteDao.getCount(map);
	}

	public IWebsiteDAO getWebsiteDao() {
		return websiteDao;
	}

	public void setWebsiteDao(IWebsiteDAO websiteDao) {
		this.websiteDao = websiteDao;
	}

	public List<Website> findAll() throws Exception {
		return websiteDao.find(new HashMap());
	}

	public List<Website> findByParentId(Integer id) throws Exception {
		HashMap map = new HashMap();
		map.put("parentId", id);
		return websiteDao.find(map);
	}

	public Website findWebById(Integer id) throws Exception {
		return websiteDao.findById(id);
	}

	public int getCount(String colName, String value) {
		HashMap map = new HashMap();
		if(null != colName && !"".equals("")){
			map.put(colName, value);
		}else{
			map.put("name", value);
		}
		return websiteDao.getCount(map);
	}

	public List<Website> getPageList(String colName, String value, Integer startIndex, Integer pageSize, boolean asc) throws Exception {
		HashMap map = new HashMap();
		if(null == colName || colName.equalsIgnoreCase("")){
			map.put("name", value);
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
		List<Website>  list = websiteDao.find(map);
		return list;
	}

	public List<Website> getRootWebSite() throws Exception {
		HashMap map = new HashMap();
		map.put("parentId", 0);
		return websiteDao.find(map);
	}

	public List<Website> getRootWebSite(int webType) throws Exception {
		HashMap map = new HashMap();
		map.put("status", 1);
		map.put("type", webType);
		map.put("parentId", 0);
		return websiteDao.find(map);
	}

	public String tree(List<Website> list) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("[\n");
		int i=0;
		for(Website bean:list){
			sb.append("\t\t{\n");
			sb.append("\t\t\"text\":\""+bean.getName()+"\",\n");
			sb.append("\t\t\"id\":\"" + bean.getId()+"\"");
			if(getCount("parentId", String.valueOf(bean.getId())) > 0){
//			if(bean.getChildren().size() > 0){
//				logger.debug("\t"+bean.getId()+"："+bean.getName()+"有下级菜单");
				sb.append(",\n");
//				sb.append("\"children:\"");
				sb.append("\t\t\t\"icon\":\"images/world_link.png\",\n");
				sb.append("\t\t\"leaf\":false\n");
//				sb.append(tree(bean.getChildren()));
			}else{
				sb.append(",\n");
				sb.append("\t\t\"leaf\":true,\n");
//				sb.append("\"icon\":\"/resources/images/default/tree/drop-yes.gif\",");
				sb.append("\"href\":\""+bean.getUrl()+"\""); //request.getContextPath()+"/content.cgi?webId="+bean.getId()
				sb.append("\t\t\t\"icon\":\"images/html.png\"\n");
			}
			sb.append("}");
			if(i<list.size()-1){
				sb.append(",\n");
			}
			i++;
		}
		sb.append("\n]");
		return sb.toString();
	}

	public String tree2(List<Website> list, HttpServletRequest request) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("[\n");
		int i=0;
		for(Website bean:list){
			sb.append("\t\t{\n");
			sb.append("\t\t\"text\":\t\""+bean.getName()+"\",\n");
			sb.append("\t\t\"id\":\"" + bean.getId()+"\",\n");
			if(null != bean.getChildren() && bean.getChildren().size() > 0){
//				sb.append("\t\t\t\"href\":\""+request.getContextPath()+"website/toTree.cgi?id="+bean.getParentId()+"\",\n");
//				sb.append("\t\t\t\"href\":\""+request.getContextPath()+"/website/sub.cgi?id="+bean.getId()+"\",\n");
//				sb.append("\t\t\t\"hrefTarget\":\"mainFrame\",\n");
				sb.append("\t\t\t\"icon\":\""+request.getContextPath()+"/images/world_link.png\",\n");
				sb.append("\t\t\t\"children\":");
				sb.append(tree2(bean.getChildren(),request));
			}else{
				sb.append("\t\t\t\"icon\":\""+request.getContextPath()+"/images/html.png\",\n");
				sb.append("\t\t\t\"leaf\":true,\n");
				switch(bean.getType()){
					case 1:
						sb.append("\t\t\t\"href\":\""+request.getContextPath()+"/pages/articles/article.jsp?id="+bean.getId()+"\",\n");
						break;
					case 2:
						sb.append("\t\t\t\"href\":\""+request.getContextPath()+"/pages/articlesdoc/articledoc.jsp?id="+bean.getId()+"\",\n");
						break;
					default:
						sb.append("\t\t\t\"href\":\""+request.getContextPath()+"/pages/articles/article.jsp?id="+bean.getId()+"\",\n");
						break;
				}
				sb.append("\t\t\t\"hrefTarget\":\"mainFrame\"");
			}
			sb.append("}");
			if(i<list.size()-1){
				sb.append(",\n");
			}
			i++;
		}
		sb.append("\n]");
		return sb.toString();
	}
	
	/**
	 * 更新记录
	 * @param website
	 * @return
	 */
	public boolean update(Website website){
		boolean b = false;
		int result = websiteDao.update(website);
		if(result > 0){
			return true;
		}
		return b;
	}

	/**
	 * 添加记录
	 * @param website
	 * @return
	 */
	public boolean insert(Website website){
		boolean b = false;
		if(chkUnquie(website)){
			return b;
		}
		int result = websiteDao.insert(website);
		if(result > 0){
			return true;
		}
		return b;
	}
	
	/**
	 * 检查是否存在相同的网址
	 * @param website
	 * @return true:已存在  false:不存在
	 */
	private boolean chkUnquie(Website website){
		HashMap map = new HashMap();
		map.put("url", website.getUrl());
		if(getCount(map) > 0){
			return true;
		}
		return false;
	}

}
