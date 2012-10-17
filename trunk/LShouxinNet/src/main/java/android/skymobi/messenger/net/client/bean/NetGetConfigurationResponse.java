package android.skymobi.messenger.net.client.bean;

import android.skymobi.messenger.net.beans.commons.ConfigInfo;

import java.util.ArrayList;

/**
 * 获取服务器端配置信息响应对象
 * @ClassName: NetGetConfigurationResponse
 * @Description: TODO
 * @author Bluestome.Zhang
 * @date 2012-8-13 下午03:58:37
 */
public class NetGetConfigurationResponse extends NetResponse {

    // 配置内容对象
    private ArrayList<ConfigInfo> configInfo = new ArrayList<ConfigInfo>();
    
    // 是否有更新版本
    private boolean hasUpdate = true;

    /**
     * @return the configInfo
     */
    public ArrayList<ConfigInfo> getConfigInfo() {
        return configInfo;
    }

    /**
     * @param configInfo the configInfo to set
     */
    public void setConfigInfo(ArrayList<ConfigInfo> configInfo) {
        this.configInfo = configInfo;
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
