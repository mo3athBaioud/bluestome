package android.skymobi.messenger.net.client.bean;

import android.skymobi.messenger.net.beans.commons.RecommendMsg;

import java.util.ArrayList;

/**
 * 获取推荐短信列表
 * @ClassName: NetGetRecommendedMsg
 * @Description: TODO
 * @author bluestome.zhang
 * @date 2012-2-16 上午09:54:17
 */
public class NetGetRecommendedMsgNewResponse extends NetResponse {
    private int totalSize;
    private int start;
    private long updateTime;
    
    private boolean hasUpdate = true;
    
    private ArrayList<RecommendMsg> textMessage = new ArrayList<RecommendMsg>();

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
     * @return the textMessage
     */
    public ArrayList<RecommendMsg> getTextMessage() {
        return textMessage;
    }

    /**
     * @param textMessage the textMessage to set
     */
    public void setTextMessage(ArrayList<RecommendMsg> textMessage) {
        this.textMessage = textMessage;
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
