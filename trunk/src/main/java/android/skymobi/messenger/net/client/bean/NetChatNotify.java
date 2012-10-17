
package android.skymobi.messenger.net.client.bean;

import android.skymobi.messenger.net.beans.commons.Audio;

/**
 * 聊天消息通知
 * 
 * @author Bluestome.Zhang
 */
public class NetChatNotify extends NetNotify {

    // 发送这SKYID
    private int skyid;

    // 发送者昵称
    private String nickname;

    // 发送时间
    private String timestamp;

    // 聊天消息
    private String msgContent;

    // 音频内容
    private Audio audio;

    // 第一次打招呼显示的提示语
    private String talkReason;

    // 聊天消息类型（0：老版本未传入，默认；1：普通聊天消息；2：普通语音消息；3：加好友成功的聊天消息；4：快聊语音消息；）
    private byte chatMsgType;

    public int getSkyid() {
        return skyid;
    }

    public void setSkyid(int skyid) {
        this.skyid = skyid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getMsgContent() {
        return msgContent;
    }

    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent;
    }

    /**
     * @return the audio
     */
    public Audio getAudio() {
        return audio;
    }

    /**
     * @param audio the audio to set
     */
    public void setAudio(Audio audio) {
        this.audio = audio;
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
