
package android.skymobi.messenger.net.client.bean;

import android.skymobi.messenger.net.beans.commons.ResultInfo;

import java.util.ArrayList;

/**
 * 批量操作联系人响应对象
 * @ClassName: NetOperateContactsResponse
 * @Description: TODO
 * @author Bluestome.Zhang
 * @date 2012-2-15 下午05:48:49
 */
public class NetOperateContactsResponse extends NetResponse {
    private long updateTime;
    private ArrayList<NetContactsResultInfo> resultInfo = new ArrayList<NetContactsResultInfo>();

    /**
     * @return the updateTime
     */
    public long getUpdateTime() {
        return updateTime;
    }

    /**
     * @param updateTime the updateTime to set
     */
    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * @return the resultInfo
     */
    public ArrayList<NetContactsResultInfo> getResultInfo() {
        return resultInfo;
    }

    /**
     * @param resultInfo the resultInfo to set
     */
    public void setResultInfo(ArrayList<NetContactsResultInfo> resultInfo) {
        this.resultInfo = resultInfo;
    }

}
