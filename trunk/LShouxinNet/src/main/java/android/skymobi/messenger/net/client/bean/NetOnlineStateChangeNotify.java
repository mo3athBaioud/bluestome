package android.skymobi.messenger.net.client.bean;


/**
 * 在线通知对象
 * @ClassName: NetOnlineStateChangeNotify
 * @Description: TODO
 * @author bluestome.zhang
 * @date 2012-2-16 下午01:35:22
 */
public class NetOnlineStateChangeNotify extends NetNotify {
    
    private int seqid;
    
    private int skyid;
    
    private String nickname;
    
    private String timestamp;
    
    private byte status;

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
     * @return the status
     */
    public byte getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(byte status) {
        this.status = status;
    }
    
    

}
