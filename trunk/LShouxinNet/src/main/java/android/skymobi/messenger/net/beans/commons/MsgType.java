package android.skymobi.messenger.net.beans.commons;

import com.skymobi.android.bean.tlv.annotation.TLVAttribute;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class MsgType implements java.io.Serializable {
    
    private static final long serialVersionUID = 1L;

    @TLVAttribute(tag = 10000043)
    private int msgTypeId;
    
    @TLVAttribute(tag = 10000035)
    private String msgTypeName;
    
    @TLVAttribute(tag=10000015,description="操作类型")
    private byte action;
    
    @TLVAttribute(tag=10000067,description="是否有更新 0：没有；1：有")
    private byte update;

    public int getMsgTypeId() {
        return msgTypeId;
    }

    public void setMsgTypeId(int msgTypeId) {
        this.msgTypeId = msgTypeId;
    }

    public String getMsgTypeName() {
        return msgTypeName;
    }

    public void setMsgTypeName(String msgTypeName) {
        if(StringUtils.isEmpty(msgTypeName)) msgTypeName = null;
        this.msgTypeName = msgTypeName;
    }
    
    @Override
    public String toString() {
        return  ToStringBuilder.reflectionToString(this,ToStringStyle.SHORT_PREFIX_STYLE);
    }

    /**
     * @return the action
     */
    public byte getAction() {
        return action;
    }

    /**
     * @param action the action to set
     */
    public void setAction(byte action) {
        this.action = action;
    }

    /**
     * @return the update
     */
    public byte getUpdate() {
        return update;
    }

    /**
     * @param update the update to set
     */
    public void setUpdate(byte update) {
        this.update = update;
    }
    
}
