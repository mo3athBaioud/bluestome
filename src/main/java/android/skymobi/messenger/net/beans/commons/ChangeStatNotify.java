package android.skymobi.messenger.net.beans.commons;

import com.skymobi.android.bean.esb.annotation.EsbField;
import com.skymobi.android.bean.esb.annotation.EsbSignal;
import com.skymobi.android.transport.protocol.esb.AbstractAccessHeaderable;

import java.util.ArrayList;

@EsbSignal( messageCode = 0xA415 )
public class ChangeStatNotify extends AbstractAccessHeaderable{

	@EsbField( index = 0 )
	private int seqId;

	public int getSeqId() {
		return seqId;
	}

	public void setSeqId(int seqId) {
		this.seqId = seqId;
	}

	private int frdSkyId;

	@EsbField( index = 1, bytes = 2 )
	private short count;

	@EsbField( index = 2, length = "count" )
	private ArrayList<StateSt> stateSts = new ArrayList<StateSt>();

	public short getCount() {
		return count;
	}

	public ArrayList<StateSt> getStateSts() {
		return stateSts;
	}

	public void setCount(short count) {
		this.count = count;
	}

	public void setStateSts(ArrayList<StateSt> stateSts) {
		this.stateSts = stateSts;
		if( stateSts != null )this.count = (short)stateSts.size();
	}

	private short state;
	private short detailState;

	private long skyid;


	public int getFrdSkyId() {
		return frdSkyId;
	}

	public void setFrdSkyId(int frdSkyId) {
		this.frdSkyId = frdSkyId;
	}

	public long getSkyid() {
		return skyid;
	}

	public void setSkyid(long skyid) {
		this.skyid = skyid;
	}

	public short getState() {
		return state;
	}

	public short getDetailState() {
		return detailState;
	}

	public void setState(short state) {
		this.state = state;
	}

	public void setDetailState(short detailState) {
		this.detailState = detailState;
	}

	public boolean check() {

		return true;
	}

}
