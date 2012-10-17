package android.skymobi.messenger.net.client.bean;


/**
 * @ClassName: NetSupRequest
 * @Description: TODO
 * @author Bluestome.Zhang
 * @date 2012-2-16 下午05:23:57
 */
public class NetSupRequest extends NetRequest {

    //应用编号
    private int appid;
    
    //应用短名称
    private String shortname;
    
    //应用版本号
    private int appver;
    
    //下载请求类型
    private int reqType;
    
    //厂商
    private String hsman;
    
    //机型
    private String hstype;
    
    //平台
    private String plat;
    
    //屏幕宽
    private int width;
    
    //屏幕高
    private int height;
    
    //内存大小（单位K）
    private int ram;
    
    //是否触摸屏
    private int touch;
    
    //是否支持WIFI
    private int wifi;
    
    //是否支持后台挂QQ
    private int backgroup;
    
    //方向键
    private int directionKey;
    
    //是否支持数字键
    private int numberKey;
    
    //是否支持视频
    private int videoSupport;
    
    //是否带启动Logo
    private int hasLogo;
    
    //是否带软解码功能
    private int softDecode;
    
    //是否支持横竖屏切换
    private int Chscr;
    
    //字体大小
    private int fontSize;
    
    //SIM卡串号
    private String imsi;
    
    //手机串号
    private String imei;
    
    //运营商
    private int provider;
    
    //短信中心号码
    private String msgcen;
    
    //移植版本号
    private int portv;
    
    //虚拟机版本号
    private int vmv;
    
    //小区号
    private int cellId;

    //运营商代码
    private int mnc;
    
    //国家吗
    private int mcc;
    
    //文件起始地址
    private int startPos;
    
    //文件MD5
    private byte[] md5;
    /**
     * @return the appid
     */
    public int getAppid() {
        return appid;
    }

    /**
     * @param appid the appid to set
     */
    public void setAppid(int appid) {
        this.appid = appid;
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
    public int getAppver() {
        return appver;
    }

    /**
     * @param appver the appver to set
     */
    public void setAppver(int appver) {
        this.appver = appver;
    }

    /**
     * @return the reqType
     */
    public int getReqType() {
        return reqType;
    }

    /**
     * @param reqType the reqType to set
     */
    public void setReqType(int reqType) {
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
    public int getWidth() {
        return width;
    }

    /**
     * @param width the width to set
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * @return the height
     */
    public int getHeight() {
        return height;
    }

    /**
     * @param height the height to set
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * @return the ram
     */
    public int getRam() {
        return ram;
    }

    /**
     * @param ram the ram to set
     */
    public void setRam(int ram) {
        this.ram = ram;
    }

    /**
     * @return the touch
     */
    public int getTouch() {
        return touch;
    }

    /**
     * @param touch the touch to set
     */
    public void setTouch(int touch) {
        this.touch = touch;
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
     * @return the backgroup
     */
    public int getBackgroup() {
        return backgroup;
    }

    /**
     * @param backgroup the backgroup to set
     */
    public void setBackgroup(int backgroup) {
        this.backgroup = backgroup;
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
     * @return the hasLogo
     */
    public int getHasLogo() {
        return hasLogo;
    }

    /**
     * @param hasLogo the hasLogo to set
     */
    public void setHasLogo(int hasLogo) {
        this.hasLogo = hasLogo;
    }

    /**
     * @return the softDecode
     */
    public int getSoftDecode() {
        return softDecode;
    }

    /**
     * @param softDecode the softDecode to set
     */
    public void setSoftDecode(int softDecode) {
        this.softDecode = softDecode;
    }

    /**
     * @return the chscr
     */
    public int getChscr() {
        return Chscr;
    }

    /**
     * @param chscr the chscr to set
     */
    public void setChscr(int chscr) {
        Chscr = chscr;
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
    public int getProvider() {
        return provider;
    }

    /**
     * @param provider the provider to set
     */
    public void setProvider(int provider) {
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
    public int getPortv() {
        return portv;
    }

    /**
     * @param portv the portv to set
     */
    public void setPortv(int portv) {
        this.portv = portv;
    }

    /**
     * @return the vmv
     */
    public int getVmv() {
        return vmv;
    }

    /**
     * @param vmv the vmv to set
     */
    public void setVmv(int vmv) {
        this.vmv = vmv;
    }

    /**
     * @return the cellId
     */
    public int getCellId() {
        return cellId;
    }

    /**
     * @param cellId the cellId to set
     */
    public void setCellId(int cellId) {
        this.cellId = cellId;
    }
    
}
