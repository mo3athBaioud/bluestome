package com.nutzd.domain;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.One;
import org.nutz.dao.entity.annotation.Prev;
import org.nutz.dao.entity.annotation.Readonly;
import org.nutz.dao.entity.annotation.SQL;
import org.nutz.dao.entity.annotation.Table;

@Table("tbl_article")
public class Article {

	@Id
	@Column("d_id")
	private int id;
	
	@Id
	@Column("d_web_id")
	private int webId;
	
	@One(target = Website.class, field = "webId")
	private Website website;
	
	@Column("d_title")
	@Name
	private String title;
	
	@Column("d_acticle_url")
	private String articleUrl;
	
	@Column("d_article_real_url")
	private String realArticleUrl;
	
	@Column("d_article_xml_url")
	private String articleXmlUrl;
	
	@Column("d_text")
	@Name
	private String text;
	
	@Column("d_createtime")
	private String createtime;
	
	@Column("d_intro")
	private String intro;
	
	@Prev( @SQL("SELECT count(*) as itotal FROM tbl_image WHERE d_article_id=@id") )
	@Readonly
	private int itotal;

	public String getArticleUrl() {
		return articleUrl;
	}

	public void setArticleUrl(String articleUrl) {
		this.articleUrl = articleUrl;
	}

	public String getArticleXmlUrl() {
		return articleXmlUrl;
	}

	public void setArticleXmlUrl(String articleXmlUrl) {
		this.articleXmlUrl = articleXmlUrl;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public String getRealArticleUrl() {
		return realArticleUrl;
	}

	public void setRealArticleUrl(String realArticleUrl) {
		this.realArticleUrl = realArticleUrl;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getWebId() {
		return webId;
	}

	public void setWebId(int webId) {
		this.webId = webId;
	}

	public Website getWebsite() {
		return website;
	}

	public void setWebsite(Website website) {
		this.website = website;
	}

	public int getItotal() {
		return itotal;
	}

	public void setItotal(int itotal) {
		this.itotal = itotal;
	}
	
	public void onFetch(){
		this.itotal++;
	}
	
}
