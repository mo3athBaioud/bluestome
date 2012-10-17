package android.skymobi.messenger.net.beans;

import android.skymobi.messenger.net.beans.header.ShouxinReqHeader;

import com.skymobi.android.bean.esb.annotation.EsbSignal;
import com.skymobi.android.bean.tlv.annotation.TLVAttribute;

/**
 * 获取推荐短信请求
 * @author Bluestome.Zhang
 *
 */
@EsbSignal(messageCode=0xA821)
public class SxGetRecommendMsgReq extends ShouxinReqHeader {

    @TLVAttribute(tag = 1002,description="默认设置18003，服务端用于区分应用")
    private short sourceId = 18003;
    
	@TLVAttribute(tag=10000043)
	private int msgTypeId;
	
	@TLVAttribute(tag=10000047)
	private int start;
	
	@TLVAttribute(tag=10000048)
	private int pageSize;
	
	@TLVAttribute(tag=10000007)
	private long updateTime;

	public int getMsgTypeId() {
		return msgTypeId;
	}

	public void setMsgTypeId(int msgTypeId) {
		this.msgTypeId = msgTypeId;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public long getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(long updateTime) {
		this.updateTime = updateTime;
	}

    /**
     * @return the sourceId
     */
    public short getSourceId() {
        return sourceId;
    }

    /**
     * @param sourceId the sourceId to set
     */
    public void setSourceId(short sourceId) {
        this.sourceId = sourceId;
    }
	
}
