
package android.skymobi.messenger.net.beans.fs;

import com.skymobi.android.bean.tlv.annotation.TLVAttribute;

/**
 * @ClassName: FsDownloadRespInfo
 * @Description: TODO
 * @author Bluestome.Zhang
 * @date 2012-2-23 下午09:51:15
 */
public class FsDownloadRespInfo {
    @TLVAttribute(tag = 202)
    private FsResponseInfo responseInfo = new FsResponseInfo();

    @TLVAttribute(tag = 90080)
    private int startPos;

    @TLVAttribute(tag = 90051)
    private byte[] fileData;

    @TLVAttribute(tag = 90077)
    private int fileSize;

    @TLVAttribute(tag = 90049)
    private byte[] md5;

    /**
     * @return the responseInfo
     */
    public FsResponseInfo getResponseInfo() {
        return responseInfo;
    }

    /**
     * @param responseInfo the responseInfo to set
     */
    public void setResponseInfo(FsResponseInfo responseInfo) {
        this.responseInfo = responseInfo;
    }

    /**
     * @return the startPos
     */
    public int getStartPos() {
        return startPos;
    }

    /**
     * @param startPos the startPos to set
     */
    public void setStartPos(int startPos) {
        this.startPos = startPos;
    }

    /**
     * @return the fileData
     */
    public byte[] getFileData() {
        return fileData;
    }

    /**
     * @param fileData the fileData to set
     */
    public void setFileData(byte[] fileData) {
        this.fileData = fileData;
    }

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
     * @return the md5
     */
    public byte[] getMd5() {
        return md5;
    }

    /**
     * @param md5 the md5 to set
     */
    public void setMd5(byte[] md5) {
        this.md5 = md5;
    }

    
}
