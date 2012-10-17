package android.skymobi.messenger.net.beans;

import android.skymobi.messenger.net.beans.commons.Location;
import android.skymobi.messenger.net.beans.commons.MobileCell;
import android.skymobi.messenger.net.beans.commons.WifiCell;
import android.skymobi.messenger.net.beans.header.ShouxinReqHeader;

import com.skymobi.android.bean.esb.annotation.EsbSignal;
import com.skymobi.android.bean.tlv.annotation.TLVAttribute;

import java.util.List;

/**
 * 查找附近的人请求
 * @ClassName: SxGetNearbyReq
 * @Description: TODO
 * @author Bluestome.Zhang
 * @date 2012-6-20 下午05:32:48
 */
@EsbSignal(messageCode=0xA859)
public class SxGetNearbyReq extends ShouxinReqHeader {

    /**
     * 开始页码（1、2、3....）
     */
    @TLVAttribute(tag=10000047,description="开始页码（1、2、3....）")
    private int start = 1;
    
    /**
     * 每页数据量（默认30）
     */
    @TLVAttribute(tag=10000048,description="每页数据量（默认30）")
    private int pageSize = 30;
    
    /**
     * 是否重新计算 0：否，默认；1：是
     */
    @TLVAttribute(tag=10000069,description="是否重新计算 0：否，默认；1：是")
    private byte recalculated = 0;
    
    /**
     * 查询性别（0：全部，默认；1：男；2：女）
     */
    @TLVAttribute(tag=34010018,description="查询性别（0：全部，默认；1：男；2：女）")
    private byte querySex = 0;
    
    /**
     * 位置精度级别：默认为0,0:不精确,1:精确 。如果通过gps定位到的距离为精确，其他定位到的暂时定位不精确
     */
    @TLVAttribute(tag=34010016,description="位置精度级别：默认为0,0:不精确,1:精确 。如果通过gps定位到的距离为精确，其他定位到的暂时定位不精确")
    private byte accurateLevel = 0;
    
    /**
     * 自己性别（0：未设置；1：男；2：女）
     */
    @TLVAttribute(tag=14010006,description="自己性别（0：未设置；1：男；2：女）")
    private String usex = "0";
    
    /**
     * 移动基站信息（对象数组）
     */
    @TLVAttribute(tag=34020001,description="移动基站信息（对象数组）")
    private List<MobileCell> mobileCellArray = null; 
    
    /**
     * 无线基站信息（对象数组）
     */
    @TLVAttribute(tag=34020002,description="无线基站信息（对象数组）")
    private List<WifiCell> wifiCellArray = null;
    
    /**
     *  地理信息 
     */
    @TLVAttribute(tag=34020003,description="地理信息")
    private Location location = null;
    
    /**
     * 获取类型（0：附近的人，默认；1：同城）
     */
    @TLVAttribute(tag=10000063,description="获取类型（0：附近的人，默认；1：同城）")
    private byte fetchFlag = 0;

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
     * @return the pageSize
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * @param pageSize the pageSize to set
     */
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * @return the recalculated
     */
    public byte getRecalculated() {
        return recalculated;
    }

    /**
     * @param recalculated the recalculated to set
     */
    public void setRecalculated(byte recalculated) {
        this.recalculated = recalculated;
    }

    /**
     * @return the querySex
     */
    public byte getQuerySex() {
        return querySex;
    }

    /**
     * @param querySex the querySex to set
     */
    public void setQuerySex(byte querySex) {
        this.querySex = querySex;
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

    /**
     * @return the usex
     */
    public String getUsex() {
        return usex;
    }

    /**
     * @param usex the usex to set
     */
    public void setUsex(String usex) {
        this.usex = usex;
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
     * @return the mobileCellArray
     */
    public List<MobileCell> getMobileCellArray() {
        return mobileCellArray;
    }

    /**
     * @param mobileCellArray the mobileCellArray to set
     */
    public void setMobileCellArray(List<MobileCell> mobileCellArray) {
        this.mobileCellArray = mobileCellArray;
    }

    /**
     * @return the wifiCellArray
     */
    public List<WifiCell> getWifiCellArray() {
        return wifiCellArray;
    }

    /**
     * @param wifiCellArray the wifiCellArray to set
     */
    public void setWifiCellArray(List<WifiCell> wifiCellArray) {
        this.wifiCellArray = wifiCellArray;
    }

    /**
     * @return the fetchFlag
     */
    public byte getFetchFlag() {
        return fetchFlag;
    }

    /**
     * @param fetchFlag the fetchFlag to set
     */
    public void setFetchFlag(byte fetchFlag) {
        this.fetchFlag = fetchFlag;
    }

}
