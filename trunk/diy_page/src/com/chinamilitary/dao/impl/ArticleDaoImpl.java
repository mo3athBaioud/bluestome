package com.chinamilitary.dao.impl;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.chinamilitary.bean.Article;
import com.chinamilitary.bean.PicfileBean;
import com.chinamilitary.dao.ArticleDao;
import com.chinamilitary.db.CommonDB;
import com.chinamilitary.util.StringUtils;

public class ArticleDaoImpl extends CommonDB implements ArticleDao {
	
	private final static String INSERT_SQL = "insert into tbl_article (d_web_id,d_acticle_url,d_article_real_url,d_article_xml_url,d_title,d_text,d_intro) values (?,?,?,?,?,?,?)";
	private final static String UPDATE_SQL = "update tbl_article set d_web_id = ?,d_acticle_url = ?,d_article_real_url = ?,d_article_xml_url = ?,d_title = ?,d_text = ?,d_intro = ? where d_id = ?";
	private final static String QUERY_SQL = "select * from tbl_article ";
	private final static String COUNT_SQL = "select count(*) from tbl_article ";

	public ArticleDaoImpl() throws Exception {
		super();
		// TODO Auto-generated constructor stub
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
	
	public synchronized int getCount(String url,String title) throws Exception {
		int count = 0;
//		System.out.println("url:"+(url == null ? "注意，异常" : url));
//		System.out.println("title:"+(title == null ? "注意，异常" : title));
		pstmt = conn.prepareStatement(COUNT_SQL+" where d_acticle_url = ?");// and d_title = ?
		pstmt.setString(1,url);
//		pstmt.setString(2,title);
		rs = pstmt.executeQuery();
		while(rs.next()){
			count = rs.getInt(1);
		}
		pstmt.close();
		rs.close();
		return count;
	}
	
	public synchronized int getCountByURL(String url,Integer webId) throws Exception{
		int count = 0;
		pstmt = conn.prepareStatement(COUNT_SQL+" where d_acticle_url = ? and d_web_id = ?");// and d_title = ?
		pstmt.setString(1,url);
		pstmt.setInt(2, webId);
		rs = pstmt.executeQuery();
		while(rs.next()){
			count = rs.getInt(1);
		}
		pstmt.close();
		rs.close();
		return count;
	}
	
	public synchronized int getCountByURL(String url) throws Exception{
		int count = 0;
		pstmt = conn.prepareStatement(COUNT_SQL+" where d_acticle_url = ?");// and d_title = ?
		pstmt.setString(1,url);
		rs = pstmt.executeQuery();
		while(rs.next()){
			count = rs.getInt(1);
		}
		pstmt.close();
		rs.close();
		return count;
	}
	
	public synchronized int getCountByTitle(String title,Integer webId) throws Exception {
		int count = 0;
		pstmt = conn.prepareStatement(COUNT_SQL+" where REPLACE(d_title, '：', ':') = ? and d_web_id = ?");// and d_title = ?
		pstmt.setString(1,title);
		pstmt.setInt(2, webId);
		rs = pstmt.executeQuery();
		while(rs.next()){
			count = rs.getInt(1);
		}
		pstmt.close();
		rs.close();
		return count;
	}
	
	public synchronized int getCountByTitle(String title) throws Exception {
		int count = 0;
		pstmt = conn.prepareStatement(COUNT_SQL+" where REPLACE(d_title, '：', ':') = ?");// and d_title = ?
		pstmt.setString(1,title);
		rs = pstmt.executeQuery();
		while(rs.next()){
			count = rs.getInt(1);
		}
		pstmt.close();
		rs.close();
		return count;
	}
	
	/**
	 * 添加文章
	 * @param article
	 * @return
	 * @throws Exception
	 */
	public int insert(Article bean) throws Exception{
		int key = -1;
		if(checkExists(bean.getTitle(),bean.getWebId())){
			System.err.println("已存在相同标题："+bean.getTitle());
			return key;
		}
		
		if(getCountByURL(bean.getArticleUrl()) > 0){
			System.err.println("已存在相同URL："+bean.getTitle());
			return key;
		}
		
		int urlResult = getCountByURL(bean.getArticleUrl());
//		System.out.println(bean.getArticleUrl()+",总数:"+urlResult);
		if(urlResult > 0){ //,bean.getWebId()
			return key;
		}
		
		pstmt = conn.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS);
		pstmt.setInt(1, bean.getWebId());
		pstmt.setString(2, bean.getArticleUrl());
		pstmt.setString(3, (bean.getActicleRealUrl() == null ? "" : bean.getActicleRealUrl()));
		pstmt.setString(4, (bean.getActicleXmlUrl() == null ? "" : bean.getActicleXmlUrl()));
		pstmt.setString(5, (bean.getTitle() == null ? "" : bean.getTitle()));
		pstmt.setString(6, (bean.getText() == null ? "NED":bean.getText()));
		pstmt.setString(7, bean.getIntro() == null ? "" : bean.getIntro());
		if(pstmt.executeUpdate()  == 1){
			rs = pstmt.getGeneratedKeys();
			while(rs.next()){
				key = rs.getInt(1);
			}
		}
		if(pstmt != null)
			pstmt.close();
		if(rs != null)
			rs.close();
//		releaseSLink();
		return key;
	}
	
