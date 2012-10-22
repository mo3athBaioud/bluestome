
package android.skymobi.messenger.net.beans;

import android.skymobi.messenger.net.beans.commons.Audio;
import android.skymobi.messenger.net.beans.commons.Image;
import android.skymobi.messenger.net.beans.header.ShouxinReqHeader;

import com.skymobi.android.bean.esb.annotation.EsbSignal;
import com.skymobi.android.bean.tlv.annotation.TLVAttribute;

/**
 * 发送消息请求
 * 
 * @author Bluestome.Zhang
 */
@EsbSignal(messageCode = 0xA813)
public class SxSendChatMsgReq extends ShouxinReqHeader {

    @TLVAttribute(tag = 1002, description = "默认设置18003，服务端用于区分应用")
    private short sourceId = 18003;

    @TLVAttribute(tag = 10000031)
    private String destSkyids;

    @TLVAttribute(tag = 11010077)
    private String nickName;

    // ,charset=SysUtils.DEFAULT_CHARSET
    @TLVAttribute(tag = 10000032)
    private String msgContent;

    @TLVAttribute(tag = 20000009, description = "音频对象")
    private Audio audio;

    @TLVAttribute(tag = 10000060, description = "第一次打招呼显示的提示语")
    private String talkReason;

    @TLVAttribute(tag = 10000074, description = "聊天消息类型（0：老版本未传入，默认；1：普通聊天消息；2：普通语音消息；3：加好友成功的聊天消息；4：快聊语音消息；5：图片消息）")
    private byte chatMsgType;

    @TLVAttribute(tag = 20000021, description = "图片内容")
    private Image image;

    public String getDestSkyids() {
        return destSkyids;
    }

    public void setDestSkyids(String destSkyids) {
        this.destSkyids = destSkyids;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
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
     * @return the sourceId
     */
    @Override
    public short getSourceId() {
        return sourceId;
    }

    /**
     * @param sourceId the sourceId to set
     */
    @Override
    public void setSourceId(short sourceId) {
        this.sourceId = sourceId;
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
