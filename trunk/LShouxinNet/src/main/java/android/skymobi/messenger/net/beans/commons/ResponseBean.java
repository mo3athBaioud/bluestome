package android.skymobi.messenger.net.beans.commons;

import android.skymobi.messenger.net.beans.ppa.RegisterUserInfo;

import com.skymobi.android.skyaaa.bean.common.LoginUserInfo;

/**
 * 响应对象
 * @ClassName: ResponseBean
 * @Description: TODO
 * @author Bluestome.Zhang
 * @date 2012-2-8 下午02:58:09
 */
public class ResponseBean {
	
	private int code = 200;
    private String msg = "注册成功!";
    
    

    public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public static class RegisterResponse{
        private int code = 200;
        private String msg = "注册成功!";
        private RegisterUserInfo userInfo;
        /**
         * @return the code
         */
        public int getCode() {
            return code;
        }
        /**
         * @param code the code to set
         */
        public void setCode(int code) {
            this.code = code;
        }
        /**
         * @return the userInfo
         */
        public RegisterUserInfo getUserInfo() {
            return userInfo;
        }
        /**
         * @param userInfo the userInfo to set
         */
        public void setUserInfo(RegisterUserInfo userInfo) {
            this.userInfo = userInfo;
        }
        /**
         * @return the msg
         */
        public String getMsg() {
            return msg;
        }
        /**
         * @param msg the msg to set
         */
        public void setMsg(String msg) {
            this.msg = msg;
        }
        
    }
    
    public static class LoginResponse{
        
        private int code = 200;
        
        private String msg = "登录成功!";
        
        private int skyId;

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

        private LoginUserInfo userInfo;
        
        /**
         * @return the code
         */
        public int getCode() {
            return code;
        }

        /**
         * @param code the code to set
         */
        public void setCode(int code) {
            this.code = code;
        }

        /**
         * @return the msg
         */
        public String getMsg() {
            return msg;
        }

        /**
         * @param msg the msg to set
         */
        public void setMsg(String msg) {
            this.msg = msg;
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

        /**
         * @return the passwd
         */
        public String getPasswd() {
            return passwd;
        }

        /**
         * @param passwd the passwd to set
         */
        public void setPasswd(String passwd) {
            this.passwd = passwd;
        }

        /**
         * @return the encryptPasswd
         */
        public byte[] getEncryptPasswd() {
            return encryptPasswd;
        }

        /**
         * @param encryptPasswd the encryptPasswd to set
         */
        public void setEncryptPasswd(byte[] encryptPasswd) {
            this.encryptPasswd = encryptPasswd;
        }

        /**
         * @return the lastLogin
         */
        public int getLastLogin() {
            return lastLogin;
        }

        /**
         * @param lastLogin the lastLogin to set
         */
        public void setLastLogin(int lastLogin) {
            this.lastLogin = lastLogin;
        }

        /**
         * @return the identity
         */
        public byte getIdentity() {
            return identity;
        }

        /**
         * @param identity the identity to set
         */
        public void setIdentity(byte identity) {
            this.identity = identity;
        }

        /**
         * @return the authCodeId
         */
        public String getAuthCodeId() {
            return authCodeId;
        }

        /**
         * @param authCodeId the authCodeId to set
         */
        public void setAuthCodeId(String authCodeId) {
            this.authCodeId = authCodeId;
        }

        /**
         * @return the authCodeImg
         */
        public byte[] getAuthCodeImg() {
            return authCodeImg;
        }

        /**
         * @param authCodeImg the authCodeImg to set
         */
        public void setAuthCodeImg(byte[] authCodeImg) {
            this.authCodeImg = authCodeImg;
        }

        /**
         * @return the secretQTag
         */
        public Integer getSecretQTag() {
            return secretQTag;
        }

        /**
         * @param secretQTag the secretQTag to set
         */
        public void setSecretQTag(Integer secretQTag) {
            this.secretQTag = secretQTag;
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
         * @return the userInfo
         */
        public LoginUserInfo getUserInfo() {
            return userInfo;
        }

        /**
         * @param userInfo the userInfo to set
         */
        public void setUserInfo(LoginUserInfo userInfo) {
            this.userInfo = userInfo;
        }
        
    }
    
    public static class CheckBindStatus{
        
        private int code = 140160;
        //是否绑定
        private boolean isBind = false;
        
        private String msg;

        /**
         * @return the isBind
         */
        public boolean isBind() {
            return isBind;
        }

        /**
         * @param isBind the isBind to set
         */
        public void setBind(boolean isBind) {
            this.isBind = isBind;
        }

        /**
         * @return the msg
         */
        public String getMsg() {
            return msg;
        }

        /**
         * @param msg the msg to set
         */
        public void setMsg(String msg) {
            this.msg = msg;
        }

        /**
         * @return the code
         */
        public int getCode() {
            return code;
        }

        /**
         * @param code the code to set
         */
        public void setCode(int code) {
            this.code = code;
        }
        
    }
    
    public static class BindStatus{
     
        // 状态
        private int bind = 1;
        
        // 发送内容
        private String smscontent=null;
        
        //接收号码
        private String recvsmsmobile=null;

        /**
         * @return the bind
         */
        public int getBind() {
            return bind;
        }

        /**
         * @param bind the bind to set
         */
        public void setBind(int bind) {
            this.bind = bind;
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
        
    }
    
}
