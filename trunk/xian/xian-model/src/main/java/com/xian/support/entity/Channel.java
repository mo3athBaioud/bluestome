package com.xian.support.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;


/**
 * 渠道表
 * @author bluestome
 *
 */
@Entity
@Table(name="tbl_channel")
public class Channel extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="d_channel_code")
	private String channelcode;

	@Column(name="d_channel_name")
	private String channelname;

	@Column(name="d_address")
	private String address;

	@Column(name="d_status")
	private Integer status = 1;

	@Transient
	private String bdcode;

	@Column(name="d_createtime")
	private Date createtime = new Date();
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "d_bdcode", referencedColumnName = "d_code")
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
	
	public String getChannelname() {
		return channelname;
	}

	public void setChannelname(String channelname) {
		this.channelname = channelname;
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
