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
 * 图片对象 tbl_image
 * 
 * @author bluestome
 * 
 */
@Entity
@Table(name="tbl_image")
public class Image extends AbstractEntity {

	/**
	 * 
	 */
	private static final Long serialVersionUID = 7126894035875863901L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="d_id")
	private Integer id;

	@Column(name="d_article_id")
	private Integer articleId;

	@Column(name="d_title")
	private String title;

	@Column(name="d_name")
	private String name;

	@Column(name="d_imgurl")
	private String imgUrl;

	@Column(name="d_httpurl")
	private String httpUrl;

	@Column(name="d_orderid")
	private Integer orderId;

	@Column(name="d_time")
	private String time;

	@Column(name="d_intro")
	private String intro;

	@Column(name="d_commentsuburl")
	private String commentsuburl;

	@Column(name="d_commentshowurl")
	private String commentshowurl;

	@Column(name="d_link")
	private String link;

	@Column(name="d_createtime")
	private Date createtime;

	@Column(name="d_status")
	private Integer status;

	@Column(name="d_filesize")
	private Long size;

	@Transient
	private String referer;

	//文章对象
	@Transient
	private Article article;

	//图片文件对象
	@Transient
	private PictureFile pictureFile;

	public Image() {
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

	public Long getSize() {
		return size;
	}

	public void setSize(Long size) {
		this.size = size;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public PictureFile getPictureFile() {
		return pictureFile;
	}

	public void setPictureFile(PictureFile pictureFile) {
		this.pictureFile = pictureFile;
	}

	public String getReferer() {
		return referer;
	}

	public void setReferer(String referer) {
		this.referer = referer;
	}

}
