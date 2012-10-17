package android.skymobi.messenger.net.beans.commons;

import com.skymobi.android.bean.tlv.annotation.TLVAttribute;

/**
 * @ClassName: WifiCell 无线网络基站对象
 * @Description: TODO
 * @author Bluestome.Zhang
 * @date 2012-6-20 下午05:07:00
 */
public class WifiCell {

    @TLVAttribute(tag=34010008,description="mac地址")
    private String macAddress;
    
    @TLVAttribute(tag=34010006,description="信号强度")
    private short signalStrength;

    /**
     * @return the macAddress
     */
    public String getMacAddress() {
        return macAddress;
    }

    /**
     * @param macAddress the macAddress to set
     */
    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    /**
     * @return the signalStrength
     */
    public short getSignalStrength() {
        return signalStrength;
    }

    /**
     * @param signalStrength the signalStrength to set
     */
    public void setSignalStrength(short signalStrength) {
        this.signalStrength = signalStrength;
    }
    
    
}
