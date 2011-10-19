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
 * 图片文件对象 tbl_pic_file
 * 
 * @author bluestome
 * 
 */
@Entity
@Table(name="tbl_pic_file")
public class PictureFile extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7664072343386712672L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="d_id")
	private Integer id;

	@Column(name="d_article_id")
	private Integer articleId;

	@Column(name="d_image_id")
	private Integer imageId;

	@Column(name="d_file_url")
	private String url;

	@Column(name="d_file_title")
	private String title;

	@Column(name="d_file_name")
	private String name;

	@Column(name="d_file_small_name")
	private String smallName;

	@Transient
	private String desc;

	@Column(name="d_createtime")
	private Date createTime;

	@Column(name="d_status")
	private Integer status;

	// 文章对象
	@Transient
	private Article article;

	// 图片对象
	@Transient
	private Image image;

	public PictureFile() {
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getArticleId() {
		return articleId;
	}

	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}

	public Integer getImageId() {
		return imageId;
	}

	public void setImageId(Integer imageId) {
		this.imageId = imageId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getSmallName() {
		return smallName;
	}

	public void setSmallName(String smallName) {
		this.smallName = smallName;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

}
