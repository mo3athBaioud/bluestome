package com.ssi.common.dal.domain;

import java.util.Date;

/**
 * 文章文档对象 tbl_article_doc
 * 
 * @author bluestome
 * 
 */
public class ArticleDoc extends AbstractEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6192433185487241025L;

	private Integer id;

	private Integer webId;

	private String title;

	private String url;

	private String author;

	private Integer grade = 5;

	private String tag;

	private String content;

	private Integer status = 1;

	private String publishTime;

	private Date createTime = new Date();

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
