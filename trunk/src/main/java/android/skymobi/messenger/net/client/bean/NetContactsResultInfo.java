package android.skymobi.messenger.net.client.bean;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * @ClassName: NetContactsResultInfo
 * @Description: TODO
 * @author Bluestome.Zhang
 * @date 2012-3-7 下午02:11:28
 */
public class NetContactsResultInfo {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    // 序列
    private int sequenceId;
    //通讯录id
    private int contactId;
    //1：成功，2失败，3：传递的action不合法 
    private byte code;
    // 1:新增2:删除 3:修改
    private byte action;
    public int getSequenceId() {
        return sequenceId;
    }
    public int getContactId() {
        return contactId;
    }
    public void setSequenceId(int sequenceId) {
        this.sequenceId = sequenceId;
    }
    public void setContactId(int contactId) {
        this.contactId = contactId;
    }
    public byte getCode() {
        return code;
    }
    public byte getAction() {
        return action;
    }
    public void setCode(byte code) {
        this.code = code;
    }
    public void setAction(byte action) {
        this.action = action;
    }
    @Override
    public String toString() {
        return  ToStringBuilder.reflectionToString(this,ToStringStyle.SHORT_PREFIX_STYLE);
    }

}
