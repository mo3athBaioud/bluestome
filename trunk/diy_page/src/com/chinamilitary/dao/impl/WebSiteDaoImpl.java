package com.chinamilitary.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.chinamilitary.bean.WebsiteBean;
import com.chinamilitary.dao.WebSiteDao;
import com.chinamilitary.db.CommonDB;

public class WebSiteDaoImpl extends CommonDB implements WebSiteDao {
	
	private final static String INSERT_SQL = "insert into tbl_web_site (d_parent_id,d_web_url,d_web_name,d_modifytime) values (?,?,?,current_timestamp) ";
	private final static String UPDATE_SQL = "update tbl_web_site set d_parent_id = ?,d_web_url=?,d_web_name=?,d_modifytime=current_timestamp where d_id = ? ";
	private final static String QUERY_SQL = "select * from tbl_web_site";
	private final static String COUNT_SQL = "select count(*) from tbl_web_site where d_status = 1";

	public WebSiteDaoImpl() throws Exception {
		super();
	}

	public int getCount() throws Exception {
		int count = 0;
		pstmt = conn.prepareStatement(COUNT_SQL);
		rs = pstmt.executeQuery();
		while(rs.next()){
			count = rs.getInt(1);
		}
		releaseLink();
		return count;
	}

	public int getCount(String sql) throws Exception {
		int count = 0;
		pstmt = conn.prepareStatement(sql);
		rs = pstmt.executeQuery();
		while(rs.next()){
			count = rs.getInt(1);
		}
		releaseLink();
		return count;
	}
	/**
	 * 添加网站记录
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	public boolean insert(WebsiteBean bean) throws Exception{
		boolean b = true;
		int count = -1;
		if(checkExists(bean.getUrl(),bean.getName())){
			return false;
		}
		pstmt = conn.prepareStatement(INSERT_SQL);
		pstmt.setInt(1, bean.getParentId());
		pstmt.setString(2, bean.getUrl());
		pstmt.setString(3, bean.getName());
		count = pstmt.executeUpdate();
		if(count == 1){
			b = true;
		}
		releaseSLink();
		return b;
	}
	
	private boolean checkExists(String url,String name) throws Exception{
		boolean b = false;
		String sql = "select count(*) from tbl_web_site where d_web_url = '"+url+"' and d_web_name='"+name+"' and d_status = 1";
		if(getCount(sql) > 0){
			b = true;
		}
		return b;
	}
	
	/**
	 * 修改网站记录
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	public boolean update(WebsiteBean bean) throws Exception{
		boolean b = false;
		pstmt = conn.prepareStatement(UPDATE_SQL);
		pstmt.setInt(1, bean.getParentId());
		pstmt.setString(2,bean.getUrl());
		pstmt.setString(3, bean.getName());
		pstmt.setInt(4, bean.getId());
		int count = pstmt.executeUpdate();
		if(count > 0){
			b = true;
		}
		releaseSLink();
		return b;
	}
	/**
	 * 查找所有记录
	 * @return
	 * @throws Exception
	 */
	public List<WebsiteBean> findAll() throws Exception{
		List<WebsiteBean> list = new ArrayList<WebsiteBean>();
		WebsiteBean bean = null;
		pstmt = conn.prepareStatement(QUERY_SQL+" where d_status = ?");
		pstmt.setInt(1, 1);
		rs = pstmt.executeQuery();
		while(rs.next()){
			bean = new WebsiteBean();
			bean.setId(rs.getInt("d_id"));
			bean.setParentId(rs.getInt("d_parent_id"));
			bean.setName(rs.getString("d_web_name"));
			bean.setUrl(rs.getString("d_web_url"));
			bean.setCreatetme(rs.getDate("d_createtime"));
			bean.setStatus(rs.getInt("d_status"));
			bean.setModifytime(rs.getDate("d_modifytime"));
			list.add(bean);
		}
		releaseLink();
		return list;
	}
	
	/**
	 * 根据ID查找记录
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public WebsiteBean findById(Integer id) throws Exception{
		WebsiteBean bean = null;
		pstmt = conn.prepareStatement(QUERY_SQL+" where d_id = ? and d_status = ?");
		pstmt.setInt(1, id);
		pstmt.setInt(2, 1);
		rs = pstmt.executeQuery();
		while(rs.next()){
			bean = new WebsiteBean();
			bean.setId(rs.getInt("d_id"));
			bean.setName(rs.getString("d_web_name"));
			bean.setParentId(rs.getInt("d_parent_id"));
			bean.setUrl(rs.getString("d_web_url"));
			bean.setCreatetme(rs.getDate("d_createtime"));
			bean.setStatus(rs.getInt("d_status"));
			bean.setModifytime(rs.getDate("d_modifytime"));
		}
		releaseLink();
		return bean;
	}
	
	/**
	 * 根据ID查找记录
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public List<WebsiteBean> findByParentId(Integer id) throws Exception{
		List<WebsiteBean> list = new ArrayList<WebsiteBean>();
		WebsiteBean bean = null;
		pstmt = conn.prepareStatement(QUERY_SQL+" where d_status = ? and d_parent_id = ? order by d_id");
		pstmt.setInt(1, 1);
		pstmt.setInt(2, id);
		rs = pstmt.executeQuery();
		while(rs.next()){
			bean = new WebsiteBean();
			bean.setId(rs.getInt("d_id"));
			bean.setParentId(rs.getInt("d_parent_id"));
			bean.setName(rs.getString("d_web_name"));
			bean.setUrl(rs.getString("d_web_url"));
			bean.setCreatetme(rs.getDate("d_createtime"));
			bean.setStatus(rs.getInt("d_status"));
			bean.setModifytime(rs.getDate("d_modifytime"));
			list.add(bean);
		}
		releaseLink();
		return list;
	}
	
	public static void main(String args[]){
		WebSiteDao dao=null;
		try{
			dao = new WebSiteDaoImpl();
//			System.out.println("dao.getCount():"+dao.getCount());
//			System.out.println("dao.findAll().size():"+dao.findAll().size());
			List<WebsiteBean> list = dao.findByParentId(36);
			for(WebsiteBean bean:list){
//				System.out.println("WEB_ID:"+bean.getId());
				System.out.println("WEB_名称:"+bean.getName());
				String url = bean.getUrl();
				Pattern p = Pattern.compile("[^0-9]."); 
				Matcher m = p.matcher(url.substring(url.lastIndexOf("/")+1, url.lastIndexOf("."))); 
				boolean b = m.matches(); 
				if(b){
					System.out.println("WEB_链接:"+url);
				}
				System.out.println("\n");
			}
//			System.out.println("dao.findByParentId(5).size():"+dao.findByParentId(5).size());
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
