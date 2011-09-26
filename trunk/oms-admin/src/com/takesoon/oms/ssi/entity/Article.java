package com.takesoon.oms.ssi.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 
 * 文章对象 tbl_article
 * 
 * @author bluestome
 * 
 */
@Entity
@Table(name="tbl_article")
public class Article extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="d_id")
	private Integer id;

	@Column(name="d_web_id")
	private Integer webId;

	@Column(name="d_acticle_url")
	private String articleUrl;

	@Column(name="d_article_real_url")
	private String acticleRealUrl;

	@Column(name="d_article_xml_url")
	private String acticleXmlUrl;

	@Column(name="d_title")
	private String title;

	@Column(name="d_text")
	private String text;

	@Column(name="d_intro")
	private String intro;

	@Column(name="d_createtime")
	private Date createTime = new Date();

	// 网站对象
	@Transient
	@OneToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "webId", referencedColumnName = "id")
	private Website website;

	public Article() {
	}

	public Article(Integer webId, String articleUrl) {
		this.webId = webId;
		this.articleUrl = articleUrl;
	}

	public String getArticleUrl() {
		return articleUrl;
	}

	public void setArticleUrl(String articleUrl) {
		this.articleUrl = articleUrl;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Integer getWebId() {
		return webId;
	}

	public void setWebId(Integer webId) {
		this.webId = webId;
	}

	public String getActicleRealUrl() {
		return acticleRealUrl;
	}

	public void setActicleRealUrl(String acticleRealUrl) {
		this.acticleRealUrl = acticleRealUrl;
	}

	public String getActicleXmlUrl() {
		return acticleXmlUrl;
	}

	public void setActicleXmlUrl(String acticleXmlUrl) {
		this.acticleXmlUrl = acticleXmlUrl;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public Website getWebsite() {
		return website;
	}

	public void setWebsite(Website website) {
		this.website = website;
	}

}
