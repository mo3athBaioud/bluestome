package android.skymobi.messenger.net.client.bean;


/**
 * 图片下载请求对象
 * @ClassName: NetDownloadLogoReq
 * @Description: 目前图片下载支持的大小和格式如下：
 * (20x20.jpg + 32x32. Jpg) 
 * (56x56. jpg + 72x72. jpg + 480x480. jpg) 
 * (16x16. jpg + 36x36. Jpg + 64x64. Jpg + 240x240. jpg + 360x360. jpg)
 * @author Bluestome.Zhang
 * @date 2012-4-19 下午01:59:56
 */
public class NetDownloadImageRespInfo extends NetRequest {

    /** UUID值 **/
    private String uuid = null;
    
    /** 文件开始位置 **/
    private int startPos = 0;
    
    /** 图片宽 **/
    private int width = 0;

    /** 图片高 **/
    private int height = 0;
    
    /** 图片扩展名 **/
    private String fileExtName;

    /** 文件内容 **/
    private byte[] fileData;

    /** 文件大小 **/
    private int fileSize;
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
    
}
