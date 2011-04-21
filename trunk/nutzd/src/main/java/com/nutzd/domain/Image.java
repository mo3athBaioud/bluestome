package com.nutzd.domain;

import java.util.Date;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.One;
import org.nutz.dao.entity.annotation.Table;

@Table("tbl_image")
public class Image {

	@Id
	@Column("d_id")
	private int id;
	
	@Column("d_article_id")
	private int articleId;
	
	@One(target = Article.class, field = "articleId")
	private Article article;
	
	@Column("d_name")
	@Name
	private String name;
	
	@Column("d_title")
	@Name
	private String title;
	
	@Column("d_imgurl")
	private String imgUrl;
	
	@Column("d_httpurl")
	private String httpUrl;
	
	@Column("d_orderid")
	private int orderId;
	
	@Column("d_time")
	private String time;
	
	@Column("d_intro")
	private String intro;
	
	@Column("d_commentsuburl")
	private String commentSubUrl;
	
	@Column("d_commentshowurl")
	private String commentShowUrl;
	
	@Column("d_link")
	@Name
	private String link;
	
	@Column("d_createtime")
	private Date createtime;
	
	@Column("d_status")
	private int status;
	
	@Column("d_filesize")
	private int fileSize;

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public int getArticleId() {
		return articleId;
	}

	public void setArticleId(int articleId) {
		this.articleId = articleId;
	}

	public String getCommentShowUrl() {
		return commentShowUrl;
	}

	public void setCommentShowUrl(String commentShowUrl) {
		this.commentShowUrl = commentShowUrl;
	}

	public String getCommentSubUrl() {
		return commentSubUrl;
	}

	public void setCommentSubUrl(String commentSubUrl) {
		this.commentSubUrl = commentSubUrl;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public int getFileSize() {
		return fileSize;
	}

	public void setFileSize(int fileSize) {
		this.fileSize = fileSize;
	}

	public String getHttpUrl() {
		return httpUrl;
	}

	public void setHttpUrl(String httpUrl) {
		this.httpUrl = httpUrl;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	
}
