package com.mss.dal.domain;

import java.io.Serializable;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

@Table("tbl_terminal")
public class Terminal implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column("d_id")
	private int id;
	
	@Column("d_hstype_id")
	private int hstypeId;
	
	@Column("d_nake_sale")
	private int nakeSale;
	
//	@Many(target=Hstype.class,field="id")
	private Hstype hstype;
	
	@Column("d_os")
	private String os;
	
	@Column("d_smart_phone")
	private int smartPhone;
	
	@Column("d_3g")
	private int g3;
	
	@Column("d_mobile_system")
	private String mobileSystem;
	
	@Column("d_screensize")
	private String screensize;
	
	@Column("d_screen_color")
	private String screenColor;
	
	@Column("d_resolution")
	private String resolution;
	
	@Column("d_camera")
	private int camera;
	
	@Column("d_camera_pixels")
	private String cameraPixels;
	
	@Column("d_gps")
	private int gps;
	
	@Column("d_wap")
	private int wap;
	
	@Column("d_www")
	private int www;
	
	@Column("d_email")
	private int email;
	
	@Column("d_mms")
	private int mms;
	
	@Column("d_java")
	private int java;
	
	@Column("d_price")
	private String price;
	
	public int getHstypeId() {
		return hstypeId;
	}

	public void setHstypeId(int hstypeId) {
		this.hstypeId = hstypeId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNakeSale() {
		return nakeSale;
	}

	public void setNakeSale(int nakeSale) {
		this.nakeSale = nakeSale;
	}

	public Hstype getHstype() {
		return hstype;
	}

	public void setHstype(Hstype hstype) {
		this.hstype = hstype;
	}

	public int getCamera() {
		return camera;
	}

	public void setCamera(int camera) {
		this.camera = camera;
	}

	public String getCameraPixels() {
		return cameraPixels;
	}

	public void setCameraPixels(String cameraPixels) {
		this.cameraPixels = cameraPixels;
	}

	public int getEmail() {
		return email;
	}

	public void setEmail(int email) {
		this.email = email;
	}

	public int getGps() {
		return gps;
	}

	public void setGps(int gps) {
		this.gps = gps;
	}

	public int getJava() {
		return java;
	}

	public void setJava(int java) {
		this.java = java;
	}

	public int getMms() {
		return mms;
	}

	public void setMms(int mms) {
		this.mms = mms;
	}

	public String getMobileSystem() {
		return mobileSystem;
	}

	public void setMobileSystem(String mobileSystem) {
		this.mobileSystem = mobileSystem;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getResolution() {
		return resolution;
	}

	public void setResolution(String resolution) {
		this.resolution = resolution;
	}

	public String getScreenColor() {
		return screenColor;
	}

	public void setScreenColor(String screenColor) {
		this.screenColor = screenColor;
	}

	public String getScreensize() {
		return screensize;
	}

	public void setScreensize(String screensize) {
		this.screensize = screensize;
	}

	public int getWap() {
		return wap;
	}

	public void setWap(int wap) {
		this.wap = wap;
	}

	public int getWww() {
		return www;
	}

	public void setWww(int www) {
		this.www = www;
	}

	public int getG3() {
		return g3;
	}

	public void setG3(int g3) {
		this.g3 = g3;
	}

	public int getSmartPhone() {
		return smartPhone;
	}

	public void setSmartPhone(int smartPhone) {
		this.smartPhone = smartPhone;
	}
	
}