	boolean checkExists(String url,String title) throws Exception{ //,Integer webId
		boolean b = false;
		if(url == null && url.equalsIgnoreCase("")){
			return b;
		}
		if(title == null && title.equalsIgnoreCase("")){
			return b;
		}
//		String sql = "select count(*) from tbl_article where d_acticle_url = '"+url+"' and d_title = '"+title+"'"; //d_web_id = "+webId+" and 
		if(getCount(url,title) > 0){
			b = true;
		}
		return b;
	}
	
	boolean checkExists(String title,Integer webId) throws Exception{
		boolean b = false;
		if(null == title || title.equalsIgnoreCase("")){
			return b;
		}
		if(getCountByTitle(title,webId) > 0){
			b = true;
		}
		return b;
	}
	
	boolean checkExists(String title) throws Exception{
		boolean b = false;
		if(null == title || title.equalsIgnoreCase("")){
			return b;
		}
		if(getCountByTitle(title) > 0){
			b = true;
		}
		return b;
	}
	
	/**
	 * 查找所有文章
	 * @return
	 * @throws Exception
	 */
	public List<Article> findAll() throws Exception{
		List<Article> list = new ArrayList<Article>();
		Article bean = null;
		pstmt = conn.prepareStatement(QUERY_SQL);
		rs = pstmt.executeQuery();
		while(rs.next()){
			bean = new Article();
			bean.setId(rs.getInt("d_id"));
			bean.setWebId(rs.getInt("d_web_id"));
			bean.setArticleUrl(rs.getString("d_acticle_url"));
			bean.setActicleRealUrl(rs.getString("d_article_real_url") == null ? "" : rs.getString("d_article_real_url"));
			bean.setActicleXmlUrl(rs.getString("d_article_xml_url") == null ? "" : rs.getString("d_article_xml_url"));
			bean.setText(rs.getString("d_text"));
			bean.setTitle(rs.getString("d_title"));
			bean.setIntro(rs.getString("d_intro"));
			bean.setCreateTime(rs.getDate("d_createtime"));
			list.add(bean);
		}
		releaseLink();
		return list;
	}
	
