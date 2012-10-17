package android.skymobi.messenger.net.beans.header;

import android.skymobi.messenger.net.utils.SequenceUtil;

import com.skymobi.android.bean.tlv.annotation.TLVAttribute;
import com.skymobi.android.transport.protocol.esb.signal.AbstractEsbT2ASignal;

/**
 * 正文的通用响应头
 * @ClassName: CommonHeader
 * @Description: TODO
 * @author Bluestome.Zhang
 * @date 2012-7-10 下午02:58:14
 */
public class CommonRequestHeader extends AbstractEsbT2ASignal{
    
    @TLVAttribute(tag = 1001, description = "客户端使用")
    private int seqid = SequenceUtil.getInstance().getSeq();

    @TLVAttribute(tag = 1002, description = "为了能标识不同的服务来源")
    private short sourceId = 18003;

    /**
     * @return the seqid
     */
    public int getSeqid() {
        return seqid;
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
