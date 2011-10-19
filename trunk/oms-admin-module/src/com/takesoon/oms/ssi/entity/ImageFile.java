package com.takesoon.oms.ssi.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 图片文件对象 tbl_pic_file
 * 
 * @author bluestome
 * 
 */
@Table(name="tbl_pic_file")
public class ImageFile extends AbstractEntity {

	private static final long serialVersionUID = 3651555464532842680L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="d_id")
	private Integer id;

	private Integer articleId;

	private Integer imageId;

	private String url;

	private String name;

	private String smallName;

	private String title;

	private String desc;

	private Date createTime = new Date();
	
	private int status;

	// 文章对象
	private Article article;

	// 图片对象
	private Image image;

	public ImageFile() {
	}

	public ImageFile(Integer articleId, Integer imageId, String url,
			String name, String desc, String title) {
		this.articleId = articleId;
		this.imageId = imageId;
		this.url = url;
		this.name = name;
		this.title = title;
		this.desc = desc;
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

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	
}
