package android.skymobi.messenger.net.beans;

import android.skymobi.messenger.net.beans.header.ShouxinReqHeader;

import com.skymobi.android.bean.esb.annotation.EsbSignal;
import com.skymobi.android.bean.tlv.annotation.TLVAttribute;

/**
 * 获取联系人列表请求
 * @author Bluestome.Zhang
 *
 */
@EsbSignal(messageCode = 0xA803)
public class SxGetContactsListReq extends ShouxinReqHeader {
    @TLVAttribute(tag = 1002,description="默认设置18003，服务端用于区分应用")
    private short sourceId = 18003;
	@TLVAttribute(tag = 10000007)
	private long updateTime = 0;
	@TLVAttribute(tag = 10000047)
	private int start =1;
	@TLVAttribute(tag = 10000048)
	private int pageSize =10;
	public long getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(long updateTime) {
		this.updateTime = updateTime;
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
