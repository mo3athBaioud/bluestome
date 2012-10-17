package android.skymobi.messenger.net.beans.commons;

import com.skymobi.android.bean.tlv.annotation.TLVAttribute;

import java.io.Serializable;

/**
 * 音频对象，用于发送语音消息
 * @ClassName: Audio
 * @Description: TODO
 * @author Bluestome.Zhang
 * @date 2012-2-9 下午03:52:07
 */
public class Audio implements Serializable{

    private static final long serialVersionUID = 1L;

    @TLVAttribute(tag=10000056,description="时长(秒)")
    private int audioLen;
    
    @TLVAttribute(tag=10000057,description="大小(byte)")
    private int audioSize;
    
    @TLVAttribute(tag=10000058,description="格式")
    private String format;
    
    @TLVAttribute(tag=10000059,description="文件md5值")
    private String md5;

    /**
     * @return the audioLen
     */
    public int getAudioLen() {
        return audioLen;
    }

    /**
     * @param audioLen the audioLen to set
     */
    public void setAudioLen(int audioLen) {
        this.audioLen = audioLen;
    }

    /**
     * @return the audioSize
     */
    public int getAudioSize() {
        return audioSize;
    }

    /**
     * @param audioSize the audioSize to set
     */
    public void setAudioSize(int audioSize) {
        this.audioSize = audioSize;
    }

    /**
     * @return the format
     */
    public String getFormat() {
        return format;
    }

    /**
     * @param format the format to set
     */
    public void setFormat(String format) {
        this.format = format;
    }

    /**
     * @return the md5
     */
    public String getMd5() {
        return md5;
    }

    /**
     * @param md5 the md5 to set
     */
    public void setMd5(String md5) {
        this.md5 = md5;
    }
    
}
