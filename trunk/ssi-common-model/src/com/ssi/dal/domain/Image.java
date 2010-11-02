package com.ssi.dal.domain;

import java.util.Date;

public class Image implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7126894035875863901L;
	/**
	 * 
	 */
	private Integer id;
	private Integer articleId;
	private String title;
	private String name;
	private String imgUrl;
	private String httpUrl;
	private Integer orderId;
	private String time;
	private String intro;
	private String commentsuburl;
	private String commentshowurl;
	private String link;
	private Date createtime;
	private int status;
	private long size;
	
	private Article article;
	private PictureFile pictureFile;
	public Image(){
	}

	public String getCommentshowurl() {
		return commentshowurl;
	}

	public void setCommentshowurl(String commentshowurl) {
		this.commentshowurl = commentshowurl;
	}

	public String getCommentsuburl() {
		return commentsuburl;
	}

	public void setCommentsuburl(String commentsuburl) {
		this.commentsuburl = commentsuburl;
	}

	public String getHttpUrl() {
		return httpUrl;
	}

	public void setHttpUrl(String httpUrl) {
		this.httpUrl = httpUrl;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public Integer getArticleId() {
		return articleId;
	}

	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public PictureFile getPictureFile() {
		return pictureFile;
	}

	public void setPictureFile(PictureFile pictureFile) {
		this.pictureFile = pictureFile;
	}
	
	
	
}
