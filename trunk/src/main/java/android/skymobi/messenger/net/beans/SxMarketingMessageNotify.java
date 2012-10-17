
package android.skymobi.messenger.net.beans;

import android.skymobi.messenger.net.beans.header.ShouxinRespHeader;

import com.skymobi.android.bean.esb.annotation.EsbSignal;
import com.skymobi.android.bean.tlv.annotation.TLVAttribute;

/**
 * 营销消息通知
 * 
 * @ClassName: SxMarketingMessageNotify
 * @Description: TODO
 * @author Bluestome.Zhang
 * @date 2012-2-8 下午05:10:35
 */
@EsbSignal(messageCode = 0xBE03)
public class SxMarketingMessageNotify extends ShouxinRespHeader {

    @TLVAttribute(tag = 1010001, description = "消息ID")
    private int activityid;

    @TLVAttribute(tag = 1010002, description = "消息标题")
    private String title;

    @TLVAttribute(tag = 1010003, description = "消息内容")
    private String content;

    @TLVAttribute(tag = 1010004, description = "url")
    private String url;

    @TLVAttribute(tag = 1010005, description = "0-无链接|1-打开wap|2-打开商品详情")
    private int urlType;

    /**
     * @return the activityid
     */
    public int getActivityid() {
        return activityid;
    }

    /**
     * @param activityid the activityid to set
     */
    public void setActivityid(int activityid) {
        this.activityid = activityid;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content the content to set
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return the urlType
     */
    public int getUrlType() {
        return urlType;
    }

    /**
     * @param urlType the urlType to set
     */
    public void setUrlType(int urlType) {
        this.urlType = urlType;
    }

}
