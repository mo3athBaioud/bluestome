package android.skymobi.messenger.net.beans.header;

import com.skymobi.android.bean.tlv.annotation.TLVAttribute;
import com.skymobi.android.transport.protocol.esb.AbstractAccessHeaderable;

/**
 * 正文的通用响应头
 * @ClassName: CommonResponseHeader
 * @Description: TODO
 * @author Bluestome.Zhang
 * @date 2012-7-10 下午03:33:11
 */
public class CommonResponseHeader extends AbstractAccessHeaderable{

    @TLVAttribute(tag = 1001,description="对应客户端请求的序号")
    private int seqid;

    /**
     * @return the seqid
     */
    public int getSeqid() {
        return seqid;
    }

    /**
     * @param seqid the seqid to set
     */
    public void setSeqid(int seqid) {
        this.seqid = seqid;
    }
    
    @TLVAttribute(tag = 1003)
    private int responseCode = 200;
    
    @TLVAttribute(tag = 1004)
    private String responseMsg;
    
    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseMsg() {
        return responseMsg;
    }

    public void setResponseMsg(String responseMsg) {
        this.responseMsg = responseMsg;
    }
    
}
