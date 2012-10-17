package android.skymobi.messenger.net.beans.sup;

import com.skymobi.android.bean.AbstractCommonBean;
import com.skymobi.android.bean.tlv.annotation.TLVAttribute;

/**
 * SUP更新请求对象
 * @ClassName: SupRequest
 * @Description: TODO
 * @author Bluestome.Zhang
 * @date 2012-2-16 下午05:34:34
 */
public class SupRequest extends AbstractCommonBean{

    @TLVAttribute(tag=10801,description="应用编号[必填]")
    private Integer appId = 2934;
    
    @TLVAttribute(tag=10802,charset = "GBK",description="应用短名称[必填]")
    private String shortname = "shouxin";
    
    @TLVAttribute(tag=10803,description="应用版本号[必填]")
    private Integer appver = 1;
    
    @TLVAttribute(tag=10800,description="下载请求类型[必填] 0:查询更新 | 1:下载更新")
    private Integer reqType = 0;
    
    @TLVAttribute(tag=10804,description="下载策略 0:强制更新，下发数据 | 1:强制更新，不下发数据")
    private Integer downloadPolicy = 1;
    
    @TLVAttribute(tag=10810,description="厂商[必填]")
    private String hsman = "moto";
    
    @TLVAttribute(tag=10811,description="机型[必填]")
    private String hstype = "me525+";
    
    @TLVAttribute(tag=10812,description="平台[必填]")
    private String plat = "MTK";
    
    @TLVAttribute(tag=10813,description="屏幕宽[必填]")
    private Integer width = 480;
    
    @TLVAttribute(tag=10814,description="屏幕高[必填]")
    private Integer height = 854;
    
    @TLVAttribute(tag=10815,description="内存大小（单位K")
    private Integer ram = 128*1024;
    
    @TLVAttribute(tag=10816,description="是否触摸屏[必填]")
    private Integer touch = 1;
    
    @TLVAttribute(tag=10832,description="是否支持WIFI[必填]")
    private Integer wifi = 1;
    
    @TLVAttribute(tag=10833,description="是否支持后台挂QQ[必填]")
    private Integer backgroup = 1;
    
    @TLVAttribute(tag=10834,description="方向键[必填]")
    private Integer directionKey = 0;
    
    @TLVAttribute(tag=10835,description="是否支持数字键[必填]")
    private Integer numberKey = 0;
    
    @TLVAttribute(tag=10836,description="是否支持视频[必填]")
    private Integer videoSupport = 1;
    
    @TLVAttribute(tag=10837,description="是否带启动Logo")
    private Integer hasLogo = 1;
    
    @TLVAttribute(tag=10838,description="是否带软解码功能")
    private Integer softDecode = 1;
    
    @TLVAttribute(tag=10828,description="是否支持横竖屏切换")
    private Integer Chscr = 1;
    
    @TLVAttribute(tag=10829,description="字体大小[必填]")
    private Integer fontSize = 16;
    
    @TLVAttribute(tag=10817,description="SIM卡串号[必填]")
    private String imsi = "354707041147044";
    
    @TLVAttribute(tag=10818,description="手机串号[必填]")
    private String imei = "4600011932601090";
    
    @TLVAttribute(tag=10819,description="运营商")
    private Integer provider = 0;
    
    @TLVAttribute(tag=10820,description="短信中心号码")
    private String msgcen = "1380013800";
    
    @TLVAttribute(tag=10821,description="移植版本号")
    private Integer portv = 0;
    
    @TLVAttribute(tag=10822,description="虚拟机版本号")
    private Integer vmv = 0;
    
    @TLVAttribute(tag=10824,description="小区号")
    private Integer cellId = 0;
    
    @TLVAttribute(tag=10825,description="运营商代码")
    private Integer mnc = 1;
    
    @TLVAttribute(tag=10826,description="国家码")
    private Integer mcc = 460;
    
    @TLVAttribute(tag=10710,description="起始位置")
    private Integer startPos = 0;
    
