package android.skymobi.messenger.net.client.bean;

import java.util.ArrayList;

/**
 * 文件下载响应对象
 * @ClassName: NetFsDownloadResponse
 * @Description: 
 * 1. fileNum 下载的文件数量
 * 2. fileList 下载的文件列表
 * @author Bluestome.Zhang
 * @date 2012-3-14 下午08:27:36
 */
public class NetFsDownloadResponse extends NetResponse {

    
    /** 下载的文件数量  **/
    private int fileNum = 0;
    
    /** 下载文件列表  **/
    private ArrayList<NetFsDownloadFile> fileList = new ArrayList<NetFsDownloadFile>();

    /**
     * @return the fileNum
     */
    public int getFileNum() {
        return fileNum;
    }

    /**
     * @param fileNum the fileNum to set
     */
    public void setFileNum(int fileNum) {
        this.fileNum = fileNum;
    }

    /**
     * @return the fileList
     */
    public ArrayList<NetFsDownloadFile> getFileList() {
        return fileList;
    }

    /**
     * @param fileList the fileList to set
     */
    public void setFileList(ArrayList<NetFsDownloadFile> fileList) {
        this.fileList = fileList;
    }
    
}
