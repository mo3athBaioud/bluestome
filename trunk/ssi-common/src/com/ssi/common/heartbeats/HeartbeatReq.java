package com.ssi.common.heartbeats;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 服务器心跳
 * 
 * @author wangqi
 * 
 */
public class HeartbeatReq implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5236375202383259291L;

	private IpPortPair address = new IpPortPair();

	private ServerType type;

	private String version;

	private ServerCategory category = new ServerCategory();

	private ServerStatus status = new ServerStatus();

	public String getVersion() {
		return version;
	}

	public HeartbeatReq setVersion(String version) {
		this.version = version;
		return this;
	}

	public String getExportIp() {
		return address.getIp();
	}

	public ServerType getType() {
		return type;
	}

	public HeartbeatReq setType(ServerType type) {
		this.type = type;
		return this;
	}

	public String getTypeName() {
		return type.name();
	}

	public int getExportPort() {
		return address.getPort();
	}

	public HeartbeatReq setExportIp(String ip) {
		address.setIp(ip);
		return this;
	}

	public HeartbeatReq setExportPort(int port) {
		address.setPort(port);
		return this;
	}

	public IpPortPair getAddress() {
		return address;
	}

	public void setAddress(IpPortPair address) {
		this.address = address;
	}

	public ServerCategory getCategory() {
		return category;
	}

	public String getDomain() {
		return category.getDomain();
	}

	public boolean isSameCategory(HeartbeatReq req) {
		return this.category.equals(req.category);
	}

	public boolean isSameDomain(HeartbeatReq req) {
		return this.category.isSameDomain(req.category);
	}

	public HeartbeatReq setCategory(String category) {
		this.category.setCategory(category);
		return this;
	}

	public ServerStatus getStatus() {
		return status;
	}

	public HeartbeatReq setCacheTotalHits(long cacheTotalHits) {
		this.status.setCacheTotalHits(cacheTotalHits);
		return this;
	}

	public HeartbeatReq setCacheTotalReq(long cacheTotalReq) {
		this.status.setCacheTotalReq(cacheTotalReq);
		return this;
	}

	public HeartbeatReq setReqNum(long reqNum) {
		this.status.setReqNum(reqNum);
		return this;
	}

	public HeartbeatReq setSessionNum(long sessionNum) {
		this.status.setSessionNum(sessionNum);
		return this;
	}

	public HeartbeatReq setCacheElements(long cacheElements) {
		this.status.setCacheElements(cacheElements);
		return this;
	}

	public HeartbeatReq setMemcachedIp(String ip) {
		this.status.setMemcachedIp(ip);
		return this;
	}

	public HeartbeatReq setMemcachedPort(String port) {
		this.status.setMemcachedPort(port);
		return this;
	}

	public HeartbeatReq setCacheUsedBytes(long cacheUsedBytes) {
		this.status.setCacheUsedBytes(cacheUsedBytes);
		return this;
	}

	public HeartbeatReq setCacheTotalBytes(long cacheTotalBytes) {
		this.status.setCacheTotalBytes(cacheTotalBytes);
		return this;
	}

	public HeartbeatReq setCacheServerUptime(long cacheServerUptime) {
		this.status.setCacheServerUptime(cacheServerUptime);
		return this;
	}

	public HeartbeatReq setCacheEvictions(long cacheEvictions) {
		this.status.setCacheEvictions(cacheEvictions);
		return this;
	}

	public HeartbeatReq setCacheBytesWritten(long cacheBytesWritten) {
		this.status.setCacheBytesWritten(cacheBytesWritten);
		return this;
	}

	public HeartbeatReq setCacheBytesRead(long cacheBytesRead) {
		this.status.setCacheBytesRead(cacheBytesRead);
		return this;
	}

	public HeartbeatReq setCacheAvailBytes(long cacheBytesWritten) {
		this.status.setCacheAvailBytes(cacheBytesWritten);
		return this;
	}
	
	public HeartbeatReq setServerStartupScript(String startupScript){
		this.status.setStartupScript(startupScript);
		return this;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.MULTI_LINE_STYLE);
	}
}
