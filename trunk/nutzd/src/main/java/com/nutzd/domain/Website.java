package com.nutzd.domain;

import java.util.Date;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Prev;
import org.nutz.dao.entity.annotation.Readonly;
import org.nutz.dao.entity.annotation.SQL;
import org.nutz.dao.entity.annotation.Table;
import org.nutz.dao.entity.annotation.View;


@Table("tbl_web_site")
//@View("view_website_count")
public class Website {

	@Id
	@Column("d_id")
	private int id;
	
	@Column("d_id")
	private int webId;
	
	@Column("d_parent_id")
	private int parentId = 0;
	
	@Column("d_web_type")
	private int type = 1;
	
	@Prev( @SQL("SELECT * FROM tbl_web_site WHERE d_id=@parentId") )
	private Website father;
	
	@Column("d_web_name")
	private String name;
	
	@Column("d_web_url")
	private String url;
	
	@Column("d_status")
	private int status = 1;
	
	@Column("d_remarks")
	private String remarks;
	
	@Column("d_createtime")
	private Date createtime;
	
	@Column("d_modifytime")
	private Date modifytime;
	
//	@Column("atotal")
	@Readonly
	private int atotal;

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getModifytime() {
		return modifytime;
	}

	public void setModifytime(Date modifytime) {
		this.modifytime = modifytime;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Website getFather() {
		return father;
	}

	public void setFather(Website father) {
		this.father = father;
	}

	public int getAtotal() {
		return atotal;
	}

	public void setAtotal(int atotal) {
		this.atotal = atotal;
	}

	public int getWebId() {
		return webId;
	}

	public void setWebId(int webId) {
		this.webId = webId;
	}
	
	
	
}
