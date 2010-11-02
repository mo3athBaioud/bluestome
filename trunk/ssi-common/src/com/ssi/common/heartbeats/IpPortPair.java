package com.ssi.common.heartbeats;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 服务器IP地址对
 * 
 * @author wangqi
 * 
 */
public class IpPortPair implements java.io.Serializable, Comparable<IpPortPair> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8755216585336683007L;

	private String ip = null;

	private int port = 0;

	public IpPortPair() {
	}

	public IpPortPair(String ip, int port) {
		this.ip = ip;
		this.port = port;
	}

	/**
	 * @return the ip
	 */
	public String getIp() {
		return ip;
	}

	/**
	 * @param ip
	 *            the ip to set
	 */
	public IpPortPair setIp(String ip) {
		this.ip = ip;
		return this;
	}

	/**
	 * @return the port
	 */
	public int getPort() {
		return port;
	}

	/**
	 * @param port
	 *            the port to set
	 */
	public IpPortPair setPort(int port) {
		this.port = port;
		return this;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ip == null) ? 0 : ip.hashCode());
		result = prime * result + port;
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
		IpPortPair other = (IpPortPair) obj;
		if (ip == null) {
			if (other.ip != null)
				return false;
		} else if (!ip.equals(other.ip))
			return false;
		if (port != other.port)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.MULTI_LINE_STYLE);
	}

	public int compareTo(IpPortPair o) {
		int rslt = this.ip.compareTo(o.ip);
		if (0 == rslt) {
			return this.port - o.port;
		}
		return rslt;
	}
}
