package com.ssi.common.heartbeats;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class ServerCategory implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8488237970449364088L;

	private String domain;

	private String group;

	public void setCategory(String category) {
		int idx = category.indexOf('@');
		if (-1 != idx) {
			domain = category.substring(idx + 1);
			group = category.substring(0, idx);
		} else {
			domain = category;
		}
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((domain == null) ? 0 : domain.hashCode());
		result = prime * result + ((group == null) ? 0 : group.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ServerCategory other = (ServerCategory) obj;
		if (domain == null) {
			if (other.domain != null)
				return false;
		} else if (!domain.equals(other.domain))
			return false;
		if (group == null) {
			if (other.group != null)
				return false;
		} else if (!group.equals(other.group))
			return false;
		return true;
	}

	public boolean isSameDomain(ServerCategory other) {
		if (null == other) {
			return false;
		}

		if (domain == null) {
			return (null == other.domain);
		} else {
			return domain.equals(other.domain);
		}
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.MULTI_LINE_STYLE);
	}
}
