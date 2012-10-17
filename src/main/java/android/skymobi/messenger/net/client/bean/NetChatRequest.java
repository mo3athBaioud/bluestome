
package android.skymobi.messenger.net.client.bean;

/**
 * 网络消息请求对象
 * 
 * @ClassName: NetChatRequest
 * @Description: TODO
 * @author Bluestome.Zhang
 * @date 2012-3-20 上午11:16:00
 */
public class NetChatRequest extends NetRequest {

    /** 发送的目标用户SKYID组，多个SKYID使用逗号(,)分割，例如:3019123,343223 **/
    private String destSkyids;

    /** 发送者昵称，可以不填写 **/
    private String nickName;

    /** 文本内容 **/
    private String msgContent;

    /** 语音长度 **/
    private int audioLen;

    /** 语音文件大小 **/
    private int audioSize;

    /** 语音文件格式 **/
    private String format;

    /** 语音文件在文件服务器上的MD5值 **/
    private String md5;

    /** 第一次打招呼显示的提示语 **/
    private String talkReason;

    /**
     * 聊天消息类型（0：老版本未传入，默认；1：普通聊天消息；2：普通语音消息；3：加好友成功的聊天消息；4：快聊语音消息；）
     */
    private byte chatMsgType;

    public String getDestSkyids() {
        return destSkyids;
    }

    /**
     * 发送的目标用户SKYID组，多个SKYID使用逗号(,)分割，例如:3019123,343223
     * 
     * @param destSkyids
     */
    public void setDestSkyids(String destSkyids) {
        this.destSkyids = destSkyids;
    }

    public String getNickName() {
        return nickName;
    }

    /**
     * 设置发送者昵称，可以为空。
     * 
     * @param nickName 发送者昵称
     */
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getMsgContent() {
        return msgContent;
    }

    /**
     * 设置文本消息内容
     * 
     * @param msgContent 消息内容
     */
    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent;
    }

    public int getAudioLen() {
        return audioLen;
    }

    /**
     * 设置语音文件时长
     * 
     * @param audioLen 语音文件时长
     */
    public void setAudioLen(int audioLen) {
        this.audioLen = audioLen;
    }

    public int getAudioSize() {
        return audioSize;
    }

    /**
     * 设置语音文件大小
     * 
     * @param audioSize
     */
    public void setAudioSize(int audioSize) {
        this.audioSize = audioSize;
    }

    public String getFormat() {
        return format;
    }

    /**
     * 设置语音文件格式
     * 
     * @param format 文件格式
     */
    public void setFormat(String format) {
        this.format = format;
    }

    public String getMd5() {
        return md5;
    }

    /**
     * 设置语音文件所在服务器的MD5值
     * 
     * @param md5 MD5值
     */
    public void setMd5(String md5) {
        this.md5 = md5;
    }

    /**
     * @return the talkReason
     */
    public String getTalkReason() {
        return talkReason;
    }

    /**
     * @param talkReason the talkReason to set
     */
    public void setTalkReason(String talkReason) {
        this.talkReason = talkReason;
    }

    /**
     * @return the chatMsgType
     */
    public byte getChatMsgType() {
        return chatMsgType;
    }

    /**
     * @param chatMsgType the chatMsgType to set
     */
    public void setChatMsgType(byte chatMsgType) {
        this.chatMsgType = chatMsgType;
    }

}
