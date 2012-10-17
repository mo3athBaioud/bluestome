package android.skymobi.messenger.net.beans.commons;

import com.skymobi.android.bean.tlv.annotation.TLVAttribute;

/**
 * 推荐消息内容对象
 * @author Bluestome.Zhang
 *
 */
public class RecommendMsg implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@TLVAttribute(tag = 10000066)
	private int msgId;
	
	@TLVAttribute(tag = 10000015)
	private short action;
	
	@TLVAttribute(tag = 10000037)
	private String textMessage;
	
	private boolean isDelete = false;

	public int getMsgId() {
		return msgId;
	}

	public void setMsgId(int msgId) {
		this.msgId = msgId;
	}

	public short getAction() {
		return action;
	}

	public void setAction(short action) {
		this.action = action;
	}

	public String getTextMessage() {
		return textMessage;
	}

	public void setTextMessage(String textMessage) {
		this.textMessage = textMessage;
	}

    /**
     * @return the isDelete
     */
    public boolean isDelete() {
        return action == 2?true:false;
    }

    /**
     * @param isDelete the isDelete to set
     */
    public void setDelete(boolean isDelete) {
        this.isDelete = isDelete;
    }
	
}
