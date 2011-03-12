package com.ssi.common.dal.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 网站对象 tbl_web_site
 * 
 * @author bluestome
 * 
 */
public class Website extends AbstractEntity {

	private static final long serialVersionUID = 9138376143415042891L;

	private Integer id;

	private Integer type;

	private Integer parentId;

	private String url;

	private String name;

	private Date createtime;

	private Integer status;

	private int count = 0;

	private List<Article> articles = new ArrayList<Article>();

	private List<Website> children = new ArrayList<Website>();

	public Website() {
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public List<Website> getChildren() {
		return children;
	}

	public void setChildren(List<Website> children) {
		this.children = children;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public List<Article> getArticles() {
		return articles;
	}

	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

}
