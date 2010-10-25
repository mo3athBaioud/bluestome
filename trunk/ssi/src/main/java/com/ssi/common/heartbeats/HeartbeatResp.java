package com.ssi.common.heartbeats;

import java.util.ArrayList;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class HeartbeatResp implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8657973352726271971L;
	private ArrayList<ServerGroup> candidates = new ArrayList<ServerGroup>();

	public ArrayList<ServerGroup> getCandidates() {
		return candidates;
	}

	public void setCandidates(ArrayList<ServerGroup> candidates) {
		this.candidates = candidates;
	}

	public void addCandidate(ServerGroup candidate) {
		if (null == candidates) {
			candidates = new ArrayList<ServerGroup>();
		}
		candidates.add(candidate);
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.MULTI_LINE_STYLE);
	}
}
