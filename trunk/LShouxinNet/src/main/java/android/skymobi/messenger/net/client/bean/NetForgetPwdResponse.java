package android.skymobi.messenger.net.client.bean;


/**
 * @ClassName: NetForgetPwdResponse
 * @Description: TODO
 * @author Bluestome.Zhang
 * @date 2012-2-15 下午02:07:12
 */
public class NetForgetPwdResponse extends NetResponse {

    private String recvsmsmobile;
    private String smscontent;
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
     * 手机是否绑定
     * @return
     */
    public boolean isBound(){
        return resultCode == 140138?false:true;
    }
    
    /**
     * 用户名不存在
     * @return false: 不存在  true:存在
     */
    public boolean isUsernameNotExists(){
        return resultCode == 140139?true:false;
    }
    
    /**
     * 企信通关闭 短信上行下发接口暂停服务
     * @return
     */
    public boolean isUplinkShutdown(){
        return resultCode == 160117?true:false;
    }
}