    @TLVAttribute(tag=10711, description="文件终止地址" )
    private Integer endPos = 0;
    
    @TLVAttribute(tag=10709,description="文件MD5")
    private byte[] md5 = null;


    
    /**
     * @return the appId
     */
    public Integer getAppId() {
        return appId;
    }

    /**
     * @param appId the appId to set
     */
    public void setAppId(Integer appId) {
        this.appId = appId;
    }

    /**
     * @return the shortname
     */
    public String getShortname() {
        return shortname;
    }

    /**
     * @param shortname the shortname to set
     */
    public void setShortname(String shortname) {
        this.shortname = shortname;
    }

    /**
     * @return the appver
     */
    public Integer getAppver() {
        return appver;
    }

    /**
     * @param appver the appver to set
     */
    public void setAppver(Integer appver) {
        this.appver = appver;
    }

    /**
     * @return the reqType
     */
    public Integer getReqType() {
        return reqType;
    }

    /**
     * @param reqType the reqType to set
     */
    public void setReqType(Integer reqType) {
        this.reqType = reqType;
    }

    /**
     * @return the hsman
     */
    public String getHsman() {
        return hsman;
    }

    /**
     * @param hsman the hsman to set
     */
    public void setHsman(String hsman) {
        this.hsman = hsman;
    }

    /**
     * @return the hstype
     */
    public String getHstype() {
        return hstype;
    }

    /**
     * @param hstype the hstype to set
     */
    public void setHstype(String hstype) {
        this.hstype = hstype;
    }

    /**
     * @return the plat
     */
    public String getPlat() {
        return plat;
    }

    /**
     * @param plat the plat to set
     */
    public void setPlat(String plat) {
        this.plat = plat;
    }

    /**
     * @return the width
     */
    public Integer getWidth() {
        return width;
    }

    /**
     * @param width the width to set
     */
    public void setWidth(Integer width) {
        this.width = width;
    }

    /**
     * @return the height
     */
    public Integer getHeight() {
        return height;
    }

    /**
     * @param height the height to set
     */
    public void setHeight(Integer height) {
        this.height = height;
    }

    /**
     * @return the ram
     */
    public Integer getRam() {
        return ram;
    }

    /**
     * @param ram the ram to set
     */
    public void setRam(Integer ram) {
        this.ram = ram;
    }

    /**
     * @return the touch
     */
    public Integer getTouch() {
        return touch;
    }

    /**
     * @param touch the touch to set
     */
    public void setTouch(Integer touch) {
        this.touch = touch;
    }

    /**
     * @return the wifi
     */
    public Integer getWifi() {
        return wifi;
    }

    /**
     * @param wifi the wifi to set
     */
    public void setWifi(Integer wifi) {
        this.wifi = wifi;
    }

    /**
     * @return the backgroup
     */
    public Integer getBackgroup() {
        return backgroup;
    }

    /**
     * @param backgroup the backgroup to set
     */
    public void setBackgroup(Integer backgroup) {
        this.backgroup = backgroup;
    }

    /**
     * @return the directionKey
     */
    public Integer getDirectionKey() {
        return directionKey;
    }

    /**
     * @param directionKey the directionKey to set
     */
    public void setDirectionKey(Integer directionKey) {
        this.directionKey = directionKey;
    }

    /**
     * @return the numberKey
     */
    public Integer getNumberKey() {
        return numberKey;
    }

    /**
     * @param numberKey the numberKey to set
     */
    public void setNumberKey(Integer numberKey) {
        this.numberKey = numberKey;
    }

    /**
     * @return the videoSupport
     */
    public Integer getVideoSupport() {
        return videoSupport;
    }

    /**
     * @param videoSupport the videoSupport to set
     */
    public void setVideoSupport(Integer videoSupport) {
        this.videoSupport = videoSupport;
    }

    /**
     * @return the hasLogo
     */
    public Integer getHasLogo() {
        return hasLogo;
    }

