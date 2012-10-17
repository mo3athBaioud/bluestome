package android.skymobi.messenger.net.client.bean;

import android.skymobi.messenger.net.beans.commons.BlackList;

import java.util.ArrayList;

/**
 * 获取黑名单列表响应
 * @ClassName: NetGetBlakListResponse
 * @Description: TODO
 * @author Bluestome.Zhang
 * @date 2012-4-9 下午03:07:39
 */
public class NetGetBlakListResponse extends NetResponse {

    //是否登录
    public boolean isLogin = true;
    //黑名单列表
    private ArrayList<BlackList> blackList = new ArrayList<BlackList>();
    //总记录数
    private int totalSize;
    //开始位置
    private int start;
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
     * @return the totalSize
     */
    public int getTotalSize() {
        return totalSize;
    }

    /**
     * @param totalSize the totalSize to set
     */
    public void setTotalSize(int totalSize) {
        this.totalSize = totalSize;
    }

    /**
     * @return the start
     */
    public int getStart() {
        return start;
    }

    /**
     * @param start the start to set
     */
    public void setStart(int start) {
        this.start = start;
    }

    /**
     * @return the blackList
     */
    public ArrayList<BlackList> getBlackList() {
        return blackList;
    }

    /**
     * @param blackList the blackList to set
     */
    public void setBlackList(ArrayList<BlackList> blackList) {
        this.blackList = blackList;
    }
    
    
}
