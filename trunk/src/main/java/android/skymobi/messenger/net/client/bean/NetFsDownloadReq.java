package android.skymobi.messenger.net.client.bean;

/**
 * @ClassName: NetFsDownloadReq
 * @Description: TODO
 * @author Bluestome.Zhang
 * @date 2012-3-14 下午08:09:35
 */
public class NetFsDownloadReq extends NetRequest {

    /** 文件MD5值 **/
    private String md5 = null;
    
    /** 文件开始位置 **/
    private int startPos = 0;

    /**
     * 无参数构造函数
     * <p>Title: </p> 
     * <p>Description: </p>
     */
    public NetFsDownloadReq(){
    }
    
    /**
     * 有参数构造函数
     * <p>Title: </p> 
     * <p>Description: </p> 
     * @param md5 文件编码字符串
     * @param startPos 文件起始位置
     */
    public NetFsDownloadReq(String md5,int startPos){
        this.md5 = md5;
        this.startPos = startPos;
    }

    /**
     * @return the md5
     */
    public String getMd5() {
        return md5;
    }

    /**
     * @param md5 the md5 to set
     */
    public void setMd5(String md5) {
        this.md5 = md5;
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
    
}
