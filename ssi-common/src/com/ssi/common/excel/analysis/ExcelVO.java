package com.ssi.common.excel.analysis;

import java.io.Serializable;
/**
 * Excel文件对象Bean
 * @author bluestome
 *
 */
public class ExcelVO implements Serializable{
	
	private static final long serialVersionUID = 2445665648751773222L;

	private Integer id;

	private String fileName;

	private String iconName; // TODO

	private String alias;

	private String description;

	private String type;

	private String drm;

	private String author;

	private String album;
	
	private Long fileSize;

	private String isRingTone;
	
	private String clearLevel;

	private String level;

	private boolean hasFile;

	private String momo;
	
	private String fileExtName;
	
	private boolean hasIcon;

	public ExcelVO() {
		this.hasFile = false;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public boolean isHasFile() {
		return hasFile;
	}

	public void setHasFile(boolean hasFile) {
		this.hasFile = hasFile;
	}

	public String getIconName() {
		return iconName;
	}

	public void setIconName(String iconName) {
		this.iconName = iconName;
	}

	public String getMomo() {
		return momo;
	}

	public void setMomo(String momo) {
		this.momo = momo;
	}

	public String getAlbum() {
		return album;
	}

	public void setAlbum(String album) {
		this.album = album;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getDrm() {
		return drm;
	}

	public void setDrm(String drm) {
		this.drm = drm;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getIsRingTone() {
		return isRingTone;
	}

	public void setIsRingTone(String isRingTone) {
		this.isRingTone = isRingTone;
	}

	public String getFileExtName() {
		return fileExtName;
	}

	public void setFileExtName(String fileExtName) {
		this.fileExtName = fileExtName;
	}

	public boolean isHasIcon() {
		return hasIcon;
	}

	public void setHasIcon(boolean hasIcon) {
		this.hasIcon = hasIcon;
	}

	public String getClearLevel() {
		return clearLevel;
	}

	public void setClearLevel(String clearLevel) {
		this.clearLevel = clearLevel;
	}

	public Long getFileSize() {
		return fileSize;
	}

	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}
	
	
}
