package android.skymobi.messenger.net.beans.fs;

import com.skymobi.android.bean.tlv.annotation.TLVAttribute;

import java.util.ArrayList;

/**
 * 文件下载请求
 * @ClassName: FsDownloadReq
 * @Description: TODO
 * @author Bluestome.Zhang
 * @date 2012-3-14 下午08:06:22
 */
public class FsDownloadReq {

    @TLVAttribute(tag=99025,description="下载请求对象列表")
    private ArrayList<DownloadInfo> reqList;

    @TLVAttribute(tag = 90079, description = "SKYID")
    private int skyId;
    
    @TLVAttribute(tag = 90080, description = "登录时的TOKEN")
    private String token;
    
    /**
     * @return the reqList
     */
    public ArrayList<DownloadInfo> getReqList() {
        return reqList;
    }

    /**
     * @param reqList the reqList to set
     */
    public void setReqList(ArrayList<DownloadInfo> reqList) {
        this.reqList = reqList;
    }

    /**
     * @return the skyId
     */
    public int getSkyId() {
        return skyId;
    }

    /**
     * @param skyId the skyId to set
     */
    public void setSkyId(int skyId) {
        this.skyId = skyId;
    }

    /**
     * @return the token
     */
    public String getToken() {
        return token;
    }

    /**
     * @param token the token to set
     */
    public void setToken(String token) {
        this.token = token;
    }
    
    
}
