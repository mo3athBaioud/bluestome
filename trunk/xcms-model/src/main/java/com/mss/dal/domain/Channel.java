package com.mss.dal.domain;

import java.io.Serializable;
import java.util.Date;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.One;
import org.nutz.dao.entity.annotation.Table;

/**
 * 通道表
 * @author bluestome
 *
 */
@Table("tbl_channel")
public class Channel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Name
	@Column("d_channel_code")
	private String channelcode;

	@Column("d_channel_name")
	private String channlename;

	@Column("d_address")
	private String address;

	@Column("d_status")
	private Integer status = 1;

	@Column("d_bdcode")
	private String bdcode;

	@Column("d_createtime")
	private Date createtime = new Date();
	
//	@One(target = BDistrict.class,field = "bdcode")
	private BDistrict bdistrict;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getBdcode() {
		return bdcode;
	}

	public void setBdcode(String bdcode) {
		this.bdcode = bdcode;
	}

	public String getChannelcode() {
		return channelcode;
	}

	public void setChannelcode(String channelcode) {
		this.channelcode = channelcode;
	}

	public String getChannlename() {
		return channlename;
	}

	public void setChannlename(String channlename) {
		this.channlename = channlename;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public BDistrict getBdistrict() {
		return bdistrict;
	}

	public void setBdistrict(BDistrict bdistrict) {
		this.bdistrict = bdistrict;
	}

	
}
