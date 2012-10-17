
package android.skymobi.messenger.net.client;

import android.skymobi.messenger.net.beans.lcs.LcsLogStatisticsRequest;
import android.skymobi.messenger.net.client.bean.NetResponse;

/**
 * 日志收集统计接口
 */
public interface ILcs {

    public NetResponse logStatistics(LcsLogStatisticsRequest req);

    /**
     * 基于复合数据日志接口
     * 
     * @param req
     * @return
     */
    public NetResponse complexRequest(String complexMessage);
}