    /**
     * @param hasLogo the hasLogo to set
     */
    public void setHasLogo(Integer hasLogo) {
        this.hasLogo = hasLogo;
    }

    /**
     * @return the softDecode
     */
    public Integer getSoftDecode() {
        return softDecode;
    }

    /**
     * @param softDecode the softDecode to set
     */
    public void setSoftDecode(Integer softDecode) {
        this.softDecode = softDecode;
    }

    /**
     * @return the chscr
     */
    public Integer getChscr() {
        return Chscr;
    }

    /**
     * @param chscr the chscr to set
     */
    public void setChscr(Integer chscr) {
        Chscr = chscr;
    }

    /**
     * @return the fontSize
     */
    public Integer getFontSize() {
        return fontSize;
    }

    /**
     * @param fontSize the fontSize to set
     */
    public void setFontSize(Integer fontSize) {
        this.fontSize = fontSize;
    }

    /**
     * @return the imsi
     */
    public String getImsi() {
        return imsi;
    }

    /**
     * @param imsi the imsi to set
     */
    public void setImsi(String imsi) {
        this.imsi = imsi;
    }

    /**
     * @return the imei
     */
    public String getImei() {
        return imei;
    }

    /**
     * @param imei the imei to set
     */
    public void setImei(String imei) {
        this.imei = imei;
    }

    /**
     * @return the provider
     */
    public Integer getProvider() {
        return provider;
    }

    /**
     * @param provider the provider to set
     */
    public void setProvider(Integer provider) {
        this.provider = provider;
    }

    /**
     * @return the msgcen
     */
    public String getMsgcen() {
        return msgcen;
    }

    /**
     * @param msgcen the msgcen to set
     */
    public void setMsgcen(String msgcen) {
        this.msgcen = msgcen;
    }

    /**
     * @return the portv
     */
    public Integer getPortv() {
        return portv;
    }

    /**
     * @param portv the portv to set
     */
    public void setPortv(Integer portv) {
        this.portv = portv;
    }

    /**
     * @return the vmv
     */
    public Integer getVmv() {
        return vmv;
    }

    /**
     * @param vmv the vmv to set
     */
    public void setVmv(Integer vmv) {
        this.vmv = vmv;
    }

    /**
     * @return the cellId
     */
    public Integer getCellId() {
        return cellId;
    }

    /**
     * @param cellId the cellId to set
     */
    public void setCellId(Integer cellId) {
        this.cellId = cellId;
    }

    /**
     * @return the mnc
     */
    public Integer getMnc() {
        return mnc;
    }

    /**
     * @param mnc the mnc to set
     */
    public void setMnc(Integer mnc) {
        this.mnc = mnc;
    }

    /**
     * @return the mcc
     */
    public Integer getMcc() {
        return mcc;
    }

    /**
     * @param mcc the mcc to set
     */
    public void setMcc(Integer mcc) {
        this.mcc = mcc;
    }

    /**
     * @return the startPos
     */
    public Integer getStartPos() {
        return startPos;
    }

    /**
     * @param startPos the startPos to set
     */
    public void setStartPos(Integer startPos) {
        this.startPos = startPos;
    }

    /**
     * @return the md5
     */
    public byte[] getMd5() {
        return md5;
    }

    /**
     * @param md5 the md5 to set
     */
    public void setMd5(byte[] md5) {
        this.md5 = md5;
    }

    /**
     * @return the downloadPolicy
     */
    public Integer getDownloadPolicy() {
        return downloadPolicy;
    }

    /**
     * @param downloadPolicy the downloadPolicy to set
     */
    public void setDownloadPolicy(Integer downloadPolicy) {
        this.downloadPolicy = downloadPolicy;
    }

    /**
     * @return the endPos
     */
    public Integer getEndPos() {
        return endPos;
    }

    /**
     * @param endPos the endPos to set
     */
    public void setEndPos(Integer endPos) {
        this.endPos = endPos;
    }
    
}
