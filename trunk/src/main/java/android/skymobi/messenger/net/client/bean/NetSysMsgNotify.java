package android.skymobi.messenger.net.client.bean;


/**
 * 系统消息通知对象
 * @ClassName: NetOnlineStateChangeNotify
 * @Description: TODO
 * @author bluestome.zhang
 * @date 2012-2-16 下午01:35:22
 */
public class NetSysMsgNotify extends NetNotify {
    
    //消息的标识
    private int seqid;
    
    //用户的skyId
    private int skyid;
    
    //用户的昵称
    private String nickname;
    
    //时间戳(“2011.11.11.11.11.11”)
    private String timestamp;
    
    //消息内容
    private String msgContent;

    //消息类型
    private byte resultType;

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
     * @return the msgContent
     */
    public String getMsgContent() {
        return msgContent;
    }

    /**
     * @param msgContent the msgContent to set
     */
    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent;
    }

    /**
     * @return the resultType
     */
    public byte getResultType() {
        return resultType;
    }

    /**
     * @param resultType the resultType to set
     */
    public void setResultType(byte resultType) {
        this.resultType = resultType;
    }

}
