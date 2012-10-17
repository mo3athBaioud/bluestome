
package android.skymobi.messenger.net.beans.fs;

import com.skymobi.android.bean.tlv.annotation.TLVAttribute;

/**
 * @ClassName: FsDownloadRespInfo
 * @Description: TODO
 * @author Bluestome.Zhang
 * @date 2012-2-23 下午09:51:15
 */
public class FsImageDownloadRespInfo {
    @TLVAttribute(tag = 202)
    private FsResponseInfo responseInfo = new FsResponseInfo();

    @TLVAttribute(tag = 90089,description="上传头像成功返回的唯一标识")
    private String uuid;
    
    @TLVAttribute(tag = 90050,description="文件内容的实际下载起始位置(下载成功必选)")
    private int startPos;

    @TLVAttribute(tag = 90051,description="文件内容")
    private byte[] fileData;

    @TLVAttribute(tag = 90077,description="文件大小（非下载的文件大小）")
    private int fileSize;

    @TLVAttribute(tag = 90090,description="实际下载文件的宽度")
    private int width;
    
    @TLVAttribute(tag = 90091,description="实际下载文件的高度")
    private int height;
    
    @TLVAttribute(tag = 90078,description="实际下载文件的格式,目前Android只支持这些格式:56x56.png + 72x72.png + 480x480.png")
    private String fileExtName;

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
     * @return the uuid
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * @param uuid the uuid to set
     */
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    /**
     * @return the width
     */
    public int getWidth() {
        return width;
    }

    /**
     * @param width the width to set
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * @return the height
     */
    public int getHeight() {
        return height;
    }

    /**
     * @param height the height to set
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * @return the fileExtName
     */
    public String getFileExtName() {
        return fileExtName;
    }

    /**
     * @param fileExtName the fileExtName to set
     */
    public void setFileExtName(String fileExtName) {
        this.fileExtName = fileExtName;
    }

}
