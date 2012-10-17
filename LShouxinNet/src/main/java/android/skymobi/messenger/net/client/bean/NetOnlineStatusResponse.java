package android.skymobi.messenger.net.client.bean;

import android.skymobi.messenger.net.beans.commons.OnlineStatus;

import java.util.ArrayList;

/**
 * 批量获取指定用户的在线状态响应
 * @ClassName: NetOnlineStatusResponse
 * @Description: TODO
 * @author Bluestome.Zhang
 * @date 2012-4-18 上午09:30:39
 */
public class NetOnlineStatusResponse extends NetResponse {

    private ArrayList<OnlineStatus> onlineStatusList = new ArrayList<OnlineStatus>();

    /**
     * @return the onlineStatusList
     */
    public ArrayList<OnlineStatus> getOnlineStatusList() {
        return onlineStatusList;
    }

    /**
     * @param onlineStatusList the onlineStatusList to set
     */
    public void setOnlineStatusList(ArrayList<OnlineStatus> onlineStatusList) {
        this.onlineStatusList = onlineStatusList;
    }

    
}
