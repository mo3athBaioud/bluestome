
package com.skymobi.android;

/**
 * 终端能力信息
 * 
 * @ClassName: TerminalInfo
 * @Description: TODO
 * @author Bluestome.Zhang
 * @date 2012-2-6 上午11:03:46
 */
public class TerminalInfo {

    // 厂商
    private String hsman = "motorola";

    // 机型
    private String hstype = "ME525+";

    // 手机卡序列号
    private String imsi = "4600000000000022";

    // 终端序列号
    private String imei = "123400000000000";

    // 终端屏幕宽
    private int width = 480;

    // 终端屏幕高
    private int height = 854;

    // 终端内存大小
    private long mem = 500 * 1024 * 1024;

    // 终端平台系统版本
    private int version = 7;

    //应用ID
    private int appid = 2934;
    
    //应用短名称
    private String shortName = "shouxin";

    //应用版本号 必填  如果版本是1.1 则显填11
    private int appver = 1;
    
    //平台
    private String plat = "LINUX";
    
    //是否触摸屏
    private int isTouch = 1;
    
    //是否支持WIFI [1=支持 0=不支持]
    private int wifi = 1;
    
    //是否支持后台挂QQ [1=支持 0=不支持]
    private int background = 1;
    
    //是否有方向键 [1=支持 0=不支持]
    private int directionKey;
    
    //是否有数字键 [1=支持 0=不支持]
    private int numberKey;
    
    //是否支持视频播放 [1=支持 0=不支持]
    private int videoSupport = 1;
    
    //字体大小
    private int fontSize = 12;
    
    //来源 必填
    private int enter = 1;

    public String getHsman() {
        return hsman;
    }

    public void setHsman(String hsman) {
        this.hsman = hsman;
    }

    public String getHstype() {
        return hstype;
    }

    public void setHstype(String hstype) {
        this.hstype = hstype;
    }

    public String getImsi() {
        return imsi;
    }

    public void setImsi(String imsi) {
        this.imsi = imsi;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public long getMem() {
        return mem;
    }

    public void setMem(long mem) {
        this.mem = mem;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public int getAppid() {
        return appid;
    }

    public void setAppid(int appid) {
        this.appid = appid;
    }

    public int getAppver() {
        return appver;
    }

    public void setAppver(int appver) {
        this.appver = appver;
    }

    /**
     * @return the enter
     */
    public int getEnter() {
        return enter;
    }

    /**
     * @param enter the enter to set
     */
    public void setEnter(int enter) {
        this.enter = enter;
    }

    /**
     * @return the shortName
     */
    public String getShortName() {
        return shortName;
    }

    /**
     * @param shortName the shortName to set
     */
    public void setShortName(String shortName) {
        this.shortName = shortName;
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
     * @return the isTouch
     */
    public int getIsTouch() {
        return isTouch;
    }

    /**
     * @param isTouch the isTouch to set
     */
    public void setIsTouch(int isTouch) {
        this.isTouch = isTouch;
    }

    /**
     * @return the wifi
     */
    public int getWifi() {
        return wifi;
    }

    /**
     * @param wifi the wifi to set
     */
    public void setWifi(int wifi) {
        this.wifi = wifi;
    }

    /**
     * @return the background
     */
    public int getBackground() {
        return background;
    }

    /**
     * @param background the background to set
     */
    public void setBackground(int background) {
        this.background = background;
    }

    /**
     * @return the directionKey
     */
    public int getDirectionKey() {
        return directionKey;
    }

    /**
     * @param directionKey the directionKey to set
     */
    public void setDirectionKey(int directionKey) {
        this.directionKey = directionKey;
    }

    /**
     * @return the videoSupport
     */
    public int getVideoSupport() {
        return videoSupport;
    }

    /**
     * @param videoSupport the videoSupport to set
     */
    public void setVideoSupport(int videoSupport) {
        this.videoSupport = videoSupport;
    }

    /**
     * @return the fontSize
     */
    public int getFontSize() {
        return fontSize;
    }

    /**
     * @param fontSize the fontSize to set
     */
    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }

    /**
     * @return the numberKey
     */
    public int getNumberKey() {
        return numberKey;
    }

    /**
     * @param numberKey the numberKey to set
     */
    public void setNumberKey(int numberKey) {
        this.numberKey = numberKey;
    }

    
}
