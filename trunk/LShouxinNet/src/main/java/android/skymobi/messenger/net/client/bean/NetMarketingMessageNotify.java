package android.skymobi.messenger.net.client.bean;

/**
 * 营销消息对象
 * @ClassName: NetMarketingMessageNotify
 * @Description: TODO
 * @author bluestome.zhang
 * @date 2012-2-16 下午01:46:40
 */
public class NetMarketingMessageNotify extends NetNotify {

    private int activityid;

    private String title;

    private String content;

    private String url;

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
