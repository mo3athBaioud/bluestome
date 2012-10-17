package android.skymobi.messenger.net.client.bean;

import android.skymobi.messenger.net.beans.commons.MsgType;

import java.util.ArrayList;

/**
 * 获取推荐短信列表
 * @ClassName: NetGetRecommendedMsg
 * @Description: TODO
 * @author bluestome.zhang
 * @date 2012-2-16 上午09:54:17
 */
public class NetGetRecommendedMsgResponse extends NetResponse {

    private int msgTypeId;
    private int totalSize;
    private int start;
    private boolean hasUpdate = true;
    
    private ArrayList<MsgType> msgTypeList = new ArrayList<MsgType>();
    private ArrayList<String> textMessage = new ArrayList<String>();
    /**
     * @return the msgTypeId
     */
    public int getMsgTypeId() {
        return msgTypeId;
    }
    /**
     * @param msgTypeId the msgTypeId to set
     */
    public void setMsgTypeId(int msgTypeId) {
        this.msgTypeId = msgTypeId;
    }
    /**
     * @return the totalSize
     */
    public int getTotalSize() {
        return totalSize;
    }
    /**
     * @param totalSize the totalSize to set
     */
    public void setTotalSize(int totalSize) {
        this.totalSize = totalSize;
    }
    /**
     * @return the start
     */
    public int getStart() {
        return start;
    }
    /**
     * @param start the start to set
     */
    public void setStart(int start) {
        this.start = start;
    }
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
    /**
     * @return the textMessage
     */
    public ArrayList<String> getTextMessage() {
        return textMessage;
    }
    /**
     * @param textMessage the textMessage to set
     */
    public void setTextMessage(ArrayList<String> textMessage) {
        this.textMessage = textMessage;
    }
    
    /**
     * 判断推荐列表是否为空
     * @return
     */
    public boolean isEmptyMsgList(){
        return resultCode == 601?true:false;
    }
    /**
     * @return the hasUpdate
     */
    public boolean isHasUpdate() {
        return hasUpdate;
    }
    /**
     * @param hasUpdate the hasUpdate to set
     */
    public void setHasUpdate(boolean hasUpdate) {
        this.hasUpdate = hasUpdate;
    }
    
}
