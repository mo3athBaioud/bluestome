package com.takesoon.oms.ssi.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 网站对象 tbl_web_site
 * @author bluestome
 * 
 */
@Entity
@Table(name="tbl_web_site")
public class Website extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="d_id")
	private Integer id;

	@Column(name="d_web_type")
	private Integer type;

	@Column(name="d_parent_id")
	private Integer parentId;

	@Column(name="d_web_url")
	private String url;

	@Column(name="d_web_name")
	private String name;

	@Column(name="d_createtime")
	private Date createtime = new Date();
	
	@Column(name="d_modifytime")
	private Date modifytime;

	@Column(name="d_status")
	private Integer status;

	@Transient
	private int count = 0;
	
	@Column(name="d_remarks")
	private String remarks;
	
	@Transient
	private List<Article> articles = new ArrayList<Article>();

	@Transient
	private List<Website> children = new ArrayList<Website>();
	
	@Transient
	private int childSize = 0;
	
	@Transient
	private Website father;

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

	public Date getModifytime() {
		return modifytime;
	}

	public void setModifytime(Date modifytime) {
		this.modifytime = modifytime;
	}

	public Website getFather() {
		return father;
	}

	public void setFather(Website father) {
		this.father = father;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public int getChildSize() {
		return childSize;
	}

	public void setChildSize(int childSize) {
		this.childSize = childSize;
	}
	
	

}
