package android.skymobi.messenger.net.client.bean;

/**
 * 下载响应
 * @ClassName: NetDownloadResponse
 * @Description: TODO
 * @author Bluestome.Zhang
 * @date 2012-2-21 上午09:23:23
 */
public class NetDownloadResponse extends NetResponse {

    //文件流
    private byte[] body;
    
    //文件大小
    private long length;

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
    public long getLength() {
        return length;
    }

    /**
     * @param length the length to set
     */
    public void setLength(long length) {
        this.length = length;
    }
    
    
}
