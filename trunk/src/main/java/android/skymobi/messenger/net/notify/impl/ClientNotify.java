
package android.skymobi.messenger.net.notify.impl;

import android.skymobi.messenger.net.notify.IClientNotify;

/**
 * 客户端通知类接口的实现 
 * 该接口应该由调用方实现
 * @ClassName: ClientNotify
 * @Description: TODO
 * @author Bluestome.Zhang
 * @date 2012-2-8 下午07:59:56
 */
public class ClientNotify implements IClientNotify {

    /**
     * 网络状态通知接口
     */
    public void netStatusNotify(Object value) {
        // TODO Auto-generated method stub
        System.out.println(value);
    }

}
