package android.skymobi.messenger.net.beans.fs;

import com.skymobi.android.bean.tlv.annotation.TLVAttribute;

import java.util.ArrayList;

/**
 * 图片文件下载响应
 * @ClassName: FsDownloadResp
 * @Description: TODO
 * @author Bluestome.Zhang
 * @date 2012-3-14 下午08:25:02
 */
public class FsImageDownloadResp {
    @TLVAttribute(tag = 202)
    private FsResponseInfo responseInfo = new FsResponseInfo();
    
    @TLVAttribute(tag = 90088)
    private int fileNum;
    
    @TLVAttribute(tag = 99042)
    private ArrayList<FsImageDownloadRespInfo> downloadresp = new ArrayList<FsImageDownloadRespInfo>();

    
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
     * @return the downloadresp
     */
    public ArrayList<FsImageDownloadRespInfo> getDownloadresp() {
        return downloadresp;
    }

    /**
     * @param downloadresp the downloadresp to set
     */
    public void setDownloadresp(ArrayList<FsImageDownloadRespInfo> downloadresp) {
        this.downloadresp = downloadresp;
    }

}
