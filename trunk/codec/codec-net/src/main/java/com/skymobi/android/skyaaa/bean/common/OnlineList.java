package com.skymobi.android.skyaaa.bean.common;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.skymobi.android.bean.tlv.annotation.TLVAttribute;
/**
 * 在线用户信息表
 * @author Bluces.Wang@sky-mobi.com
 *
 */
@TLVAttribute(tag = 11020004)
public class OnlineList {
	@TLVAttribute(tag=11010002,description="斯凯ID")
	private int skyId;
	@TLVAttribute(tag=11010003,description="用户名")
	private String username;
	@TLVAttribute(tag=11010085,description="接入模块的esb分配地址号")
	private int accessESBAddr;
	@TLVAttribute(tag=11010086,description="接入模块的index")
	private int accessSessionIndex;
	@TLVAttribute(tag=11010012,description="来源")
	private String source;
	
	public int getSkyId() {
		return skyId;
	}

	public void setSkyId(int skyId) {
		this.skyId = skyId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getAccessESBAddr() {
		return accessESBAddr;
	}

	public void setAccessESBAddr(int accessESBAddr) {
		this.accessESBAddr = accessESBAddr;
	}

	public int getAccessSessionIndex() {
		return accessSessionIndex;
	}

	public void setAccessSessionIndex(int accessSessionIndex) {
		this.accessSessionIndex = accessSessionIndex;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String toString() {
		return  ToStringBuilder.reflectionToString(this, 
				ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
