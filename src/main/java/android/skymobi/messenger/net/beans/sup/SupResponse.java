package android.skymobi.messenger.net.beans.sup;

import android.skymobi.messenger.net.beans.commons.AbstractCommonBean;

import com.skymobi.android.bean.tlv.annotation.TLVAttribute;

/**
 * @ClassName: SupResponse
 * @Description: TODO
 * @author Bluestome.Zhang
 * @date 2012-2-21 下午03:54:01
 */
public class SupResponse extends AbstractCommonBean {
    
    @TLVAttribute(tag=712)
    private int checkInterval;
    
    @TLVAttribute(tag=713)
    private int checkAfterTimes;
    
    @TLVAttribute(tag=100)
    private int responseCode;
    
    @TLVAttribute(tag=714,description="当前版本的跟新内容")
    private String feature;
    
    @TLVAttribute(tag=708)
    private int filelen;
    
    @TLVAttribute(tag=709)
    private byte[] md5;
    
    @TLVAttribute(tag=710)
    private int startPos;
    
    @TLVAttribute(tag=715)
    private String appOutVersion;
    
    @TLVAttribute(tag=721)
    private byte[] body;
    

    /**
     * @return the responseCode
     */
    public int getResponseCode() {
        return responseCode;
    }

    /**
     * @param responseCode the responseCode to set
     */
    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
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
     * @return the filelen
     */
    public int getFilelen() {
        return filelen;
    }

    /**
     * @param filelen the filelen to set
     */
    public void setFilelen(int filelen) {
        this.filelen = filelen;
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
