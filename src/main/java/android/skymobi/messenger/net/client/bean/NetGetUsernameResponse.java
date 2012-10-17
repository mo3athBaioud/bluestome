package android.skymobi.messenger.net.client.bean;

/**
 * 根据SKYID获取用户名响应
 * @ClassName: NetGetUsernameResponse
 * @Description: TODO
 * @author Bluestome.Zhang
 * @date 2012-3-27 上午11:13:38
 */
public class NetGetUsernameResponse extends NetResponse {

    //用户名
    private String username;
    private boolean isSkyidOk = true;
    private boolean isUsernameOk = true;
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
     * @return the isSkyidOk
     */
    public boolean isSkyidOk() {
        return isSkyidOk;
    }

    /**
     * @param isSkyidOk the isSkyidOk to set
     */
    public void setSkyidOk(boolean isSkyidOk) {
        this.isSkyidOk = isSkyidOk;
    }

    /**
     * @return the isUsernameOk
     */
    public boolean isUsernameOk() {
        return isUsernameOk;
    }

    /**
     * @param isUsernameOk the isUsernameOk to set
     */
    public void setUsernameOk(boolean isUsernameOk) {
        this.isUsernameOk = isUsernameOk;
    }
    
    
}
