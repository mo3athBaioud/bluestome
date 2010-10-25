package com.ssi.common.heartbeats;

import java.util.ArrayList;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 服务器组
 * 
 * @author wangqi
 * 
 */
public class ServerGroup implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -607611169789323798L;

	private ServerType serverType;

	private ArrayList<IpPortPair> servers = new ArrayList<IpPortPair>();

	public ServerType getServerType() {
		return serverType;
	}

	public void setServerType(ServerType serverType) {
		this.serverType = serverType;
	}

	public ArrayList<IpPortPair> getServers() {
		return servers;
	}

	public void setServers(ArrayList<IpPortPair> servers) {
		this.servers = servers;
	}

	public void addServer(IpPortPair ipPort) {
		if (null == this.servers) {
			this.servers = new ArrayList<IpPortPair>();
		}

		this.servers.add(ipPort);
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.MULTI_LINE_STYLE);
	}
}
