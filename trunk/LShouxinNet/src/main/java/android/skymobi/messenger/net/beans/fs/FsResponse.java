package android.skymobi.messenger.net.beans.fs;

import com.skymobi.android.bean.tlv.annotation.TLVAttribute;

import java.util.ArrayList;

/**
 * @ClassName: FsResponse
 * @Description: TODO
 * @author Bluestome.Zhang
 * @date 2012-2-23 下午09:50:04
 */
public class FsResponse extends FsBaseResponse {
    @TLVAttribute(tag = 90088)
    private int fileNum;
    
    @TLVAttribute(tag = 99025)
    private ArrayList<FsDownloadRespInfo> downloadresp = new ArrayList<FsDownloadRespInfo>();

    public int getFileNum() {
        return fileNum;
    }

    public void setFileNum(int fileNum) {
        this.fileNum = fileNum;
    }

    public ArrayList<FsDownloadRespInfo> getDownloadresp() {
        return downloadresp;
    }

    public void setDownloadresp(ArrayList<FsDownloadRespInfo> downloadresp) {
        this.downloadresp = downloadresp;
    }

}
