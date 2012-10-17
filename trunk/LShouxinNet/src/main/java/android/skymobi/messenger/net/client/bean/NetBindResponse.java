package android.skymobi.messenger.net.client.bean;

public class NetBindResponse extends NetResponse{
	public enum BindStatus{
		BOUND,UNBOUND
	};
	 // 状态
    private BindStatus bind = BindStatus.UNBOUND;
    
    // 发送内容
    private String smscontent=null;
    
    //接收号码
    private String recvsmsmobile=null;
    
    private byte status = 1;
    
    private String nickname = null;
    
    private String username = null;
    
    private String personNickName = null;
    
    private String mobile;
    
    private int skyid;

	public BindStatus getBind() {
		return bind;
	}

	public void setBind(BindStatus bind) {
		this.bind = bind;
	}
	
	//是否绑定
	public boolean isBound(){
		return bind==BindStatus.BOUND?true:false;
	}
	
	//IMSI是否一致
	public boolean isImsiSame(){
	    return status == 3?true:false;
	}
	
	public String getSmscontent() {
		return smscontent;
	}
	
	public void setSmscontent(String smscontent) {
		this.smscontent = smscontent;
	}

	public String getRecvsmsmobile() {
		return recvsmsmobile;
	}

	public void setRecvsmsmobile(String recvsmsmobile) {
		this.recvsmsmobile = recvsmsmobile;
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
     * @return the nickname
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * @param nickname the nickname to set
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the personNickName
     */
    public String getPersonNickName() {
        return personNickName;
    }

    /**
     * @param personNickName the personNickName to set
     */
    public void setPersonNickName(String personNickName) {
        this.personNickName = personNickName;
    }

    /**
     * @return the mobile
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * @param mobile the mobile to set
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * @return the skyid
     */
    public int getSkyid() {
        return skyid;
    }

    /**
     * @param skyid the skyid to set
     */
    public void setSkyid(int skyid) {
        this.skyid = skyid;
    }

}
