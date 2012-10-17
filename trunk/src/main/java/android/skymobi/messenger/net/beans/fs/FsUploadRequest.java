package android.skymobi.messenger.net.beans.fs;

import com.skymobi.android.bean.tlv.annotation.TLVAttribute;

/**
 * 文件上传服务请求对象
 * @ClassName: FsUploadRequest
 * @Description: TODO
 * @author Bluestome.Zhang
 * @date 2012-3-14 下午07:18:08
 */
public class FsUploadRequest {
    
    @TLVAttribute(tag = 90051, description = "文件内容")
    private byte[] body = null;
    
    @TLVAttribute(tag = 90077, description = "文件大小")
    private int length = 0;
    
    @TLVAttribute(tag = 90076, description = "文件类型，1-语音文件，2-头像类图片，3-普通图片")
    private int fileType = 1;

    @TLVAttribute(tag = 90078, description = "文件扩展名,不需要加.")
    private String fileExtName = "JPG";
    
    @TLVAttribute(tag = 90079, description = "SKYID")
    private int skyId;
    
    @TLVAttribute(tag = 90080, description = "登录时的TOKEN")
    private String token;
    
    /**
     * @return the body
     */
    public byte[] getBody() {
        return body;
    }

    /**
     * @param body the body to set
     */
    public void setBody(byte[] body) {
        this.body = body;
    }

    /**
     * @return the length
     */
    public int getLength() {
        return length;
    }

    /**
     * @param length the length to set
     */
    public void setLength(int length) {
        this.length = length;
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
     * @return the fileType
     */
    public int getFileType() {
        return fileType;
    }

    /**
     * @param fileType the fileType to set
     */
    public void setFileType(int fileType) {
        this.fileType = fileType;
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
