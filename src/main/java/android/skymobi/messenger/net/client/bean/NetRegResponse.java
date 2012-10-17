package android.skymobi.messenger.net.client.bean;

public class NetRegResponse extends NetResponse{

    //手信号
	private int skyId;
	
	//登录TOKEN
	private String token;
	
	//用户名
	private String username;
	
	//用户密码
	private String passwd;
	
	//自动登录密码
	private byte[] autoLoginPwd;
	
	//昵称
	private String nickname;

	//企信通号码
    private String recvsmsmobile;

    //短信内容
    private String smscontent;
    
    //是否允许注册
    private boolean isAllowReg = true;

    //是否被阻止注册 [false:未阻止  true:阻止]
    private boolean isUserNameBan = false;
    
    //用户名已存在      [false:未存在  true:存在]
    private boolean isUserNameExists = false;

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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	
	/**
     * @return the autoLoginPwd
     */
    public byte[] getAutoLoginPwd() {
        return autoLoginPwd;
    }

    /**
     * @param autoLoginPwd the autoLoginPwd to set
     */
    public void setAutoLoginPwd(byte[] autoLoginPwd) {
        this.autoLoginPwd = autoLoginPwd;
    }

    /**
     * @return the isAllowReg
     */
    public boolean isAllowReg() {
        return isAllowReg;
    }

    /**
     * @param isAllowReg the isAllowReg to set
     */
    public void setAllowReg(boolean isAllowReg) {
        this.isAllowReg = isAllowReg;
    }

    /**
	 * 判断帐号是否绑定
	 * @return
	 */
	public boolean isAccountBind(){
	    return !isAllowReg;
	}

    /**
     * @return the recvsmsmobile
     */
    public String getRecvsmsmobile() {
        return recvsmsmobile;
    }

    /**
     * @param recvsmsmobile the recvsmsmobile to set
     */
    public void setRecvsmsmobile(String recvsmsmobile) {
        this.recvsmsmobile = recvsmsmobile;
    }

    /**
     * @return the smscontent
     */
    public String getSmscontent() {
        return smscontent;
    }

    /**
     * @param smscontent the smscontent to set
     */
    public void setSmscontent(String smscontent) {
        this.smscontent = smscontent;
    }

    /**
     * @return the isUserNameBan
     */
    public boolean isUserNameBan() {
        return isUserNameBan;
    }

    /**
     * @param isUserNameBan the isUserNameBan to set
     */
    public void setUserNameBan(boolean isUserNameBan) {
        this.isUserNameBan = isUserNameBan;
    }

    /**
     * @return the isUserNameExists
     */
    public boolean isUserNameExists() {
        return isUserNameExists;
    }

    /**
     * @param isUserNameExists the isUserNameExists to set
     */
    public void setUserNameExists(boolean isUserNameExists) {
        this.isUserNameExists = isUserNameExists;
    }
	
    
}
