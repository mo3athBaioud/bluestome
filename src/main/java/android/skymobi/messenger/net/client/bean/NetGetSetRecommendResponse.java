package android.skymobi.messenger.net.client.bean;

/**
 * @ClassName: NetGetSetRecommendResponse
 * @Description: TODO
 * @author Bluestome.Zhang
 * @date 2012-4-10 下午01:42:34
 */
public class NetGetSetRecommendResponse extends NetResponse {

    /**
     * 是否登录
     */
    private boolean isLogin = true;
    
    /**
     * 是否推荐 默认为推荐
     */
    private boolean recommend = true;
    
    /**
     * 是否隐藏LBS信息
     */
    private boolean hideLBS = false;

    /**
     * @return the isLogin
     */
    public boolean isLogin() {
        return isLogin;
    }

    /**
     * @param isLogin the isLogin to set
     */
    public void setLogin(boolean isLogin) {
        this.isLogin = isLogin;
    }

    /**
     * @return the recommend
     */
    public boolean isRecommend() {
        return recommend;
    }

    /**
     * @param recommend the recommend to set
     */
    public void setRecommend(boolean recommend) {
        this.recommend = recommend;
    }

    /**
     * @return the hideLBS
     */
    public boolean isHideLBS() {
        return hideLBS;
    }

    /**
     * @param hideLBS the hideLBS to set
     */
    public void setHideLBS(boolean hideLBS) {
        this.hideLBS = hideLBS;
    }

}
