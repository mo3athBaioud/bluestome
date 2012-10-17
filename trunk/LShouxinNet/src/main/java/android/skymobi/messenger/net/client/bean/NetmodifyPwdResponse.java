package android.skymobi.messenger.net.client.bean;

/**
 * 修改用户密码响应对象
 * @ClassName: NetmodifyPwdResponse
 * @Description: TODO
 * @author Bluestome.Zhang
 * @date 2012-2-15 下午03:59:36
 */
public class NetmodifyPwdResponse extends NetResponse {

    private byte[] encryptPasswd;

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
     * 密码是否错误
     * @return
     */
    public boolean isPasswordError(){
        return resultCode == 110105?true:false;
    }
    
    /**
     * SKYID是否存在
     * @return
     */
    public boolean isSkyidExists(){
        return resultCode == 110101?true:false;
    }
}
