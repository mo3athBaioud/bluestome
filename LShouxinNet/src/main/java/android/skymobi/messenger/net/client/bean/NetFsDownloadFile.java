package android.skymobi.messenger.net.client.bean;

/**
 * 文件服务之下载文件详情
 * @ClassName: NetFsDownloadFile
 * @Description: TODO
 * @author Bluestome.Zhang
 * @date 2012-3-14 下午08:30:59
 */
public class NetFsDownloadFile {

    private int startPos;

    private byte[] fileData;

    private int fileSize;

    private byte[] md5;

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
