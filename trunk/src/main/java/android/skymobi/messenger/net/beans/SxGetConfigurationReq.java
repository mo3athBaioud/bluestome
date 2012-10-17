package android.skymobi.messenger.net.beans;

import android.skymobi.messenger.net.beans.commons.ConfigInfo;
import android.skymobi.messenger.net.beans.header.ShouxinReqHeader;

import com.skymobi.android.bean.esb.annotation.EsbSignal;
import com.skymobi.android.bean.tlv.annotation.TLVAttribute;

import java.util.ArrayList;

/**
 * 配置信息请求
 * @ClassName: SxGetConfigurationReq
 * @Description: TODO
 * @author Bluestome.Zhang
 * @date 2012-8-13 下午03:50:29
 */
@EsbSignal(messageCode=0xA861)
public class SxGetConfigurationReq extends ShouxinReqHeader {

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
