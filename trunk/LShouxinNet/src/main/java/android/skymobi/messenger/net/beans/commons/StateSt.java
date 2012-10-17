package android.skymobi.messenger.net.beans.commons;

import com.skymobi.android.bean.esb.annotation.EsbField;

public class StateSt {

	@EsbField(index = 0, bytes = 4 )
	private int frdSkyId;
	
	@EsbField(index = 1, bytes = 1 )
	private byte stat;

	public int getFrdSkyId() {
		return frdSkyId;
	}

	public void setFrdSkyId(int frdSkyId) {
		this.frdSkyId = frdSkyId;
	}

	public byte getStat() {
		return stat;
	}

	public void setStat(byte stat) {
		this.stat = stat;
	}
	
	
}
