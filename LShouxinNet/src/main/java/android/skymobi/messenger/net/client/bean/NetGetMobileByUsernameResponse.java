package android.skymobi.messenger.net.client.bean;

/**
 * @ClassName: NetGetMobileByUsernameResponse
 * @Description: TODO
 * @author Bluestome.Zhang
 * @date 2012-2-17 下午04:41:52
 */
public class NetGetMobileByUsernameResponse extends NetResponse {

    private String mobile;

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
     * 是否绑定手机
     * @return
     */
    public boolean isBind(){
        return resultCode == 140138?false:true;
    }

    /**
     * 用户名是否存在
     * @return
     */
    public boolean isUsernameExists(){
        return resultCode == 140139?false:true;
    }
    
    /**
     * 用户名是否合法
     * @return
     */
    public boolean isUsernameIllegal(){
        return resultCode == 140136?false:true;
    }
}
