
package android.skymobi.messenger.net.beans;

import android.skymobi.messenger.net.beans.commons.Audio;
import android.skymobi.messenger.net.beans.commons.Image;
import android.skymobi.messenger.net.beans.header.ShouxinRespHeader;

import com.skymobi.android.bean.esb.annotation.EsbSignal;
import com.skymobi.android.bean.tlv.annotation.TLVAttribute;

/**
 * 聊天消息通知
 * 
 * @author Bluestome.Zhang
 */
@EsbSignal(messageCode = 0xB803)
public class SxChatMsgNotify extends ShouxinRespHeader {
    @TLVAttribute(tag = 1001)
    private int seqid;

    @TLVAttribute(tag = 1005, description = "用户的skyId")
    private int skyid;

    @TLVAttribute(tag = 11010077, description = "用户的昵称")
    private String nickname;

    @TLVAttribute(tag = 10000051, description = "时间戳")
    private String timestamp;

    @TLVAttribute(tag = 10000032, description = "消息内容")
    private String msgContent;

    @TLVAttribute(tag = 20000009, description = "语音内容")
    private Audio audio;

    @TLVAttribute(tag = 10000060, description = "第一次打招呼显示的提示语")
    private String talkReason;

    @TLVAttribute(tag = 10000074, description = "聊天消息类型（0：老版本未传入，默认；1：普通聊天消息；2：普通语音消息；3：加好友成功的聊天消息；4：快聊语音消息；5：图片消息）")
    private byte chatMsgType;

    @TLVAttribute(tag = 20000021, description = "图片内容")
    private Image image;

    @Override
    public int getSeqid() {
        return seqid;
    }

    @Override
    public void setSeqid(int seqid) {
        this.seqid = seqid;
    }

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

    public Audio getAudio() {
        return audio;
    }

    public void setAudio(Audio audio) {
        this.audio = audio;
    }

    public String getTalkReason() {
        return talkReason;
    }

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

    /**
     * @return the image
     */
    public Image getImage() {
        return image;
    }

    /**
     * @param image the image to set
     */
    public void setImage(Image image) {
        this.image = image;
    }

}
