package android.skymobi.messenger.net.client.bean;

public class NetLoginResponse extends NetResponse{
	
	private int skyId;
	
	private String userName;

    private String token;

    private String passwd;

    private byte[] encryptPasswd;

    private int lastLogin;

    private byte identity;

    private String authCodeId;

    private byte[] authCodeImg;

    private Integer secretQTag;

    private String recvsmsmobile;

    private String smscontent;
    
    private String mobile;

	public int getSkyId() {
		return skyId;
	}

	public void setSkyId(int skyId) {
		this.skyId = skyId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public byte[] getEncryptPasswd() {
		return encryptPasswd;
	}

	public void setEncryptPasswd(byte[] encryptPasswd) {
		this.encryptPasswd = encryptPasswd;
	}

	public int getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(int lastLogin) {
		this.lastLogin = lastLogin;
	}

	public byte getIdentity() {
		return identity;
	}

	public void setIdentity(byte identity) {
		this.identity = identity;
	}

	public String getAuthCodeId() {
		return authCodeId;
	}

	public void setAuthCodeId(String authCodeId) {
		this.authCodeId = authCodeId;
	}

	public byte[] getAuthCodeImg() {
		return authCodeImg;
	}

	public void setAuthCodeImg(byte[] authCodeImg) {
		this.authCodeImg = authCodeImg;
	}

	public Integer getSecretQTag() {
		return secretQTag;
	}

	public void setSecretQTag(Integer secretQTag) {
		this.secretQTag = secretQTag;
	}

	public String getRecvsmsmobile() {
		return recvsmsmobile;
	}

	public void setRecvsmsmobile(String recvsmsmobile) {
		this.recvsmsmobile = recvsmsmobile;
	}

	public String getSmscontent() {
		return smscontent;
	}

	public void setSmscontent(String smscontent) {
		this.smscontent = smscontent;
	}
	
	/**
	 * 用户名不存在
	 * @return false: 不存在  true:存在
	 */
	public boolean isUsernameExists(){
	    return resultCode == 110102?false:true;
	}
	
	/**
	 * 密码是否正确
	 * @return  false 密码不正确 true 密码正确
	 */
	public boolean isPasswordOk(){
	    return resultCode == 110105?false:true;
	}
	
	/**
	 * 用户状态是否错误
	 * @return true 状态错误 | false状态正常
	 */
	public boolean isUserStatusError(){
	    return resultCode == 110113?true:false;
	}
	
	/**
	 * 是否禁止登录
	 * @return true 禁止登录 |  false允许登录
	 */
	public boolean isBan(){
	   return resultCode == 110134?true:false; 
	}

    /**
     * 该应用是否被冻结
     * @return
     */
    public boolean isAppFreeze(){
        return resultCode == 110138?true:false; 
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
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }
	
}
