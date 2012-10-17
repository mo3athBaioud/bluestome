package android.skymobi.messenger.net.beans;

import android.skymobi.messenger.net.beans.commons.MsgType;
import android.skymobi.messenger.net.beans.header.ShouxinRespHeader;

import com.skymobi.android.bean.esb.annotation.EsbSignal;
import com.skymobi.android.bean.tlv.annotation.TLVAttribute;

import java.util.ArrayList;

/**
 * 获取推荐短信响应
 * @author Bluestome.Zhang
 *
 */
@EsbSignal(messageCode=0xA822)
public class SxGetRecommendMsgResp extends ShouxinRespHeader {
	
	@TLVAttribute(tag=20000006)
	private ArrayList<MsgType> msgTypeList = new ArrayList<MsgType>();
	
	@TLVAttribute(tag=10000043)
	private int msgTypeId;
	
	@TLVAttribute(tag = 10000037)
	private ArrayList<String> recommendMsg = new ArrayList<String>();
	
	@TLVAttribute(tag=10000041)
	private int totalSize;
	
	@TLVAttribute(tag=10000047)
	private int start;

	/**
     * @return the msgTypeList
     */
    public ArrayList<MsgType> getMsgTypeList() {
        return msgTypeList;
    }

    /**
     * @param msgTypeList the msgTypeList to set
     */
    public void setMsgTypeList(ArrayList<MsgType> msgTypeList) {
        this.msgTypeList = msgTypeList;
    }

    public int getMsgTypeId() {
		return msgTypeId;
	}

	public void setMsgTypeId(int msgTypeId) {
		this.msgTypeId = msgTypeId;
	}

	public int getTotalSize() {
		return totalSize;
	}

	public void setTotalSize(int totalSize) {
		this.totalSize = totalSize;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public ArrayList<String> getRecommendMsg() {
		return recommendMsg;
	}

	public void setRecommendMsg(ArrayList<String> recommendMsg) {
		this.recommendMsg = recommendMsg;
	}
	
}
