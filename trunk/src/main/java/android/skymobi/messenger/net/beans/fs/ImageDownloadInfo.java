package android.skymobi.messenger.net.beans.fs;

import com.skymobi.android.bean.tlv.annotation.TLVAttribute;

/**
 * 图片下载对象
 * @ClassName: ImageDownloadInfo
 * @Description: TODO
 * @author Bluestome.Zhang
 * @date 2012-4-19 下午02:08:39
 */
public class ImageDownloadInfo {
    
    /** 图片唯一标识**/
	@TLVAttribute(tag = 90089)
	private String uuid;
	
	/** 图片下载开始位置**/
	@TLVAttribute(tag = 90050)
	private int startPos;

    /** 图片宽 **/
	@TLVAttribute(tag = 90090)
    private int width = 0;

    /** 图片高 **/
	@TLVAttribute(tag = 90091)
    private int height = 0;
    
    /** 图片扩展名 **/
	@TLVAttribute(tag = 90078)
    private String fileExtName;
	
	public int getStartPos() {
		return startPos;
	}

	public void setStartPos(int startPos) {
		this.startPos = startPos;
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
	
}
