package android.skymobi.messenger.net.beans;

import android.skymobi.messenger.net.beans.commons.Location;
import android.skymobi.messenger.net.beans.commons.MobileCell;
import android.skymobi.messenger.net.beans.commons.WifiCell;
import android.skymobi.messenger.net.beans.header.ShouxinReqHeader;

import com.skymobi.android.bean.esb.annotation.EsbSignal;
import com.skymobi.android.bean.tlv.annotation.TLVAttribute;

/**
 * @ClassName: SxSaveUserLocationReq 保存用户的位置信息
 * @Description: TODO
 * @author Bluestome.Zhang
 * @date 2012-6-20 下午05:00:51
 */
@EsbSignal(messageCode=0xC101)
public class SxSaveUserLocationReq extends ShouxinReqHeader {

    @TLVAttribute(tag=11010012)
    private int appId;
    
    @TLVAttribute(tag=34020001,description="移动基站信息（对象数组）")
    private MobileCell[] mobileCellArray = null; 
    
    @TLVAttribute(tag=34020002,description="无线基站信息（对象数组）")
    private WifiCell[] wifiCellArray = null;
    
    @TLVAttribute(tag=34020003,description="地理信息")
    private Location location = null;
    
    @TLVAttribute(tag=14010006,description="用户的性别(0：未设置;1:男;2:女) 默认为: 0")
    private String sex = "0";
    
    @TLVAttribute(tag=34010016,description="位置精度级别：默认为0,0:不精确,1:精确 。如果通过gps定位到的距离为精确，其他定位到的暂时定位不精确")
    private byte accurateLevel = 0;

    /**
     * @return the appId
     */
    public int getAppId() {
        return appId;
    }

    /**
     * @param appId the appId to set
     */
    public void setAppId(int appId) {
        this.appId = appId;
    }

    /**
     * @return the mobileCellArray
     */
    public MobileCell[] getMobileCellArray() {
        return mobileCellArray;
    }

    /**
     * @param mobileCellArray the mobileCellArray to set
     */
    public void setMobileCellArray(MobileCell[] mobileCellArray) {
        this.mobileCellArray = mobileCellArray;
    }

    /**
     * @return the wifiCellArray
     */
    public WifiCell[] getWifiCellArray() {
        return wifiCellArray;
    }

    /**
     * @param wifiCellArray the wifiCellArray to set
     */
    public void setWifiCellArray(WifiCell[] wifiCellArray) {
        this.wifiCellArray = wifiCellArray;
    }

    /**
     * @return the location
     */
    public Location getLocation() {
        return location;
    }

    /**
     * @param location the location to set
     */
    public void setLocation(Location location) {
        this.location = location;
    }

    /**
     * @return the sex
     */
    public String getSex() {
        return sex;
    }

    /**
     * @param sex the sex to set
     */
    public void setSex(String sex) {
        this.sex = sex;
    }

    /**
     * @return the accurateLevel
     */
    public byte getAccurateLevel() {
        return accurateLevel;
    }

    /**
     * @param accurateLevel the accurateLevel to set
     */
    public void setAccurateLevel(byte accurateLevel) {
        this.accurateLevel = accurateLevel;
    }
    
}
