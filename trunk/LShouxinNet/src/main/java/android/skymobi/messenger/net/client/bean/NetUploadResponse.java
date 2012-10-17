package android.skymobi.messenger.net.client.bean;

/**
 * 上传文件响应对象
 * @ClassName: NetUploadResponse
 * @Description: 
 * 1.上传文件 使用到md5字段  
 * 2.上传图片 id,imageName,imageUrl字段
 * @author Bluestome.Zhang
 * @date 2012-2-21 上午09:38:22
 */
public class NetUploadResponse extends NetResponse {

    /** 文件服务器返回MD5 **/
    private String md5;
    
    /** 图片服务器返回上传图片的ID **/
    private int id;
    
    /** 文件服务器返回的图片UUID **/
    private String uuid;
    
    /** 图片服务器返回上传图片的名称 **/
    private String imageName;
    
    /** 图片服务器返回上传图片的访问地址 **/
    private String imageUrl;

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
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the imageName
     */
    public String getImageName() {
        return imageName;
    }

    /**
     * @param imageName the imageName to set
     */
    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    /**
     * @return the imageUrl
     */
    public String getImageUrl() {
        return imageUrl;
    }

    /**
     * @param imageUrl the imageUrl to set
     */
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
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
    
}
