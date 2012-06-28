package com.skymobi.android.transport;

/**
 * @ClassName: INetStateNotify
 * @Description: TODO
 * @author Bluestome.Zhang
 * @date 2012-2-23 下午01:18:51
 */
public interface INetStateNotify {

    /**
     * 网络状态通知
     * @param obj -1:网络断开 1:网络连接
     */
    void netStateNotify(Integer obj);
}