	/**
	 * 查找所有文章地址
	 * @return
	 * @throws Exception
	 */
	public List<String> findAllArticleURL(int parentWebId) throws Exception{
		List<String> list = new ArrayList<String>();
		pstmt = conn.prepareStatement("select distinct d_acticle_url from tbl_article where d_web_id in (select distinct d_id from tbl_web_site where d_parent_id = "+parentWebId+")");
		rs = pstmt.executeQuery();
		String url = "none";
		while(rs.next()){
			url = rs.getString("d_acticle_url");
			list.add(url);
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
	public Article findById(Integer id) throws Exception{
		Article bean = null;
		pstmt = conn.prepareStatement(QUERY_SQL+" where d_id=?");
		pstmt.setInt(1, id);
		rs = pstmt.executeQuery();
		while(rs.next()){
			bean = new Article();
			bean.setId(rs.getInt("d_id"));
			bean.setWebId(rs.getInt("d_web_id"));
			bean.setArticleUrl(rs.getString("d_acticle_url"));
			bean.setActicleRealUrl(rs.getString("d_article_real_url"));
			bean.setActicleXmlUrl(rs.getString("d_article_xml_url"));
			bean.setText(rs.getString("d_text"));
			bean.setTitle(rs.getString("d_title"));
			bean.setIntro(rs.getString("d_intro"));
			bean.setCreateTime(rs.getDate("d_createtime"));
		}
		releaseLink();
		return bean;
	}
	
	public List<Article> findShowImg(Integer webId,Integer id) throws Exception{
		List<Article> list = new ArrayList<Article>();
		Article bean = null;
		pstmt = conn.prepareStatement(QUERY_SQL+" where d_web_id > ? and d_text = ? and d_id > ?");
		pstmt.setInt(1, webId);
		pstmt.setString(2, "NED");
		pstmt.setInt(3, id);
		rs = pstmt.executeQuery();
		while(rs.next()){
			bean = new Article();
			bean.setId(rs.getInt("d_id"));
			bean.setWebId(rs.getInt("d_web_id"));
			bean.setArticleUrl(rs.getString("d_acticle_url"));
			bean.setActicleRealUrl(rs.getString("d_article_real_url"));
			bean.setActicleXmlUrl(rs.getString("d_article_xml_url"));
			bean.setText(rs.getString("d_text"));
			bean.setTitle(rs.getString("d_title"));
			bean.setIntro(rs.getString("d_intro"));
			bean.setCreateTime(rs.getDate("d_createtime"));
			list.add(bean);
		}
		if(pstmt !=null)
			pstmt.close();
		if(rs !=null)
			rs.close();
		return list;
	}
	
	public List<Article> findShowImg(Integer webId) throws Exception{
		List<Article> list = new ArrayList<Article>();
		Article bean = null;
		pstmt = conn.prepareStatement(QUERY_SQL+" where d_web_id = ? and d_text = ?");
		pstmt.setInt(1, webId);
		pstmt.setString(2, "NED");
		rs = pstmt.executeQuery();
		while(rs.next()){
			bean = new Article();
			bean.setId(rs.getInt("d_id"));
			bean.setWebId(rs.getInt("d_web_id"));
			bean.setArticleUrl(rs.getString("d_acticle_url"));
			bean.setActicleRealUrl(rs.getString("d_article_real_url"));
			bean.setActicleXmlUrl(rs.getString("d_article_xml_url"));
			bean.setText(rs.getString("d_text"));
			bean.setTitle(rs.getString("d_title"));
			bean.setIntro(rs.getString("d_intro"));
			bean.setCreateTime(rs.getDate("d_createtime"));
			list.add(bean);
		}
		if(pstmt !=null)
			pstmt.close();
		if(rs !=null)
			rs.close();
		return list;
	}
	
	/**
	 * 
	 * @param webId
	 * @param text
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public List<Article> findShowImg(Integer webId,String text,Integer id) throws Exception{
		List<Article> list = new ArrayList<Article>();
		Article bean = null;
		pstmt = conn.prepareStatement(QUERY_SQL+" where d_web_id = ? and d_text = ? and d_id > ?");
		pstmt.setInt(1, webId);
		pstmt.setString(2, text);
		pstmt.setInt(3, id);
		rs = pstmt.executeQuery();
		while(rs.next()){
			bean = new Article();
			bean.setId(rs.getInt("d_id"));
			bean.setWebId(rs.getInt("d_web_id"));
			bean.setArticleUrl(rs.getString("d_acticle_url"));
			bean.setActicleRealUrl(rs.getString("d_article_real_url"));
			bean.setActicleXmlUrl(rs.getString("d_article_xml_url"));
			bean.setText(rs.getString("d_text"));
			bean.setTitle(rs.getString("d_title"));
			bean.setIntro(rs.getString("d_intro"));
			bean.setCreateTime(rs.getDate("d_createtime"));
			list.add(bean);
		}
		if(pstmt !=null)
			pstmt.close();
		if(rs !=null)
			rs.close();
		return list;
	}
	
	/**
	 * 更新记录
	 * @param article
	 * @return
	 * @throws Exception
	 */
	public boolean update(Article bean) throws Exception{
		boolean b = false;
		try{
			pstmt = conn.prepareStatement(UPDATE_SQL);
			pstmt.setInt(1, bean.getWebId());
			pstmt.setString(2, bean.getArticleUrl());
			pstmt.setString(3, bean.getActicleRealUrl() == null ? "" : bean.getActicleRealUrl());
			pstmt.setString(4, bean.getActicleXmlUrl() == null ? "": bean.getActicleXmlUrl());
			pstmt.setString(5, bean.getTitle() == null ? "" : bean.getTitle());
			pstmt.setString(6, bean.getText() == null ? "" : bean.getText());
			pstmt.setString(7, bean.getIntro() == null ? "" : bean.getIntro());
			pstmt.setInt(8, bean.getId());
			int key  = pstmt.executeUpdate();
			if(key == 1){
				b = true;
			}
		}catch(Exception e){
			return b;
		}finally{
			if(null != pstmt)
				pstmt.close();
		}
		return b;
	}

	/**
	 * 根据WEBID查找记录
	 * @param webId
	 * @return
	 */
	public List<Article> findByWebId(Integer webId) throws Exception {
		List<Article> list = new ArrayList<Article>();
		Article bean = null;
		try{
			pstmt = conn.prepareStatement(QUERY_SQL+" where d_web_id = ?"); // and d_text = ?
			pstmt.setInt(1, webId);
//			pstmt.setString(2, "0");
			rs = pstmt.executeQuery();
			while(rs.next()){
				bean = new Article();
				bean.setId(rs.getInt("d_id"));
				bean.setWebId(rs.getInt("d_web_id"));
				bean.setArticleUrl(rs.getString("d_acticle_url"));
				bean.setActicleRealUrl(rs.getString("d_article_real_url"));
				bean.setActicleXmlUrl(rs.getString("d_article_xml_url"));
				bean.setText(rs.getString("d_text"));
				bean.setTitle(rs.getString("d_title"));
				bean.setIntro(rs.getString("d_intro"));
				bean.setCreateTime(rs.getDate("d_createtime"));
				list.add(bean);
			}
		}catch(Exception e){
			return list;
		}finally{
			if(pstmt !=null)
				pstmt.close();
			if(rs !=null)
				rs.close();
		}
		return list;
	}

	/**
	 * 根据WEBID查找记录
	 * @param webId
	 * @return
	 */
	public List<Article> findByWebId(Integer webId,String text) throws Exception {
		List<Article> list = new ArrayList<Article>();
		Article bean = null;
		try{
			pstmt = conn.prepareStatement(QUERY_SQL+" where d_web_id = ? and d_text = ? order by d_id");
			pstmt.setInt(1, webId);
			pstmt.setString(2, text);
			rs = pstmt.executeQuery();
			while(rs.next()){
				bean = new Article();
				bean.setId(rs.getInt("d_id"));
				bean.setWebId(rs.getInt("d_web_id"));
				bean.setArticleUrl(rs.getString("d_acticle_url"));
				bean.setActicleRealUrl(rs.getString("d_article_real_url"));
				bean.setActicleXmlUrl(rs.getString("d_article_xml_url"));
				bean.setText(rs.getString("d_text"));
				bean.setTitle(rs.getString("d_title"));
				bean.setIntro(rs.getString("d_intro"));
				bean.setCreateTime(rs.getDate("d_createtime"));
				list.add(bean);
			}
		}catch(Exception e){
			e.printStackTrace();
			return list;
		}finally{
			if(pstmt !=null)
				pstmt.close();
			if(rs !=null)
				rs.close();
		}
		return list;
	}

}
