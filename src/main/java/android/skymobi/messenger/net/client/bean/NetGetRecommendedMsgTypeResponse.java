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
public class NetGetRecommendedMsgTypeResponse extends NetResponse {
    
    //推荐短信类型列表
    private ArrayList<MsgType> msgTypeList = new ArrayList<MsgType>();
    
    //推荐短信类型版本
    private long updateTime;
    
    private boolean hasUpdate = true;

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
     * @return the updateTime
     */
    public long getUpdateTime() {
        return updateTime;
    }

    /**
     * @param updateTime the updateTime to set
     */
    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
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
