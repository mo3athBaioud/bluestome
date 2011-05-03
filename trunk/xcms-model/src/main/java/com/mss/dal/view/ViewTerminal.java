package com.mss.dal.view;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.View;

@View("view_tac_calling")
public class ViewTerminal {

	@Column("d_phone_num")
	private String phoneNum;
	
	@Column("d_tac")
	private String tac;
	
	@Column("d_imei")
	private String imei;
	
	@Column("d_hsman_name")
	private String hsmanName;
	
	@Column("d_hsman_name_en")
	private String hsmanEnName;
	
	@Column("d_hstype_name")
	private String hstypeName;
	
	@Column("d_hstype_name_en")
	private String hstypeEnName;
	
	private String mms = "支持";
	
	public String getHsmanName() {
		return hsmanName;
	}

	public void setHsmanName(String hsmanName) {
		this.hsmanName = hsmanName;
	}

	public String getHstypeName() {
		return hstypeName;
	}

	public void setHstypeName(String hstypeName) {
		this.hstypeName = hstypeName;
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public String getTac() {
		return tac;
	}

	public void setTac(String tac) {
		this.tac = tac;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getHsmanEnName() {
		return hsmanEnName;
	}

	public void setHsmanEnName(String hsmanEnName) {
		this.hsmanEnName = hsmanEnName;
	}

	public String getHstypeEnName() {
		return hstypeEnName;
	}

	public void setHstypeEnName(String hstypeEnName) {
		this.hstypeEnName = hstypeEnName;
	}

	public String getMms() {
		return mms;
	}

	public void setMms(String mms) {
		this.mms = mms;
	}
	
	
}
