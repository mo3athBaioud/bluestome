package com.skymobi.android.notify;

/**
 * 客户端调用接口，用于统计流量或者其他
 * @ClassName: ClientNotify
 * @Description: TODO
 * @author Bluestome.Zhang
 * @date 2012-5-12 下午03:39:09
 */
public interface INetClientNotify {
    
    /**
     * 用于统计流量
     * @param packLen
     */
    void addByteLength(long packLen);

}
