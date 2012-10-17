
package android.skymobi.messenger.net.notify;

/**
 * 用于通知UI的接口
 * 
 * @ClassName: IClientNotify
 * @Description: TODO
 * @author Bluestome.Zhang
 * @date 2012-2-5 下午06:54:37
 */
public interface IClientNotify {

    /**
     * 网络层状态通知接口
     * 
     * @param value
     */
    void netStatusNotify(Object value);
}
