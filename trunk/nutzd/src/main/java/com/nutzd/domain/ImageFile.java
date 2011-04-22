package com.nutzd.domain;

import java.util.Date;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.One;
import org.nutz.dao.entity.annotation.PK;
import org.nutz.dao.entity.annotation.Table;

@Table("tbl_pic_file")
public class ImageFile {

	@Id
	@Column("d_id")
	private int id;
	
	@Column("d_article_id")
	private int articleId;
	
	@One(target = Article.class, field = "articleId")
	private Article article;
	
	@Column("d_image_id")
	private int imageId;
	
	@One(target = Image.class, field = "imageId")
	private Image image;
	
	@Column("d_file_url")
	private String fileUrl;
	
	@Column("d_file_title")
	private String fileTitle;
	
	@Column("d_file_name")
	private String fileName;
	
	@Column("d_file_small_name")
	private String fileSmallName;
	
	@Column("d_createtime")
	private Date createtime;
	
	@Column("d_status")
	private int status;

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

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileSmallName() {
		return fileSmallName;
	}

	public void setFileSmallName(String fileSmallName) {
		this.fileSmallName = fileSmallName;
	}

	public String getFileTitle() {
		return fileTitle;
	}

	public void setFileTitle(String fileTitle) {
		this.fileTitle = fileTitle;
	}

	public String getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public int getImageId() {
		return imageId;
	}

	public void setImageId(int imageId) {
		this.imageId = imageId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	
}
