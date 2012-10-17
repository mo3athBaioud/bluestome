package android.skymobi.messenger.net.beans.commons;

import com.skymobi.android.bean.tlv.annotation.TLVAttribute;

/**
 * @ClassName: ConfigInfo
 * @Description: TODO
 * @author Bluestome.Zhang
 * @date 2012-8-13 下午03:51:57
 */
public class ConfigInfo {
    @TLVAttribute(tag=10000071,description="配置类型，1：短信邀请，2：语音邀请")
    private byte configType;
    
    @TLVAttribute(tag=10000072,description="配置内容")
    private String configContent;
 
    @TLVAttribute(tag=10000007,description="服务端的本版时间戳")
    private long updateTime;

    /**
     * @return the configType
     */
    public byte getConfigType() {
        return configType;
    }

    /**
     * @param configType the configType to set
     */
    public void setConfigType(byte configType) {
        this.configType = configType;
    }

    /**
     * @return the configContent
     */
    public String getConfigContent() {
        return configContent;
    }

    /**
     * @param configContent the configContent to set
     */
    public void setConfigContent(String configContent) {
        this.configContent = configContent;
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
    
}
