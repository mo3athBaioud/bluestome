package com.takesoon.oms.ssi.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 文章文档对象 tbl_article_doc
 * 
 * @author bluestome
 * 
 */
@Entity
@Table(name="tbl_article_doc")
public class ArticleDoc extends AbstractEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6192433185487241025L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="d_id")
	private Integer id;

	@Column(name="d_web_id")
	private Integer webId;

	@Column(name="d_title")
	private String title;

	@Column(name="d_url")
	private String url;

	@Column(name="d_author")
	private String author;

	@Column(name="d_grade")
	private Integer grade = 5;

	@Column(name="d_tag")
	private String tag;

	@Transient
	private String content;

	@Column(name="d_status")
	private Integer status = 1;

	@Column(name="d_publish_time")
	private String publishTime;

	@Column(name="d_createtime")
	private Date createTime = new Date();

	@Column(name="d_modifytime")
	private Date modifyTime = new Date();

	public ArticleDoc() {
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
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

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getWebId() {
		return webId;
	}

	public void setWebId(Integer webId) {
		this.webId = webId;
	}

	public Integer getGrade() {
		return grade;
	}

	public void setGrade(Integer grade) {
		this.grade = grade;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(String publishTime) {
		this.publishTime = publishTime;
	}

}
