package android.skymobi.messenger.net.beans;

import android.skymobi.messenger.net.beans.commons.OnlineStatus;
import android.skymobi.messenger.net.beans.header.ShouxinRespHeader;

import com.skymobi.android.bean.esb.annotation.EsbSignal;
import com.skymobi.android.bean.tlv.annotation.TLVAttribute;

import java.util.ArrayList;

/**
 * 
 * 批量获取指定用户的在线状态响应
 * @ClassName: SxGetOnlineStatusResp
 * @Description: TODO
 * @author Bluestome.Zhang
 * @date 2012-4-18 上午09:19:32
 */
@EsbSignal(messageCode=0xA852)
public class SxGetOnlineStatusResp extends ShouxinRespHeader {

    @TLVAttribute(tag=20000016)
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
