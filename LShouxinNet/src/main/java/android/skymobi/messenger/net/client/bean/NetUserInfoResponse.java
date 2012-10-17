package android.skymobi.messenger.net.client.bean;

public class NetUserInfoResponse extends NetResponse{
	
    //用户信息
	private NetUserInfo userInfo=null;
	
	//在线状态
	private byte status;
	
	private boolean isBlack = false;

	public NetUserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(NetUserInfo userInfo) {
		this.userInfo = userInfo;
	}

    /**
     * @return the status
     */
    public byte getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(byte status) {
        this.status = status;
    }

    /**
     * @return the isBlack
     */
    public boolean isBlack() {
        return isBlack;
    }

    /**
     * @param isBlack the isBlack to set
     */
    public void setBlack(boolean isBlack) {
        this.isBlack = isBlack;
    }
    
	
	
}
