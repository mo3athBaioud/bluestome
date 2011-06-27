package com.xcms;

import com.mss.dal.domain.BDistrict;
import com.mss.dal.domain.Channel;
import com.mss.dal.domain.Staff;

public class UserSession {

	//UUID
	private String usid;
	
	private Staff staff;
	
	private Channel channel;
	
	private BDistrict bdistrict;

	public BDistrict getBdistrict() {
		return bdistrict;
	}

	public void setBdistrict(BDistrict bdistrict) {
		this.bdistrict = bdistrict;
	}

	public Channel getChannel() {
		return channel;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}

	public Staff getStaff() {
		return staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}

	public String getUsid() {
		return usid;
	}

	public void setUsid(String usid) {
		this.usid = usid;
	}

	
}