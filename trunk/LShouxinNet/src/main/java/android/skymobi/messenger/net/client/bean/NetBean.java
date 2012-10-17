package android.skymobi.messenger.net.client.bean;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public abstract class NetBean {
	/*
	private UUID uuid = UUID.randomUUID();
	 
	public UUID getID() {
		return uuid;
	}

	public void setID(UUID uuid) {
		this.uuid = uuid;
	}
	*/

	public String toString() {
	        return  ToStringBuilder.reflectionToString(this, 
	                            ToStringStyle.SHORT_PREFIX_STYLE);
	    }
}
