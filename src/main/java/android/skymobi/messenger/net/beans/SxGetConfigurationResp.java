package android.skymobi.messenger.net.beans;

import android.skymobi.messenger.net.beans.commons.ConfigInfo;
import android.skymobi.messenger.net.beans.header.ShouxinRespHeader;

import com.skymobi.android.bean.esb.annotation.EsbSignal;
import com.skymobi.android.bean.tlv.annotation.TLVAttribute;

import java.util.ArrayList;

/**
 * 
 * 配置信息响应
 * @ClassName: SxGetConfigurationResp
 * @Description: TODO
 * @author Bluestome.Zhang
 * @date 2012-8-13 下午03:54:59
 */
@EsbSignal(messageCode=0xA862)
public class SxGetConfigurationResp extends ShouxinRespHeader {

    @TLVAttribute(tag=20000020,description="配置对象")
    private ArrayList<ConfigInfo> configInfos = new ArrayList<ConfigInfo>();

    /**
     * @return the configInfos
     */
    public ArrayList<ConfigInfo> getConfigInfos() {
        return configInfos;
    }

    /**
     * @param configInfos the configInfos to set
     */
    public void setConfigInfos(ArrayList<ConfigInfo> configInfos) {
        this.configInfos = configInfos;
    }
    
}
