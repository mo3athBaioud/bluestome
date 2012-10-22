
package android.skymobi.messenger.net.beans.commons;

import com.skymobi.android.bean.tlv.annotation.TLVAttribute;

/**
 * @ClassName: Image
 * @Description: TODO
 * @author Bluestome.Zhang
 * @date 2012-10-22 下午05:46:32
 */
public class Image {

    @TLVAttribute(tag = 10000057, description = "大小(byte，语音大小、图片大小)")
    private int fileSize;
    @TLVAttribute(tag = 10000058, description = "格式(语音格式、图片格式)")
    private String format;
    @TLVAttribute(tag = 10000059, description = "文件md5值")
    private String md5;

    /**
     * @return the fileSize
     */
    public int getFileSize() {
        return fileSize;
    }

    /**
     * @param fileSize the fileSize to set
     */
    public void setFileSize(int fileSize) {
        this.fileSize = fileSize;
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
