package android.skymobi.messenger.net.client.bean;

import java.util.Map;


/**
 * 接收名片通知对象
 * @ClassName: NetVCardNotify
 * @Description: TODO
 * @author bluestome.zhang
 * @date 2012-2-16 下午01:35:22
 */
public class NetVCardNotify extends NetNotify {
    
    //联系人在MAP中的KEY
    public static final String CONTACT_NAME = "CONTACT_NAME";
    
    //联系人详情列表在MAP中的KEY
    public static final String CONTACT_DETAIL_LIST = "CONTACT_LIST_DETAIL";
    
    //消息ID
    private int seqid;
    
    //发送人
    private int skyid;
    
    //昵称
    private String nickname;
    
    //发送时间
    private String timestamp;
    
    //名片内容
    private Map vCardContentMap;

    /**
     * @return the seqid
     */
    public int getSeqid() {
        return seqid;
    }

    /**
     * @param seqid the seqid to set
     */
    public void setSeqid(int seqid) {
        this.seqid = seqid;
    }

    /**
     * @return the skyid
     */
    public int getSkyid() {
        return skyid;
    }

    /**
     * @param skyid the skyid to set
     */
    public void setSkyid(int skyid) {
        this.skyid = skyid;
    }

    /**
     * @return the nickname
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * @param nickname the nickname to set
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * @return the timestamp
     */
    public String getTimestamp() {
        return timestamp;
    }

    /**
     * @param timestamp the timestamp to set
     */
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * @return the vCardContentMap
     */
    public Map getvCardContentMap() {
        return vCardContentMap;
    }

    /**
     * @param vCardContentMap the vCardContentMap to set
     */
    public void setvCardContentMap(Map vCardContentMap) {
        this.vCardContentMap = vCardContentMap;
    }

}
