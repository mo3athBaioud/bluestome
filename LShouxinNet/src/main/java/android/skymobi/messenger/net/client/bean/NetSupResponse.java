package android.skymobi.messenger.net.client.bean;

/**
 * 自更新响应对象
 * @ClassName: NetSupResponse
 * @Description: TODO
 * @author Bluestome.Zhang
 * @date 2012-2-21 下午05:03:44
 */
public class NetSupResponse extends NetResponse {
    
    //下次检查的时间间隔
    private int checkInterval;
    
    //距离下次更新的次数
    private int checkAfterTimes;
    
    //当前版本的跟新内容
    private String feature;
    
    //文件长度
    private int fileLength;
    
    //文件md5校验
    private String md5;
    
    //应用对外版本号
    private String appOutVersion;
    
    //文件内容
    private byte[] body;
    
    //文件起始地址
    private int startPos;
    
    //是否强制更新,默认为否
    private boolean fUpdate = false;
    
    //是否需要更新,默认为否
    private boolean needUpdate = false;
    
    //是否完整下载
    private boolean isCompleateDownload = false;
    
    /**
     * @param needUpdate the needUpdate to set
     */
    public void setNeedUpdate(boolean needUpdate) {
        this.needUpdate = needUpdate;
    }

    /**
     * 是否需要更新
     * @return boolean  true:有更新  | false:无更新
     */
    public boolean isNeedUpdate(){
        return needUpdate;
    }

    /**
     * @return the fileLength
     */
    public int getFileLength() {
        return fileLength;
    }

    /**
     * @param fileLength the fileLength to set
     */
    public void setFileLength(int fileLength) {
        this.fileLength = fileLength;
    }

    /**
     * @return the md5
     */
    public String getMd5() {
        return md5;
    }

    /**
     * @param md5 the md5 to set
     */
    public void setMd5(String md5) {
        this.md5 = md5;
    }

    /**
     * @return the body
     */
    public byte[] getBody() {
        return body;
    }

    /**
     * @param body the body to set
     */
    public void setBody(byte[] body) {
        this.body = body;
    }

    /**
     * @return the startPos
     */
    public int getStartPos() {
        return startPos;
    }

    /**
     * @param startPos the startPos to set
     */
    public void setStartPos(int startPos) {
        this.startPos = startPos;
    }

    /**
     * @return the checkInterval
     */
    public int getCheckInterval() {
        return checkInterval;
    }

    /**
     * @param checkInterval the checkInterval to set
     */
    public void setCheckInterval(int checkInterval) {
        this.checkInterval = checkInterval;
    }

    /**
     * @return the checkAfterTimes
     */
    public int getCheckAfterTimes() {
        return checkAfterTimes;
    }

    /**
     * @param checkAfterTimes the checkAfterTimes to set
     */
    public void setCheckAfterTimes(int checkAfterTimes) {
        this.checkAfterTimes = checkAfterTimes;
    }

    /**
     * @return the feature
     */
    public String getFeature() {
        return feature;
    }

    /**
     * @param feature the feature to set
     */
    public void setFeature(String feature) {
        this.feature = feature;
    }
    
    
    /**
     * @param fUpdate the fUpdate to set
     */
    public void setfUpdate(boolean fUpdate) {
        this.fUpdate = fUpdate;
    }

    /**
     * 是否强制更新
     * @return boolean  true:强制更新  | false:不强制更新
     */
    public boolean isForce2Update(){
        return fUpdate;
    }

    /**
     * @return the isCompleateDownload
     */
    public boolean isCompleateDownload() {
        return isCompleateDownload;
    }

    /**
     * @param isCompleateDownload the isCompleateDownload to set
     */
    public void setCompleateDownload(boolean isCompleateDownload) {
        this.isCompleateDownload = isCompleateDownload;
    }

    /**
     * @return the fUpdate
     */
    public boolean isfUpdate() {
        return fUpdate;
    }

    /**
     * @return the appOutVersion
     */
    public String getAppOutVersion() {
        return appOutVersion;
    }

    /**
     * @param appOutVersion the appOutVersion to set
     */
    public void setAppOutVersion(String appOutVersion) {
        this.appOutVersion = appOutVersion;
    }
    
    
    
}
